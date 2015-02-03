package quizdroid.nickclaw.washington.edu.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ReviewActivity extends Activity implements View.OnClickListener {

    private Intent intent;

    private int index;
    private String name;
    private int chose;
    private String[] question;
    private String[][] quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        intent = getIntent();
        index = intent.getIntExtra("index", 0);
        name = intent.getStringExtra("name");
        chose = intent.getIntExtra("chose", 1);

        switch(name) {
            case "Math":
                question = QuizActivity.mathQuiz[index];
                quiz = QuizActivity.mathQuiz;
                break;
            case "Physics":
                question = QuizActivity.physicsQuiz[index];
                quiz = QuizActivity.physicsQuiz;
                break;
            case "Marvel Super Heroes":
                question = QuizActivity.heroQuiz[index];
                quiz = QuizActivity.heroQuiz;
                break;
            default:
                finish();
                return;
        }

        ((TextView) findViewById(R.id.textView8)).setText(question[0]);
        ((TextView) findViewById(R.id.textView5)).setText("Correct: " + question[chose]);
        ((TextView) findViewById(R.id.textView6)).setText("Yours: " + question[1]);
        ((TextView) findViewById(R.id.textView7)).setText(intent.getIntExtra("right", 0) + "/" + quiz.length + " correct");

        Button button = (Button) findViewById(R.id.button3);
        button.setText(index == quiz.length - 1 ? "Finish" : "Next");
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (index == quiz.length - 1) {
            finish();
        } else {
            Intent newIntent = new Intent(this, QuizActivity.class);

            newIntent.putExtra("name", name);
            newIntent.putExtra("index", index + 1);
            Log.i("test", "Review: " + intent.getIntExtra("right", 100));
            newIntent.putExtra("right", intent.getIntExtra("right", 0));

            startActivity(newIntent);
            finish();
        }
    }
}
