package Moduls.Users;

import Game.UserGame;

import java.awt.*;
import java.util.Dictionary;

enum UserType{
    STANDARD,
    PREGNANT,
    DISABLED
}

public class User extends UserGame implements Cloneable {

    private Image profileImage;
    private String name;
    private String surname;
    private int age;
    private String passportId;
    private String phoneNumber;
    private UserType type;

    public UserType getType() {
        return type;
    }

    public User userClone(){
        //TODO: метод для клонування юзера
    }



}
