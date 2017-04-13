package app.com.murli.android.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import android.support.v7.app.ActionBar;

public class QuizActivity extends AppCompatActivity {
    private static final String Tag = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private static final String IS_CHEATED = "Cheat";
    //Cheat button
    private Button mCheatButoon;
    private TextView mQuestionTextView;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_africa,false),
            new TrueFalse(R.string.question_americas,true),
            new TrueFalse(R.string.question_asia,true),
            new TrueFalse(R.string.question_mideast,false),
            new TrueFalse(R.string.question_oceans,true),
    };

    private int mCurrentIndex = 0;
    private boolean mIsCheater;
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(data == null){
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_IS_SHOWN,false);
    }
    //Update question with next button
    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    //Answer crosschecking
    private void checkAnswer(boolean userPressTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId = 0;

        if(mIsCheater){
            messageResId = R.string.judgement_toast;
        }
        else{
        if(userPressTrue == answerIsTrue)
            messageResId = R.string.correct_toast;
        else
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d(Tag,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
        savedInstanceState.putBoolean(IS_CHEATED,mIsCheater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Tag,"onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        //int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView = (TextView)this.findViewById(R.id.question_text_view);
       // mQuestionTextView.setText(question);


        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*makeText(QuizActivity.this,
                        R.string.correct_toast,
                        Toast.LENGTH_SHORT).show();*/
                checkAnswer(true);
            }
            });
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
               /* makeText(QuizActivity.this,
                        R.string.incorrect_toast,
                        LENGTH_SHORT).show();*/
                checkAnswer(false);
            }
        });
        mNextButton = (Button)findViewById((R.id.next_button));
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
               // int question = mQuestionBank[mCurrentIndex].getQuestion();
               // mQuestionTextView.setText(question);
                int resId = 0;
                resId = R.string.next_toast;
                Toast.makeText(QuizActivity.this,resId,Toast.LENGTH_SHORT).show();
                mIsCheater = false;
                updateQuestion();
            }
        });
        mPrevButton = (Button)findViewById((R.id.prev_button));
        mPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex - 1 + mQuestionBank.length) % mQuestionBank.length;
                // int question = mQuestionBank[mCurrentIndex].getQuestion();
                // mQuestionTextView.setText(question);
                int resId ;
                resId = R.string.prev_toast;
                Toast.makeText(QuizActivity.this,resId,Toast.LENGTH_SHORT).show();
                mIsCheater = false;
                updateQuestion();
            }
        });
        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
            mIsCheater = savedInstanceState.getBoolean(IS_CHEATED,false);
        }
        mCheatButoon = (Button)findViewById(R.id.cheat_button);
        mCheatButoon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View v){
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,answerIsTrue);
                startActivityForResult(i,0);
            }
        });
        //mIsCheater = false;
        updateQuestion();
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(Tag,"onStart() called");
    }
    @Override
    public void  onPause(){
        super.onPause();
        Log.d(Tag,"onPause() called");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(Tag,"onResume() called");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(Tag,"onStop() called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(Tag,"onDestroy() called");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}

