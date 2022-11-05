package com.example.railwaystation.classes.Helpers;

import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Game.QueuePoligon;
import com.example.railwaystation.classes.Moduls.OurQueue;
import com.example.railwaystation.classes.Moduls.Users.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QueueManager {

    private GameLevel level;
    private QueuePoligon poligon;

    public QueueManager(GameLevel level) {
        this.level = level;
    }

    public void addUser(QueuePoligon poligon, User user) {
        this.poligon = poligon;
        var queue = poligon.get_queue();
        moveNewUser(user);
    }

    private void findPolygonQueue(OurQueue queue){
        for (var p: level.get_poligons()) {
            if(p.get_queue() == queue){
                poligon = p;
                return;
            }
        }

        poligon = null;
    }

    // Moves all users in the QueuePolygon  one step forward
    public void moveUsersInQueuePolygon(OurQueue queue) {
        findPolygonQueue(queue);
        if(poligon == null)
            return;

        int index = 0;
        var users = poligon.get_queue()
                .getUsers()
                .stream()
                .toList();

        for (var cell: poligon.get_queueCells()) {
            var position = cell.getPosition();
            if(index < users.size()) {
                var userPosition = users.get(index).getPosition();
                userPosition.setXY(position.getX(), position.getY());
            }
            else
                return;
            ++index;
        }
    }

    public void moveNewUser(User newUser) {

        if(isPolygonFull())
            return;

        var newUserCellIndex = poligon.get_queue().getUsers().size() - 1;
        var cellPosition = poligon.get_queueCells()
                .stream()
                .toList()
                .get(newUserCellIndex)
                .getPosition();

        newUser.getPosition().setXY(
                cellPosition.getX(),
                cellPosition.getY()
        );
    }

    private boolean isPolygonFull() {
        int usersNumber = poligon.get_queue().getUsers().size();

        if(usersNumber >= poligon.get_queueCells().size())
            return true;
        return false;
    }
}
