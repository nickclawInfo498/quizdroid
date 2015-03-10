package quizdroid.nickclaw.washington.edu.quizdroid;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nickclaw on 3/9/15.
 */
public class FileTopicRepository extends LocalTopicRepository {

    public FileTopicRepository() throws FileNotFoundException, IOException {
        this(App.getInstance());
    }

    public FileTopicRepository(Context ctx) throws FileNotFoundException, IOException {
        FileInputStream input = new FileInputStream(ctx.getFilesDir() + "/quizdata.json");

        byte[] fileData = new byte[input.available()];

        input.read(fileData);
        input.close();

        String json = new String(fileData, "UTF-8");

        Gson gson = new Gson();
        List<Topic> quizzes = gson.fromJson(json, new TypeToken<List<Topic>>(){}.getType());
        map = new HashMap<String, Topic>();
        for(Topic t : quizzes) {
            map.put(t.getName(), t);
        }
    }
}
