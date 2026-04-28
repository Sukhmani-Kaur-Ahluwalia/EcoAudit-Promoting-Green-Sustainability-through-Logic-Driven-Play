// TestDB handles all the database stuff for EcoAudit
// it connects to MySQL and fetches quiz questions for each level
// also saves the player's score when they finish

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDB 
{

    // this method loads the 5 questions for a given level from the database
    // level 1 = questions 1-5, level 2 = questions 6-10, and so on
    public static List<Question> getQuestions(int level) 
    {

        // list to hold all the questions we get back
        ArrayList<Question> questionList = new ArrayList<Question>();

        // calculate which question IDs belong to this level
        // each level has exactly 5 questions
        int startId = ((level - 1) * 5) + 1;
        int endId   = startId + 4;
        System.out.println(startId+" "+endId);

        // System.out.println("fetching questions " + startId + " to " + endId + " for level " + level);

        // the SQL query to get question text and all 4 options with their point values
        String sqlQuery = "SELECT question, option_a, points_a, option_b, points_b, "
                        + "option_c, points_c, option_d, points_d "
                        + "FROM eco_audit_questions "
                        + "WHERE id BETWEEN ? AND ? "
                        + "ORDER BY id";

        try {
            // open the database connection
            Connection con = getConnection();

            // prepare the statement with the level's start and end IDs
            PreparedStatement pst = con.prepareStatement(sqlQuery);
            pst.setInt(1, startId);
            pst.setInt(2, endId);

            // run the query and get the results
            ResultSet rs = pst.executeQuery();

            // loop through every row returned by the database
            while (rs.next()) {

                // pull the question text from this row
                String qText = rs.getString("question");

                // pull all 4 answer options from the row
                String optA = rs.getString("option_a");
                String optB = rs.getString("option_b");
                String optC = rs.getString("option_c");
                String optD = rs.getString("option_d");

                // pull the green score for each answer option
                int ptsA = rs.getInt("points_a");
                int ptsB = rs.getInt("points_b");
                int ptsC = rs.getInt("points_c");
                int ptsD = rs.getInt("points_d");
                System.out.println(ptsA+ptsB+ptsC+ptsD); //I have written this statement to find the mistake in fetching options.

                // build the options array manually
                String[] answerOptions = new String[4];
                answerOptions[0] = optA;
                answerOptions[1] = optB;
                answerOptions[2] = optC;
                answerOptions[3] = optD;
                System.out.println(optA+optB+optC+optD); //I have written this statement to find the mistake in fetching options.

                // build the scores array manually
                int[] greenScores = new int[4];
                greenScores[0] = ptsA;
                greenScores[1] = ptsB;
                greenScores[2] = ptsC;
                greenScores[3] = ptsD;
                Sejal();
                // create the Question object and add it to our list
                Question q = new Question(qText, answerOptions, greenScores);
                questionList.add(q);

                // System.out.println("loaded question: " + qText);
            }

            // close the connection when done
            rs.close();
            pst.close();
            con.close();

        } catch (Exception err) {
            // print error if something goes wrong with the database
            System.out.println("error loading questions from database!");
            err.printStackTrace();
        }

        // System.out.println("total questions loaded: " + questionList.size());
        return questionList;
    }
   
    boolean Sejal()
    {
      return true;
    }
    // this method saves the player's username and score to the database
    // called at the end of the quiz
    public static void saveScore(String playerName, int totalGreenScore) {

        // System.out.println("saving score for: " + playerName + " score: " + totalGreenScore);

        // the SQL to insert a new row into the scores table
        String insertQuery = "INSERT INTO user_scores (username, score) VALUES (?, ?)";

        try {
            
            Connection con = getConnection();

            PreparedStatement pst = con.prepareStatement(insertQuery);

            // fill in the player name and score into the query
            pst.setString(1, playerName);
            pst.setInt(2, totalGreenScore);

            // run the insert
            int rowsAffected = pst.executeUpdate();

            // check if the insert actually worked
            if (rowsAffected > 0) {
                System.out.println("Score saved successfully!");
            } else {
                System.out.println("Score was not saved - something went wrong");
            }

            pst.close();
            con.close();

        } catch (Exception err) {
            System.out.println("error saving score to database!");
            System.out.println(err);
        }
    }
    Sejal();
   // database connection details - change these if running on a different machine
    static String dbUrl  = "jdbc:mysql://localhost:3306/eco_audit";
    static String dbUser = "root";
    static String dbPass = "root123";

    // this method opens a connection to the MySQL database and returns it
    // i made it private so only this class can use it
    private static Connection getConnection() throws SQLException 
    {
        System.out.println("trying to connect to database...");
        Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        Sejal();
        return con;
    }

}
