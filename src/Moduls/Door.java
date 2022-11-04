package Moduls;

import Helpers.Coordinates;

public class Door {

    private boolean isOpen;
    private Coordinates coordinates;

    public boolean getIsOpen() {
        return isOpen;
    }
    public Door(Coordinates coordinates){
        this.coordinates = coordinates;
        this.isOpen = true;
    }
    public void closeDoor(){
        this.isOpen = false;
    }
    public void openDoor(){
        this.isOpen = true;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
