package com.example.testtemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/logistics")
public class LogisticsController {


    private final RestTemplate restTemplate;
    private final String apiURL = "http://spider2.analytics-euw1-dev-1.eks.schibsted.io/api/v1";
    private String sessionId;

    public LogisticsController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public record Location(double latitude, double longitude) {
    }

    public void createSessionFromLocations(List<Location> locations) {
        ClassPathResource inputFile = new ClassPathResource("input.json");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Resource> request = new HttpEntity<>(inputFile, headers);
        String url = apiURL + "/sessions";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.getBody());
                sessionId = jsonNode.get("id").asText();
                System.out.println("Session ID: " + sessionId);
            } catch (IOException e) {
                System.out.println("Error while parsing JSON response from /api/v1/sessions endpoint.");
            }
        } else {
            System.out.println("Failed to call the /api/v1/sessions endpoint. HTTP Status Code: " + response.getStatusCode());
        }
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            List<Location> locations = getDeliveries(file);

            createSessionFromLocations(locations);

            checkBestSolution();

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
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            double latitude = Double.parseDouble(values[0]);
            double longitude = Double.parseDouble(values[1]);
            Location location = new Location(latitude, longitude);
            locations.add(location);
            System.out.println(location);
        }
        reader.close();
        return locations;
    }

    private void checkBestSolution() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = apiURL + "/sessions/" + sessionId + "/bestSolution";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(response);
        System.out.println(response.getBody());
    }
}
