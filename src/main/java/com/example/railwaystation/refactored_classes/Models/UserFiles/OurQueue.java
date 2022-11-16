package com.example.railwaystation.refactored_classes.Models.UserFiles;


import com.example.railwaystation.refactored_classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Moduls.Users.User;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class OurQueue implements Iterable<User> {
    private int size;

    private Coordinates coordinates;

    private CopyOnWriteArrayList<User> usersQueue;

    public OurQueue() {
        this.usersQueue = new CopyOnWriteArrayList<>();
    }

    public void addUser(User user) {
        this.usersQueue.add(user);
        if (usersQueue.size() > 1) {
            Collections.sort(usersQueue.subList(1, usersQueue.size()), new Comparator<User>() {
                public int compare(User u1, User u2) {
                    return Integer.compare(u2.getPriority().ordinal(), u1.getPriority().ordinal());
                }
            });
        }
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

    public List<User> getUsers() {
        return usersQueue;
    }

    public int size() {
        return usersQueue.size();
    }

    public void setUsersQueue(CopyOnWriteArrayList<User> usersQueue) {
        this.usersQueue = usersQueue;
    }

    public void removeFirsUser() {
        if (usersQueue.size() > 0)
            usersQueue.remove(0);
    }

    public User getFirsUser() {
        if (usersQueue.size() > 0)
            return usersQueue.get(0);
        return null;
    }

    @NotNull
    @Override
    public Iterator<User> iterator() {
        return usersQueue.iterator();
    }

    @Override
    public String toString() {
        String trynewString = "";
        for (int i = 0; i < usersQueue.size(); i++) {
            trynewString += usersQueue.get(i);
        }
        trynewString += "\n";


        return trynewString;
    }
}
