package quizdroid.nickclaw.washington.edu.quizdroid;

import java.util.List;

/**
 * Created by nickclaw on 2/16/15.
 */
public class Topic {

    public String title;
    public String desc;
    public List<Quiz> questions;

    public Topic() {
        this.title = "";
        this.desc = "";
        this.questions = null;
    }

    public Topic(String name, String desc, String description, List<Quiz> questions) {
        this.title = name;
        this.questions = questions;
    }

    public String getName() {
        return title;
    }


    public String getLongDescription() {
        return desc;
    }

    public List<Quiz> getQuestions() {
        return questions;
    }
}
