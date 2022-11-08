package com.example.railwaystation.classes.Moduls;

import com.example.railwaystation.classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Moduls.Users.User;
import javafx.scene.image.Image;

public class CashRegister extends GameObject {

    private OurQueue ourQueue;
    private User processingUser;
    private long secondsToProcessUser = 0;
    private State state;
    private StopWatch timer;
    private boolean isOpen;

    public void processUser(){
        //TODO: процес обробки першого юзера з черги
    }

    public void updateQueue(){
        //TODO: повідомляє про те що обробили ще одного юзера або взяли ще одного юзера
    }

    public void closeCashRegister(){
        //TODO: закрити касу
//        this.setSprite(new Image("file:src/main/resources/com/example/railwaystation/img/icons/nocash.png"));
    }

    public void openCashRegister(){
        //TODO: відкрити касу
//        this.setSprite(new Image("file:src/main/resources/com/example/railwaystation/img/icons/cash.png"));

    }

    public CashRegister() {
        ourQueue = new OurQueue();
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

    public OurQueue getOurQueue() {
        return ourQueue;
    }

    public State getState() {
        return state;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOurQueue(OurQueue ourQueue) {
        this.ourQueue = ourQueue;
    }

    public User getProcessingUser() {
        return processingUser;
    }

    public void setProcessingUser(User processingUser) {
        this.processingUser = processingUser;
    }

    public long getSecondsToProcessUser() {
        return secondsToProcessUser;
    }

    public void setSecondsToProcessUser(long secondsToProcessUser) {
        this.secondsToProcessUser = secondsToProcessUser;
    }

    public void setState(State state) {
        this.state = state;
    }

    public StopWatch getTimer() {
        return timer;
    }

    public void setTimer(StopWatch timer) {
        this.timer = timer;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
