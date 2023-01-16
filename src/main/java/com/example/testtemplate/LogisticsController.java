package com.example.testtemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    public record Location(double latitude, double longitude) {
    }

    private List<Location> locations;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            locations = new ArrayList<>();
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

            // Call external API with CSV data
            // ...

            return new ResponseEntity<>("File uploaded successfully.", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error uploading file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
