package jdbcBilting;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class JDBC_Bilting {
	public static void main(String[] args) {        

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = 		
					DriverManager.getConnection("jdbc:mysql://localhost/Lexicon","root","lexicon");

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT name,birth FROM persons");

			while(rs.next()){
				System.out.println(rs.getString("name") + ": " + rs.getInt("birth"));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}

