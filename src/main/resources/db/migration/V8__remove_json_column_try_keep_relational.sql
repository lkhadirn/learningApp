-- only 1 correct answer, scalar value in correct_answer sufficient
ALTER TABLE public.question
    DROP COLUMN IF EXISTS answers;

