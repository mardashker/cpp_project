package com.example.railwaystation.classes.Helpers;

import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.classes.Moduls.Door;
import com.example.railwaystation.classes.Moduls.Users.User;
import com.example.railwaystation.classes.Moduls.Users.PrototypeRegistry;
import com.example.railwaystation.classes.Moduls.Users.UserType;


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
                    dataGenerator.generatePassportId(), dataGenerator.generatePhoneNumber(),dataGenerator.generateTickets(), door.getPosition(),door.getAngle());
            return user;
            //return new User(Game.imageForUserType.get(userType), "Stephan", "Mariik", 18, "FD1223", "+380987197943", userType,door.coordinates);
        }
        return null;
    }
}