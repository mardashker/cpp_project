package com.example.railwaystation.classes.Logic;

import com.example.railwaystation.classes.Game.GameLevel;

import java.util.List;

public class Game {

    public static final double cell_width = 32;
    public static final double cell_height = 32;
    private List<GameLevel> levels;

    private GameLevel currentLevel;

    public GameLevel get_currentLevel(){
        return currentLevel;
    }

    //TODO: підтягування текстур, завантаження початкових даних
    private void InitAssets(){
        String assetsFolder = "";

    }

}
