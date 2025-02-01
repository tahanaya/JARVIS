package com.example.coreai.commands;

import com.example.coreai.Command;
import com.example.coreai.ConfigLoader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class WeatherCommand implements Command {

    private static final String API_KEY = ConfigLoader.getProperty("weather.api.key");
    private static final String WEATHER_URL = "http://api.weatherapi.com/v1/current.json";

    @Override
    public String execute(String[] args) {
        if (API_KEY == null || API_KEY.isBlank()) {
            return "Error: Weather API key is not set in config.properties.";
        }
        if (args.length == 0) {
            return "Usage: weather <city>";
        }
        String city = String.join(" ", args);
        String url = String.format("%s?key=%s&q=%s", WEATHER_URL, API_KEY, city);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());
                JSONObject current = json.getJSONObject("current");
                double tempC = current.getDouble("temp_c");
                String condition = current.getJSONObject("condition").getString("text");
                return String.format("Current weather in %s: %.1f°C, %s", city, tempC, condition);
            } else {
                return "Error: Unable to fetch weather data. HTTP status " + response.statusCode();
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Error: " + e.getMessage();
        }
    }
}
