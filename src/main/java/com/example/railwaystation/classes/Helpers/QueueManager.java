package com.example.railwaystation.classes.Helpers;

import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Game.QueuePoligon;
import com.example.railwaystation.refactored_classes.Helpers.DistanceHelper;
import com.example.railwaystation.refactored_classes.Models.UserFiles.OurQueue;
import com.example.railwaystation.classes.Moduls.Users.User;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QueueManager {


    private static GameLevel level;
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
        moveAllUsers(poligon);
    }

    private void moveAllUsers(QueuePoligon poligon) {
        var users = poligon.get_queue().getUsers().stream().toList();
        var cells = poligon.get_queueCells().stream().toList();
        double cellX = 0;
        double cellY = 0;

        for (int i = 0; i < users.size(); i++) {
            if(i < cells.size()) {
                cellX = cells.get(i).getPosition().getX();
                cellY = cells.get(i).getPosition().getY();

                users.get(i).getPosition().setXY(cellX, cellY);
            }else {
                users.get(users.size() - 1).getPosition().setXY(cellX, cellY);
                return;
            }
        }
    }

    public void moveNewUser(User newUser, QueuePoligon poligon) {
        moveAllUsers(poligon);
    }

    private boolean isPolygonFull() {
        int usersNumber = poligon.get_queue().getUsers().size();

        return usersNumber >= poligon.get_queueCells().size();
    }

    //Клієнт має можливість обирати одну з кас
    // за принципом вибору тієї, в черзі до якої
    // є найменша кількість людей. Якщо кількість
    // людей в черзі є однаковою, то клієнт обирає
    // ту, яка є найближчою.
    public static QueuePoligon getCorrectQueue(User usr, List<QueuePoligon> lst) {

        var ourq = level.get_cashRegistersList()
                .stream().filter(p -> p.isOpen())
                .map(p -> p.getOurQueue()).collect(Collectors.toList());

        var new_lst = lst.stream()
                .filter(p -> ourq.contains(p.get_queue()))
                .collect(Collectors.toList());

        var res = new_lst
                .stream()
                .sorted(Comparator.comparingInt(QueuePoligon::get_potential_count)).toList();

        if (res.stream().allMatch(p -> p.get_potential_count() == res.get(0).get_potential_count())) {
            return res
                    .stream()
                    .sorted(Comparator.comparingInt(o -> DistanceHelper.calcDistance(usr, o))).toList()
                    .get(0);
        }

        return res.get(0);
    }
}
