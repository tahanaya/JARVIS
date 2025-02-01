package com.example.coreai.commands;

import com.example.coreai.Command;
import com.example.coreai.CommandInterpreter;

import java.util.Set;

public class HelpCommand implements Command {

    private final CommandInterpreter commandInterpreter;

    public HelpCommand(CommandInterpreter commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }


    @Override
    public String execute(String[] args) {
        Set<String> availableCommands = commandInterpreter.getRegisteredCommands();
        StringBuilder helpMessage = new StringBuilder("ha les commandes li kaynin :\n");
        for(String command : availableCommands) {
            helpMessage.append("- ").append(command).append("\n");
        }
        return helpMessage.toString();
    }
}
