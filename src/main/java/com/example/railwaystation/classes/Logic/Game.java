package com.example.railwaystation.classes.Logic;

import com.example.railwaystation.classes.Game.AssetsReader;
import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Game.LevelReader;
import com.example.railwaystation.classes.Game.QueuePoligon;
import com.example.railwaystation.classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Helpers.Star.DoorPolygonResolver;
import com.example.railwaystation.classes.Helpers.Star.Node;
import com.example.railwaystation.classes.Helpers.WiseGenerator;
import com.example.railwaystation.classes.Interfaces.Generator;
import com.example.railwaystation.classes.Moduls.Door;
import com.example.railwaystation.classes.Moduls.OurQueue;
import com.example.railwaystation.classes.Moduls.Users.*;
import com.example.railwaystation.classes.Rendering.ResourceManagerUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Game {
    public static final double cell_width = 20;
    public static final double cell_height = 25;
    public static int usersCount;
    private static List<Generator> _generators;

    public static HashMap<Coordinates, HashMap<Coordinates, List<Node>>> resolver;

    private static OurQueue queueToShow = null;

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

    public static void showQueueDetails(OurQueue queue){
        Game.queueToShow = queue;
        //TODO: actions to show queue detailed info
    }
}
