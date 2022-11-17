package com.example.railwaystation.refactored_classes.Helpers;

import com.example.railwaystation.classes.Moduls.Users.User;
import com.example.railwaystation.refactored_classes.Interfaces.Observer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class ConsoleLogger implements Observer {
    @Override
    public void process(UserProcessedEventArgs args) {
        System.out.println(formatte(args));
    }
    private String formatte(UserProcessedEventArgs args){
        StringBuilder result = new StringBuilder("USER PROCEEDED - * - * - * - * - * - * - *\n");

        var startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(args.get_startTime()), ZoneId.systemDefault());
        var endTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(args.get_endTime()), ZoneId.systemDefault());

        result.append("Start Time: ").append(startTime.toLocalTime()).append("\n");
        result.append("End Time: ").append(endTime.toLocalTime()).append("\n");
        result.append("Customer: ").append(args.get_user().getUserInfo().getName())
        result.append(" ").append(args.get_user().getUserInfo().getSurname()).append("\n");
        result.append("Age: ").append(args.get_user().getUserInfo().getAge()).append("\n");
        result.append("Passport Id: ").append(args.get_user().getUserInfo().getPassportId()).append("\n");
        result.append("Phone Number: ").append(args.get_user().getUserInfo().getPhoneNumber()).append("\n");
        result.append(args.get_user().getTickets().size()).append(" tickets were issued!");

        return result.toString();
    }
}
