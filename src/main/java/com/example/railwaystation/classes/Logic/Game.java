package com.example.railwaystation.classes.Logic;

import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Game.LevelReader;
import com.example.railwaystation.classes.Helpers.WiseGenerator;
import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Moduls.Users.User;
import com.example.railwaystation.classes.Moduls.Users.UserPrototypeManager;
import javafx.print.Collation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Game {
    public static final double cell_width = 32;
    public static final double cell_height = 32;
    public static int usersCount;
    private List<Generator> generators;

    private Collection<GameLevel> _levels;
    private Collection<User> _users;

    private GameLevel currentLevel;

    public List<Generator> getGenerators() {
        return generators;
    }

    public void setGenerators(List<Generator> generators) {
        this.generators = generators;
    }

    public void Init(){
        InitAssets();
        _levels = LevelReader.loadLevels();
        
    }

    //TODO: підтягування текстур, завантаження початкових даних
    public void InitAssets(){
        generators = new ArrayList<Generator>();
        var doors = currentLevel.get_doorsList();
        doors.stream().forEach(door -> {
            try {
                generators.add(new WiseGenerator(door));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            var prototypeManager = new UserPrototypeManager();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //GameLoop loop = new GameLoop();
        //loop.run();
        generators.stream().forEach(generator -> {
            var user = generator.generateUser();

        });
        String assetsFolder = "";
    }

}
