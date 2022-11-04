import Helpers.ConstUserGenerator;
import Helpers.Coordinates;
import Helpers.UserTypeGenerator;
import Helpers.WiseGenerator;
import Moduls.Door;
import Moduls.Users.UserPrototypeManager;
import Moduls.Users.UserType;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        //  ТЕСТ ГЕНЕРАТОРІВ
        var manager = new UserPrototypeManager();
        var wise = new WiseGenerator(new Door(new Coordinates(2,2,45)));
          var standard = new ConstUserGenerator(new Door(new Coordinates(2,2,45)));
        var typeG = new UserTypeGenerator(new Door(new Coordinates(2,2,45)), UserType.PREGNANT);
        System.out.println("Wise");

        for(int i = 0; i < 20; i++){
            System.out.println(wise.generateUser());
        }
        System.out.println("Standard");

        for(int i = 0; i < 20; i++){
            System.out.println(standard.generateUser());
        }
        System.out.println("Type");

        for(int i = 0; i < 20; i++){
            System.out.println(typeG.generateUser());
        }
    }
}