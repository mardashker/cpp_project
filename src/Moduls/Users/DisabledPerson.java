package Moduls.Users;

import Game.Priority;
import Game.UserGame;

import java.awt.*;

public class DisabledPerson extends UserGame {
    public DisabledPerson() {
        speed = 5; //коли робитимете фізику то поміняєте
        priority = Priority.HIGHT;
    }
}
