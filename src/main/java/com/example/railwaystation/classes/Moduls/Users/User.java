package com.example.railwaystation.classes.Moduls.Users;

import com.example.railwaystation.classes.Game.QueuePoligon;
import com.example.railwaystation.refactored_classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Helpers.Star.Node;
import com.example.railwaystation.refactored_classes.Models.Door;
import com.example.railwaystation.refactored_classes.Models.GameObject;
import com.example.railwaystation.refactored_classes.Models.TicketFIles.Ticket;
import com.example.railwaystation.refactored_classes.Models.UserFiles.Priority;
import com.example.railwaystation.refactored_classes.Models.UserFiles.UserInfo;
import com.example.railwaystation.refactored_classes.Models.UserFiles.UserType;
import javafx.scene.image.Image;

import java.util.Iterator;
import java.util.List;


public class User extends GameObject  {
    private UserType type;
    private int pathIndex = 1;
    private Iterator<Node> _it;
    private List<Node> path = null;
    private UserInfo userInfo;
    private List<Ticket> tickets;
    public Priority priority;
    private QueuePoligon target = null;
    private Door birthPlace = null;


    public boolean move_next_step() {
        if (this.path.size() > this.pathIndex) {
            var el = this.path.get(this.pathIndex);
            setPosition(new Coordinates(el.getCol(), el.getRow()));
            this.pathIndex++;
            return true;
        }

        return false;
    }

    public User(Image sprite, UserType type, Priority priority) {
        this.type = type;
        this.priority = priority;
        this.setSprite(sprite);
        this.target = null;
    }

    public User userClone() {
        return new User(this.getSprite(), this.type, this.priority);
    }

    public void setPersonInfo(String name, String surname, int age, String passportId, String phoneNumber, List<Ticket> tickets, Coordinates coordinates, Door birthPlace) {
        this.userInfo = new UserInfo(name, surname, age, passportId, phoneNumber);
        this.tickets = tickets;
        this.setPosition(coordinates);
        this.birthPlace = birthPlace;
    }

    public UserType getType() {
        return type;
    }

    public Priority getPriority() {
        return priority;
    }

    public void set_path(List<Node> _path) {
        this.path = _path;
        this._it = this.path.iterator();
    }


    public QueuePoligon get_target() {
        return target;
    }

    public void set_target(QueuePoligon _target) {
        this.target = _target;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<Node> get_path() {
        return path;
    }

    public Door get_birth_place() {
        return birthPlace;
    }



    @Override
    public String toString() {
        String tmp = "";

        tmp += "   " + type + "    " + refact(userInfo.toString(), 35) + " ";
        tmp += tickets.get(0);
        for (int i = 1; i < tickets.size(); i++) {
            tmp += "                                                                               " + tickets.get(i);
        }
        tmp += "----------------------------------------------------------------------------------------------------------------------------------------\n";

        return tmp;
    }

    public String refact(String user, int len) {
        int a = userInfo.toString().length();
        len -= a;
        String add = "";

        for (int i = 0; i < len / 2; i++) {
            add += " ";
        }
        if ((len % 2 == 1) && (len > 0)) {
            return add + user + add + " ";
        } else {
            return add + user + add;
        }
    }

}


