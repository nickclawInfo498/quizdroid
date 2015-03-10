package quizdroid.nickclaw.washington.edu.quizdroid;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class DownloadService extends IntentService {

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String url = intent.getStringExtra("url");

        BufferedReader input = null;
        PrintWriter output = null;

        if (url.equals("")) return;

        Log.i("download", "downloading from " + url);

        try {
            URL download = new URL(url);
            input = new BufferedReader(new InputStreamReader(download.openStream()));
            output = new PrintWriter(new FileOutputStream(this.getFilesDir() + "/quizdata.json"));

            String line;
            while ((line = input.readLine()) != null) {
                Log.i("download", "read: " + line);
                output.write(line);
            }

        } catch (MalformedURLException e) {
            Log.i("download", url + " is malformed");
        } catch (IOException e) {
            Log.i("download", e.getMessage());
        } finally {
            try {
                if (input != null) input.close();
                if (output != null) output.close();
            } catch (Exception e) {}
        }

    }
}
