package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDateTime;

public class NewQuestion {

    String title;
    String description;

    int user_id;

    public NewQuestion(String title, String description, int user_id) {

        this.title = title;
        this.description = description;
        this.user_id = user_id;
    }



    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    public int getUser_id() {
        return user_id;
    }
}
