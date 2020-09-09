package com.example.quizapp;

public class QuestionBank {

    private Question[] mQuestionBank;

    public  QuestionBank (Question[] questions) {
        this.mQuestionBank = questions;
    }

    boolean isAnswerTrueAt(int mCurrentIndex) {
        return mQuestionBank[mCurrentIndex].isAnswerTrue();
    }

    int totalNumberOfQuestion() {
        return mQuestionBank.length;
    }

    int getQuestionBodyAt(int mCurrentIndex) {
        return mQuestionBank[mCurrentIndex].getTextResId();
    }

    void hideQuestionAt(int mCurrentIndex) {
       Question question = mQuestionBank[mCurrentIndex];
       question.setShouldShow(false);
    }

    void answeredCorrectlyAt(int mCurrentIndex) {
        Question question = mQuestionBank[mCurrentIndex];
        question.setAnsweredCorrectly(true);
    }

    boolean allQuestionsAnswered() {
        for (int i = 0; i < mQuestionBank.length; i++) {
            Question question = mQuestionBank[i];
            if (question.isShouldShow()) {
                return false;
            }
        }
        return true;
    }

    int calculatedPercent() {
        int correctAnswers = 0;
        for (int i = 0; i < mQuestionBank.length; i++) {
         Question question = mQuestionBank[i];
         if (question.isAnsweredCorrectly()) {
             correctAnswers++;
         }

        }
        return (100 / totalNumberOfQuestion()) * correctAnswers;

    }
}
