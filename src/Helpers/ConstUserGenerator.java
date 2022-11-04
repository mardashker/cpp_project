package Helpers;

import Interfaces.Generator;
import Moduls.Door;
import Moduls.Users.User;
import Moduls.Users.UserPrototypeManager;
import Moduls.Users.UserType;

import java.io.IOException;
import java.util.SplittableRandom;
import Logic.Game;
import java.util.concurrent.ThreadLocalRandom;

// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive

//генерує конкретний тип юзеру
//пошукати можливість передавати тип юзеру (не сам об'єкт)
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
        if (this.door.getIsOpen()){
            Game.usersCount++;
            var typeIndex = randomizer.nextInt(UserType.values().length);
            var user = UserPrototypeManager.basicUsers.get(typeIndex).userClone();
            user.setPersonInfo(dataGenerator.generateName(), dataGenerator.generateLastName(), dataGenerator.generateAge(),
                    dataGenerator.generatePassportId(), dataGenerator.generatePhoneNumber(),dataGenerator.generateTickets());
            return user;
        } return null;
    }
}
