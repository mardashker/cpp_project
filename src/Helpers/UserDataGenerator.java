package Helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDataGenerator {
    private static UserDataGenerator userDataGenerator;
    public static UserDataGenerator getInstance() throws IOException {
        if(userDataGenerator == null){
            userDataGenerator = new UserDataGenerator();
        }
        return userDataGenerator;
    }
    private String fileFirstName = "C:\\Users\\User\\IdeaProjects\\Project\\cpp_project\\src\\resources\\first_name.txt";
    private String fileLastName = "C:\\Users\\User\\IdeaProjects\\Project\\cpp_project\\src\\resources\\last_name.txt";
    private static SplittableRandom  randomizer;

    private static List<String> randomFisrtNameList;
    private static List<String> randomLastNameList;

    private UserDataGenerator() throws IOException {
        randomizer = new SplittableRandom();
        try (Stream<String> lines = Files.lines(Paths.get(fileFirstName))) {
            randomFisrtNameList = lines.collect(Collectors.toList());
        }
        try (Stream<String> lines = Files.lines(Paths.get(fileLastName))) {
            randomLastNameList = lines.collect(Collectors.toList());
        }
    }
    public  String generatePhoneNumber(){
        return "+380" + randomizer.nextInt(999999999);
    }
    public String generatePassportId(){
        return String.valueOf(randomizer.nextInt(999999999));
    }
    public String generateName(){
        return randomFisrtNameList.get(randomizer.nextInt(randomFisrtNameList.size()));
    }
    public String generateLastName(){
        return randomLastNameList.get(randomizer.nextInt(randomLastNameList.size()));
    }

    public int generateAge(){
        return randomizer.nextInt(18,61);
    }
}