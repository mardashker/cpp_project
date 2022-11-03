package Moduls;

import Moduls.Users.User;

import java.util.Comparator;
import java.util.PriorityQueue;
import Helpers.Coordinates;

//черга до каси
public class OurQueue {

    private int size;

    private Coordinates coordinates;

    private PriorityQueue<User> usersQueue;

    public OurQueue() {
        this.usersQueue = new PriorityQueue<User>(10, new Comparator<User>() {
            public int compare(User u1, User u2) {
                return Integer.compare(u1.getPriority().ordinal(), u2.getPriority().ordinal());
            }
        });
    }

    public void addUser(User user){
        this.usersQueue.add(user);
        //TODO
    }
}
