package com.example.coreai.commands;

import com.example.coreai.Command;

public class GreetCommand implements Command {
    @Override
    public String execute(String[] args) {
        if (args.length > 0) {
            return "Hello, " + String.join(" ", args) + "!";
        } else {
            return "Hello!";
        }
    }
}
