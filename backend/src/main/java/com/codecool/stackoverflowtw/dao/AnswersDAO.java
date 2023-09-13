package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.AnswerVote;
import com.codecool.stackoverflowtw.dao.model.NewAnswer;

import java.util.List;

public interface AnswersDAO {
    int getAnswerCountByQuestionId(int questionId);
    List<Answer> getAnswersByQuestionId(int questionId);
    Answer getAnswerById(int id);
    Answer createAnswer(NewAnswer newAnswer);
    boolean deleteAnswer(int id);
    boolean deleteAnswersByUserId(int user_id);
    boolean vote(AnswerVote answerVote);
}
