package Moduls.Users;

import Game.Priority;
import Game.UserGame;

import java.awt.*;

public class StandartUser extends UserGame {

    public StandartUser() {
        speed = 5; //коли робитимете фізику то поміняєте
        priority = Priority.MEDIUM;
    }
}
