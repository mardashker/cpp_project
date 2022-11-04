package Moduls.Users;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserPrototypeManager {
    //TODO зробити доступ до ліста через гетер аналог ResourceManagerUser (HashMap)
    public static List<User> basicUsers;
    public UserPrototypeManager() throws IOException {
        basicUsers = new ArrayList<>();
        basicUsers.add(new User(ImageIO.read(new File("cpp_project/src/resources/tree.jpg")), UserType.STANDARD, Priority.LOW, 5));
        basicUsers.add(new User(ImageIO.read(new File("cpp_project/src/resources/tree.jpg")), UserType.PREGNANT, Priority.MEDIUM, 3));
        basicUsers.add(new User(ImageIO.read(new File("cpp_project/src/resources/tree.jpg")), UserType.DISABLED, Priority.HIGH, 1));
    }
}