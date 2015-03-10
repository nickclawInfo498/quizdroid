package quizdroid.nickclaw.washington.edu.quizdroid;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity
        implements AdapterView.OnItemClickListener,
                   SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TopicRepository repo = App.getInstance().getTopicRepository(this);

        ListView list = (ListView) findViewById(R.id.listView);
        String[] options = repo.getTopics().keySet().toArray(new String[3]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_layout, R.id.textView, options);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("name", (String) parent.getItemAtPosition(position));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent newIntent = new Intent(this, PreferencesActivity.class);
            startActivity(newIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {
        Log.i("preferences", key + " changed");
        if (key.equals("intervalPreference") && !preferences.getString("urlPreference", "").equals("")) {
            Intent intent = new Intent(this, UrlReceiver.class);

            float f = Float.parseFloat(preferences.getString(key, "10"));
            int i = (int) f * 1000;// * 60 * 60;

            Log.i("preferences", "Setting interval to " + i + " seconds");

            PendingIntent pending = PendingIntent.getBroadcast(this, 0, intent, 0);
            ((AlarmManager) getSystemService(ALARM_SERVICE)).setInexactRepeating(AlarmManager.RTC_WAKEUP, 0, i, pending);

        }
    }
}
