package com.example.coreai.commands;

import com.example.coreai.Command;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeCommand implements Command {

    @Override
    public String execute(String[] args) {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    return "Lw9t daba huwa :"+now.format(formatter);
    }
}
