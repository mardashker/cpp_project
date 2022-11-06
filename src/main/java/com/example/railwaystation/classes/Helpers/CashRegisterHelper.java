package com.example.railwaystation.classes.Helpers;

import com.example.railwaystation.classes.Moduls.StopWatch;
import com.example.railwaystation.classes.Moduls.Ticket;
import com.example.railwaystation.classes.Moduls.Users.User;

import java.util.List;


public class CashRegisterHelper {
    private static long processTicketTime = 2;
    public static boolean userProcessingTimeExpired(long seconds, StopWatch timer) {
        if(timer.getElapsedTimeSecs() >= seconds)
            return true;
        return false;
    }

    public static long countTicketProcessTime(List<Ticket> tickets) {
        return tickets.size() * processTicketTime;
    }
}
