package com.example.railwaystation.refactored_classes.Models.TicketFIles;

import com.example.railwaystation.refactored_classes.Models.TicketFIles.TicketType;

import java.time.LocalDate;

public class Ticket {
    private TicketType type;
    private LocalDate departureDate;
    private int price;
    private String arrivalPlace;
    public Ticket(TicketType type, int price, String arrivalPlace, LocalDate departureDate){
        this.type = type;
        this.departureDate = departureDate;
        this.price = price;
        this. arrivalPlace = arrivalPlace;
    }

    @Override
    public String toString() {
        return "Ticket:" +
                "~" + type +
                "~" + departureDate +
                "~" + price + " uah"+
                "~" + arrivalPlace+"\n";
    }
}
