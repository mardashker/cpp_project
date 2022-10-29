package Moduls.Users;


import java.util.HashMap;
import java.util.Map;

public class PrototypeRegistry {

    private static Map<UserType, User> prototypes = new HashMap<>();

    public void add(User user){
        prototypes.put(user.getType(), user);
        //TODO: додати до мапи
    }

    public User get(UserType type){
        return prototypes.get(type).userClone();
    }

}
