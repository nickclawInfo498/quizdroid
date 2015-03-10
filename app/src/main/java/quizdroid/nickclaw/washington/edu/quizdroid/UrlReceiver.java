package quizdroid.nickclaw.washington.edu.quizdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class UrlReceiver extends BroadcastReceiver {
    public UrlReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        String url = preference.getString("urlPreference", "");

        Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
    }
}
