-- only 1 correct answer, scalar value in correct_answer sufficient
ALTER TABLE public.question
    DROP COLUMN correct_answer_array;

