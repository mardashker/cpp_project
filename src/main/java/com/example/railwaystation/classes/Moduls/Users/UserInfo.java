package com.example.railwaystation.classes.Moduls.Users;

public class UserInfo {
    private String name;
    private String surname;
    private int age;
    private String passportId;
    private String phoneNumber;

    public UserInfo(){}

    public UserInfo(String name, String surname, int age, String passportId, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.passportId = passportId;
        this.phoneNumber = phoneNumber;
    }

}
