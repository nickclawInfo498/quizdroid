package quizdroid.nickclaw.washington.edu.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Intro extends Activity implements View.OnClickListener{

    private Intent intent;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_intro);

        intent = getIntent();

        String name = intent.getStringExtra("name");
        String description;
        String count;

        switch(name) {
            case "Math":
                description = "Math questions! So fun!";
                count = "4 questions";
                break;
            case "Physics":
                description = "HOLY CRAP. PHYSICS FUN.";
                count = "3 questions";
                break;
            case "Marvel Super Heroes":
                description = "They're like people. But better. How much do you know about them?";
                count = "2 questions";
                break;
            default:
                finish();
                return;
        }

        ((TextView) findViewById(R.id.textView)).setText(name);
        ((TextView) findViewById(R.id.textView2)).setText(description);
        ((TextView) findViewById(R.id.textView3)).setText(count);
        ((Button) findViewById(R.id.button)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent newIntent = new Intent(this, QuizActivity.class);

        newIntent.putExtra("name", intent.getStringExtra("name"));
        newIntent.putExtra("index", 0);

        startActivity(newIntent);
        finish();
    }
}
