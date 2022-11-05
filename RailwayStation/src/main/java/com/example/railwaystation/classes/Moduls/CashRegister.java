package com.example.railwaystation.classes.Moduls;

import com.example.railwaystation.classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Rendering.ResourceManagerCashRegister;
import javafx.scene.image.Image;

//стан каси
enum State{
    OPEN,
    CLOSE,
    WORKING
}

public class CashRegister extends GameObject {

    private OurQueue ourQueue;

    private State state;

    private boolean isOpen;

    public void processUser(){
        //TODO: процес обробки першого юзера з черги
    }

    public void updateQueue(){
        //TODO: повідомляє про те що обробили ще одного юзера або взяли ще одного юзера
    }

    public void closeCashRegister(){
        this.setSprite(ResourceManagerCashRegister.getSprite("closed"));
    }

    public void openCashRegister(){
        // TODO: change CashRegister to also store direction it is looking to **north** is just as stub
        this.setSprite(ResourceManagerCashRegister.getSprite("north"));

    }

    public CashRegister() {
    }

    public CashRegister(OurQueue ourQueue, State state, boolean isOpen) {
        this.ourQueue = ourQueue;
        this.state = state;
        this.isOpen = isOpen;
    }

    public CashRegister(Coordinates position, double width, double height, Image sprite, double angle, OurQueue ourQueue, State state, boolean isOpen) {
        super(position, width, height, sprite, angle);
        this.ourQueue = ourQueue;
        this.state = state;
        this.isOpen = isOpen;
    }
}
