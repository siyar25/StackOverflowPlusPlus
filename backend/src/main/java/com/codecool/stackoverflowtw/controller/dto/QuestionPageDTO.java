package com.codecool.stackoverflowtw.controller.dto;

import com.codecool.stackoverflowtw.dao.model.User;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionPageDTO(int id, String title, String description, LocalDateTime created, User user,
                              List<AnswerDTO> answers, int upVoteCount, int[] upVoteIds, int downVoteCount, int[] downVoteIds) {
}
