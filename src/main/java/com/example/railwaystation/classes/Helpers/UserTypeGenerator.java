package com.example.railwaystation.classes.Helpers;

import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.refactored_classes.Models.Door;
import com.example.railwaystation.classes.Rendering.ResourceManagerUser;
import com.example.railwaystation.refactored_classes.Models.UserFiles.User;
import com.example.railwaystation.refactored_classes.Models.UserFiles.PrototypeRegistry;
import com.example.railwaystation.refactored_classes.Models.UserFiles.UserType;


import java.io.IOException;
import java.util.SplittableRandom;

public class UserTypeGenerator implements Generator {

    private Door door;
    private UserDataGenerator dataGenerator;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    private UserType userType;
    private SplittableRandom randomizer;


    public UserTypeGenerator(Door door, UserType type) throws IOException {
        this.door = door;
        this.userType = type;
        randomizer = new SplittableRandom();
        dataGenerator = UserDataGenerator.getInstance();
    }


    @Override
    public User generateUser() {
        if (this.door.isOpen()) {
            Game.usersCount++;
            var user = PrototypeRegistry.getPrototype(userType).userClone();
            user.setPersonInfo(dataGenerator.generateName(), dataGenerator.generateLastName(), dataGenerator.generateAge(),
                    dataGenerator.generatePassportId(), dataGenerator.generatePhoneNumber(),dataGenerator.generateTickets(), door.getPosition(),door.getAngle(), this.door);
            if(user.getType() == UserType.ORDINARY){
                user.setSprite(ResourceManagerUser.getSprite("ordinary_"+randomizer.nextInt(1,36)));
            }
            return user;
        }
        return null;
    }
}