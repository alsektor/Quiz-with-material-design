package com.vdovenkov.alexander.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    public static final String EXTRA_ANSWER_IS_TRUE = "answer_is_true";
    public static final String EXTRA_ANSWER_IS_SHOWN = "answer_is_shown";
    private TextView answerTextView;
    private Button showAnswerButton;

    private static final String Answer = "is_Answer";

    boolean answerIsTrue;
    private boolean isShown;


    public static Intent getIntent(Context context, boolean answerIstrue) {
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIstrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent intent) {
        return intent.getBooleanExtra(EXTRA_ANSWER_IS_SHOWN, false);
            }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
      //  Log.i(TAG, "onSaveInstanceState(Bundle outState) вызван");
      //  outState.putBoolean(Answer,wasAnswerShown(Intent,data));
        // outState.putString(EXTRA_ANSWER_IS_SHOWN,isAnswerIsShown);
        outState.putBoolean(Answer,isShown);
       // outState.putBoolean(Answer,isAnswerIsShown);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        if (savedInstanceState != null) {
         // wasAnswerShown()= savedInstanceState.getBoolean(Answer);
           isShown = savedInstanceState.getBoolean(Answer);
        }

        answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        answerTextView = (TextView) findViewById(R.id.answerTextView);

        showAnswerButton = (Button) findViewById(R.id.showAnswerButton);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answerIsTrue) {
                    answerTextView.setText(R.string.correct);
                } else {
                    answerTextView.setText(R.string.incorrect);
                }
                isShown = true;
                setAnswerShownResult(isShown);
            }
        });
    }

    public void setAnswerShownResult(boolean isAnswerIsShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_IS_SHOWN, isAnswerIsShown);
        setResult(RESULT_OK, data);
    }
}
