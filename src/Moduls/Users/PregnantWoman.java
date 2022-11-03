package Moduls.Users;

import Game.Priority;
import Game.UserGame;

import java.awt.*;

public class PregnantWoman extends UserGame {
    public PregnantWoman() {
        speed = 5; //коли робитимете фізику то поміняєте
        priority = Priority.VERY_HIGHT;
    }
}
