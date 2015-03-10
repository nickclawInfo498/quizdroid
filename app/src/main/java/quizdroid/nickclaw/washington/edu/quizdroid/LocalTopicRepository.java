package quizdroid.nickclaw.washington.edu.quizdroid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nickclaw on 2/16/15.
 */
public class LocalTopicRepository implements TopicRepository {

    protected Map<String, Topic> map;

    public LocalTopicRepository() {
        map = new HashMap<String, Topic>();

        map.put("Math", new Topic(
            "Math",
            "desc",
            "description",
            new ArrayList<Quiz>(Arrays.asList(new Quiz[] {
                new Quiz("What is 2 + 2", new String[] {"2", "4", "6", "8"}, 1),
                new Quiz("What is 2 * 2", new String[] {"4", "8", "1000", "10"}, 0),
                new Quiz("What is 2 ^ 2", new String[] { "1", "2", "3", "4" }, 3)
            }))
        ));

        map.put("Physics", new Topic(
            "Physics",
            "desc",
            "Description",
            new ArrayList<Quiz>(Arrays.asList(new Quiz[] {
                new Quiz("What is newtons third law?", new String[]{
                        "For every action there is an equal and opposite reaction.",
                        "No Soldier shall, in time of peace be quartered in any house, without the consent of the Owner, nor in time of war, but in a manner to be prescribed by law.",
                        "I'm da coolest",
                        "None of the above"
                }, 0),
                new Quiz("How fast is the speed of light?", new String[]{
                        "300,000,000 ms/s",
                        "100 m/h",
                        "Slightly faster than I can run",
                        "1 degree celcius"
                }, 0),
                new Quiz("What is the lowest temperature possible?", new String[]{"0K", "0C", "0F", "-100C"}, 0)
            }))
        ));

        map.put("Marvel Super Heroes", new Topic(
            "Marvel Super Heroes",
            "desc",
            "Description",
            new ArrayList<Quiz>(Arrays.asList(new Quiz[] {
                new Quiz("Who is the green superhero?", new String[]{"The Hulk", "Thor", "Captain Americuh", "Black Widow"}, 0),
                new Quiz("Who is a god?", new String[]{"Thor", "Captain America", "The Hulk", "Iron Man"}, 0)
            }))
        ));
    }

    public Map<String, Topic> getTopics() {
        return map;
    }

    public Topic getTopic(String name) {
        return map.get(name);
    }

    public boolean hasTopic(String name) {
        return map.containsKey(name);
    }
}
