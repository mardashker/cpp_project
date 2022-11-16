package com.example.railwaystation.classes.Helpers;


import java.io.IOException;
import java.util.SplittableRandom;

// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive
import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Logic.Game;
import com.example.railwaystation.refactored_classes.Models.Door;
import com.example.railwaystation.refactored_classes.Models.UserFiles.User;
import com.example.railwaystation.refactored_classes.Models.UserFiles.PrototypeRegistry;
import com.example.railwaystation.refactored_classes.Models.UserFiles.UserType;
import com.example.railwaystation.classes.Rendering.ResourceManagerUser;


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
                    dataGenerator.generatePassportId(), dataGenerator.generatePhoneNumber(),dataGenerator.generateTickets(), door.getPosition(), door.getAngle(),door);
            if(user.getType() == UserType.ORDINARY){
                user.setSprite(ResourceManagerUser.getSprite("ordinary_"+randomizer.nextInt(1,36)));
            }
            return user;
        } return null;
    }
}
