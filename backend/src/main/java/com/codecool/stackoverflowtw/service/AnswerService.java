package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.AnswerVoteDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.AnswersDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.UsersDAO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.AnswerVote;
import com.codecool.stackoverflowtw.dao.model.NewAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    private final AnswersDAO answersDAO;
    private final UsersDAO usersDAO;
    private final QuestionsDAO questionsDAO;

    @Autowired
    public AnswerService(AnswersDAO answersDAO, UsersDAO usersDAO, QuestionsDAO questionsDAO) {
        this.answersDAO = answersDAO;
        this.usersDAO = usersDAO;
        this.questionsDAO = questionsDAO;
    }

    public boolean deleteAnswerById(int id) {
        return answersDAO.deleteAnswer(id);
    }

    public AnswerDTO addNewAnswer(NewAnswerDTO newAnswerDTO) {
        Answer answer = answersDAO.createAnswer(new NewAnswer(newAnswerDTO.answer(), newAnswerDTO.question_id(), newAnswerDTO.user_id()));
        return new AnswerDTO(answer.getId(), answer.getAnswer(), usersDAO.getUserFromUserId(answer.getUser_id()),
                answer.getCreated(), answer.getQuestion_id(), answer.getUpVoteCount(), answer.getUpVoteIds(), answer.getDownVoteCount(), answer.getDownVoteIds());
    }

    public boolean voteToAnswer(AnswerVoteDTO answerVoteDTO) {

        return answersDAO.vote(new AnswerVote(answerVoteDTO.vote(),
                answerVoteDTO.userId(), answerVoteDTO.answerId()));
    }

}
