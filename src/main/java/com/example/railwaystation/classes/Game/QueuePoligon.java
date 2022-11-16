package com.example.railwaystation.classes.Game;

//клас для зображення черги, вигляд (координати) черги

import com.example.railwaystation.classes.Moduls.GameObject;
import com.example.railwaystation.classes.Moduls.OurQueue;
import com.example.railwaystation.classes.Rendering.Rendering;

import java.util.Collection;

public class QueuePoligon extends GameObject {

    private int _potential_count = 0;
    private final Collection<GameObject> _queueCells;
    private volatile OurQueue _queue = new OurQueue();


    public QueuePoligon(Collection<GameObject> queueCells) {
        this._queueCells = queueCells;
    }

    @Override
    public void DrawSprite(Rendering ctx){

        // draw N first users from the queue
        _queueCells.forEach(g -> g.DrawSprite(ctx));

        if(_queue.getUsers().size() <= 0)
            return;

        var usersToDrawAtStart = get_queue().getUsers().stream()
                .limit(_queueCells.size() - 1)
                .toList();
        usersToDrawAtStart.forEach(u -> u.DrawSprite(ctx));

        //if there are no users return

        //else draw the last user
        get_queue().getUsers().stream()
                .toList()
                .get(_queue.getUsers().size() - 1).DrawSprite(ctx);
    }



    public Collection<GameObject> get_queueCells() {
        return _queueCells;
    }
    public int get_potential_count() {
        return _potential_count;
    }
    public void set_potential_count(int _potential_count) {
        this._potential_count = _potential_count;
    }
    public GameObject getQueueTailCoordinates() {
        GameObject lastElement = null;

        for (GameObject element : _queueCells) {
            lastElement = element;
        }

        return lastElement;
    }
    public synchronized void set_queue(OurQueue _queue) {
        this._queue = _queue;
    }
    public synchronized OurQueue get_queue() {
        return _queue;
    }
}
