package com.example.railwaystation.classes.Helpers;

public class Coordinates {

    private double x;

    private double y;

    //зробити гетери сетери

    public Coordinates(){
        x = 0;
        y = 0;
    }
    public Coordinates(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
