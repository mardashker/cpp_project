package com.example.railwaystation.classes.Helpers;

import com.example.railwaystation.classes.Game.QueuePoligon;
import com.example.railwaystation.classes.Moduls.Users.User;

import java.util.Comparator;
import java.util.List;

public class QueueManager {

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
