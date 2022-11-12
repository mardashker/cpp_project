package com.example.railwaystation.classes.Helpers.Star;

import java.util.List;

public class StarTest {
    public static void main(String[] args) {
        Node initialNode = new Node(2, 1);
        Node finalNode = new Node(2, 5);
        int rows = 6;
        int cols = 7;
        StarAlgorithm aStar = new StarAlgorithm(rows, cols, initialNode, finalNode);
        int[][] blocksArray = new int[][]{{0, 3}, {1, 3}, {2, 3}, {3, 3}, {4,3}};
        aStar.setBlocks(blocksArray);
        List<Node> path = aStar.findPath();
        for (Node node : path) {
            System.out.println(node);
        }
    }
}
