package com.example.railwaystation.classes.Rendering;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ResourceManagerCashRegister {
    private static final Map<Integer, Image> sprites = new HashMap<>();
    public Image getSprite(Integer key){
        return sprites.get(key);
    }
    public void setSprite(Integer key, Image sprite){
        sprites.put(key, sprite);
    }
}
