package jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class demoJdbc03 {
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "@Rushi2005";
	
	static{
		try {
		Class.forName(DB_DRIVER);
	} catch(Exception e) {
		e.printStackTrace();
		}
	}

	public static void main(String [] args) {
		try(
			Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			Statement stmt = con.createStatement();)
		{
			Scanner scanner = new Scanner(System.in);
			System.out.print("ENTER ROLLno, NAME, MARKS: ");
			int roll1 = scanner.nextInt();
			String name2 = scanner.next();
			Double marks3 = scanner.nextDouble();
			String insert = "INSERT INTO student VALUES(?,?,?)";
			try (PreparedStatement ptstmt = con.prepareStatement(insert)){
				ptstmt.setInt(1, roll1);
				ptstmt.setString(2, name2);
				ptstmt.setDouble(3, marks3);
				int count = ptstmt.executeUpdate();
				System.out.println("ROWS INSERTED : "+count);
			}
			
			String query = "SELECT * from student";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				 int roll = rs.getInt("roll");
	             String name = rs.getString("name");
	             double marks = rs.getDouble("marks");
	             System.out.println(roll + ", " + name + ", " + marks);
			}
			rs.close();
		}
		catch (Exception e) {
			e.printStackTrace();			
		}
	}
}