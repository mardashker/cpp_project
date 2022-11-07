package com.example.railwaystation.classes.Logic;

import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Helpers.DrawingManagement.DrawingManager;
import com.example.railwaystation.classes.Helpers.GeneratingManagement.GeneratingManager;
import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Rendering.Rendering;

import java.util.List;

//TODO: клас для управління всієї логіки програми
public class GameLoop {

    private static final int FPS = 30;
    private int _maxUserCount = 25;
    private boolean _isRunning = true;
    private final List<Generator> _userSources;
    private final Game _game;
    private final Rendering _renderingUnit;
    public GameLoop(Game game, List<Generator> userSources, Rendering renderingUnit){
        if(game == null || userSources == null || renderingUnit == null)
            throw new IllegalArgumentException("Parameters can't be null!");
        this._game = game;
        this._userSources = userSources;
        this._renderingUnit = renderingUnit;
    }


    public void run() throws InterruptedException {
        this._isRunning = true;

        double drawInterval = 1_000_000_000f / FPS;             // interval between frames in nanoseconds
        double nextFrameTime = System.nanoTime() + drawInterval;// next frame time in nanoseconds
        double timeToWaitBeforeNext = 0;                        // free time after rendering before next iteration

        while(_isRunning){

            updateStationState();
            renderNewFrame();

            timeToWaitBeforeNext = nextFrameTime - System.nanoTime();   //
            timeToWaitBeforeNext = Math.max(timeToWaitBeforeNext, 0);   //calculate a time to wait before next iteration
            timeToWaitBeforeNext /= 1_000_000;                          //

            Thread.sleep((long)timeToWaitBeforeNext);

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
        if(isCrowded)
            manager.closeDoors();
    }
    private void checkRegistryServices(){

    }
    private void moveUsers(){

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

