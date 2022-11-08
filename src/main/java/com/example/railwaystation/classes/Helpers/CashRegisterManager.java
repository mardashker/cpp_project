package com.example.railwaystation.classes.Helpers;

import com.example.railwaystation.classes.Moduls.CashRegister;
import com.example.railwaystation.classes.Moduls.State;

public class CashRegisterManager {
    private QueueManager manager;

    public CashRegisterManager(QueueManager manager) {
        this.manager = manager;
    }

    public void processUser(CashRegister register){

        if(register.getState() == State.CLOSE) {
            return;
        }
        else if(register.getState() == State.OPEN) {
            startProcessingNewUser(register);
        }
        else if (register.getState() == State.WORKING) {

            processUserTickets(register);
        }
    }

    private void startProcessingNewUser(CashRegister register) {
        register.setProcessingUser(register.getOurQueue().getFirsUser());

        if(register.getProcessingUser() == null)
            return;

        register.setState(State.WORKING);
        register.setSecondsToProcessUser(
                CashRegisterHelper.countTicketProcessTime(
                        register.getProcessingUser()
                                .getTickets()
                )
        );
        register.getTimer().start();
    }

    private void processUserTickets(CashRegister register) {
        if(CashRegisterHelper.userProcessingTimeExpired(register.getSecondsToProcessUser(), register.getTimer())) {
            register.getTimer().stop();
            //TODO: log user
            register.getOurQueue().removeFirsUser();
            register.setProcessingUser(null);
            register.setState(State.OPEN);
            // Add event to update Queue Polygon
            manager.moveUsersInQueuePolygon(register.getOurQueue());
        }
    }

    public void updateQueue(){
        //TODO: повідомляє про те що обробили ще одного юзера або взяли ще одного юзера
    }

    public void closeCashRegister(CashRegister register){
        register.setState(State.CLOSE);
        //TODO: закрити касу
//        register.setSprite(new Image("file:src/main/resources/com/example/railwaystation/img/icons/nocash.png"));
    }

    public void openCashRegister(CashRegister register) {
        register.setState(State.OPEN);

        //TODO: відкрити касу
//        register.setSprite(new Image("file:src/main/resources/com/example/railwaystation/img/icons/cash.png"));

    }
}
