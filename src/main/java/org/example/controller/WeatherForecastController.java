package org.example.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;;

@RestController
@RequestMapping("/")
public class WeatherForecastController {

    private static final String API_KEY = "d4a910d418mshc6da816c9a51540p1575f5jsnb083c4e9a8a6";
    private static final String API_HOST = "forecast9.p.rapidapi.com";

    @GetMapping("forecastSummary")
    public ResponseEntity<String> forecastSummary(@RequestParam("cityName") String cityName) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", API_HOST);
        headers.set("x-rapidapi-key", API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = UriComponentsBuilder.fromHttpUrl("https://forecast9.p.rapidapi.com/rapidapi/forecast/cityName/summary/")
                .toUriString();
        url = url.replace("cityName", cityName);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );
            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Failed to fetch weather forecast. Status: " + response.getStatusCodeValue());
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred");
        }
    }

    @GetMapping("hourlyForecastSummary")
    public ResponseEntity<String> hourlyForecastSummary(@RequestParam("cityName") String cityName) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", API_HOST);
        headers.set("x-rapidapi-key", API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = UriComponentsBuilder.fromHttpUrl("https://forecast9.p.rapidapi.com/rapidapi/forecast/cityName/hourly/")
                .toUriString();
        url = url.replace("cityName", cityName);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );
            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Failed to fetch weather forecast. Status: " + response.getStatusCodeValue());
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred");
        }
    }

}
