package com.example.railwaystation.classes.Moduls;

import com.example.railwaystation.classes.Helpers.Coordinates;
import javafx.scene.image.Image;

public class Door extends GameObject {

    private boolean _open;
    public Door(){
        _open = false;
    }

    public Door(Coordinates position, double width, double height, Image sprite, float angle, boolean _open) {
        super(position, width, height, sprite, angle);
        this._open = _open;
    }

//передавати кут як стрічку і так витягувати картинку
    public boolean isOpen() {
        return _open;
    }
    public void open(){
        setState(true);
    }
    public void close(){
        setState(false);
    }
    public void setState(boolean flag){
        _open = flag;
    }

}
