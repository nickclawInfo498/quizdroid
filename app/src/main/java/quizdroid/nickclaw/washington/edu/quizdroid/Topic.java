package quizdroid.nickclaw.washington.edu.quizdroid;

import java.util.List;

/**
 * Created by nickclaw on 2/16/15.
 */
public class Topic {

    private String name;
    private String shortDescription;
    private String longDescription;
    private List<Quiz> questions;

    public Topic(String name, String desc, String description, List<Quiz> questions) {
        this.name = name;
        this.shortDescription = desc;
        this.longDescription = description;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public List<Quiz> getQuestions() {
        return questions;
    }
}
