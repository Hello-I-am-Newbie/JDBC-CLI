package com.danny.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.danny.app.entitiy.Notice;

public class NoticeService {
	
	private String url = "jdbc:oracle:thin:@DB20220609152312_high?TNS_ADMIN=/Library/opt/OracleCloudWallet/ATP";
	private String uid = "ADMIN";
	private String pwd = "Rlarldnd5345411!";
	private String driver = "oracle.jdbc.driver.OracleDriver";
 	public List<Notice> getList(int page, String Field, String query) throws ClassNotFoundException, SQLException{
	
 		int start = 1 + (page-1)*10;
 		int end = 10*page;
 		
		String sql = "SELECT * FROM NOTICE_VIEW2 WHERE " + Field + " LIKE ? AND NUM BETWEEN ? AND ?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		st.setInt(2,  start);
		st.setInt(3, end);
		ResultSet rs = st.executeQuery();
	
		List<Notice> list = new ArrayList<Notice>();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("hit");
			String files = rs.getString("FILES");
			
			Notice notice = new Notice(
								id,
								title,
								writerId,
								regDate,
								content,
								hit,
								files
							);
			
			list.add(notice);
			
		} // while
		
		
		rs.close();
		st.close();
		con.close();
		
		return list;
	}
 	
	public int getCount(int page,String Field, String query) throws SQLException, ClassNotFoundException {
		int start = 1 + (page-1)*10;
 		int end = 10*page;
 		
		int count = 0;
 		
		String sql = "SELECT COUNT(ID) COUNT FROM NOTICE_VIEW2 WHERE " + Field + " LIKE ? AND NUM BETWEEN ? AND ?"; 
//				"SELECT COUNT(ID) COUNT FROM NOTICE";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		st.setInt(2,  start);
		st.setInt(3, end);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) count = rs.getInt("COUNT");
	
		rs.close();
		st.close();
		con.close();
		
		return count;
	} // getCount
	
	public String getContent(int id) throws ClassNotFoundException, SQLException{
		
		String sql = "SELECT * FROM NOTICE_VIEW WHERE ID = ?";
		
		String content = "";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
	
		
		if(rs.next()) content = rs.getString("CONTENT");
			
		
		
		rs.close();
		st.close();
		con.close();
		
		return content;
	} // getContent
 	
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		String title = notice.getTitle();
		String writerId = notice.getWriterId();
		String content = notice.getContent();
		String files = notice.getFiles();
		Date regdate = notice.getRegDate();
		int hit = notice.getHit();
		
		String sql = "INSERT INTO notice(	"+
				" 	title," +
				"	writer_Id," +
				"	content," +
				"	files" +
				") VALUES(?,?,?,?)";

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
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
		
		return result;
	}
	
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		String title = notice.getTitle();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
		
		String sql = "UPDATE NOTICE SET"
				+ "    TITLE = ?,"
				+ "    CONTENT = ?,"
				+ "    FILES = ?"
				+ "WHERE ID = ?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
//		Statement st = con.createStatement();
//		st.executeQuery(sql);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,title);
		st.setString(2,content);
		st.setString(3,files);
		st.setInt(4,id);
		
		int result = st.executeUpdate();
		
		
		st.close();
		con.close();
		
		return result;
	}
	public int delete(int id) throws ClassNotFoundException, SQLException {
		
		String sql = "DELETE NOTICE WHERE ID = ?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
//		Statement st = con.createStatement();
//		st.executeQuery(sql);
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,id);
		
		int result = st.executeUpdate();
		
		
		st.close();
		con.close();
		
		return result;
	}

	
} // end class
