package dataBase;

import java.sql.*;

public class UpdateData {
	
	connection connect = new connection();
	
	public UpdateData() {}
	
	public void update_isRead(String query) throws SQLException {
		Connection c = connect.connectDB();
		
		Statement st = c.createStatement();
		st.executeUpdate(query);
		st.close();
	}
}
