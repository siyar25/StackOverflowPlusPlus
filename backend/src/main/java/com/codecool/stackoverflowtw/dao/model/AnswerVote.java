package com.codecool.stackoverflowtw.dao.model;

public class AnswerVote {

    boolean vote;
    int user_id;
    int answer_id;

    public AnswerVote(boolean vote, int user_id, int answer_id) {
        this.vote = vote;
        this.user_id = user_id;
        this.answer_id = answer_id;
    }

    public boolean isVote() {
        return vote;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getAnswer_id() {
        return answer_id;
    }
}
