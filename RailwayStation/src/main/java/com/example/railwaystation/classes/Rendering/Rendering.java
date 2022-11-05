package com.example.railwaystation.classes.Rendering;

import com.example.railwaystation.classes.Helpers.Coordinates;

import javafx.scene.image.Image;

public interface Rendering {
    public void DrawSprite(Coordinates pos, double width, double height, double angle, Image sprite);
    public void ClearCanvas();
}
