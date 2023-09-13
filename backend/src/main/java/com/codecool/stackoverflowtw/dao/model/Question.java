package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDateTime;

public class Question {
    int id;
    String title;
    String description;
    LocalDateTime created;
    int user_id;
    int upVoteCount;
    int[] upVoteIds;
    int downVoteCount;
    int[] downVoteIds;

    public Question(int id, String title, String description, LocalDateTime created, int user_id, int upVoteCount, int[] upVoteIds, int downVoteCount, int[] downVoteIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created = created;
        this.user_id = user_id;
        this.upVoteCount = upVoteCount;
        this.upVoteIds = upVoteIds;
        this.downVoteCount = downVoteCount;
        this.downVoteIds = downVoteIds;
    }

    public int[] getUpVoteIds() {
        return upVoteIds;
    }

    public int[] getDownVoteIds() {
        return downVoteIds;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getUpVoteCount() {
        return upVoteCount;
    }

    public int getDownVoteCount() {
        return downVoteCount;
    }
}
