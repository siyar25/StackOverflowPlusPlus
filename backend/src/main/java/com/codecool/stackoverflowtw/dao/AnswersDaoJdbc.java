package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.AnswerVote;
import com.codecool.stackoverflowtw.dao.model.Database;
import com.codecool.stackoverflowtw.dao.model.NewAnswer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswersDaoJdbc implements AnswersDAO {
    Database database;

    public AnswersDaoJdbc(Database database) {
        this.database = database;
    }

    @Override
    public int getAnswerCountByQuestionId(int questionId) {
        String template = "SELECT count(id) as result FROM answers WHERE question_id = ? GROUP BY question_id;";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("result");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<Answer> getAnswersByQuestionId(int questionId) {
        String template = "SELECT * FROM answers WHERE question_id = ?";
        List<Answer> answers = new ArrayList<>();
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                answers.add(new Answer(
                        resultSet.getInt("id"),
                        resultSet.getString("answer"),
                        resultSet.getTimestamp("created").toLocalDateTime(),
                        resultSet.getInt("question_id"),
                        resultSet.getInt("user_id"),
                        getUpvoteCount(resultSet.getInt(1)),
                        getUpvoteUserIds(resultSet.getInt(1)),
                        getDownVoteCount(resultSet.getInt(1)),
                        getDownVoteUserIds(resultSet.getInt(1))

                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answers;
    }

    public Answer getAnswerById(int id) {
        String template = "SELECT * FROM answers WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Answer(
                        resultSet.getInt("id"),
                        resultSet.getString("answer"),
                        resultSet.getTimestamp("created").toLocalDateTime(),
                        resultSet.getInt("question_id"),
                        resultSet.getInt("user_id"),
                        getUpvoteCount(id),
                        getUpvoteUserIds(id),
                        getDownVoteCount(id),
                        getDownVoteUserIds(id)
                );
            }
        } catch (SQLException e) {
            System.out.println("Answer not found. Error message:");
            System.out.println(e.getErrorCode());
        }
        return null;
    }

    @Override
    public Answer createAnswer(NewAnswer newAnswer) {
        String template = "INSERT INTO answers (answer, created, question_id, user_id) VALUES (?, localtimestamp, ?, ?) RETURNING id";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setString(1, newAnswer.getAnswer());
            statement.setInt(2, newAnswer.getQuestion_id());
            statement.setInt(3, newAnswer.getUser_id());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getAnswerById(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to insert answer. Error message:");
            System.out.println(e.getSQLState());
        }
        return null;
    }

    @Override
    public boolean deleteAnswer(int id) {
        String template = "DELETE FROM answers WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            return false;
        }
    }

    @Override
    public boolean deleteAnswersByUserId(int user_id) {
        String template = "DELETE FROM answers WHERE user_id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, user_id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            return false;
        }
    }

    @Override
    public boolean vote(AnswerVote answerVote) {

        String template = "INSERT INTO answervotes (answer_id, user_id, answervote)\n" +
                "VALUES (?, ?, ?)\n" +
                "ON CONFLICT (answer_id, user_id) DO UPDATE SET answervote = EXCLUDED.answervote;";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {

            statement.setInt(1, answerVote.getAnswer_id());
            statement.setInt(2, answerVote.getUser_id());
            statement.setBoolean(3, answerVote.isVote());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getUpvoteCount(int id) {
        String template =
                "SELECT answer_id," +
                        " COUNT(CASE WHEN answervote = true THEN 1 END) as upvotes " +
                        " FROM answervotes GROUP BY answer_id;";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) == id) {
                    return resultSet.getInt("upvotes");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    private int[] getUpvoteUserIds(int id) {
        String template =
                "SELECT user_id " +
                        "FROM answervotes WHERE answer_id = ? AND answervote = true";
        List<Integer> upvotersId = new ArrayList<>();
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                upvotersId.add(resultSet.getInt("user_id"));
            }
            return upvotersId.stream().mapToInt(Integer::intValue).toArray();
        } catch (SQLException e) {
            System.out.println("Couldnt get upvoter ids");
            System.out.println(e.getMessage());
        }
        return null;
    }

    private int getDownVoteCount(int id) {
        String template =
                "SELECT answer_id," +
                        " COUNT(CASE WHEN answervote = false THEN 1 END) as downvotes " +
                        " FROM answervotes GROUP BY answer_id;";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) == id) {
                    return resultSet.getInt("downvotes");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    private int[] getDownVoteUserIds(int id) {
        String template =
                "SELECT user_id " +
                        "FROM answervotes WHERE answer_id = ? AND answervote = false";
        List<Integer> downvotersId = new ArrayList<>();
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                downvotersId.add(resultSet.getInt("user_id"));
            }
            return downvotersId.stream().mapToInt(Integer::intValue).toArray();
        } catch (SQLException e) {
            System.out.println("Couldnt get downvoter ids");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
