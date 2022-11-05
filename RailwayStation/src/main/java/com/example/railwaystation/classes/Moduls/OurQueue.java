package com.example.railwaystation.classes.Moduls;


import com.example.railwaystation.classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Moduls.Users.User;

import java.util.Comparator;
import java.util.PriorityQueue;

//черга до каси
public class OurQueue {
//чому не наслідує GameObject
    private int size;

    private Coordinates coordinates;

    private final PriorityQueue<User> usersQueue;

    public int size(){
        return usersQueue.size();
    }

    public OurQueue() {
        this.usersQueue = new PriorityQueue<User>(10, new Comparator<User>() {
            public int compare(User u1, User u2) {
                return Integer.compare(u1.priority.ordinal(), u2.priority.ordinal());
            }
        });
    }

    public void addUser(User user){
        this.usersQueue.add(user);
        //TODO
    }
}
