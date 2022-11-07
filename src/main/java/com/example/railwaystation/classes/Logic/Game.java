package com.example.railwaystation.classes.Logic;

import com.example.railwaystation.classes.Game.AssetsReader;
import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Game.LevelReader;
import com.example.railwaystation.classes.Helpers.WiseGenerator;
import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Moduls.Users.*;
import com.example.railwaystation.classes.Rendering.ResourceManagerUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Game {
    public static final double cell_width = 20;
    public static final double cell_height = 20;
    public static int usersCount;
    private static List<Generator> _generators;

    private static Collection<GameLevel> _levels;

    public static GameLevel getCurrentLevel() {
        return currentLevel;
    }

    public static GameLevel currentLevel;

    public static List<Generator> getGenerators() {
        return _generators;
    }

    public static GameLevel get_currentLevel(){
        return currentLevel;
    }

    public static void setGenerators(List<Generator> generators) {
        _generators = generators;
    }

    // Load all needed resources and populate UserPrototypeManger with resources
    public static void Init(){
        // Sprites
        AssetsReader.loadAssets();
        // Levels
        _levels = LevelReader.loadLevels();
        // Users
        PrototypeRegistry.setPrototype(UserType.ORDINARY,new User(ResourceManagerUser.getSprite("ordinary"),UserType.ORDINARY, Priority.LOW,5));
        PrototypeRegistry.setPrototype(UserType.PREGNANT,new User(ResourceManagerUser.getSprite("pregnant"),UserType.PREGNANT,Priority.MEDIUM,3));
        PrototypeRegistry.setPrototype(UserType.DISABLED,new User(ResourceManagerUser.getSprite("disabled"),UserType.DISABLED,Priority.HIGH,2));


    }

//    public void InitAssets(){
//        generators = new ArrayList<Generator>();
//        var doors = currentLevel.get_doorsList();
//        doors.stream().forEach(door -> {
//            try {
//                generators.add(new WiseGenerator(door));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        try {
//            var prototypeManager = new PrototypeRegistry();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        //GameLoop loop = new GameLoop();
//        //loop.run();
//        generators.stream().forEach(generator -> {
//            var user = generator.generateUser();
//
//        });
//        String assetsFolder = "";
//    }

}
