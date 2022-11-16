package com.example.railwaystation.classes.Helpers;

import com.example.railwaystation.refactored_classes.MovingLogic.StopWatch;
import com.example.railwaystation.refactored_classes.Models.TicketFIles.Ticket;

import java.util.List;


public class CashRegisterHelper {
    private static long processTicketTime = 1;

    public static boolean userProcessingTimeExpired(long seconds, StopWatch timer) {
        if(timer.getElapsedTimeSecs() >= seconds)
            return true;
        return false;
    }

    public static long countTicketProcessTime(List<Ticket> tickets) {
        return tickets.size() * processTicketTime;
    }
}
