package com.example.railwaystation.classes.Logic.Handlers;

import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.classes.Moduls.CashRegister;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.Optional;

public class ClickOnCanvasHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event){

        var cashRegistryOpt = findCashRegistry(event);

        if(cashRegistryOpt.isEmpty())
            return;

        if(event.isPrimaryButtonDown())
            Game.showQueueDetails(cashRegistryOpt.get().getOurQueue());
        else
            cashRegistryOpt.get().setOpen(false);
    }


    private Optional<CashRegister> findCashRegistry(MouseEvent event){
        //find coordinates
        double clickX = event.getX();
        double clickY = event.getY();
        double x = (int)clickX / Game.cell_width;
        double y = (int)clickY / Game.cell_height;
        //find the clicked queue
        return Game.get_currentLevel().get_cashRegistersList().stream()
                .filter(cr -> {
                    var poss = cr.getPosition();
                    return poss.getX() == x && poss.getY() == y;
                })
                .findFirst();
    }
}
