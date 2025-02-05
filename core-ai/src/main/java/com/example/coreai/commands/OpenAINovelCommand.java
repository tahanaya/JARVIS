package com.example.coreai.commands;

import com.example.coreai.Command;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class OpenAINovelCommand implements Command {

    // Path to your full novel file
    private static final String NOVEL_FILE_PATH = "core-ai/src/main/resources/novel.txt";
    // Maximum number of characters to use from the novel (you might want to pre-summarize if very long)
    private static final int MAX_CONTEXT_LENGTH = 3000;

    // The OpenAI endpoint and model to use
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/completions";
    // You can choose "text-davinci-003" or another available model
    private static final String MODEL = "text-davinci-003";

    @Override
    public String execute(String[] args) {
        // Combine the user-provided prompt
        String userPrompt = String.join(" ", args).trim();
        if (userPrompt.isEmpty()) {
            return "Please provide a prompt for generating the next chapter.";
        }

        // Read the entire novel (or a relevant context)
        String novelText;
        try {
            novelText = new String(Files.readAllBytes(Paths.get(NOVEL_FILE_PATH)));
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading the novel file: " + e.getMessage();
        }

        // If the novel is too long, use only the last part (or use a summary)
        if (novelText.length() > MAX_CONTEXT_LENGTH) {
            novelText = novelText.substring(novelText.length() - MAX_CONTEXT_LENGTH);
        }

        // Build the full prompt
        String fullPrompt = novelText + "\n\n" + userPrompt;

        // Prepare the JSON payload for the OpenAI API
        JSONObject payload = new JSONObject();
        payload.put("model", MODEL);
        payload.put("prompt", fullPrompt);
        payload.put("max_tokens", 200);  // Adjust as needed
        payload.put("temperature", 0.7); // Adjust creativity

        // Read your API key from an environment variable
        String apiKey = "";

        // Create the HTTP request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(OPENAI_API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                .build();

        // Send the request and parse the response
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // Check for successful status code
            if (response.statusCode() != 200) {
                return "Error: Received status code " + response.statusCode() + "\n" + response.body();
            }
            JSONObject jsonResponse = new JSONObject(response.body());
            // OpenAI returns an array of choices; take the first one.
            String generatedText = jsonResponse.getJSONArray("choices")
                    .getJSONObject(0)
                    .getString("text");
            return "Suggested continuation:\n" + generatedText;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error generating text: " + e.getMessage();
        }
    }
}
