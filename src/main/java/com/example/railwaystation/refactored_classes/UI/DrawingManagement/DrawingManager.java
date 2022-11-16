package com.example.railwaystation.refactored_classes.UI.DrawingManagement;

import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.refactored_classes.Rendering.Rendering;
public class DrawingManager {

    private final GameLevel _level;
    private final Rendering _context;

    public DrawingManager(GameLevel level, Rendering context){
        if(level == null || context == null)
            throw new IllegalArgumentException("Neither parameter is allowed to be null!");
        this._level = level;
        this._context = context;
    }

    public void drawFrame(){

        for(var door : _level.get_doorsList())
            door.DrawSprite(_context);
        for(var poligonQ : _level.get_poligons()){
            poligonQ.DrawSprite(_context);
        }
        for(var register : _level.get_cashRegistersList())
            register.DrawSprite(_context);
        for(var user : _level.get_movingUsers())
            user.DrawSprite(_context);
    }

    public void clearCanvas(){
        _context.ClearCtx();
    }
}
