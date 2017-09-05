package com.vdovenkov.alexander.quiz;

public class Question {
    private int questionResId;
    private boolean answerIsTrue;

    public Question(int questionResId, boolean answerIsTrue) {
        this.questionResId = questionResId;
        this.answerIsTrue = answerIsTrue;
    }

    public int getQuestionResId() {
        return questionResId;
    }

    public void setQuestionResId(int questionResId) {
        this.questionResId = questionResId;
    }

    public boolean isAnswerIsTrue() {
        return answerIsTrue;
    }

    public void setAnswerIsTrue(boolean answerIsTrue) {
        this.answerIsTrue = answerIsTrue;
    }
}
