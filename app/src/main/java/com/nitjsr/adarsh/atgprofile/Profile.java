package com.nitjsr.adarsh.atgprofile;

public class Profile {
    String name;
    String location;
    String age;
    String online;
    String status;
    String interestList[];

    public Profile() {
    }

    public Profile(String name, String location, String age, String online, String status, String[] interestList) {
        this.name = name;
        this.location = location;
        this.age = age;
        this.online = online;
        this.status = status;
        this.interestList = interestList;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getAge() {
        return age;
    }

    public String getOnline() {
        return online;
    }

    public String getStatus() {
        return status;
    }

    public String[] getInterestList() {
        return interestList;
    }
}
