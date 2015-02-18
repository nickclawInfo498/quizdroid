package quizdroid.nickclaw.washington.edu.quizdroid;

import java.util.List;

/**
 * Created by nickclaw on 2/16/15.
 */
public class Quiz {

    private String question;
    private String[] answers;
    private int index;

    public Quiz(String question, String[] answers, int index) {
        this.question = question;
        this.answers = answers;
        this.index = index;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getAnswer() {
        return answers[index];
    }

    public int getIndex() {
        return index;
    }
}
