package com.example.railwaystation.classes.Helpers;
import com.example.railwaystation.classes.Game.CellState;
import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Moduls.GameObject;

public class MovingManager {

    public static GameObject findNextPost(Coordinates usr, Coordinates fin, GameLevel lvl) throws Exception {

        var mtrx = lvl.get_matrix();

        var dx = (int)(usr.getX() - fin.getX());
        var dy = (int)(usr.getY() - fin.getY());

        // if dx is negative, user stays on the left of the cashreg
        // if dy is negative, user stays above the cashreg

        var go = new GameObject();
        var res_pos = new Coordinates(usr.getX(), usr.getY());

        if (dx == 0 && dy != 0) {
            // move only by Y axis
            if (dy > 0 && mtrx[(int)res_pos.getX()][(int)res_pos.getY() - 1] != CellState.EMPTY) {
                res_pos.setY(res_pos.getY() - 1);
                go.setPosition(res_pos);
                return go;
            }
            else if (dy < 0 && mtrx[(int)res_pos.getX()][(int)res_pos.getY() + 1] != CellState.EMPTY){
                res_pos.setY(res_pos.getY() + 1);
                go.setPosition(res_pos);
                return go;
            }
        }

        if (dy == 0 && dx != 0) {
            // move only by X axis
            if (dx > 0 && mtrx[(int)res_pos.getX() - 1][(int)res_pos.getY()] != CellState.EMPTY) {
                res_pos.setX(res_pos.getX() - 1);
                go.setPosition(res_pos);
                return go;
            }
            else if (dx < 0 && mtrx[(int)res_pos.getX() + 1][(int)res_pos.getY()] != CellState.EMPTY){
                res_pos.setX(res_pos.getX() + 1);
                go.setPosition(res_pos);
                return go;
            }
        }

        if (dy != 0 && dx != 0) {
            // move by X and Y axes
            // move by diagonal
            if (dx < 0 && dy < 0 && mtrx[(int)res_pos.getX() + 1][(int)res_pos.getY() + 1] != CellState.EMPTY) {
                res_pos.setX(res_pos.getX() + 1);
                res_pos.setY(res_pos.getY() + 1);
                go.setPosition(res_pos);
                return go;
            }
            else if (dx > 0 && dy > 0 && mtrx[(int)res_pos.getX() - 1][(int)res_pos.getY() - 1] != CellState.EMPTY) {
                res_pos.setX(res_pos.getX() - 1);
                res_pos.setY(res_pos.getY() - 1);
                go.setPosition(res_pos);
                return go;
            }
            else if (dx > 0 && dy < 0 && mtrx[(int)res_pos.getX() - 1][(int)res_pos.getY() + 1] != CellState.EMPTY) {
                res_pos.setX(res_pos.getX() - 1);
                res_pos.setY(res_pos.getY() + 1);
                go.setPosition(res_pos);
                return go;
            }
            else if (dx < 0 && dy > 0 && mtrx[(int)res_pos.getX() + 1][(int)res_pos.getY() - 1] != CellState.EMPTY) {
                res_pos.setX(res_pos.getX() + 1);
                res_pos.setY(res_pos.getY() - 1);
                go.setPosition(res_pos);
                return go;
            }
        }

        throw new Exception("Can't find next pos");
    }

}
