package com.example.railwaystation.refactored_classes.Generators;


import java.io.IOException;
import java.util.SplittableRandom;

import com.example.railwaystation.refactored_classes.Interfaces.Generator;
import com.example.railwaystation.refactored_classes.Models.Door;
import com.example.railwaystation.classes.Moduls.Users.User;
import com.example.railwaystation.refactored_classes.Models.UserFiles.PrototypeRegistry;
import com.example.railwaystation.refactored_classes.Models.UserFiles.UserType;
import com.example.railwaystation.refactored_classes.Rendering.ResourceManagerUser;



public class ConstUserGenerator implements Generator{
    SplittableRandom randomizer;
    Door door;
    private UserDataGenerator dataGenerator;
    public ConstUserGenerator(Door door) throws IOException {
        this.door = door;
        this.randomizer = new SplittableRandom();
        dataGenerator = UserDataGenerator.getInstance();
    }

    @Override
    public User generateUser() {
        if (this.door.isOpen()){
            var typeIndex = randomizer.nextInt(UserType.values().length);
            var user = PrototypeRegistry.getPrototype(UserType.values()[randomizer.nextInt(UserType.values().length)]).userClone();
            user.setPersonInfo(dataGenerator.generateName(), dataGenerator.generateLastName(), dataGenerator.generateAge(),
                    dataGenerator.generatePassportId(), dataGenerator.generatePhoneNumber(),dataGenerator.generateTickets(), door.getPosition(), this.door);
            if(user.getType() == UserType.ORDINARY){
                user.setSprite(ResourceManagerUser.getSprite("ordinary_"+randomizer.nextInt(1,36)));
            }
            return user;
        } return null;
    }
}
