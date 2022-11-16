package com.example.railwaystation.classes.Helpers.Star;

import com.example.railwaystation.classes.Game.CellState;
import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.refactored_classes.Helpers.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmResolver {
    public static List<Node> resolvePath(Coordinates s, Coordinates f, GameLevel game) {
        Node start = new Node((int) s.getY(), (int) s.getX());
        Node finish = new Node((int) f.getY(), (int) f.getX());

        var m = game.get_matrix();
        int rows= m.length; //X 50
        int cols = m[0].length; //Y 25

        StarAlgorithm resolver = new StarAlgorithm(cols, rows, start, finish);

        var lst = new ArrayList<Coordinates>();

//        System.out.println(s + " " + f);

        for (int i = 0; i < rows; i++) { // X
            for (int j = 0; j < cols; j++) { // Y
                var current = new Coordinates(i, j);
                if (m[i][j] != CellState.EMPTY && m[i][j] != CellState.DOOR && current.compareStartAlg(f)) {

                    lst.add(new Coordinates(i, j));
                }
            }
        }

        resolver.setBlocks(lst);

        List<Node> path = resolver.findPath();

//        for (Node node : path) {
//            System.out.println("RES:" + node);
//        }
//
//        System.out.println("\n");


        return path;
    }
}
