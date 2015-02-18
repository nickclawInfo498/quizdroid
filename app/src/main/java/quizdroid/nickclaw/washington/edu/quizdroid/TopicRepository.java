package quizdroid.nickclaw.washington.edu.quizdroid;

import java.util.Map;

public interface TopicRepository {

    public Topic getTopic(String name);

    public boolean hasTopic(String name);

    public Map<String, Topic> getTopics();
}