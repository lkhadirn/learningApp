CREATE TABLE question
(
    id                   BIGSERIAL PRIMARY KEY,
    question             TEXT NOT NULL,
    question_id          INT  NOT NULL UNIQUE,
    chapter              TEXT NOT NULL,
    explanation          TEXT,
    image_normal         TEXT,
    image_large          TEXT,
    correct_answer       INT  NOT NULL,
    question_video       TEXT,
    correct_answer_array INT[]

);

CREATE TABLE answer
(
    id    BIGSERIAL PRIMARY KEY,
    key   INT  NOT NULL,
    value TEXT NOT NULL
);

CREATE TABLE question_answers
(
    id          BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL REFERENCES question (id),
    answers_id  BIGINT NOT NULL REFERENCES answer (id)
);