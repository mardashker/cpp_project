package com.example.railwaystation.classes.Helpers;

public class Coordinates {

    private double x;

    private double y;

    private double angle;

    //зробити гетери сетери

    public Coordinates(){
        x = 0;
        y = 0;
    }
    public Coordinates(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
        this.angle = 0;
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

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
