package com.example.coreai.commands;

import com.example.coreai.Command;
import com.example.coreai.ConfigLoader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class NewsCommand implements Command {

    private static final String API_KEY = ConfigLoader.getProperty("news.api.key");
    private static final String NEWS_URL = "https://newsapi.org/v2/top-headlines";

    @Override
    public String execute(String[] args) {
        if (API_KEY == null || API_KEY.isBlank()) {
            return "Error: News API key is not set in config.properties.";
        }
        String country = (args.length > 0) ? args[0] : "us";
        String url = String.format("%s?country=%s&apiKey=%s", NEWS_URL, country, API_KEY);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());
                JSONArray articles = json.getJSONArray("articles");
                StringBuilder headlines = new StringBuilder("Top headlines:\n");
                int count = Math.min(5, articles.length());
                for (int i = 0; i < count; i++) {
                    JSONObject article = articles.getJSONObject(i);
                    headlines.append("- ").append(article.getString("title")).append("\n");
                }
                return headlines.toString();
            } else {
                return "Error: Unable to fetch news. HTTP status " + response.statusCode();
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Error: " + e.getMessage();
        }
    }
}
