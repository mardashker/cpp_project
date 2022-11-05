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
    public void ClearCanvas(){
        canvas.getGraphicsContext2D()
                .clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
