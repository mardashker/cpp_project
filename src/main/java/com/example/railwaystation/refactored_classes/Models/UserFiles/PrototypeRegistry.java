package com.example.railwaystation.refactored_classes.Models.UserFiles;

import com.example.railwaystation.classes.Moduls.Users.User;

import java.util.HashMap;
import java.util.Map;


public class PrototypeRegistry {
    private static Map<UserType, User> basicUsers = new HashMap<>();

    public static User getPrototype(UserType type){
        return basicUsers.get(type);
    }
    public static void setPrototype(UserType type, User user){
        basicUsers.put(type, user);
    }
}