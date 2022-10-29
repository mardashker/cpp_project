package Game;

import Moduls.CashRegister;
import Moduls.Door;

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

    private List<Door> doorsList;
    private List<CashRegister> cashRegistersList;
    private List<QueuePoligon> poligons;

    //дані можуть міститися в файлі (незалежно від формату файлу матриця не змінюється)
    //TODO: знайти клас Matrix або щось подібне
    private Collection<Collection<CellState>> matrix;

}
