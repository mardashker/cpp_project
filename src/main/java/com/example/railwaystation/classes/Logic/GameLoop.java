package com.example.railwaystation.classes.Logic;

import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Game.QueuePoligon;
import com.example.railwaystation.classes.Helpers.CashRegisterManager;
import com.example.railwaystation.classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Helpers.DrawingManagement.DrawingManager;
import com.example.railwaystation.classes.Helpers.GeneratingManagement.GeneratingManager;
import com.example.railwaystation.classes.Helpers.MovingManager;
import com.example.railwaystation.classes.Helpers.QueueManager;
import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Moduls.CashRegister;
import com.example.railwaystation.classes.Moduls.State;
import com.example.railwaystation.classes.Moduls.Users.User;
import com.example.railwaystation.classes.Rendering.Rendering;

import java.util.Iterator;
import java.util.List;

//TODO: клас для управління всієї логіки програми
public class GameLoop implements Runnable {

    private static final int FPS = 6;
    private int _maxUserCount = 30;
    private boolean _isRunning = true;
    private final List<Generator> _userSources;
    private final Game _game;
    private final Rendering _renderingUnit;
    private final QueueManager _queueManager;
    private final CashRegisterManager _cashRegisterManager;
    public GameLoop(Game game, List<Generator> userSources, Rendering renderingUnit){
        if(game == null || userSources == null || renderingUnit == null)
            throw new IllegalArgumentException("Parameters can't be null!");
        this._game = game;
        this._userSources = userSources;
        this._renderingUnit = renderingUnit;
        this._queueManager = new QueueManager(Game.get_currentLevel());
        this._cashRegisterManager = new CashRegisterManager(_queueManager);
    }


    public void run() {
        this._isRunning = true;

        double drawInterval = 1_000_000_000f / FPS;             // interval between frames in nanoseconds
        double nextFrameTime = System.nanoTime() + drawInterval;// next frame time in nanoseconds
        double timeToWaitBeforeNext = 0;                        // free time after rendering before next iteration
        Game.get_currentLevel().get_cashRegistersList().forEach(c->c.setState(State.OPEN));
        while(_isRunning){

            updateStationState();
            renderNewFrame();

            timeToWaitBeforeNext = nextFrameTime - System.nanoTime();   //
            timeToWaitBeforeNext = Math.max(timeToWaitBeforeNext, 0);   //calculate a time to wait before next iteration
            timeToWaitBeforeNext /= 1_000_000;                          //

            try {
                Thread.sleep(1000);
            } catch (Exception ex ){
                throw new RuntimeException(ex);
            }

            nextFrameTime += drawInterval;
        }
    }


    private void updateStationState(){
        checkDoorsAndNewUsers();
        checkRegistryServices();
        moveUsers();
    }
    private void checkDoorsAndNewUsers(){
        GeneratingManager manager =
                new GeneratingManager(_game.get_currentLevel(), this._userSources);

        int userNumber = manager.countUsersInStation();
        boolean isCrowded = userNumber >= _maxUserCount;
        var newUsers = manager.collectUsers();

        if(isCrowded)
            manager.closeDoors();
        else
            manager.openDoors();
    }

    private void checkRegistryServices(){
        var cashRegisters = Game.get_currentLevel().get_cashRegistersList();
        cashRegisters.forEach(c -> _cashRegisterManager.processUser(c));
    }
    private void moveUsers() {
//        var lvl = Game.get_currentLevel();
//
//        QueuePoligon correct_queue;
//        var it = lvl.get_movingUsers().iterator();
//
//        while(it.hasNext()){
//            var el = it.next();
//            if (el.get_target() != null) {
//                correct_queue = el.get_target();
//            } else {
//                correct_queue = QueueManager.getCorrectQueue(el, lvl.get_poligons());
//                el.set_target(correct_queue);
//            }
//
//            try {
//                var res = MovingManager.findNextPos(lvl, correct_queue, el, this._queueManager, it);
//                if(res != null){
//                    el.setPosition(res.getPosition());
//                }
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    private void renderNewFrame(){
        var drawingManager
                = new DrawingManager(_game.get_currentLevel(), _renderingUnit);
        drawingManager.clearCanvas();
        drawingManager.drawFrame();
    }



    public boolean isRunning(){
        return _isRunning;
    }
    public void stop(){
        this._isRunning = false;
    }
}

