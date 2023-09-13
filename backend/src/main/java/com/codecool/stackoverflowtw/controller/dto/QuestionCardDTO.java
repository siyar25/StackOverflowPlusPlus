package com.codecool.stackoverflowtw.controller.dto;

import com.codecool.stackoverflowtw.dao.model.User;

import java.time.LocalDateTime;

public record QuestionCardDTO(int id, String title, LocalDateTime created, User user, int answerCount,
                              int upVoteCount, int downVoteCount) {}
