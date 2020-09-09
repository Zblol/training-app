package com.example.quizapp;

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mShouldShow;
    private boolean mAnsweredCorrectly;


    public Question(int textResId, boolean answerTrue){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mShouldShow = true;
    }

    public boolean isShouldShow() {
        return mShouldShow;
    }

    public void setShouldShow(boolean shouldShow) {
        mShouldShow = shouldShow;
    }

    public boolean isAnsweredCorrectly() {
        return mAnsweredCorrectly;
    }

    public void setAnsweredCorrectly(boolean answeredCorrectly) {
        mAnsweredCorrectly = answeredCorrectly;
    }


    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }



}
