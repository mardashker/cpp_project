package com.example.railwaystation.classes.Moduls.Users;

import com.example.railwaystation.classes.Moduls.GameObject;
import com.example.railwaystation.classes.Moduls.Ticket;

import java.util.List;

enum UserType{
    STANDARD,
    PREGNANT,
    DISABLED
}

enum State{
    MOVING,
    PROCESSING, //користувача обслуговує каса
    PENDING //стоїть в черзі

}
public class User extends GameObject implements Cloneable {
    // TODO: add get setters and getters
    private UserType type;
    private UserInfo userInfo;
    private List<Ticket> tickets;

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public int priority;
    private float speed;

    private State state;

    public UserType getType() {
        return type;
    }

    public User userClone(){
        //TODO: метод для клонування юзера
        return null;
    }
}
