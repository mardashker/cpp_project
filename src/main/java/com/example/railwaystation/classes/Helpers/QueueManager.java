package com.example.railwaystation.classes.Helpers;

import com.example.railwaystation.classes.Game.QueuePoligon;
import com.example.railwaystation.classes.Moduls.Users.User;

import java.util.Comparator;
import java.util.List;


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

        moveNewUser(user,poligon);
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

    public void moveNewUser(User newUser, QueuePoligon poligon) {
        this.poligon = poligon;

        if(isPolygonFull())
            return;


        var newUserCellIndex = poligon.get_queue().getUsers().size() - 1;
        if(newUserCellIndex > poligon.get_queueCells().size() - 1)
            newUserCellIndex = poligon.get_queueCells().size() - 1;

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

    //Клієнт має можливість обирати одну з кас
    // за принципом вибору тієї, в черзі до якої
    // є найменша кількість людей. Якщо кількість
    // людей в черзі є однаковою, то клієнт обирає
    // ту, яка є найближчою.
    public static QueuePoligon getCorrectQueue(User usr, List<QueuePoligon> lst) {

        var res = lst
                .stream()
                .sorted(Comparator.comparingInt(o -> o.get_queue().size())).toList();

        // if all queues are the same
        if (res.stream().allMatch(p -> p.get_queue().size() == res.get(0).get_queue().size())) {
            return res
                    .stream()
                    .sorted(Comparator.comparingInt(o -> DistanceHelper.calcDistance(usr, o))).toList()
                    .get(0);
        }

        return res.get(0);
    }
}
