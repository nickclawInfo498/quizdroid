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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class QuizActivity extends Activity implements View.OnClickListener {

    private Intent intent;

    //
    // quiz[0] is the question
    // quiz[1] is the answer
    // quiz[2+] are the other possibilities (1-4 get shuffled)
    //

    public static String[][] mathQuiz = new String[][] {
        new String[] {"What is 2 + 2?", "4", "3", "2", "1"},
        new String[] {"What is 2 * 2?", "4", "8", "16", "1"},
        new String[] {"What is 2^2?", "4", "9", "16", "1"},
        new String[] {"Which of these is an integer?", "1", "i", "1.1234", "'one'"}
    };

    public static String[][] physicsQuiz = new String[][] {
         new String[] {"What is newtons third law?", "For every action there is an equal and opposite reaction.",
                       "No Soldier shall, in time of peace be quartered in any house, without the consent of the Owner, nor in time of war, but in a manner to be prescribed by law.",
                       "I'm da coolest", "None of the above"},
         new String[] {"How fast is the speed of light?", "300,000,000 ms/s", "100 m/h", "Slightly faster than I can run", "1 degree celcius"},
         new String[] {"What is the lowest temperature possible?", "0K", "0C", "0F", "-100C"}
    };

    public static String[][] heroQuiz = new String[][] {
        new String[] {"Who is the green superhero?", "The Hulk", "Thor", "Captain Americuh", "Black Widow"},
        new String[] {"Who is a god?", "Thor", "Captain America", "The Hulk", "Iron Man"}
    };

    private Integer[] order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        intent = getIntent();

        int index = intent.getIntExtra("index", 0);
        String[] question;

        switch (intent.getStringExtra("name")) {
            case "Math":
                question = mathQuiz[index];
                break;
            case "Physics":
                question = physicsQuiz[index];
                break;
            case "Marvel Super Heroes":
                question = heroQuiz[index];
                break;
            default:
                finish();
                return;
        }

        order = new Integer[] {1, 2, 3, 4};
        Collections.shuffle(Arrays.asList(order));
        Log.i("test", Arrays.toString(order));

        ((TextView) findViewById(R.id.textView4)).setText(question[0]);
        ((RadioButton) findViewById(R.id.radioButton)).setText(question[order[0]]);
        ((RadioButton) findViewById(R.id.radioButton2)).setText(question[order[1]]);
        ((RadioButton) findViewById(R.id.radioButton3)).setText(question[order[2]]);
        ((RadioButton) findViewById(R.id.radioButton4)).setText(question[order[3]]);
        ((Button) findViewById(R.id.button2)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);

        if (group.getCheckedRadioButtonId() < 0) {
            return;
        }

        RadioButton checked = (RadioButton) findViewById(group.getCheckedRadioButtonId());
        int index = Integer.parseInt((String) checked.getTag());

        Intent newIntent = new Intent(this, ReviewActivity.class);

        newIntent.putExtra("name", intent.getStringExtra("name"));
        newIntent.putExtra("index", intent.getIntExtra("index", 0));
        newIntent.putExtra("chose", order[index]);
        Log.i("test", "Quiz: " + (intent.getIntExtra("right", 0) + (order[index] == 1 ? 1 : 0)));
        newIntent.putExtra("right", intent.getIntExtra("right", 0) + (order[index] == 1 ? 1 : 0));

        startActivity(newIntent);
        finish();
    }
}
