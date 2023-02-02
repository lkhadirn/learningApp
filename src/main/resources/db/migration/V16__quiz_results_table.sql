CREATE TABLE quiz_results
(
    id          SERIAL PRIMARY KEY,
    user_id     BIGINT  NOT NULL,
    question_id BIGINT  NOT NULL,
    correct     BOOLEAN NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_account (id),
    FOREIGN KEY (question_id) REFERENCES question (id)
);
