package com.example.coreai;

import com.example.coreai.commands.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialize the command interpreter
        CommandInterpreter interpreter = new CommandInterpreter();

        // Register commands
        interpreter.registerCommand("greet", new GreetCommand());
        interpreter.registerCommand("help", new HelpCommand(interpreter));
        interpreter.registerCommand("time", new TimeCommand());
        interpreter.registerCommand("date", new DateCommand());
        interpreter.registerCommand("joke", new JokeCommand());
        interpreter.registerCommand("quote", new QuoteCommande());
        interpreter.registerCommand("define", new DefineCommand());
        // Register external API integration commands
        interpreter.registerCommand("weather", new WeatherCommand());
        interpreter.registerCommand("news", new NewsCommand());

        interpreter.registerCommand("music", new MusicCommand());



        // Basic command line loop
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Core AI. Type 'greet <name>' or 'exit' to quit.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input.trim())) {
                System.out.println("Exiting Core AI. Goodbye!");
                break;
            }
            String output = interpreter.interpret(input);
            System.out.println(output);
        }
        scanner.close();
    }
}
