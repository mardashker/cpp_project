package com.example.railwaystation.classes.Logic.Handlers;

import com.example.railwaystation.classes.Game.QueuePoligon;
import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.classes.Moduls.CashRegister;
import com.example.railwaystation.classes.Moduls.OurQueue;
import com.example.railwaystation.classes.Rendering.Camera2D;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.util.Optional;

public class ClickOnCanvasHandler implements EventHandler<MouseEvent> {
    private Camera2D _camera;
    public ClickOnCanvasHandler(Camera2D camera) {
        _camera = camera;
    }

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
        updateCoordinates(closedCashReg, reservePoligon);

        reservePoligon.set_queue(closedCashReg.getOurQueue());
        reserveCashReg.setOurQueue(closedCashReg.getOurQueue());
        reserveCashReg.setOpen(true);
        reserveCashReg.setSprite(new Image("file:src/main/resources/com/example/railwaystation/assets/reservcash.png"));

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
        updateCoordinates(reserveCashReg, openedQPoligon);
        openedQPoligon.set_queue(reservePoligon.get_queue());
        openedReg.setOurQueue(reserveCashReg.getOurQueue());
        reserveCashReg.setOpen(false);
        reserveCashReg.setSprite(new Image("file:src/main/resources/com/example/railwaystation/assets/noreservecash.png"));

//set an empty queue to the closed reserve polygon and cashRegister
        var emptyQueue = new OurQueue();
        reservePoligon.set_queue(emptyQueue);
        reserveCashReg.setOurQueue(emptyQueue);
    }

    //TODO: these methods should be moved to another class
    private Optional<CashRegister> findCashRegistry(MouseEvent event){
        //find coordinates
        double clickX = event.getX() + _camera.get_position().getX();
        double clickY = event.getY() + _camera.get_position().getY();
        double x = (int)(clickX / Game.cell_width / _camera.get_zoom());
        double y = (int)(clickY / Game.cell_height / _camera.get_zoom());
        //find the clicked queue
        return Game.get_currentLevel().get_cashRegistersList().stream()
                .filter(cr -> {
                    var poss = cr.getPosition();
                    return poss.getX() == x && poss.getY() == y;
                })
                .findFirst();
    }
    /**
     * Changes coordinates of each user according to the polygon
     * @param cashReg - cashRegister to replace user coordinates to
     * @param polygon - new poligon for the queue from cashRegister
     */
    private static void updateCoordinates(CashRegister cashReg, QueuePoligon polygon){
        var users = cashReg.getOurQueue().getUsers();
        var cells = polygon.get_queueCells().stream().toList();

        for(int i = 0; i < users.size(); ++i){
            users.get(i).setPosition(
                    cells.get(Math.min(i, cells.size() - 1)).getPosition().copy()
            );
        }
    }
    public long numberOfClosedCashRegisters(){
        return Game.get_currentLevel().get_cashRegistersList().stream()
                .filter(cr -> !cr.isOpen())
                .count();
    }
}
