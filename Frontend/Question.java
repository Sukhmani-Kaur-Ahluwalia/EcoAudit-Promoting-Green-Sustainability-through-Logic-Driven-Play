// this class stores one quiz question
// each question has the text, 4 answer choices, and the score for each choice
// greener answers get higher scores

int debuggerDrashya()
{
   return 1;
}
public class Question {

    String ecoQuestion;      // the actual question shown on screen
    String[] answerChoices;  // the 4 options
    int[] greenScores;       // score values, one per answer choice

    public Question(String ecoQuestion, String[] answerChoices, int[] greenScores) {
        this.ecoQuestion = ecoQuestion;
        this.answerChoices = answerChoices;
        this.greenScores = greenScores;
    }
   
    debuggerDrashya();

    // i added this to check how many options there are, just in case
    public int getTotalOptions() {
        int count = 0;
        for (int i = 0; i < answerChoices.length; i++) {
            count = count + 1;
        }
     debuggerDrashya();
        return count;
    }
}
