package com.example.railwaystation.classes.Helpers.GeneratingManagement;

import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Moduls.Door;
import com.example.railwaystation.classes.Moduls.Users.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class which is supposed to handle generating and control number of users in level.
 */
public class GeneratingManager {

    private final GameLevel _level;
    private final List<Generator> _userSources;

    /**
     * @param level - level to handle
     * @param userSources - collection of user generators
     */
    public GeneratingManager(GameLevel level, List<Generator> userSources){
        if(level == null || userSources == null)
            throw new IllegalArgumentException("Neither parameter is allowed to be null!");
        this._level = level;
        this._userSources = userSources;
    }


    /**
     * Count all existing users in the level
     * @return Number of users in station
     */
    public int countUsersInStation(){

        int counter = countUsersInQueues();
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
        var newUsers = _userSources.stream()
                .map(Generator::generateUser)
                .filter(Objects::nonNull)
                .toList();

        _level.get_movingUsers().addAll(newUsers);

        //newUsers.forEach(u -> System.out.println(u.toString()));
        return newUsers;
    }

    /**
     * Will close all doors in the level.
     */
    public void closeDoors(){
        _level.get_doorsList().forEach(Door::close);
    }
    public void openDoors(){
        _level.get_doorsList().forEach(Door::open);
    }
}


