package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.NewUser;
import com.codecool.stackoverflowtw.dao.model.User;

import java.util.List;

public interface UsersDAO {
    List<User> getAllUsers();
    User getUserFromUserId(int id);
    User getUserByNameAndPassword(NewUser newUser);
    int countQuestionsByUser(int userId);
    int countAnswersByUser(int userId);
    int createUser(NewUser user);
    boolean deleteUser(int id);
    int[] getQuestionIdsByUser(int id);
    int[] getAnswersIdsByUser(int id);

}
