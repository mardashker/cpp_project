package Moduls.Users;

import Game.UserGame;
import Moduls.Ticket;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class User extends UserGame implements Cloneable {

    List<Ticket> tickets;
    private BufferedImage profileImage;
    private String name;
    private String surname;
    private int age;
    private String passportId;
    private String phoneNumber;
    private UserType type;

    public User(BufferedImage profileImage, String name, String surname, int age, String passportId, String phoneNumber, UserType type, List<Ticket> tickets) {
        this.profileImage = profileImage;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.passportId = passportId;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.tickets = tickets;
    }

    public void setPersonInfo(String name, String surname, int age, String passportId, String phoneNumber, List<Ticket> tickets){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.passportId = passportId;
        this.phoneNumber = phoneNumber;
        this.tickets = tickets;
    }

    public UserType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "User{" +
                " name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", passportId='" + passportId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", type=" + type +
                ", speed=" + speed +
                ", state=" + state +
                ", priority=" + priority +
                "tickets=" + tickets +
                '}';
    }

    User(BufferedImage profileImage, UserType type, Priority priority, float speed){
        this.tickets = new ArrayList<Ticket>();
        this.profileImage = profileImage;
        this.type = type;
        this.priority = priority;
        this.speed = speed;
    }

    public User userClone(){
        return new User(this.profileImage,this.type,this.priority,this.speed);
    }

}
