package com.example.railwaystation.classes.Logic;

import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Helpers.DrawingManagement.DrawingManager;
import com.example.railwaystation.classes.Helpers.GeneratingManagement.GeneratingManager;
import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Rendering.Rendering;

import java.util.List;

//TODO: клас для управління всієї логіки програми
public class GameLoop {

    private final List<Generator> _userSources;
    private final Game _game;

    private final Rendering _renderingUnit;
    public static final int MAX_USER_NUMBER = 25;
    public GameLoop(Game game, List<Generator> userSources, Rendering renderingUnit){
        if(game == null || userSources == null || renderingUnit == null)
            throw new IllegalArgumentException("Parameters can't be null!");
        this._game = game;
        this._userSources = userSources;
        this._renderingUnit = renderingUnit;
    }

    public void run(){
        while(true){
            drawFrame();
        }
    }

    private void drawFrame(){

        /*  1. GENERATING NEW USERS AND ADD THEM TO THE COLLECTION OF MOVING USERS,
                CHECK IF THE STATION IS OVERCROWDED AND CLOSE DOORS IF IT IS */
        checkDoorsAndNewUsers();
        /*  2. REMOVING SERVED USER IF THERE'S SUCH ONES */
        checkRegistryServices();
        /*  3. MOVING EACH USER */
        moveUsers();
        /*  4. RENDERING ALL OBJECTS */
        renderNewFrame();
    }

    private void checkDoorsAndNewUsers(){
        GeneratingManager manager =
                new GeneratingManager(_game.get_currentLevel(), this._userSources);

        int userNumber = manager.countUsersInStation();
        boolean isCrowded = userNumber >= MAX_USER_NUMBER;
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
}

