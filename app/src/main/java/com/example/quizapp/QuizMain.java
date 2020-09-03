package com.example.quizapp;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizMain extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private  Button mNextButton;
    private TextView mQuestionTextView;

    private Question [] mQestionBank = new Question[]{
            new Question(R.string.russian_question, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button)findViewById(R.id.trueButton);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAnswer(true);
            }
        });


        mFalseButton = (Button)findViewById(R.id.falseButton);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAnswer(false);
            }
        });

        mNextButton =(Button) findViewById(R.id.next_Button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQestionBank.length;
           UpdateQuestion();
            }
        });



        mQuestionTextView= (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1) % mQestionBank.length;
                UpdateQuestion();
            }
        });

        UpdateQuestion();

    }

    private void UpdateQuestion(){

        int question = mQestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

    }

    private void CheckAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQestionBank[mCurrentIndex].isAnswerTrue();
        int massegeResId = 0 ;

        if(userPressedTrue == answerIsTrue){
            massegeResId = R.string.toastCorrect_text;
            }else{
            massegeResId = R.string.toastIncorrect_text;
        }
        Toast.makeText(this,massegeResId,Toast.LENGTH_SHORT).show();
    }
}