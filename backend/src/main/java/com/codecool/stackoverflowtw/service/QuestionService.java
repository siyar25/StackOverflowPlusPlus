package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.*;
import com.codecool.stackoverflowtw.dao.AnswersDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.UsersDAO;
import com.codecool.stackoverflowtw.dao.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionsDAO questionsDAO;
    private final UsersDAO usersDAO;
    private final AnswersDAO answersDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO, UsersDAO usersDAO, AnswersDAO answersDAO) {
        this.questionsDAO = questionsDAO;
        this.usersDAO = usersDAO;
        this.answersDAO = answersDAO;
    }

    public List<QuestionCardDTO> getAllQuestions() {
        return questionsDAO.getAllQuestions().stream().map(q -> new QuestionCardDTO(q.getId(), q.getTitle(), q.getCreated(),
                usersDAO.getUserFromUserId(q.getUser_id()), answersDAO.getAnswerCountByQuestionId(q.getId()),
                q.getUpVoteCount(), q.getDownVoteCount())).toList();
    }

    public QuestionPageDTO getQuestionById(int id) {
        Question question = questionsDAO.getQuestionById(id);
        User user = usersDAO.getUserFromUserId(question.getUser_id());
        List<AnswerDTO> answerDTOList = createAnswerDTOList(answersDAO.getAnswersByQuestionId(id));

        return new QuestionPageDTO(question.getId(), question.getTitle(), question.getDescription(),
                question.getCreated(), user, answerDTOList, question.getUpVoteCount(),
                question.getUpVoteIds(), question.getDownVoteCount(), question.getDownVoteIds());
    }

    public boolean deleteQuestionById(int id) {
        return questionsDAO.deleteQuestionById(id);
    }

    public int addNewQuestion(NewQuestionDTO question) {
        return questionsDAO.addNewQuestion(new NewQuestion(question.title(), question.description(),
                question.user_id()));
    }

    private List<AnswerDTO> createAnswerDTOList(List<Answer> answerList) {
        return answerList.stream().map(answer -> new AnswerDTO(answer.getId(), answer.getAnswer(),
                        usersDAO.getUserFromUserId(answer.getUser_id()), answer.getCreated(),
                        answer.getQuestion_id(), answer.getUpVoteCount(), answer.getUpVoteIds()
                        , answer.getDownVoteCount(), answer.getDownVoteIds()))
                .collect(Collectors.toList());

    }

    public boolean voteToQuestion(QuestionVoteDTO questionVoteDTO) {

        return questionsDAO.vote(new QuestionVote(questionVoteDTO.questionId(), questionVoteDTO.userId(), questionVoteDTO.vote()));
    }
}
