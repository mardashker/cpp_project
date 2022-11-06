package com.example.railwaystation.classes.Game;

//клас для зображення черги, вигляд (координати) черги

import com.example.railwaystation.classes.Moduls.GameObject;
import com.example.railwaystation.classes.Moduls.OurQueue;
import com.example.railwaystation.classes.Rendering.Rendering;

import java.util.Collection;
public class QueuePoligon extends GameObject {
    private final Collection<GameObject> _queueCells;

    private OurQueue _queue = null;
    public QueuePoligon(Collection<GameObject> queueCells) {
        this._queueCells = queueCells;
    }
    @Override
    public void DrawSprite(Rendering ctx){
        // TODO: Draw first n object in cells
        _queueCells.stream().forEach(g -> g.DrawSprite(ctx));
    }
    public Collection<GameObject> get_queueCells() {
        return _queueCells;
    }

    public GameObject getQueueTailCoordinates() {
        GameObject lastElement = null;

        for (GameObject element : _queueCells) {
            lastElement = element;
        }

        return lastElement;
    }

    public void set_queue(OurQueue _queue) {
        this._queue = _queue;
    }
    public OurQueue get_queue() {
        return _queue;
    }
}
