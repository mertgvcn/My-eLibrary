package dataBase;
import java.sql.*;

public class AddData {
	
	public connection connect = new connection();
	
	public AddData() {
	}
	
	
	//DataBase'e veri ekleme methodu                                        //preparedstatement hata vermesin diye throws ettik.
	public void addData(String dataTable, String name, String author, int page, short isRead) throws SQLException {

		//connection sýnýfýmýzdan baðlandýk, url yi bi daha yazmadýk.
		Connection co = connect.connectDB();
		
		//veri ekleme iþlemi :
		String query = "INSERT INTO " + dataTable + " (book_name, book_author, book_page, book_isRead) VALUES (?,?,?,?)";
		PreparedStatement pt = co.prepareStatement(query);
		pt.setString(1, name);  //birinci ? = name
		pt.setString(2, author);//ikinci  ? = author
		pt.setInt(3, page);
		pt.setInt(4, isRead);
		pt.executeUpdate(); //*********************important for saving datas to sql!
		pt.close();
		
		
	}	
}
