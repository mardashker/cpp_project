package com.example.railwaystation.classes.Moduls.Users;

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

    public static Map<UserType, User> getBasicUsers() {
        return basicUsers;
    }

    public static void setBasicUsers(Map<UserType, User> basicUsers) {
        PrototypeRegistry.basicUsers = basicUsers;
    }
}