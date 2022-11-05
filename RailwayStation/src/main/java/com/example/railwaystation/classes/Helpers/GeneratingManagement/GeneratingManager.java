package com.example.railwaystation.classes.Helpers.GeneratingManagement;

import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Moduls.Users.User;

import java.util.List;
import java.util.Objects;

public class GeneratingManager {

    private GameLevel _level;

    public GeneratingManager(GameLevel level){
        this._level = level;
    }

    public int countUsersInSration(){

        int counter = countUsersInSration();
        counter += _level.get_movingUsers().size();
        return counter;
    }

    public int countUsersInQueues(){
        int counter = 0;
        for (var poligon : _level.get_poligons())
            counter += poligon.get_queue().size();

        return counter;
    }

    public List<User> collectUsers(){
        // collect users from each generator
        var newUsers = _level.get_generators().stream()
                .map(Generator::generateUser)
                .filter(Objects::nonNull)
                .toList();
        newUsers.removeAll(null);

        return newUsers;
    }
}


