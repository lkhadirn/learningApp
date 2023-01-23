package com.example.testtemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Question {

    @Id
    private Long id;

    private String question;

    @JsonProperty("question_type")
    private int questionType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers;

    @Embedded
    private Answerbit answerbit;

    @ElementCollection
    @JsonProperty("correct_answer_array")
    private List<Integer> correctAnswerArray;

    private String chapter;

    @JsonProperty("chapter_id")
    private int chapterId;

    @JsonProperty("subchapter_id")
    private int subchapterId;

    @JsonProperty("course_mapped")
    private boolean courseMapped;

    private String explanation;

    @JsonProperty("explanation_active")
    private int explanationActive;

    private int calculator;

    @JsonProperty("user_answer")
    private int userAnswer;

    @ElementCollection
    private List<Integer> userAnswerArray;

    private int questionImageId;

    private String IMAGE_NORMAL;

    private String IMAGE_LARGE;

    private boolean answered;

    private boolean alreadyAnswered;

    private boolean serverSaved;

    private int explanationImageId;

    private int explanationVideoId;

    private int correctAnswer;

    private int videoId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "question_question_video_image",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "question_video_image_id"))
    private List<QuestionVideoImage> questionVideoImages;

    private String imageModifiers;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answerbit getAnswerbit() {
        return answerbit;
    }

    public void setAnswerbit(Answerbit answerbit) {
        this.answerbit = answerbit;
    }

    public List<Integer> getCorrectAnswerArray() {
        return correctAnswerArray;
    }

    public void setCorrectAnswerArray(List<Integer> correctAnswerArray) {
        this.correctAnswerArray = correctAnswerArray;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getSubchapterId() {
        return subchapterId;
    }

    public void setSubchapterId(int subchapterId) {
        this.subchapterId = subchapterId;
    }

    public boolean isCourseMapped() {
        return courseMapped;
    }

    public void setCourseMapped(boolean courseMapped) {
        this.courseMapped = courseMapped;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getExplanationActive() {
        return explanationActive;
    }

    public void setExplanationActive(int explanationActive) {
        this.explanationActive = explanationActive;
    }

    public int getCalculator() {
        return calculator;
    }

    public void setCalculator(int calculator) {
        this.calculator = calculator;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }

    public List<Integer> getUserAnswerArray() {
        return userAnswerArray;
    }

    public void setUserAnswerArray(List<Integer> userAnswerArray) {
        this.userAnswerArray = userAnswerArray;
    }



    public int getQuestionImageId() {
        return questionImageId;
    }

    public void setQuestionImageId(int questionImageId) {
        this.questionImageId = questionImageId;
    }

    public String getIMAGE_NORMAL() {
        return IMAGE_NORMAL;
    }

    public void setIMAGE_NORMAL(String IMAGE_NORMAL) {
        this.IMAGE_NORMAL = IMAGE_NORMAL;
    }

    public String getIMAGE_LARGE() {
        return IMAGE_LARGE;
    }

    public void setIMAGE_LARGE(String IMAGE_LARGE) {
        this.IMAGE_LARGE = IMAGE_LARGE;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public boolean isAlreadyAnswered() {
        return alreadyAnswered;
    }

    public void setAlreadyAnswered(boolean alreadyAnswered) {
        this.alreadyAnswered = alreadyAnswered;
    }

    public boolean isServerSaved() {
        return serverSaved;
    }

    public void setServerSaved(boolean serverSaved) {
        this.serverSaved = serverSaved;
    }

    public int getExplanationImageId() {
        return explanationImageId;
    }

    public void setExplanationImageId(int explanationImageId) {
        this.explanationImageId = explanationImageId;
    }

    public int getExplanationVideoId() {
        return explanationVideoId;
    }

    public void setExplanationVideoId(int explanationVideoId) {
        this.explanationVideoId = explanationVideoId;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public List<QuestionVideoImage> getQuestionVideoImages() {
        return questionVideoImages;
    }

    public void setQuestionVideoImages(List<QuestionVideoImage> questionVideoImages) {
        this.questionVideoImages = questionVideoImages;
    }

    public String getImageModifiers() {
        return imageModifiers;
    }

    public void setImageModifiers(String imageModifiers) {
        this.imageModifiers = imageModifiers;
    }
}
