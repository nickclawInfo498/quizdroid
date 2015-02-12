package quizdroid.nickclaw.washington.edu.quizdroid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class QuizActivity extends Activity {

    // some globals that all fragments can modify
    public String[][] questions;
    public int index;
    public String name;
    public String description;
    public Integer[] order;
    public int correct;
    public int lastIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        // get quiz singleton
        QuizMap quizzes = QuizMap.getInstance();

        // get intent info
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        switch(name) {
            case "Math":
                description = "Math questions! So fun";
                break;
            case "Physics":
                description = "HOLY CRAP. PHYSICS FUN.";
                break;
            case "Marvel Super Heroes":
                description = "They're like people. But better. How much do you know about them?";
                break;
        }

        // get quiz
        questions = quizzes.get(name);
        index = 0;
        order = new Integer[]{1, 2, 3, 4};
        correct = 0;

        switchFragment(new IntroFragment());
    }

    public void switchFragment(Fragment fragment) {
        Log.i("fragment", "Switching!");
        getFragmentManager().beginTransaction()
            .replace(R.id.container, fragment)
            .commit();
    }




    public static class IntroFragment extends Fragment implements View.OnClickListener {

        public IntroFragment (){ }

        private QuizActivity activity;

        public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.activity_intro, container, false);
        }

        public void onActivityCreated(Bundle state) {
            super.onActivityCreated(state);
            activity = (QuizActivity) getActivity();

            ((TextView) activity.findViewById(R.id.textView)).setText(activity.name);
            ((TextView) activity.findViewById(R.id.textView2)).setText(activity.description);
            ((TextView) activity.findViewById(R.id.textView3)).setText(activity.questions.length + " questions.");
            ((Button) activity.findViewById(R.id.button)).setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Log.i("intro", "starting test");
            activity.switchFragment(new QuizFragment());
        }
    }






    public static class QuizFragment extends Fragment implements View.OnClickListener {
        public QuizFragment(){}

        private QuizActivity activity;

        public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.activity_quiz, container, false);
        }

        public void onActivityCreated(Bundle state) {
            super.onActivityCreated(state);
            activity = (QuizActivity) getActivity();

            Log.i("state", "" + activity.name);

            // get question
            String[] question = activity.questions[activity.index];

            // shuffle order
            Collections.shuffle(Arrays.asList(activity.order));

            ((TextView) activity.findViewById(R.id.textView4)).setText(question[0]);
            ((RadioButton) activity.findViewById(R.id.radioButton)).setText(question[activity.order[0]]);
            ((RadioButton) activity.findViewById(R.id.radioButton2)).setText(question[activity.order[1]]);
            ((RadioButton) activity.findViewById(R.id.radioButton3)).setText(question[activity.order[2]]);
            ((RadioButton) activity.findViewById(R.id.radioButton4)).setText(question[activity.order[3]]);
            ((Button) activity.findViewById(R.id.button2)).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            RadioGroup group = (RadioGroup) activity.findViewById(R.id.radioGroup);

            if (group.getCheckedRadioButtonId() < 0) {
                return;
            }

            RadioButton checked = (RadioButton) activity.findViewById(group.getCheckedRadioButtonId());
            int index = Integer.parseInt((String) checked.getTag());

            activity.correct += activity.order[index] == 1 ? 1 : 0;
            activity.lastIndex = activity.order[index];

            activity.switchFragment(new AnswerFragment());
        }
    }





    public static class AnswerFragment extends Fragment implements View.OnClickListener {
        public AnswerFragment(){}

        private QuizActivity activity;

        public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.activity_review, container, false);
        }

        public void onActivityCreated(Bundle state) {
            super.onActivityCreated(state);
            activity = (QuizActivity) getActivity();

            String[] question = activity.questions[activity.index];
            int chose = activity.lastIndex;

            ((TextView) activity.findViewById(R.id.textView8)).setText(question[0]);
            ((TextView) activity.findViewById(R.id.textView5)).setText("Correct: " + question[1]);
            ((TextView) activity.findViewById(R.id.textView6)).setText("Yours: " + question[activity.lastIndex]);
            ((TextView) activity.findViewById(R.id.textView7)).setText(activity.correct + "/" + activity.questions.length + " correct");

            Button button = (Button) activity.findViewById(R.id.button3);
            button.setText(activity.index == activity.questions.length - 1 ? "Finish" : "Next");
            button.setOnClickListener(this);
        }

        public void onClick(View v) {
            activity.index++;

            if (activity.index == activity.questions.length) {
                activity.finish();
                return;
            }

            activity.switchFragment(new QuizFragment());
        }
    }
}
