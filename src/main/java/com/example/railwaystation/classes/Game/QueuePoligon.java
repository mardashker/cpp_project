package com.example.railwaystation.classes.Game;

//клас для зображення черги, вигляд (координати) черги

import com.example.railwaystation.classes.Moduls.GameObject;
import com.example.railwaystation.classes.Moduls.OurQueue;
import com.example.railwaystation.classes.Rendering.Rendering;

import java.util.Collection;
public class QueuePoligon extends GameObject {
    private final Collection<GameObject> _queueCells;

    private OurQueue _queue = new OurQueue();
    public QueuePoligon(Collection<GameObject> queueCells) {
        this._queueCells = queueCells;
    }
    @Override
    public void DrawSprite(Rendering ctx){
        // TODO: Check if not to slow
        for(int i=0; i < _queueCells.size(); i++){
            _queueCells.stream().toList().get(i).DrawSprite(ctx);
            if(i < _queue.size())
                _queue.getUsers().stream().toList().get(i).DrawSprite(ctx);
        }
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
