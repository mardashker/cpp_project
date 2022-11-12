package com.example.railwaystation.classes.Logic.Handlers;

import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.classes.Moduls.CashRegister;
import com.example.railwaystation.classes.Moduls.OurQueue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.util.Optional;

public class ClickOnCanvasHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event){

        var cashRegistryOpt = findCashRegistry(event);

        if(cashRegistryOpt.isEmpty())
            return;

        if(event.getButton() == MouseButton.PRIMARY)
            Game.showQueueDetails(cashRegistryOpt.get().getOurQueue());
        else {
            var cashRegister = cashRegistryOpt.get();

            // check if user tries to close the reserve cash register
            if(Game.get_currentLevel().get_reserveCashRegister().equals(cashRegister)){
                System.out.println("Can't close/open the reserve Q!");
                return;
            }
            //if the reserve cash register is already opened then we can't close one more cash register
            if(cashRegister.isOpen() && Game.get_currentLevel().get_reserveCashRegister().isOpen()) {
                System.out.println("There's an open Q already!");
                return;
            }
            //check the number of closed cash registers just in case
            long closedNumber = numberOfClosedCashRegisters();
            if(closedNumber != 1)
                throw new RuntimeException("Number of closed cash registers must be 1! It was " + closedNumber);

            cashRegister.setOpen(!cashRegister.isOpen());
            // swap queues
            if(cashRegister.isOpen()) {
                openRegisterInsteadOfSpare(cashRegister);
                System.out.println("Move users to the opened Q!");
            }
            else {
                openSpareRegisterInstadeOf(cashRegister);
                System.out.println("Move users to the reserve Q!");
            }
        }

    }

    public long numberOfClosedCashRegisters(){
        return Game.get_currentLevel().get_cashRegistersList().stream()
                .filter(cr -> !cr.isOpen())
                .count();
    }
    public static void openSpareRegisterInstadeOf(CashRegister closedCashReg){
        //TODO: simplify it if CashRegister and QueuePolygon is changed
        var reserveCashReg = Game.get_currentLevel().get_reserveCashRegister();
        var reservePoligon = Game.get_currentLevel().get_reservePolygon();

        //find the polygon
        var closedQPoligon = Game.currentLevel.get_poligons().stream()
                .filter(p -> p.get_queue().equals(closedCashReg.getOurQueue()))
                .findFirst()
                .get();


        //move the queue to the reserve polygon and cashRegister
        reservePoligon.set_queue(closedCashReg.getOurQueue());
        reserveCashReg.setOurQueue(closedCashReg.getOurQueue());
        reserveCashReg.setOpen(true);

        //set an empty queue to the closed polygon and cashRegister
        var emptyQueue = new OurQueue();
        closedQPoligon.set_queue(emptyQueue);
        closedCashReg.setOurQueue(emptyQueue);
    }
    public static void openRegisterInsteadOfSpare(CashRegister openedReg){
        //TODO: simplify it if CashRegister and QueuePolygon is changed
        var reserveCashReg = Game.get_currentLevel().get_reserveCashRegister();
        var reservePoligon = Game.get_currentLevel().get_reservePolygon();

        //find the polygon
        var openedQPoligon = Game.currentLevel.get_poligons().stream()
                .filter(p -> p.get_queue().equals(openedReg.getOurQueue()))
                .findFirst()
                .get();

//move the queue to the just opened polygon and cashRegister
        openedQPoligon.set_queue(reservePoligon.get_queue());
        openedReg.setOurQueue(reserveCashReg.getOurQueue());
        reserveCashReg.setOpen(false);

//set an empty queue to the closed reserve polygon and cashRegister
        var emptyQueue = new OurQueue();
        reservePoligon.set_queue(emptyQueue);
        reserveCashReg.setOurQueue(emptyQueue);
    }
    private Optional<CashRegister> findCashRegistry(MouseEvent event){
        //find coordinates
        double clickX = event.getX();
        double clickY = event.getY();
        double x = (int)(clickX / Game.cell_width);
        double y = (int)(clickY / Game.cell_height);
        //find the clicked queue
        return Game.get_currentLevel().get_cashRegistersList().stream()
                .filter(cr -> {
                    var poss = cr.getPosition();
                    return poss.getX() == x && poss.getY() == y;
                })
                .findFirst();
    }
}
