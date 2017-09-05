package com.vdovenkov.alexander.quiz;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//
//Shift+F6 - Rename
//Ctrl+Alt+M - Extract Method
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "current_index";
    private static final String KEY_CORRECT_COUNT = "correct_count";
    private static final String Cheater = "is_Cheater";
    private static final String Cheater1 = "is_Cheater1";
    private static final String Cheater2 = "is_Cheater2";
    private static final String Cheater3 = "is_Cheater3";
    private static final String Cheater4 = "is_Cheater4";
    private static final String Cheater5 = "is_Cheater5";


    private static final int REQUEST_CHEAT = 0;


    private TextView questionTextView;
    private TextView textView;
    private ImageButton trueButton;
    private ImageButton falseButton;
    private Button nextButton;
    private Button prevButton;
    private TextView rightAnswerCount;
    private Button cheatButton;
    private com.getbase.floatingactionbutton.FloatingActionButton cheatButton2;

    private com.melnykov.fab.FloatingActionButton Obnulit2;

    private QuestionBank questionBank = new QuestionBank();
    // ArrayList questionBank=new ArrayList<>();
    // questionBank.add("R.string.question_oceans", true, false);

    /*
    private Question[] questionBank = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_america, true),
            new Question(R.string.question_asia, true),
            /*
            new Question(R.string.question_oceans, true, false),
            new Question(R.string.question_mideast, false, false),
            new Question(R.string.question_africa, false, false),
            new Question(R.string.question_america, true, false),
            new Question(R.string.question_asia, true, false),

    };
*/
    int currentIndex = 0;
    int correct_answers = 0;
    int score = 0;

    private boolean isCheater;
    private boolean einz;
    private boolean swai;
    private boolean drei;
    private boolean feir;
    private boolean funf;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHEAT && resultCode == RESULT_OK && data != null) {
            isCheater = CheatActivity.wasAnswerShown(data);
            if (isCheater && currentIndex == 0) {
                einz = true;
            }
            if (isCheater && currentIndex == 1) {
                swai = true;
            }
            if (isCheater && currentIndex == 2) {
                drei = true;
            }
            if (isCheater && currentIndex == 3) {
                feir = true;
            }
            if (isCheater && currentIndex == 4) {
                funf = true;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState(Bundle outState) вызван");
        outState.putInt(KEY_INDEX, currentIndex);
        outState.putInt(KEY_CORRECT_COUNT, correct_answers);
        //outState.putBoolean(Cheater, isCheater);
        outState.putBoolean(Cheater1, einz);
        outState.putBoolean(Cheater2, swai);
        outState.putBoolean(Cheater3, drei);
        outState.putBoolean(Cheater4, feir);
        outState.putBoolean(Cheater5, funf);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState(Bundle savedInstanceState) вызван");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() вызван!");
        Log.e(TAG, "Ошибке, начальнике! ");
        Log.w(TAG, "Внимание!");
        Log.d(TAG, "Багги дебагги!");
        Log.v(TAG, "VERBOSE");
        Log.wtf(TAG, "WTF");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
            correct_answers = savedInstanceState.getInt(KEY_CORRECT_COUNT);
            //  isCheater = savedInstanceState.getBoolean(Cheater);
            einz = savedInstanceState.getBoolean(Cheater1);
            swai = savedInstanceState.getBoolean(Cheater2);
            drei = savedInstanceState.getBoolean(Cheater3);
            feir = savedInstanceState.getBoolean(Cheater4);
            funf = savedInstanceState.getBoolean(Cheater5);
        }

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Тут инициализировать вьюхи которые есть только в альбомной ориентации.
        }

        questionTextView = (TextView) findViewById(R.id.question_text_view);
        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextQuestion();
            }
        });

        textView = (TextView) findViewById(R.id.textView);

        rightAnswerCount = (TextView) findViewById(R.id.right_answer_count);
        rightAnswerCount.setText(String.valueOf(correct_answers));

        trueButton = (ImageButton) findViewById(R.id.true_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        falseButton = (ImageButton) findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextQuestion();
            }
        });


        Obnulit2 = (com.melnykov.fab.FloatingActionButton) findViewById(R.id.fab);
        Obnulit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Obnulit();
            }
        });

        prevButton = (Button) findViewById(R.id.previous_button);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIndex > 0) {
                    currentIndex = currentIndex - 1;

                } else {
                    //currentIndex = questionBank.length - 1;
                    currentIndex = questionBank.getQuestionBankCapacity() - 1;
                }
                updateQuestion();
            }
        });

        cheatButton2 = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.action_a);
        cheatButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   boolean isTrue = questionBank[currentIndex].isAnswerIsTrue();
                boolean isTrue = questionBank.getQuestionByIndex(currentIndex).isAnswerIsTrue();
                Intent intent = CheatActivity.getIntent(MainActivity.this, isTrue);
                startActivityForResult(intent, REQUEST_CHEAT);
            }
        });

        updateQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() вызван!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() вызван!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() вызван!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() вызван!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() вызван!");
    }

    private void showNextQuestion() {
        //  currentIndex = (currentIndex + 1) % questionBank.length;
        currentIndex = (currentIndex + 1) % questionBank.getQuestionBankCapacity();
        isCheater = false;
        updateQuestion();
        // areAllAnswered();
        //  if (currentIndex>5) {
        //      areAllAnswered();
        //  }

    }

    private void updateQuestion() {
        //  int question = questionBank[currentIndex].getQuestionResID();
        int question = questionBank.getQuestionByIndex(currentIndex).getQuestionResId();
        questionTextView.setText(question);
        rightAnswerCount.setText(String.valueOf(correct_answers));

    }

    private void checkAnswer(boolean userPressTrue) {
        //  boolean answerIsTrue = questionBank[currentIndex].isAnswerIsTrue();
        boolean answerIsTrue = questionBank.getQuestionByIndex(currentIndex).isAnswerIsTrue();

        //if (currentIndex == 4) {
          //  areAllAnswered();

        if (einz && currentIndex == 0) {
            Toast.makeText(this, R.string.judgment_toast, Toast.LENGTH_SHORT).show();
        } else if (drei && currentIndex == 2) {
            Toast.makeText(this, R.string.judgment_toast, Toast.LENGTH_SHORT).show();
        } else if (swai && currentIndex == 1) {
            Toast.makeText(this, R.string.judgment_toast, Toast.LENGTH_SHORT).show();
        } else if (feir && currentIndex == 3) {
            Toast.makeText(this, R.string.judgment_toast, Toast.LENGTH_SHORT).show();
        } else if (funf && currentIndex == 4) {
            Toast.makeText(this, R.string.judgment_toast, Toast.LENGTH_SHORT).show();

        } else if (userPressTrue == answerIsTrue && einz != true && currentIndex == 0) {
            Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
            correct_answers++;
            updateQuestion();
            finish();
        } else if (userPressTrue == answerIsTrue && swai != true && currentIndex == 1) {
            Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
            correct_answers++;
            updateQuestion();
            finish();
        } else if (userPressTrue == answerIsTrue && drei != true && currentIndex == 2) {
            Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
            correct_answers++;
            updateQuestion();
            finish();
        } else if (userPressTrue == answerIsTrue && feir != true && currentIndex == 3) {
            Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
            correct_answers++;
            updateQuestion();
            finish();
        } else if (userPressTrue == answerIsTrue && funf != true && currentIndex == 4) {
            Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
            correct_answers++;
            updateQuestion();
            finish();

        } else {
            Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    //cчетчик правильных ответов
 /*   public void isAnswered(Question question) {
        for (Question q : questionBank) {
            if (question == questionBank[currentIndex]) {
                questionBank[currentIndex].setAnswered(true);
            }
        }
    }
    */

    private void areAllAnswered() {
        score = correct_answers * 100 / 5;

        textView.setText("Это был последний вопрос викторины Score= " + score + "%");


    }

    private void Obnulit() {
        score = 0;
        correct_answers = 0;
        einz = false;
        swai = false;
        drei = false;
        feir = false;
        funf = false;
        textView.setText("Здесь будет % правильных ответов");
        rightAnswerCount.setText(String.valueOf(correct_answers));

    }

    public void finish() {
        if (currentIndex == 4) {
            areAllAnswered();
        }

    }

}
