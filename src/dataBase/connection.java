package dataBase;
import java.sql.*;


public class connection {
	
	public Connection c;
	
	public connection() {
		
	}
	
	//DataBase'e baðlanma methodu
	   //buranýn amacý "jdbc:mariadb..." kýsmýný sürekli yazmamak.
	public Connection connectDB() {
		try {
			this.c = DriverManager.getConnection("jdbc:mariadb://localhost:3325/a_kutuphane?user=root&password=iskenderunlu2244");
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}
}
