package Helpers;

public class Coordinates {

    private int x;

    private int y;

    private int angle; //кут для визначення позиції

    public Coordinates(int x, int y, int angle){
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    //зробити гетери сетери

}
