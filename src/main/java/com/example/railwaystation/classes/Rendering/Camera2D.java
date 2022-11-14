package com.example.railwaystation.classes.Rendering;

import com.example.railwaystation.classes.Helpers.Coordinates;

public class Camera2D {

    private double _zoom = 1.0f;
    private Coordinates _position;
    public Camera2D(){
        _position = new Coordinates();
    }
    public Camera2D(Coordinates position){
        _position = position;
    }
    public Camera2D(Coordinates position, double zoom){
        _position = position;
        _zoom = zoom;
    }

    public double get_zoom() {
        return _zoom;
    }

    public Coordinates get_position() {
        return _position;
    }

    public void set_zoom(double _zoom) {
        this._zoom = _zoom;
    }

    public void set_position(Coordinates _position) {
        this._position = _position;
    }
}