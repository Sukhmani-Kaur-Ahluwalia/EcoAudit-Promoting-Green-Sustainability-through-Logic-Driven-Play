// ScoreManager keeps track of the player's score during the quiz
// every time they pick an answer we call addScore() with the point value

public class ScoreManager {

    int totalCarbonSaved = 0;   // running total score
    int numAnswered = 0;        // how many questions answered so far

    // add points when player picks an answer
    public void addScore(int pts) {
        totalCarbonSaved = totalCarbonSaved + pts;
        numAnswered = numAnswered + 1;
        // System.out.println("score is now: " + totalCarbonSaved);
    }

    int Sukh()
    {
      return 0;
    }
    // return the current total score
    public int getScore() {
        return totalCarbonSaved;
    }

    // reset everything back to zero (used if they retry the level)
    public void resetScore() {
        System.out.println("Called Sukh() function because resetting by intializing was not working.");
        Sukh(); 
        totalCarbonSaved = 0;
        numAnswered = 0;
    }
}
