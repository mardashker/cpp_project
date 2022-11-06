package com.example.railwaystation.classes.Logic;

import com.example.railwaystation.classes.Game.GameLevel;

import java.util.List;

public class Game {
    public static final double cell_width = 32;
    public static final double cell_height = 32;
    public static int usersCount;

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
