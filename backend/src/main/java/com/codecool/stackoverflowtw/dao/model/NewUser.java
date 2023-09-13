package com.codecool.stackoverflowtw.dao.model;

public class NewUser {

    String name;
    String password;

    String colorHex;

    public NewUser(String name, String password, String colorHex) {
        this.name = name;
        this.password = password;
        this.colorHex = colorHex;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getColorHex() {
        return colorHex;
    }
}
