package com.example.railwaystation.classes.Moduls.Users;

import com.example.railwaystation.classes.Rendering.ResourceManagerUser;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserPrototypeManager {
    //TODO зробити доступ до ліста через гетер аналог ResourceManagerUser (HashMap)
    //public static List<User> basicUsers;
    public UserPrototypeManager() throws IOException {
        basicUsers.put(UserType.ORDINARY,new User(ResourceManagerUser.getSprite("ordinary"),UserType.ORDINARY,Priority.LOW,5));
        basicUsers.put(UserType.PREGNANT,new User(ResourceManagerUser.getSprite("pregnant"),UserType.PREGNANT,Priority.MEDIUM,3));
        basicUsers.put(UserType.DISABLED,new User(ResourceManagerUser.getSprite("disabled"),UserType.DISABLED,Priority.HIGH,2));

    }
    private static Map<UserType, User> basicUsers = new HashMap<>();

    public static User getPrototype(UserType type){
        return basicUsers.get(type);
    }
    public static void setPrototype(UserType type, User user){
        basicUsers.put(type, user);
    }
}