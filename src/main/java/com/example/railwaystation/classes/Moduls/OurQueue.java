package com.example.railwaystation.classes.Moduls;


import com.example.railwaystation.classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Moduls.Users.User;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

//черга до каси
public class OurQueue implements Iterable<User>{
//чому не наслідує GameObject
    private int size;

    private Coordinates coordinates;

    private PriorityQueue<User> usersQueue;

    public OurQueue() {
        this.usersQueue = new PriorityQueue<>(10, new Comparator<User>() {
            public int compare(User u1, User u2) {
                return Integer.compare(u1.getPriority().ordinal(), u2.getPriority().ordinal());
            }
        });
    }

    public void addUser(User user){
        this.usersQueue.add(user);
        //TODO
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public PriorityQueue<User> getUsers() {
        return usersQueue;
    }

    public int size(){
        return usersQueue.size();
    }
    public void setUsersQueue(PriorityQueue<User> usersQueue) {
        this.usersQueue = usersQueue;
    }
    public void removeFirsUser() {
        if(usersQueue.size() > 0)
            usersQueue.remove();
    }

    public User getFirsUser() {
        return usersQueue.peek();
    }

    @NotNull
    @Override
    public Iterator<User> iterator() {
        return usersQueue.iterator();
    }
}
