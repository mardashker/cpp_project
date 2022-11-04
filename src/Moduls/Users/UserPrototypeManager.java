package Moduls.Users;

import Game.Priority;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserPrototypeManager {
    public static List<User> basicUsers;
    public UserPrototypeManager() throws IOException {
        basicUsers = new ArrayList<>();

        basicUsers.add(new User(ImageIO.read(new File("src/resources/tree.jpg")), UserType.STANDARD, Priority.LOW, 5));
        basicUsers.add(new User(ImageIO.read(new File("src/resources/tree.jpg")), UserType.PREGNANT, Priority.MEDIUM, 3));
        basicUsers.add(new User(ImageIO.read(new File("src/resources/tree.jpg")), UserType.DISABLED, Priority.HIGH, 1));
    }
}