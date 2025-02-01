package com.example.coreai.nlp;

import com.example.coreai.CommandInterpreter;

import java.io.IOException;

public class NlpInterpreter {

    private final IntentClassifier classifier;
    private final CommandInterpreter commandInterpreter;

    public NlpInterpreter(String modelPath, CommandInterpreter commandInterpreter) throws IOException {
        this.classifier = new IntentClassifier(modelPath);
        this.commandInterpreter = commandInterpreter;
    }

    /**
     * Interprets the user input using the NLP classifier and routes the request to the
     * appropriate command in the CommandInterpreter.
     */
    public String interpret(String input) {
        String intent = classifier.classify(input);
        intent = intent.toLowerCase();

        // Debug print to show what intent was classified.
        // System.out.println("Classified intent: " + intent);

        if ("weather".equals(intent)) {
            // Extract a city name from the input text.
            String city = extractCity(input);
            // Fallback if extraction fails.
            if (city.isEmpty()) {
                return "Sorry, I could not detect a city in your weather request.";
            }
            return commandInterpreter.interpret("weather " + city);
        } else if ("greet".equals(intent)) {
            return commandInterpreter.interpret("greet");
        } else if ("time".equals(intent)) {
            return commandInterpreter.interpret("time");
        } else if ("date".equals(intent)) {
            return commandInterpreter.interpret("date");
        } else if ("quote".equals(intent)) {
            return commandInterpreter.interpret("quote");
        } else if ("joke".equals(intent)) {
            return commandInterpreter.interpret("joke");
        }else if ("news".equals(intent)) {
            return commandInterpreter.interpret("news");
        }
        // Default fallback: pass the classified intent as the command keyword.
        return commandInterpreter.interpret(intent);
    }

    /**
     * A basic method to extract a city name from a weather request.
     * This first checks for the preposition "in" and returns what follows,
     * otherwise it falls back to removing common words.
     */
    private String extractCity(String input) {
        String lower = input.toLowerCase();
        if (lower.contains(" in ")) {
            int index = lower.lastIndexOf(" in ");
            // Return the substring after the last occurrence of " in ".
            return input.substring(index + 4).trim();
        } else {
            // Fallback: remove known phrases and hope the remainder is the city.
            String city = input.replaceAll("(?i)weather|what is|the|is|can you tell me|how", "").trim();
            return city;
        }
    }
}
