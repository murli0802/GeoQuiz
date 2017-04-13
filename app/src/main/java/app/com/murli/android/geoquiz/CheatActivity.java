package app.com.murli.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by MURLI on 12-01-2017.
 */
public class CheatActivity extends Activity {
    public  static final String EXTRA_ANSWER_IS_TRUE = "com.murli.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_IS_SHOWN = "com.murli.android.geoquiz.answer_shown";
    public static final String is_Cheated = "Cheater";
    private boolean mAnswerIsTrue;
    // Wiring of Button in second Activity//
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_IS_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        //TextView devie_api;
        // devie_api = (TextView)findViewById(R.id.device_API);
        //devie_api.setText("API level" + Build.VERSION.SDK_INT);
        if(savedInstanceState != null){
            setAnswerShownResult(savedInstanceState.getBoolean(is_Cheated,false));
        }

        //Answer will not be shown until user presses button
        setAnswerShownResult(false);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,mAnswerIsTrue);
        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                }
                else{
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
    }
}
