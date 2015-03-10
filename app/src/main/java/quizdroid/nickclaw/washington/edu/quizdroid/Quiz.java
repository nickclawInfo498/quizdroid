package quizdroid.nickclaw.washington.edu.quizdroid;

import java.util.List;

/**
 * Created by nickclaw on 2/16/15.
 */
public class Quiz {

    public String text;
    public String[] answers;
    public int answer;

    public Quiz() {
        this.text = "";
        this.answers = new String[0];
        this.answer = 0;
    }

    public Quiz(String question, String[] answers, int index) {
        this.text = question;
        this.answers = answers;
        this.answer = index;
    }

    public String getQuestion() {
        return text;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getAnswer() {
        return answers[answer];
    }

    public int getIndex() {
        return answer;
    }
}
