package com.example.railwaystation.classes.Helpers.GeneratingManagement;

import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Moduls.Users.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GeneratingManager {

    private GameLevel _level;

    public GeneratingManager(GameLevel level){
        this._level = level;
    }


    /**
     * Count all existing users in the level
     * @return Number of users in station
     */
    public int countUsersInStation(){

        int counter = countUsersInSration();
        counter += _level.get_movingUsers().size();
        return counter;
    }

    /**
     * Count users that is currently in a queue
     * @return Number of users
     */
    public int countUsersInQueues(){
        int counter = 0;
        for (var poligon : _level.get_poligons())
            counter += poligon.get_queue().size();

        return counter;
    }

    /**
     * Collect users from all generators and add them to the collection of
     * moving users of the level.
     * @return Users from all generators without nulls;
     */
    public List<User> collectUsers(){
        // collect users from each generator
        var newUsers = _level.get_generators().stream()
                .map(Generator::generateUser)
                .filter(Objects::nonNull)
                .toList();

        _level.get_movingUsers().addAll(newUsers);
        return collectUsers();
    }
}


