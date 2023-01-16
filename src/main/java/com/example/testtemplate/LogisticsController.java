package com.example.testtemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/logistics")
public class LogisticsController {


    private final RestTemplate restTemplate;
    private final String apiURL = "https://spider2.analytics-euw1-dev-1.eks.schibsted.io/api/v1";

    public LogisticsController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public record Location(double latitude, double longitude) {
    }

    public String createSessionFromLocations(List<Location> locations) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            File inputFile = ResourceUtils.getFile("classpath:input.json");
            jsonNode = objectMapper.readTree(inputFile);
            String sessionId = jsonNode.get("id").asText();
            boolean sessionAlreadyCreated = isSessionAlreadyCreated(objectMapper, sessionId);
            if (sessionAlreadyCreated) {
                return sessionId;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectNode vrpNode = (ObjectNode) jsonNode.get("vrp");
        ArrayNode ordersNode = (ArrayNode) vrpNode.get("orders");

        if (locations.size() > 0) {
            for (int i = 0; i < locations.size(); i++) {
                Location location = locations.get(i);
                ObjectNode orderNode = objectMapper.createObjectNode();
                orderNode.put("id", "order_" + i);
                orderNode.put("type", "delivery");
                ObjectNode objectNode = (ObjectNode) jsonNode;
                objectNode.set("size", objectMapper.createArrayNode().add(1));
                ObjectNode deliveryNode = objectMapper.createObjectNode();
                deliveryNode.put("address", String.format("lat=%.8f;lon=%.8f", location.latitude, location.longitude));
                orderNode.set("delivery", deliveryNode);
                ordersNode.add(orderNode);
            }
        }
        String json = objectMapper.writeValueAsString(jsonNode);
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        String url = apiURL + "/sessions";
        ResponseEntity<String> response = null;
        response = restTemplate.postForEntity(url, request, String.class);
        if (response.getStatusCode() == HttpStatus.CREATED) {
            try {
                jsonNode = objectMapper.readTree(response.getBody());
                String sessionId = jsonNode.get("id").asText();
                System.out.println("Session ID: " + sessionId);
                return sessionId;
            } catch (IOException e) {
                System.out.println("Error while parsing JSON response from /api/v1/sessions endpoint.");
            }
        } else {
            System.out.println("Trouble creating session");
            return "-1";
        }
        return "-1";
    }

    private boolean isSessionAlreadyCreated(ObjectMapper objectMapper, String sessionId) throws JsonProcessingException {
        // Call the sessions endpoint with the sessionId
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        String url = apiURL + "/sessions/" + sessionId;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        // Check the status of the session
        JsonNode responseJson = objectMapper.readTree(response.getBody());

        // TODO, not really right. The session can be created, without being ready.
        return responseJson.get("isReady").asBoolean();
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            List<Location> locations = getDeliveries(file);

            String sessionId = createSessionFromLocations(locations);

            checkBestSolution(sessionId);

            return new ResponseEntity<>("File uploaded successfully.", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error uploading file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static List<Location> getDeliveries(MultipartFile file) throws IOException {
        List<Location> locations = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        reader.readLine(); // Skip headers
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            double latitude = Double.parseDouble(values[0]);
            double longitude = Double.parseDouble(values[1]);
            Location location = new Location(latitude, longitude);
            locations.add(location);
        }
        reader.close();
        return locations;
    }

    private void checkBestSolution(String sessionId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = apiURL + "/sessions/" + sessionId + "/bestSolution";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(response);
        System.out.println(response.getBody());
    }
}
