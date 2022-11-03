package com.example.railwaystation.classes.Rendering;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

public class ResourceManagerUser {
    static Map<String, Image> sprites = new HashMap<>();
    public Image getSprite(String key){
        return sprites.get(key);
    }
    public void setSprite(String key, Image sprite){
        sprites.put(key, sprite);
    }
}
