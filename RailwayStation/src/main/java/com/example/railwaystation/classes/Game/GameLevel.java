package com.example.railwaystation.classes.Game;

import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Moduls.CashRegister;
import com.example.railwaystation.classes.Moduls.Door;
import com.example.railwaystation.classes.Moduls.Users.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//потрібно щоб знати чи можна сатвати на певну клітинку
enum CellState{
    DOOR,
    CASH_REGISTER_PART, //чатина каса, бо коли закривається, то закривається не 1 клітинка а вся каса
    QUEUE,
    EMPTY,
    SOLID //зайнята клітинка, але ще не знаємо причину
}

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
    public List<User> get_movingUsers() {
        return _movingUsers;
    }
    public List<Generator> get_generators(){
        return null; //TODO: implement generators and return them here :)
    }


    private final List<Door> _doorsList;
    private final List<CashRegister> _cashRegistersList;
    private final List<QueuePoligon> _poligons;
    private final List<User> _movingUsers;


    //дані можуть міститися в файлі (незалежно від формату файлу матриця не змінюється)
    //TODO: знайти клас Matrix або щось подібне
    private final CellState[][] _matrix;

    public GameLevel(List<Door> doorsList, List<CashRegister> cashRegistersList, List<QueuePoligon> poligons, CellState[][] matrix) {
        this._doorsList = doorsList;
        this._cashRegistersList = cashRegistersList;
        this._poligons = poligons;
        this._matrix = matrix;
        this._movingUsers = new ArrayList<>();
    }
}
