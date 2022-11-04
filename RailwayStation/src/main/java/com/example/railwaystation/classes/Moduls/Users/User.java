package com.example.railwaystation.classes.Moduls.Users;

import com.example.railwaystation.classes.Moduls.GameObject;

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
