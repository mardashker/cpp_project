package com.example.railwaystation.classes.Helpers;

import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Game.QueuePoligon;
import com.example.railwaystation.classes.Moduls.Users.User;
import com.example.railwaystation.refactored_classes.Helpers.Coordinates;

import java.util.Iterator;

public class MovingManager {


    public static void findNextPos(GameLevel lvl, QueuePoligon pol, User u, QueueManager man, Iterator<User> ui) throws Exception {
        var indicator = u.move_next_step();
        var usr = u.getPosition();

        var res_pos = new Coordinates(usr.getX(), usr.getY());
        if (!indicator) {
            pol.get_queue().addUser(u);
            man.addUser(pol, u);
            ui.remove();
        }
    }

}
