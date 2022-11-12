package com.example.railwaystation.classes.Helpers;

import com.example.railwaystation.classes.Helpers.GeneratingManagement.GeneratingManager;
import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.classes.Moduls.Door;
import com.example.railwaystation.classes.Moduls.Users.User;
import com.example.railwaystation.classes.Moduls.Users.PrototypeRegistry;
import com.example.railwaystation.classes.Moduls.Users.UserType;
import com.example.railwaystation.classes.Rendering.ResourceManagerUser;

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
                var user = PrototypeRegistry.getPrototype(UserType.values()[randomizer.nextInt(UserType.values().length)]).userClone();
                user.setPersonInfo(dataGenerator.generateName(), dataGenerator.generateLastName(), dataGenerator.generateAge(),
                        dataGenerator.generatePassportId(), dataGenerator.generatePhoneNumber(), dataGenerator.generateTickets(), door.getPosition(),door.getAngle());
                if(user.getType() == UserType.ORDINARY){
                    user.setSprite(ResourceManagerUser.getSprite("ordinary_"+randomizer.nextInt(1,5)));
                }
                return user;
            }
        }
        return null;
    }
    private void checkProbability(){
        if(isBetween(Game.usersCount,0,(int) (Game.maxUserCount * 0.25))){
            probability = 100;
        } else if(isBetween(Game.usersCount,(int) (Game.maxUserCount * 0.25),(int) (Game.maxUserCount * 0.5))){
            probability = 75;
        } else if(isBetween(Game.usersCount,(int) (Game.maxUserCount * 0.5),(int)(Game.maxUserCount * 0.75))){
            probability = 50;
        }  else{
            probability = 25;
        }
    }
    private  boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
}