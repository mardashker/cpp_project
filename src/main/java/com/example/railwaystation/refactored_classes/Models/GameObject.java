package com.example.railwaystation.refactored_classes.Models;

import com.example.railwaystation.refactored_classes.Helpers.Coordinates;
import com.example.railwaystation.refactored_classes.Rendering.Rendering;
import javafx.scene.image.Image;


public class GameObject {
    private Coordinates position;
    private double width;
    private double height;
    private Image sprite;
    private double angle;

    public GameObject(){
        position = new Coordinates();
        width = 0;
        height = 0;
        sprite = null;
        angle = 0;
    }
    public GameObject(Coordinates position, double width, double height, Image sprite, double angle) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        this.angle = angle;
    }

    public Coordinates getPosition() {
        return position;
    }
    public void setPosition(Coordinates position) {
        this.position = position;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public Image getSprite() {
        return sprite;
    }
    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }
    public double getAngle() {
        return angle;
    }
    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void DrawSprite(Rendering ctx){
        ctx.DrawSprite(position,width,height,angle,sprite);
    }
}
