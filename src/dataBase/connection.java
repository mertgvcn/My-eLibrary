package dataBase;
import java.sql.*;


public class connection {
	
	public Connection c;
	
	public connection() {
		
	}
	
	//DataBase'e ba�lanma methodu
	   //buran�n amac� "jdbc:mariadb..." k�sm�n� s�rekli yazmamak.
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
