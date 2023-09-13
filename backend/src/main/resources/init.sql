DROP TABLE IF EXISTS questions CASCADE;
DROP TABLE IF EXISTS answers CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS questionVotes CASCADE;
DROP TABLE IF EXISTS answerVotes CASCADE;

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255) UNIQUE,
    password VARCHAR(80),
    colorhex VARCHAR(80),
        registration TIMESTAMP WITHOUT TIME ZONE,
    is_admin BOOLEAN DEFAULT false
);

CREATE TABLE questions
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(80),
    description VARCHAR(255),
    created     TIMESTAMP WITHOUT TIME ZONE,
    user_id     INT,
    FOREIGN KEY (user_id)
        REFERENCES users (id)
);

CREATE TABLE answers
(
    id          SERIAL PRIMARY KEY,
    answer      VARCHAR(255),
    created     TIMESTAMP WITHOUT TIME ZONE,
    question_id INT,
    user_id     INT,
    FOREIGN KEY (question_id)
        REFERENCES questions (id),
    FOREIGN KEY (user_id)
        REFERENCES users (id)
);
CREATE TABLE questionVotes
(
    question_id  INT     NOT NULL,
    user_id      INT     NOT NULL,
    questionVote boolean NOT NULL,
    PRIMARY KEY (question_id, user_id),
    FOREIGN KEY (question_id) REFERENCES questions (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE TABLE answerVotes
(
    answer_id  INT     NOT NULL,
    user_id    INT     NOT NULL,
    answerVote boolean NOT NULL,
    PRIMARY KEY (answer_id, user_id),
    FOREIGN KEY (answer_id) REFERENCES answers (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO users (name, password, registration, colorhex)
VALUES ('Siyar', 'tevedurumteve', localtimestamp(2),'#4caf50');
INSERT INTO users (name, password, registration,colorhex)
VALUES ('Zoli', 'taborialpha', localtimestamp(2), '#f44336');
INSERT INTO users (name, password, registration,colorhex)
VALUES ('Dénes', 'questionman', localtimestamp(2), '#00bcd4');
INSERT INTO users (name, password, registration, colorhex, is_admin)
VALUES ('Marci', '1_10_11_100', localtimestamp(2),'#ff9800', true);
INSERT INTO users (name, password, registration, colorhex)
VALUES ('Réka', 'bubumaci', localtimestamp(2), '#795548');


INSERT INTO questions (title, description, created, user_id)
VALUES ('Are camels polyamouros?',
        'Hi guys, I recently fell in love with a camel and i was wondering if it was possible for him to return my love and affection.',
        localtimestamp, 1);
INSERT INTO questions (title, description, created, user_id)
VALUES ('Wath is the best OOP? With Regards, D.', 'Description.', localtimestamp, 3);
INSERT INTO questions (title, description, created, user_id)
VALUES ('Hogy mondanád ezt? Mi érzi a kapcsolatokat?', 'Csak kérdem.', localtimestamp, 3);
INSERT INTO questions (title, description, created, user_id)
VALUES ('Hogyan kell? Tudod törülni a kérdést?', 'Csak kérdem.',
        localtimestamp,
        3);

INSERT INTO answers (answer, created, question_id, user_id)
VALUES ('No.', localtimestamp, 1, 2);
INSERT INTO answers (answer, created, question_id, user_id)
VALUES ('I have made out with a camel already, but I might have dreamed that.', localtimestamp, 1, 4);
INSERT INTO answers (answer, created, question_id, user_id)
VALUES ('Check out this juicy video about it!', localtimestamp, 2, 3);
INSERT INTO answers (answer, created, question_id, user_id)
VALUES ('...', localtimestamp, 3, 5);
INSERT INTO answers (answer, created, question_id, user_id)
VALUES ('...', localtimestamp, 4, 5);
