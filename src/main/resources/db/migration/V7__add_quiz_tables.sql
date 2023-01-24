CREATE TABLE question
(
    id                   BIGINT PRIMARY KEY,
    question             TEXT    NOT NULL,
    chapter              TEXT    NOT NULL,
    chapter_id           INT     NOT NULL,
    subchapter_id        INT     NOT NULL,
    course_mapped        BOOLEAN NOT NULL,
    explanation          TEXT    NOT NULL,
    explanation_active   INT     NOT NULL,
    calculator           INT     NOT NULL,
    question_image_id    INT     NOT NULL,
    image_id             INT     NOT NULL,
    image_normal         TEXT    NOT NULL,
    image_large          TEXT    NOT NULL,
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


CREATE TABLE correct_answer_array
(
    question_id BIGINT NOT NULL REFERENCES question (id),
    value       INT    NOT NULL
);
