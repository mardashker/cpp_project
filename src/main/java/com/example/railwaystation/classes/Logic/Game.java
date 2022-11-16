package com.example.railwaystation.classes.Logic;

import com.example.railwaystation.classes.Game.AssetsReader;
import com.example.railwaystation.classes.Game.GameLevel;
import com.example.railwaystation.classes.Game.LevelReader;
import com.example.railwaystation.refactored_classes.Helpers.Coordinates;
import com.example.railwaystation.classes.Helpers.Star.Node;
import com.example.railwaystation.refactored_classes.Interfaces.Generator;
import com.example.railwaystation.refactored_classes.Models.Door;
import com.example.railwaystation.refactored_classes.Models.UserFiles.OurQueue;
import com.example.railwaystation.refactored_classes.Rendering.ResourceManagerUser;
import com.example.railwaystation.refactored_classes.Models.UserFiles.Priority;
import com.example.railwaystation.refactored_classes.Models.UserFiles.PrototypeRegistry;
import com.example.railwaystation.classes.Moduls.Users.User;
import com.example.railwaystation.refactored_classes.Models.UserFiles.UserType;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Game {


    public static int usersCount;
    private static int maxUserCount;


    private static List<Generator> _generators;

    public static HashMap<Door, HashMap<Coordinates, List<Node>>> resolver;

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
    public static int get_currentLevelCell_Size(){
        return currentLevel.getCell_size();
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
        PrototypeRegistry.setPrototype(UserType.ORDINARY,new User(ResourceManagerUser.getSprite("ordinary"),UserType.ORDINARY, Priority.LOW));
        PrototypeRegistry.setPrototype(UserType.PREGNANT,new User(ResourceManagerUser.getSprite("pregnant"),UserType.PREGNANT,Priority.MEDIUM));
        PrototypeRegistry.setPrototype(UserType.DISABLED,new User(ResourceManagerUser.getSprite("disabled"),UserType.DISABLED,Priority.HIGH));
    }

    public static void showQueueDetails(OurQueue queue){
        Game.queueToShow = queue;
    }
    public static String getShowQueueDetailsString(OurQueue queue){
        Game.queueToShow = queue;
        return Game.queueToShow.toString();

    }
    public static int getMaxUserCount() {
        return maxUserCount;
    }

    public static void setMaxUserCount(int maxUserCount) {
        Game.maxUserCount = maxUserCount;
    }

    public static int getUsersCount() {
        return usersCount;
    }

    public static void setUsersCount(int usersCount) {
        Game.usersCount = usersCount;
    }
    public static OurQueue getQueueToShow() {
        return queueToShow;
    }

    public static void setQueueToShow(OurQueue queueToShow) {
        Game.queueToShow = queueToShow;
    }

    public static void setCurrentLevel(GameLevel currentLevel) {
        Game.currentLevel = currentLevel;
    }
}
