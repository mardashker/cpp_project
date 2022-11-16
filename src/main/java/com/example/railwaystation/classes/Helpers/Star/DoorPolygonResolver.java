package com.example.railwaystation.classes.Helpers.Star;

import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.refactored_classes.Helpers.Coordinates;
import com.example.railwaystation.refactored_classes.Models.Door;

import java.util.HashMap;
import java.util.List;

public class DoorPolygonResolver {
    public static HashMap<Door, HashMap<Coordinates, List<Node>>> calculate(GameLevel gl) {
        var map = new HashMap<Door, HashMap<Coordinates, List<Node>>>();
        for (var door : gl.get_doorsList()) {
            var tmap = new HashMap<Coordinates, List<Node>>();
            for (var qe : gl.get_poligons()) {

                var res = AlgorithmResolver.resolvePath(
                        door.getPosition(),
                        qe.getQueueTailCoordinates().getPosition(),
                        gl);

                tmap.put(qe.getQueueTailCoordinates().getPosition(), res);
            }

            map.put(door, tmap);
        }

        return map;
    }
}
