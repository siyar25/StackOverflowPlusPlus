package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDateTime;

public class Answer {
    int id;
    String answer;
    LocalDateTime created;
    int question_id;
    int user_id;
    int upVoteCount;
    int[] upVoteIds;
    int downVoteCount;
    int[] downVoteIds;

    public Answer(int id, String answer, LocalDateTime created, int question_id, int user_id, int upVoteCount, int[] upVoteIds, int downVoteCount, int[] downVoteIds) {
        this.id = id;
        this.answer = answer;
        this.created = created;
        this.question_id = question_id;
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

    public int getUpVoteCount() {
        return upVoteCount;
    }

    public int getDownVoteCount() {
        return downVoteCount;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public int getUser_id() {
        return user_id;
    }
}
