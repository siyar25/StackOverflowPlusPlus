package com.codecool.stackoverflowtw.controller.dto;

public record NewAnswerDTO(String answer, int question_id, int user_id, int upVoteCount, int downVoteCount) {
}
