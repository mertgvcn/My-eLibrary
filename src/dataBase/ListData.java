package dataBase;

import java.sql.*;

import javax.swing.table.DefaultTableModel;

public class ListData {
	
	connection connect = new connection();
	
	public ListData() {
		
	}
	
	
	public void listData(String dataTable, DefaultTableModel dtm , Object[] row) throws SQLException {
		Connection c = connect.connectDB();
		
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM " + dataTable);
		
		while(rs.next()) {
			row[0] = " " + rs.getInt("book_id");
			row[1] = rs.getString("book_name");
			row[2] = rs.getString("book_author");
			row[3] = " " + rs.getInt("book_page");
			
			if(rs.getShort("book_isRead") == 0) {  //okunmadýysa (-) , okunduysa (+) yazýcak
				row[4] = "    -";
			}else {
				row[4] = "    +";
			}

			dtm.addRow(row);
		}

	}
	
}
