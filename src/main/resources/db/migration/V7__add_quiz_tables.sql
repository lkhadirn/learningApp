CREATE TABLE question
(
    id                   BIGINT PRIMARY KEY,
    question             TEXT    NOT NULL,
    question_type        INT     NOT NULL,
    chapter              TEXT    NOT NULL,
    chapter_id           INT     NOT NULL,
    subchapter_id        INT     NOT NULL,
    course_mapped        BOOLEAN NOT NULL,
    explanation          TEXT    NOT NULL,
    explanation_active   INT     NOT NULL,
    calculator           INT     NOT NULL,
    user_answer          INT     NOT NULL,
    question_image_id    INT     NOT NULL,
    image_id             INT     NOT NULL,
    image_normal         TEXT    NOT NULL,
    image_large          TEXT    NOT NULL,
    answered             BOOLEAN NOT NULL,
    already_answered     BOOLEAN NOT NULL,
    server_saved         BOOLEAN NOT NULL,
    explanation_image_id INT,
    explanation_video_id INT,
    correct_answer       INT     NOT NULL,
    video_id             INT,
    image_modifiers      TEXT    NOT NULL
);

CREATE TABLE answer
(
    id    BIGSERIAL PRIMARY KEY,
    key   INT  NOT NULL,
    value TEXT NOT NULL
);

CREATE TABLE question_answers
(
    question_id BIGINT NOT NULL REFERENCES question (id),
    answer_id   BIGINT NOT NULL REFERENCES answer (id)
);

CREATE TABLE answerbit
(
    question_id BIGINT NOT NULL REFERENCES question (id),
    bitstr      INT    NOT NULL,
    type        TEXT   NOT NULL
);

CREATE TABLE correct_answer_array
(
    question_id BIGINT NOT NULL REFERENCES question (id),
    value       INT    NOT NULL
);

CREATE TABLE user_answer_array
(
    question_id BIGINT NOT NULL REFERENCES question (id),
    value       INT    NOT NULL
);

CREATE TABLE question_video_image
(
    id          BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL REFERENCES question (id),
    src         TEXT   NOT NULL
);

CREATE TABLE question_question_video_image
(
    question_id             BIGINT NOT NULL REFERENCES question (id),
    question_video_image_id BIGINT NOT NULL REFERENCES question_video_image (id)
);
