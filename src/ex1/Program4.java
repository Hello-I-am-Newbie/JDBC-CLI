package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program4 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		int id = 254;
		
		String url = "jdbc:oracle:thin:@DB20220609152312_high?TNS_ADMIN=/Library/opt/OracleCloudWallet/ATP";
		String sql = "DELETE NOTICE WHERE ID = ?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"ADMIN","Rlarldnd5345411!");
//		Statement st = con.createStatement();
//		st.executeQuery(sql);
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,id);
		
		int result = st.executeUpdate();
		
		
		st.close();
		con.close();
	} // main
	
} // end class
