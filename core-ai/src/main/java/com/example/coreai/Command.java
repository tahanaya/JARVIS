package com.example.coreai;

public interface Command {
    /**
     *
     * Execute the command with provided argument
     *
     * @param args Argument for the command
     * @return the result of command execution
     */
    String execute(String[] args);
}
