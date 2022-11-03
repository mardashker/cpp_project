package com.example.railwaystation.classes.Game;

//клас для зображення черги, вигляд (координати) черги

import com.example.railwaystation.classes.Moduls.GameObject;

import java.util.Collection;
//можливо окремо зробити клас quecell?
public class QueuePoligon {
    public Collection<GameObject> queueCells;

    public QueuePoligon(Collection<GameObject> queueCells) {
        this.queueCells = queueCells;
    }
}
