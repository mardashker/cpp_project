package com.example.railwaystation.classes.Helpers;

import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Moduls.Door;
import com.example.railwaystation.classes.Moduls.Users.User;

public class ConstTimeGenerator implements Generator {

    private float interval;
    private Door door;

    public ConstTimeGenerator(int interval, Door door) {
        this.interval = interval;
        this.door = door;
    }

    @Override
    public User generateUser() {
        return null;
    }
}
