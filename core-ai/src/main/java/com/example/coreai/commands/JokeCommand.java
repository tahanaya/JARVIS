package com.example.coreai.commands;


import com.example.coreai.Command;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class JokeCommand implements Command {

    private static final List<String> JOKES = loadJokes();

    private static List<String> loadJokes() {
        try {
            return Files.lines(Paths.get("core-ai/src/main/resources/jokes.txt"))
                    .filter(line -> !line.trim().isEmpty())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return List.of("Sorry, I ran out of jokes!"); // Default fallback joke
        }
    }

    @Override
    public String execute(String[] args) {
        if (JOKES.isEmpty()) {
            return "Sorry, I have no jokes to tell!";
        }
        int index = ThreadLocalRandom.current().nextInt(JOKES.size());
        return JOKES.get(index);
    }
}
