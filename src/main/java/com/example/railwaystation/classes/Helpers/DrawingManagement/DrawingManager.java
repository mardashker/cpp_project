package com.example.railwaystation.classes.Helpers.DrawingManagement;

import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.classes.Rendering.Rendering;

/**
 * This class will handle game level when it's time to draw the next frame
 * and all the data is updated and renewed.
 */
public class DrawingManager {

    private final GameLevel _level;
    private final Rendering _context;

    /**
     * @param level - level to handle.
     * @param context - canvas context. All objects will be displayed on it.
     */
    public DrawingManager(GameLevel level, Rendering context){
        if(level == null || context == null)
            throw new IllegalArgumentException("Neither parameter is allowed to be null!");
        this._level = level;
        this._context = context;
    }

    /**
     * Draws a new frame using information received from GameLevel.
     */
    public void drawFrame(){

//        _context.DrawGrid(_level.get_matrix().length * Game.cell_width, _level.get_matrix()[0].length * Game.cell_height, Game.cell_width, Game.cell_height); //TODO: that's just for testing, remove it later
        // draw doors
        for(var door : _level.get_doorsList())
            door.DrawSprite(_context);

        // draw polygons and users on these polygons
        for(var poligonQ : _level.get_poligons()){
            poligonQ.DrawSprite(_context);
        }


        // draw cashRegisters
        for(var register : _level.get_cashRegistersList())
            register.DrawSprite(_context);

        //draw users beyond any polygon
        for(var user : _level.get_movingUsers())
            user.DrawSprite(_context);
    }

    /**
     * Clears canvas. Usually before drawing a new frame.
     */
    public void clearCanvas(){
        _context.ClearCtx();
    }
}
