package dataBase;

import java.sql.*;

public class RemoveData {
	public connection connect = new connection();
	
	
	public RemoveData() {}//nesne oluþturmak için boþ constructor
	
	
	
	public void removeBook(String dataTable, int id) throws SQLException {
		
		Connection c = connect.connectDB();
		
		String query = "delete from " + dataTable + " where book_id=" + id;
		Statement s = c.createStatement();
		s.executeUpdate(query);
		s.close();
	}
	
	
	//bir veriyi silerken id'si yeterli olacaðý için preparedstatement'a gerek yok.
	
	//String query = "delete from elibrary where book_id=?";
	//PreparedStatement ps = c.prepareStatement();
	//ps.setInt(1, id);
	//ps.executeUpdate();
	//ps.close(); 
	
	//yine de bu þekilde de yapýlabilirdi..
}
