package dataBase;

import java.sql.*;

public class LoanedOutData {
	
	connection connect = new connection();
	
	public LoanedOutData() {
		
	}
	
	public void transferData(String query) throws SQLException {
		
		Connection c = connect.connectDB();
		
		Statement st = c.createStatement();
		st.executeUpdate(query);
		st.close();
	}
}
