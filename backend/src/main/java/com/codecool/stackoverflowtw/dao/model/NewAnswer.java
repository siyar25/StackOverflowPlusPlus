package com.codecool.stackoverflowtw.dao.model;

public class NewAnswer {
    private String answer;
    private int question_id;
    private int user_id;

    public NewAnswer(String answer, int question_id, int user_id) {
        this.answer = answer;
        this.question_id = question_id;
        this.user_id = user_id;
    }

    public String getAnswer() {
        return answer;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public int getUser_id() {
        return user_id;
    }
}
