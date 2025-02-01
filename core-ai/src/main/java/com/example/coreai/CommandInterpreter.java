package com.example.coreai;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandInterpreter {

    private final Map<String,Command> CommandRegistry = new HashMap<>();

    /**
     * Register a command with its keyword
     *
     * @param keyword the command keyword for example greet
     * @param command the command implementation
     *
     */

    public void registerCommand(String keyword, Command command) {
        CommandRegistry.put(keyword, command);
    }
    /**
     * Process an input string, extract command and arguments, then execute.
     *
     * @param input Raw input string.
     * @return The output from command execution.
     */
    public String interpret(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "madkhlti 7ta commande";

        }
        // par example ila dkhlt command fiha greet taha
        String[] tokens = input.trim().split("\\s+");
        // tokens = ["greet", "taha"]
        String commandKey = tokens[0].toLowerCase();
        Command command = CommandRegistry.get(commandKey);
        if (command == null) {
            return "command ma3rfthach" + commandKey;
        }

        String args[] = new String[tokens.length -1];
        System.arraycopy(tokens, 1, args, 0, args.length);
        return command.execute(args);
    }

    /**
     * Retrieve an unmodifiable set of all registered command keywords.
     *
     * @return Set of command keywords.
     */
public Set<String> getRegisteredCommands() {
        return Collections.unmodifiableSet(CommandRegistry.keySet());
}
}
