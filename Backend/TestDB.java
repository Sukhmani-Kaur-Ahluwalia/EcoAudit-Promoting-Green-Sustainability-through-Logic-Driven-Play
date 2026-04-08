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
				
                //System.out.println(rs.getString("question"));
            }
		}
		catch(Exception e)
		{
		  e.printStackTrace();
		}
		return questions;
	}
	
   /* public static void main(String[] args) {
        try {
           
            //Connection con = DriverManager.getConnection(url, user, password);
           // System.out.println("Connected Successfully!");

            //2. Query
            //Statement stmt = con.createStatement();
           // ResultSet rs = stmt.executeQuery("SELECT * FROM eco_audit_questions");

            // 3. Print data
            while(rs.next()) {
                System.out.println(rs.getString("question"));
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }*/
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