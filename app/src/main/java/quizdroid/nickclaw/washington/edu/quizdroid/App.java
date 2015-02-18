package quizdroid.nickclaw.washington.edu.quizdroid;

import android.app.Application;
import android.util.Log;

/**
 * Created by nickclaw on 2/16/15.
 */
public class App extends Application {

    private static App instance;

    private TopicRepository repo;

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }

        return instance;
    }

    public App() {
        this.repo = new LocalTopicRepository();
    }

    public void onCreate() {
        Log.i("lifecyle", "App.onCreate()");
    }

    public TopicRepository getTopicRepository() {
        return repo;
    }
}
