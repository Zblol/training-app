package com.example.quizapp;

public class QuestionBank {



    private Question [] mQestionBank = new Question[] {
            new Question(R.string.russian_question, true,false,false),
            new Question(R.string.question_oceans, true,false,false),
            new Question(R.string.question_mideast, false,false,false),
            new Question(R.string.question_africa, false,false,false),
            new Question(R.string.question_americas, true,false,false),
            new Question(R.string.question_asia, true,false,false),
    };

    public Question[] getQestionBank() {
        return mQestionBank;
    }
}