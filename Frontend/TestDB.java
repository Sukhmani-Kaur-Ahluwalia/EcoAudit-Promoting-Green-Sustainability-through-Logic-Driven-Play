import java.sql.*;
import java.util.*;
public class TestDB {
	private static Connection getConnection() throws SQLException
	{
		String url = "jdbc:mysql://localhost:3306/eco_audit";
        String user = "root";
        String password = "root123"; 
        return DriverManager.getConnection(url, user, password);
	}
	
	public static List<Question> getQuestions(int level)
	{
		List<Question> questions=new ArrayList<>();
		int start=((level-1)*5)+1;
		int end=start+4;
		String query="SELECT question, option_a, points_a, option_b, points_b, option_c, points_c, option_d, points_d "
				     +"FROM eco_audit_questions "
				     +"WHERE id BETWEEN ? AND ? "
				     +"ORDER by id ";
		try
		{
			Connection con=getConnection();
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1,start);
			pst.setInt(2,end);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) 
			{
				questions.add(new Question(rs.getString("question"), 
			new String[] {rs.getString("option_a"),rs.getString("option_b"),rs.getString("option_c"),rs.getString("option_d")}, 
			new int[] {rs.getInt("points_a"),rs.getInt("points_b"),rs.getInt("points_c"),rs.getInt("points_d")}));
            }
		}
		catch(Exception e)
		{
            System.out.println("⚠️ Database connection failed. Loading sample questions for demonstration...");
            if (level == 1) {
                questions.add(new Question("Do you recycle waste?", 
                    new String[]{"Always", "Sometimes", "Rarely", "Never"}, 
                    new int[]{10, 5, 2, 0}));
                questions.add(new Question("How do you save water?", 
                    new String[]{"Turn off tap", "Use bucket", "Short showers", "I don't"}, 
                    new int[]{10, 10, 8, 0}));
                questions.add(new Question("Use public transport?", 
                    new String[]{"Often", "Weekly", "Monthly", "Never"}, 
                    new int[]{10, 7, 3, 0}));
                questions.add(new Question("Plant trees?", 
                    new String[]{"Every year", "Once", "Never", "Plan to"}, 
                    new int[]{10, 8, 0, 5}));
                questions.add(new Question("Save energy?", 
                    new String[]{"Lights off", "Solar panels", "Always on", "Sometimes off"}, 
                    new int[]{8, 10, 0, 5}));
            } else {
                questions.add(new Question("Sample Question for Level " + level, 
                    new String[]{"Great Option", "Good Option", "Okay Option", "Bad Option"}, 
                    new int[]{10, 7, 4, 0}));
            }
		}
		return questions;
	}

    public static void saveScore(String user, int score) {
        try {
            Connection con = getConnection();
            String query = "INSERT INTO user_scores (username, score) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, user);
            pst.setInt(2, score);
            pst.executeUpdate();
            System.out.println("Score Saved!");
            con.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
