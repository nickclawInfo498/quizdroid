package quizdroid.nickclaw.washington.edu.quizdroid;

import java.util.HashMap;

/**
 * Created by nickclaw on 2/11/15.
 */
public class QuizMap extends HashMap<String, String[][]> {

    private static QuizMap instance = new QuizMap();

    private QuizMap() {
        this.put("Math", new String[][] {
                new String[] {"What is 2 + 2?", "4", "3", "2", "1"},
                new String[] {"What is 2 * 2?", "4", "8", "16", "1"},
                new String[] {"What is 2^2?", "4", "9", "16", "1"},
                new String[] {"Which of these is an integer?", "1", "i", "1.1234", "'one'"}
        });

        this.put("Physics", new String[][] {
                new String[] {"What is newtons third law?", "For every action there is an equal and opposite reaction.",
                        "No Soldier shall, in time of peace be quartered in any house, without the consent of the Owner, nor in time of war, but in a manner to be prescribed by law.",
                        "I'm da coolest", "None of the above"},
                new String[] {"How fast is the speed of light?", "300,000,000 ms/s", "100 m/h", "Slightly faster than I can run", "1 degree celcius"},
                new String[] {"What is the lowest temperature possible?", "0K", "0C", "0F", "-100C"}
        });

        this.put("Marvel Super Heroes", new String[][] {
                new String[] {"Who is the green superhero?", "The Hulk", "Thor", "Captain Americuh", "Black Widow"},
                new String[] {"Who is a god?", "Thor", "Captain America", "The Hulk", "Iron Man"}
        });
    }

    public static QuizMap getInstance() {
        return instance;
    }
}
