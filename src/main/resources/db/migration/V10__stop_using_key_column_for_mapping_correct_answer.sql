ALTER TABLE public.answer ADD COLUMN is_correct BOOLEAN;

UPDATE public.answer
SET is_correct = TRUE
FROM public.question
WHERE public.answer.question_id = public.question.id
  AND public.answer.key = public.question.correct_answer;

UPDATE public.answer
SET is_correct = FALSE
WHERE is_correct IS NULL;
