package com.example.railwaystation.classes.Helpers;

import com.example.railwaystation.classes.Game.QueuePoligon;
import com.example.railwaystation.refactored_classes.Models.UserFiles.User;

public class DistanceHelper {
    public static int calcDistance(User usr, QueuePoligon qp) {
        var upos = usr.getPosition();
        var qpos = qp.getQueueTailCoordinates().getPosition();
        return (int)Math.sqrt(Math.pow(qpos.getX() - upos.getX(), 2) + Math.pow(qpos.getY() - upos.getY(), 2));
    }
}
