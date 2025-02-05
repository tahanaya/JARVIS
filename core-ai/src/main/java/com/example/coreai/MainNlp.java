package com.example.coreai;

import com.example.coreai.commands.*;
import com.example.coreai.commands.QuoteCommande;
import com.example.coreai.nlp.NlpInterpreter;

import java.util.Scanner;

public class MainNlp {

    public static void main(String[] args) throws Exception {
        CommandInterpreter interpreter = new CommandInterpreter();
        // Register all desired commands
        interpreter.registerCommand("greet", new GreetCommand());
        interpreter.registerCommand("help", new HelpCommand(interpreter));
        interpreter.registerCommand("weather", new WeatherCommand());
        interpreter.registerCommand("time", new TimeCommand());
        interpreter.registerCommand("date", new DateCommand());
        interpreter.registerCommand("joke", new JokeCommand());
        interpreter.registerCommand("quote", new QuoteCommande());
        interpreter.registerCommand("news", new NewsCommand());

        // (Register "calc" if needed)

        // Create NLP Interpreter (adjust model path as needed)
        String modelPath = "core-ai/src/main/resources/doccat-model.bin";
        NlpInterpreter nlpInterpreter = new NlpInterpreter(modelPath, interpreter);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Core AI (NLP Version). Type 'exit' to quit.");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input.trim())) {
                break;
            }
            String output = nlpInterpreter.interpret(input);
            System.out.println(output);
        }
        scanner.close();
        System.out.println("Exiting NLP-based Core AI.");
    }
}
