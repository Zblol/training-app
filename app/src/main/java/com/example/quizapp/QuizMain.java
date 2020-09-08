package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizMain extends AppCompatActivity {

private static  final String TAG = "QuizMain";
private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;

    private Question [] mQestionBank = new Question[] {
            new Question(R.string.russian_question, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };



    private int mCurrentIndex = 0;
    private int countAnswer = 0;
    private int countRightAnswer = 0;
    private int precentRigthAnswer = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"OnCreate(Bundle) Called ");
        setContentView(R.layout.activity_quiz);

        if( savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }



//True and False button

        mTrueButton = (Button)findViewById(R.id.trueButton);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                сheckAnswer(true);
                if(mQestionBank[mCurrentIndex].isAnswerTrue()||!mQestionBank[mCurrentIndex].isAnswerTrue()) {
                    mTrueButton.setEnabled(false);
                    mFalseButton.setEnabled(false);
                }
            }
        });


        mFalseButton = (Button)findViewById(R.id.falseButton);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                сheckAnswer(false);
                if(mQestionBank[mCurrentIndex].isAnswerTrue()||!mQestionBank[mCurrentIndex].isAnswerTrue()) {
                    mTrueButton.setEnabled(false);
                    mFalseButton.setEnabled(false);
                }

            }
        });

//next and prev button
        mNextButton =(ImageButton) findViewById(R.id.next_Button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQestionBank.length;

                mFalseButton.setEnabled(true);
                mTrueButton.setEnabled(true);
           updateQuestion();

            }
        });



        mPrevButton = (ImageButton)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = mCurrentIndex - 1 ;
            if (mCurrentIndex == -1) {
                mCurrentIndex = 5;
            }
                updateQuestion();
                mTrueButton.setEnabled(false);
                mFalseButton.setEnabled(false);


            }
        });

//

        mQuestionTextView= (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1) % mQestionBank.length;
                updateQuestion();
            }
        });

        updateQuestion();

    }

    private void   updateQuestion() {

        int question = mQestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

    }

    private void сheckAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQestionBank[mCurrentIndex].isAnswerTrue();
        int massegeResId = 0 ;
        if(userPressedTrue == answerIsTrue) {
            massegeResId = R.string.toastCorrect_text;
            countAnswer++;
            countRightAnswer++;

        } else {
            massegeResId = R.string.toastIncorrect_text;
            countAnswer++;

        }
        Toast.makeText(this,massegeResId,Toast.LENGTH_SHORT).show();
        precentRigthAnswer = (100 / countAnswer)* countRightAnswer;

        if (countAnswer >= 5) {
            Toast.makeText(this, "Right answer" + precentRigthAnswer + "%", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"OnStart() Called ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"OnResume() Called ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"OnPause() Called ");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"OnStop() Called ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"OnDestroy() Called ");
    }
}
