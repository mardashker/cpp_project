package com.example.railwaystation.classes.Game;

import com.example.railwaystation.classes.Moduls.CashRegister;
import com.example.railwaystation.classes.Moduls.Door;
import com.example.railwaystation.classes.Moduls.Users.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameLevel {

    public List<Door> get_doorsList() {
        return _doorsList;
    }

    public List<CashRegister> get_cashRegistersList() {
        return _cashRegistersList;
    }

    public List<QueuePoligon> get_poligons() {
        return _poligons;
    }

    public CellState[][] get_matrix() {
        return _matrix;
    }

    private final List<Door> _doorsList;
    private final List<CashRegister> _cashRegistersList;
    private final List<QueuePoligon> _poligons;
    private final List<User> _movingUsers = new ArrayList<User>();


    //дані можуть міститися в файлі (незалежно від формату файлу матриця не змінюється)
    //TODO: знайти клас Matrix або щось подібне
    private final CellState[][] _matrix;

    public GameLevel(List<Door> doorsList, List<CashRegister> cashRegistersList, List<QueuePoligon> poligons, CellState[][] matrix) {
        this._doorsList = doorsList;
        this._cashRegistersList = cashRegistersList;
        this._poligons = poligons;
        this._matrix = matrix;
    }

    public List<User> get_movingUsers() {
        return _movingUsers;
    }
}
