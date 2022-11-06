package com.example.railwaystation.classes.Rendering;

import com.example.railwaystation.classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Logic.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public class CanvasRendering implements Rendering {
    Canvas canvas =new Canvas();
    public CanvasRendering(Canvas canvas_1) {
        canvas=canvas_1;
    }

    @Override
    public void DrawSprite(Coordinates pos, double width, double height, double angle, Image sprite) {
        canvas.getGraphicsContext2D().drawImage(sprite, pos.getX()* Game.cell_width, pos.getY()*Game.cell_height, Game.cell_width, Game.cell_height);
    }

    @Override
    public void DrawGrid(double width, double height, double step_x, double step_y) {
        var ctx = _canvas.getGraphicsContext2D();
        ctx.setStroke(Color.BLUEVIOLET);
        for(double x=0; x <= width; x+=step_x ){
            _canvas.getGraphicsContext2D().strokeLine(x,0,x,height);
        }
        for (double y=0; y <= height; y+= step_y){
            _canvas.getGraphicsContext2D().strokeLine(0,y,width,y);
        }
    }
    @Override
    public void ClearCtx(){
        _canvas.getGraphicsContext2D().clearRect(0, 0, _canvas.getWidth(), _canvas.getHeight());
    }
}
