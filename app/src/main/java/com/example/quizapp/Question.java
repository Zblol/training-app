package com.example.quizapp;

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mShouldShow;
    private boolean mAweredCorrectly;


    public Question(int textResId, boolean answerTrue, boolean shouldShow, boolean answeredCorrectly){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mShouldShow = shouldShow;
        mAweredCorrectly = answeredCorrectly;

    }

    public boolean isShouldShow() {
        return mShouldShow;
    }

    public void setShouldShow(boolean shouldShow) {
        mShouldShow = shouldShow;
    }

    public boolean isAweredCorrectly() {
        return mAweredCorrectly;
    }

    public void setAweredCorrectly(boolean aweredCorrectly) {
        mAweredCorrectly = aweredCorrectly;
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
