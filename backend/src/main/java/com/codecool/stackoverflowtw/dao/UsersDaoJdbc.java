package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Database;
import com.codecool.stackoverflowtw.dao.model.NewUser;
import com.codecool.stackoverflowtw.dao.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoJdbc implements UsersDAO {
    Database database;

    public UsersDaoJdbc(Database database) {
        this.database = database;
    }

    @Override
    public List<User> getAllUsers() {
        String template = "SELECT id, name, colorhex, registration, is_admin FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement(template)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("colorhex"),
                        resultSet.getTimestamp(
                                "registration").toLocalDateTime(), resultSet.getBoolean("is_admin")));
            }
            return users;
        } catch (SQLException e) {
            System.out.println("Could not get the users.\n");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User getUserFromUserId(int id) {
        String template = "SELECT id, name, colorhex, registration, is_admin FROM users WHERE id = ?";

        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(id, resultSet.getString("name"),
                        resultSet.getString("colorhex"), resultSet.getTimestamp("registration").toLocalDateTime(),
                        resultSet.getBoolean(
                                "is_admin"));
            } else {
                System.out.println("No user with that id.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Could not get the user with id: " + id + "\n");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User getUserByNameAndPassword(NewUser newUser) {
        String template = "SELECT id, name, colorhex, registration, password, is_admin FROM users WHERE name = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setString(1, newUser.getName());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString("password").equals(newUser.getPassword())) {
                    return new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString(
                            "colorhex"), resultSet.getTimestamp("registration").toLocalDateTime(), resultSet.getBoolean("is_admin"));
                }
                System.out.println("Password does not match.");
            } else {
                System.out.println("No user with that id.");
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Could not get the user with name: " + newUser.getName() + "\n");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int countQuestionsByUser(int userId) {
        String template = "SELECT COUNT(id) AS questionCount FROM questions WHERE user_id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                System.out.println("There was a problem with counting the questions of user: " + userId);
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("There was a problem with counting the questions of user: " + userId + "\n");
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int countAnswersByUser(int userId) {
        String template = "SELECT COUNT(id) AS answerCount FROM answers WHERE user_id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                System.out.println("There was a problem with counting the answers of user: " + userId);
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("There was a problem with counting the answers of user: " + userId + "\n");
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int createUser(NewUser user) {
        String template = "INSERT INTO users (name, password, colorhex, registration) " +
                "VALUES (?, ?, ?, localtimestamp(2)) RETURNING id";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getColorHex());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            } else {
                System.out.println("Could not create this user.");
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("There was a problem creating this user.\n");
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean deleteUser(int id) {
        String template = "DELETE FROM users WHERE id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("There was a problem deleting this user.\n");
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public int[] getAnswersIdsByUser(int userId) {
        List<Integer> userAnswers = new ArrayList<>();
        String template = "SELECT id FROM answers WHERE user_id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userAnswers.add(resultSet.getInt("id"));
            }
            return userAnswers.stream().mapToInt(Integer::intValue).toArray();
        } catch (SQLException e) {
            System.out.println("There was a problem with getting the answerids of user: " + userId + "\n");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int[] getQuestionIdsByUser(int userId) {
        List<Integer> userQuestions = new ArrayList<>();
        String template = "SELECT id FROM questions WHERE user_id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userQuestions.add(resultSet.getInt("id"));
            }
            return userQuestions.stream().mapToInt(Integer::intValue).toArray();
        } catch (SQLException e) {
            System.out.println("There was a problem with getting the questions ids of user: " + userId + "\n");
            System.out.println(e.getMessage());
            return null;
        }
    }

}
