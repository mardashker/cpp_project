package com.example.railwaystation.classes.Helpers;

import com.example.railwaystation.classes.Moduls.Ticket;
import com.example.railwaystation.classes.Moduls.TicketType;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;
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

    private static SplittableRandom  randomizer;

    private static List<String> randomFisrtNameList;
    private List<String> randomCityList;
    private static List<String> randomLastNameList;

    private UserDataGenerator() throws IOException {
        randomizer = new SplittableRandom();
        File fileFirstName = new File("src/main/resources/com/example/railwaystation/generatedData/first_name.txt");
        File fileLastName = new File("src/main/resources/com/example/railwaystation/generatedData/last_name.txt");
        File fileCities = new File("src/main/resources/com/example/railwaystation/generatedData/city.txt");
        try (Stream<String> lines = Files.lines(fileFirstName.toPath())){
            randomFisrtNameList = lines.collect(Collectors.toList());
        }
        try (Stream<String> lines = Files.lines(fileLastName.toPath())) {
            randomLastNameList = lines.collect(Collectors.toList());
        }
        try (Stream<String> lines = Files.lines(fileCities.toPath())) {
            randomCityList = lines.collect(Collectors.toList());
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
    public List<Ticket> generateTickets(){
        var tickets = new ArrayList<Ticket>();
        for(int i = randomizer.nextInt(1,5); i > 0; i--){
            tickets.add(new Ticket(TicketType.values()[randomizer.nextInt(TicketType.values().length)],randomizer.nextInt(100,1000),randomCityList.get(randomizer.nextInt(randomCityList.size())),randomDate(LocalDate.now(),12)));
        }
        return tickets;
    }
    private LocalDate randomDate(LocalDate start, int months) {
        LocalDate end = start.plusMonths(months);
        int days = (int) ChronoUnit.DAYS.between(start, end);
        return start.plusDays(randomizer.nextInt(days + 1));
    }
}