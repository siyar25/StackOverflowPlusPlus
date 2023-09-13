package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.*;
import com.codecool.stackoverflowtw.dao.model.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StackoverflowTwApplication {
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";

    public static final String dbUserName = System.getenv(USERNAME);
    public static final String dbPassword = System.getenv(PASSWORD);

    Database database = new Database(
            "jdbc:postgresql://localhost:5432/stackoverflow",
            dbUserName,
            dbPassword);

    public static void main(String[] args) {
        SpringApplication.run(StackoverflowTwApplication.class, args);
    }

    @Bean
    public QuestionsDAO questionsDAO() {
        return new QuestionsDaoJdbc(database);
    }

    @Bean
    public UsersDAO usersDAO() {
        return new UsersDaoJdbc(database);
    }
    @Bean
    public AnswersDAO answersDAO() {
        return new AnswersDaoJdbc(database);
    }
}
