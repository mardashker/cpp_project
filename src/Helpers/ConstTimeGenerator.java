package Helpers;

import Interfaces.Generator;
import Moduls.Door;
import Moduls.Users.User;

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
