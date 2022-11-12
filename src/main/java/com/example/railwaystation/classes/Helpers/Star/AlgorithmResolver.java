package com.example.railwaystation.classes.Helpers.Star;

import com.example.railwaystation.classes.Game.CellState;
import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Helpers.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmResolver {
    public static List<Node> resolvePath(Coordinates s, Coordinates f, GameLevel game) {
        Node start = new Node(s);
        Node finish = new Node(f);

        var m = game.get_matrix();
        int rows= m.length; //X 50
        int cols = m[0].length; //Y 25

        StarAlgorithm resolver = new StarAlgorithm(rows, cols, start, finish);

        var lst = new ArrayList<Coordinates>();

        for (int i = 0; i < rows; i++) { // X
            for (int j = 0; j < cols; j++) { // Y
                var current = new Coordinates(i, j);
                if (m[i][j] != null && m[i][j] != CellState.DOOR && current != f) {
                    lst.add(new Coordinates(i, j));
                }
            }
        }

        resolver.setBlocks(lst);

        //System.out.println(game.get_matrix());

        //resolver.setBlocks(game.get_matrix());

        List<Node> path = resolver.findPath();

        for (Node node : path) {
            System.out.println(node);
        }

        System.out.println("\n");


        return path;
    }
}
