package com.example.coreai.commands;

import com.example.coreai.Command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateCommand implements Command {

    @Override
    public String execute(String[] args) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "اللهم استرنا على وجه الأرض وارحمنا في بطن الأرض واغفر لنا يوم العرض عليك"+ today.format(formatter);
    }
}
