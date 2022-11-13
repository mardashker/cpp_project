package com.example.railwaystation.classes.Helpers;


import java.io.IOException;
import java.util.SplittableRandom;

// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive
import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.classes.Moduls.Door;
import com.example.railwaystation.classes.Moduls.Users.User;
import com.example.railwaystation.classes.Moduls.Users.PrototypeRegistry;
import com.example.railwaystation.classes.Moduls.Users.UserType;

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
        if (this.door.isOpen()){
            Game.usersCount++;
            // Додати координати дверей
            var typeIndex = randomizer.nextInt(UserType.values().length);
            var user = PrototypeRegistry.getPrototype(UserType.values()[randomizer.nextInt(UserType.values().length)]).userClone();
            user.setPersonInfo(dataGenerator.generateName(), dataGenerator.generateLastName(), dataGenerator.generateAge(),
                    dataGenerator.generatePassportId(), dataGenerator.generatePhoneNumber(),dataGenerator.generateTickets(), door.getPosition(), door.getAngle());

            user.set_birth_place(door);
            return user;
        } return null;
    }
}
