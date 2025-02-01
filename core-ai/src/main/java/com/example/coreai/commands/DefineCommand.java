package com.example.coreai.commands;

import com.example.coreai.Command;
import java.util.HashMap;
import java.util.Map;

public class DefineCommand implements Command {

    private final Map<String, String> dictionary = new HashMap<>();

    public DefineCommand() {
        // Sample definitions
        dictionary.put("java", "A high-level programming language originally developed by Sun Microsystems.");
        dictionary.put("ai", "Artificial Intelligence: the simulation of human intelligence in machines.");
        dictionary.put("open-source", "Software for which the original source code is made freely available.");
    }

    @Override
    public String execute(String[] args) {
        if (args.length == 0) {
            return "Usage: define <word>";
        }
        String word = args[0].toLowerCase();
        String definition = dictionary.get(word);
        if (definition != null) {
            return word + ": " + definition;
        } else {
            return "No definition found for: " + word;
        }
    }
}
