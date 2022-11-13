package com.example.railwaystation.classes.Game;

import com.example.railwaystation.classes.Helpers.CashRegisterHelper;
import com.example.railwaystation.classes.Moduls.CashRegister;
import com.example.railwaystation.classes.Moduls.Door;
import com.example.railwaystation.classes.Moduls.Users.User;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class GameLevel {
    private final List<Door> _doorsList;
    private final List<CashRegister> _cashRegistersList;
    private volatile CashRegister _reserveCashRegister;
    private volatile QueuePoligon _reserveQueuePolygon;
    private final List<QueuePoligon> _poligons;
    private final List<User> _movingUsers = new ArrayList<>();
    private final CellState[][] _matrix;


    public GameLevel(List<Door> doorsList, List<CashRegister> cashRegistersList, List<QueuePoligon> poligons, CellState[][] matrix) {
        this._doorsList = doorsList;
        this._cashRegistersList = cashRegistersList;
        this._poligons = poligons;
        this._matrix = matrix;
        //TODO: initialization of the reserve CashReg ↓ ↓ ↓
        this._reserveCashRegister = cashRegistersList.get(cashRegistersList.size() - 1);
        this._reserveQueuePolygon = poligons.get(poligons.size() - 1);

        _cashRegistersList.forEach(cr -> cr.setOpen(true));
        _reserveCashRegister.setOpen(false);
    }


    public List<User> get_movingUsers() {
        return _movingUsers;
    }
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
    public CashRegister get_reserveCashRegister() {
        return _reserveCashRegister;
    }
    public QueuePoligon get_reservePolygon(){
        return _reserveQueuePolygon;
    }
}
