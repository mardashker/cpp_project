package Moduls.Users;

import Game.Priority;
import Game.UserGame;

import java.awt.*;
import java.util.Dictionary;

public class User extends UserGame implements Cloneable {

    private Image profileImage;
    private String name;
    private String surname;
    private int age;
    private String passportId;
    private String phoneNumber;
    private UserType type;

    public User(Image profileImage, String name, String surname, int age, String passportId, String phoneNumber, UserType type) {
        this.profileImage = profileImage;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.passportId = passportId;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public void setPersonInfo(String name, String surname, int age, String passportId, String phoneNumber){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.passportId = passportId;
        this.phoneNumber = phoneNumber;
    }

    public UserType getType() {
        return type;
    }

    private User(Image profileImage, UserType type, Priority priority, float speed){
        this.profileImage = profileImage;
        this.type = type;
        this.priority = priority;
        this.speed = speed;
    }

    public User userClone(){
        return new User(this.profileImage,this.type,this.priority,this.speed);
    }

}
