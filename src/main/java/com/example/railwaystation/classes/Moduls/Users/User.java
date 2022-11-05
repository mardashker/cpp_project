package com.example.railwaystation.classes.Moduls.Users;

import com.example.railwaystation.classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Moduls.GameObject;
import com.example.railwaystation.classes.Moduls.Ticket;
import javafx.scene.image.Image;

import java.util.List;

enum State{
    MOVING,
    PROCESSING, //користувача обслуговує каса
    PENDING //стоїть в черзі

}

public class User extends GameObject implements Cloneable {
    // TODO: add get setters and getters
    private UserType type;
    private UserInfo userInfo;
    public Priority priority;
    private float speed;
    private State state;
    List<Ticket> tickets;

    public User(UserType type, UserInfo userInfo, Priority priority, float speed, State state, List<Ticket> tickets) {
        this.type = type;
        this.userInfo = userInfo;
        this.priority = priority;
        this.speed = speed;
        this.state = state;
        this.tickets = tickets;
    }

    public User(Coordinates position, double width, double height, Image sprite, double angle, UserType type, UserInfo userInfo, Priority priority, float speed, State state, List<Ticket> tickets) {
        super(position, width, height, sprite, angle);
        this.type = type;
        this.userInfo = userInfo;
        this.priority = priority;
        this.speed = speed;
        this.state = state;
        this.tickets = tickets;
    }
    public User(Image sprite, UserType type, Priority priority, float speed){
        this.type = type;
        this.speed = speed;
        this.priority = priority;
        this.setSprite(sprite);
    }
    public User userClone(){
        return new User(this.getSprite(), this.type, this.priority, this.speed);
    }
    public void setPersonInfo(String name, String surname, int age, String passportId, String phoneNumber, List<Ticket> tickets, Coordinates coordinates, double angle){
        this.userInfo = new UserInfo(name,surname,age,passportId,phoneNumber);
        this.tickets = tickets;
        this.setPosition(coordinates);
        this.setAngle(angle);
        this.state = State.MOVING;
    }
    public UserType getType() {
        return type;
    }

    public Priority getPriority() {
        return priority;
    }
}


