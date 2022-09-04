package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String title ="TEST";
		String writerId ="newlec";
		String content ="hahaha";
		String files ="";
		
		String url = "jdbc:oracle:thin:@DB20220609152312_high?TNS_ADMIN=/Library/opt/OracleCloudWallet/ATP";
		String sql = "INSERT INTO notice(	"+
				" 	title," +
				"	writer_Id," +
				"	content," +
				"	files" +
				") VALUES(?,?,?,?)";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"ADMIN","Rlarldnd5345411!");
//		Statement st = con.createStatement();
//		st.executeQuery(sql);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,title);
		st.setString(2,writerId);
		st.setString(3,content);
		st.setString(4,files);
		
		int result = st.executeUpdate();
		
		
		st.close();
		con.close();
	} // main
	
} // end class
