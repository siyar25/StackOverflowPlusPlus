package com.codecool.stackoverflowtw.dao.model;

public class QuestionVote {
    boolean vote;
    int user_id;
    int question_id;

    public QuestionVote(int question_id, int user_id, boolean vote) {
        this.vote = vote;
        this.user_id = user_id;
        this.question_id = question_id;
    }

    public boolean isVote() {
        return vote;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getQuestion_id() {
        return question_id;
    }
}
