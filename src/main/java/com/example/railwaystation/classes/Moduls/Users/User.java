package com.example.railwaystation.classes.Moduls.Users;

import com.example.railwaystation.classes.Game.QueuePoligon;
import com.example.railwaystation.classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Helpers.Star.Node;
import com.example.railwaystation.classes.Moduls.Door;
import com.example.railwaystation.classes.Moduls.GameObject;
import com.example.railwaystation.classes.Moduls.Ticket;
import javafx.scene.image.Image;

import java.util.Iterator;
import java.util.List;

enum State {
    MOVING,
    PROCESSING, //користувача обслуговує каса
    PENDING //стоїть в черзі
}

public class User extends GameObject implements Cloneable {
    // TODO: add get setters and getters
    private UserType type;

    private int path_index = 1;

    private Iterator<Node> _it;

    private List<Node> _path = null;

    public List<Node> get_path() {
        return _path;
    }

    private Door _birth_place = null;

    public Door get_birth_place() {
        return _birth_place;
    }

    public void set_birth_place(Door _birth_place) {
        this._birth_place = _birth_place;
    }

    public boolean move_next_step() {
        if (this._path.size() > this.path_index) {
            var el = this._path.get(this.path_index);
//            System.out.println(el + "path:" + this._path);
            setPosition(new Coordinates(el.getCol(), el.getRow()));
            this.path_index++;
            return true;
        }

        return false;
    }

    public void set_path(List<Node> _path) {
        this._path = _path;
        this._it = this._path.iterator();
    }

    private UserInfo userInfo;
    private List<Ticket> tickets;
    public Priority priority;
    private float speed;
    private State state;
    private QueuePoligon _target = null;

    public QueuePoligon get_target() {
        return _target;
    }

    public void set_target(QueuePoligon _target) {
        this._target = _target;
    }

    public User(UserType type, UserInfo userInfo, Priority priority, float speed, State state, List<Ticket> tickets) {
        this.type = type;
        this.userInfo = userInfo;
        this.priority = priority;
        this.speed = speed;
        this.state = state;
        this.tickets = tickets;
        this._target = null;
    }

    public User(Coordinates position, double width, double height, Image sprite, double angle, UserType type, UserInfo userInfo, Priority priority, float speed, State state, List<Ticket> tickets) {
        super(position, width, height, sprite, angle);
        this.type = type;
        this.userInfo = userInfo;
        this.priority = priority;
        this.speed = speed;
        this.state = state;
        this.tickets = tickets;
        this._target = null;
    }

    public User(Image sprite, UserType type, Priority priority, float speed) {
        this.type = type;
        this.speed = speed;
        this.priority = priority;
        this.setSprite(sprite);
        this._target = null;
    }

    public User userClone() {
        return new User(this.getSprite(), this.type, this.priority, this.speed);
    }

    public void setPersonInfo(String name, String surname, int age, String passportId, String phoneNumber, List<Ticket> tickets, Coordinates coordinates, double angle) {
        this.userInfo = new UserInfo(name, surname, age, passportId, phoneNumber);
        this.tickets = tickets;
        this.setPosition(coordinates);
        this.setAngle(angle);
        this.state = State.MOVING;
    }

    public UserType getType() {
        return type;
    }

    public Priority getPriority() {
        return priority;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public float getSpeed() {
        return speed;
    }

    public State getState() {
        return state;
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


