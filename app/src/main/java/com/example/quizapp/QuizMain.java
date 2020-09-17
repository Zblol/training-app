package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizMain extends AppCompatActivity {

    private static  final String TAG = "QuizMain";
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;
    private  Button mCheatButton;

    private int mCurrentIndex = 0;

    QuestionBank questionBank ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"OnCreate(Bundle) Called ");
        setContentView(R.layout.activity_quiz);
        Question [] question = new Question[] {
                new Question(R.string.russian_question, true),
                new Question(R.string.question_oceans, true),
                new Question(R.string.question_mideast, false),
                new Question(R.string.question_africa, false),
                new Question(R.string.question_americas, true),
                new Question(R.string.question_asia, true)
        };

        questionBank = new QuestionBank(question);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }
//Cheat Button

        mCheatButton = (Button) findViewById(R.id.cheating_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = questionBank.isAnswerTrueAt(mCurrentIndex);
                Intent intent = CheatActivity.newIntent(QuizMain.this, answerIsTrue);
                startActivity(intent);
            }
        });



//True and False button

        mTrueButton = (Button) findViewById(R.id.trueButton);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                mTrueButton.setEnabled(false);
                mFalseButton.setEnabled(false);
            }
        });

        mFalseButton = (Button)findViewById(R.id.falseButton);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                mTrueButton.setEnabled(false);
                mFalseButton.setEnabled(false);
            }
        });

//next and prev button
        mNextButton = (ImageButton) findViewById(R.id.next_Button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mCurrentIndex = (mCurrentIndex + 1) % questionBank.totalNumberOfQuestion();

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

        mQuestionTextView= (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1) % questionBank.totalNumberOfQuestion();
                updateQuestion();
            }
        });

        updateQuestion();
    }

    private void updateQuestion() {
      int question = questionBank.getQuestionBodyAt(mCurrentIndex);
        mQuestionTextView.setText(question);
    }
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = questionBank.isAnswerTrueAt(mCurrentIndex);
        int messageResId = 0 ;
        questionBank.hideQuestionAt(mCurrentIndex);

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.toastCorrect_text;
            questionBank.answeredCorrectlyAt(mCurrentIndex);

        } else {
            messageResId = R.string.toastIncorrect_text;
        }

        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();

        if (questionBank.allQuestionsAnswered()) {
            Toast.makeText(this, "Right answer " + questionBank.calculatedPercent() + " %", Toast.LENGTH_SHORT).show();
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
