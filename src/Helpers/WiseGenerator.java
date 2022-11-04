package Helpers;

import Interfaces.Generator;
import Logic.Game;
import Moduls.Door;
import Moduls.Users.User;
import Moduls.Users.UserPrototypeManager;
import Moduls.Users.UserType;

import java.io.IOException;
import java.util.*;

public class WiseGenerator implements Generator {

    private Door door;
    private UserDataGenerator dataGenerator;
    private float probability;
    private SplittableRandom randomizer;
    public WiseGenerator(Door door) throws IOException {
        this.probability = 100;
        this.randomizer = new SplittableRandom();
        this.door = door;
        dataGenerator = UserDataGenerator.getInstance();
    }

    @Override
    public User generateUser() {
        checkProbability();
        if(this.door.getIsOpen()){
            if(randomizer.nextInt(1, 101) <= probability){
                var typeIndex = randomizer.nextInt(UserType.values().length);
                Game.usersCount++;
                var user = UserPrototypeManager.basicUsers.get(typeIndex).userClone();
                user.setPersonInfo(dataGenerator.generateName(), dataGenerator.generateLastName(), dataGenerator.generateAge(),
                        dataGenerator.generatePassportId(), dataGenerator.generatePhoneNumber(), dataGenerator.generateTickets(), door.getCoordinates());
                return user;
                //return new User(Game.imageForUserType.get(type),"Zakhar","Boiko",14,"730423","0631166494",type, door.coordinates);
            }
        }
        return null;
    }
    private void checkProbability(){
        var usersCount = Game.usersCount;
        if(isBetween(usersCount,0,25)){
            probability = 100;
        } else if(isBetween(usersCount,25,50)){
            probability = 75;
        } else if(isBetween(usersCount,50,75)){
            probability = 50;
        }  else{
            probability = 25;
        }
    }
    private  boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
}