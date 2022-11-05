package com.example.railwaystation.classes.Logic;


import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Game.QueuePoligon;
import com.example.railwaystation.classes.Helpers.CashRegisterManager;
import com.example.railwaystation.classes.Helpers.QueueManager;
import com.example.railwaystation.classes.Moduls.CashRegister;
import com.example.railwaystation.classes.Moduls.OurQueue;

import java.util.Queue;

//TODO: клас для управління всієї логіки програми
public class GameLoop {
    GameLevel level;
    QueueManager queueManager;
    CashRegisterManager cashManager;

    public GameLoop() {
        queueManager = new QueueManager(level);
        cashManager = new CashRegisterManager(queueManager);
    }

    public void run() {
        while (true) {
            OurQueue q = new OurQueue();
            CashRegister c = new CashRegister();


        }
    }
}
