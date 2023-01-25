UPDATE public.question
SET answers = (
    SELECT json_agg(
                   json_build_object(
                           'key', public.answer.key,
                           'value', public.answer.value
                       )
               )
    FROM public.question_answers
             JOIN public.answer ON public.answer.id = public.question_answers.answers_id
    WHERE public.question_answers.question_id = public.question.id
);