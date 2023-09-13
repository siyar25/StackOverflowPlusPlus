package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDateTime;

public class User {
    int id;
    String name;
    String colorhex;
    LocalDateTime registrationDate;
    boolean isAdmin;

    public User(int id, String name, String colorHex, LocalDateTime registrationDate, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.colorhex = colorHex;
        this.registrationDate = registrationDate;
        this.isAdmin = isAdmin;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColorhex() {
        return colorhex;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
