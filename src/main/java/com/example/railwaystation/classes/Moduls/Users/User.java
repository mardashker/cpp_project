package com.example.railwaystation.classes.Moduls.Users;

import com.example.railwaystation.classes.Game.QueuePoligon;
import com.example.railwaystation.classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Moduls.GameObject;
import com.example.railwaystation.classes.Moduls.Ticket;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Objects;

enum State{
    MOVING,
    PROCESSING, //користувача обслуговує каса
    PENDING //стоїть в черзі

}

public class User extends GameObject {
    // TODO: add get setters and getters
    private UserType type;
    private UserInfo userInfo;
    private List<Ticket> tickets;
    public Priority priority;
    private float speed;
    private State state;
    private QueuePoligon _target = null;

    public QueuePoligon get_target() {
        return _target;
    }

    public void set_target(QueuePoligon _target) {
        this._target = _target;
    }

    public User() {
    }

    public User(UserType type, UserInfo userInfo, Priority priority, float speed, State state, List<Ticket> tickets) {
        this.type = type;
        this.userInfo = userInfo;
        this.priority = priority;
        this.speed = speed;
        this.state = state;
        this.tickets = tickets;
        this._target = null;
    }

    public User(Coordinates position, double width, double height, Image sprite, double angle, UserType type, UserInfo userInfo, Priority priority, float speed, State state, List<Ticket> tickets) {
        super(position, width, height, sprite, angle);
        this.type = type;
        this.userInfo = userInfo;
        this.priority = priority;
        this.speed = speed;
        this.state = state;
        this.tickets = tickets;
        this._target = null;
    }
    public User(Image sprite, UserType type, Priority priority, float speed){
        this.type = type;
        this.speed = speed;
        this.priority = priority;
        this.setSprite(sprite);
        this._target = null;
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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public float getSpeed() {
        return speed;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "User{" +
                "type=" + type +
                ", userInfo=" + userInfo +
                ", tickets=" + tickets +
                ", priority=" + priority +
                ", speed=" + speed +
                ", state=" + state +
                '}';
    }
}


