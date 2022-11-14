package com.example.railwaystation.classes.Helpers;

import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.classes.Moduls.Door;
import com.example.railwaystation.classes.Moduls.Users.User;
import com.example.railwaystation.classes.Moduls.Users.PrototypeRegistry;
import com.example.railwaystation.classes.Moduls.Users.UserType;

import java.io.IOException;
import java.util.SplittableRandom;

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
        if(this.door.isOpen()){
            if(randomizer.nextInt(1, 101) <= probability){
                Game.usersCount++;
                var user = PrototypeRegistry.getPrototype(UserType.values()[randomizer.nextInt(UserType.values().length)]).userClone();
                user.setPersonInfo(dataGenerator.generateName(), dataGenerator.generateLastName(), dataGenerator.generateAge(),
                        dataGenerator.generatePassportId(), dataGenerator.generatePhoneNumber(), dataGenerator.generateTickets(), door.getPosition(),door.getAngle());
                user.set_birth_place(this.door);
                System.out.println(user.get_birth_place());
                return user;
                //return new User(Game.imageForUserType.get(type),"Zakhar","Boiko",14,"730423","0631166494",type, door.coordinates);
            }
        }
        return null;
    }
    private void checkProbability(){
        if(isBetween(Game.getUsersCount(),0,(int) (Game.getMaxUserCount() * 0.25))){
            probability = 100;
        } else if(isBetween(Game.getUsersCount(),(int) (Game.getMaxUserCount() * 0.25),(int) (Game.getMaxUserCount() * 0.5))){
            probability = 75;
        } else if(isBetween(Game.getUsersCount(),(int) (Game.getMaxUserCount() * 0.5),(int)(Game.getMaxUserCount() * 0.75))){
            probability = 50;
        }  else{
            probability = 25;
        }
    }
    private  boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
}