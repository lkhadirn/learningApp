-- First, create a new column in the answer table to store the question_id
ALTER TABLE answer ADD COLUMN question_id INTEGER;

-- Next, copy the data from the existing question_answers table into the new question_id column
UPDATE answer SET question_id = question_answers.question_id
FROM question_answers
WHERE answer.id = question_answers.answers_id;

-- Then, create a foreign key constraint to ensure referential integrity
ALTER TABLE answer ADD CONSTRAINT fk_question_id FOREIGN KEY (question_id) REFERENCES question(id);

-- Finally, drop the old question_answers table
DROP TABLE question_answers;
