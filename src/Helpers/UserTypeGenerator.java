package Helpers;

import Interfaces.Generator;
import Moduls.Door;
import Moduls.Users.User;
import Moduls.Users.UserPrototypeManager;
import Moduls.Users.UserType;
import Logic.Game;


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
        if (this.door.getIsOpen()) {
            Game.usersCount++;
            var user = UserPrototypeManager.basicUsers.get(userType.ordinal()).userClone();
            user.setPersonInfo(dataGenerator.generateName(), dataGenerator.generateLastName(), dataGenerator.generateAge(),
                    dataGenerator.generatePassportId(), dataGenerator.generatePhoneNumber());
            return user;
            //return new User(Game.imageForUserType.get(userType), "Stephan", "Mariik", 18, "FD1223", "+380987197943", userType,door.coordinates);
        }
        return null;
    }
}