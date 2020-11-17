package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Database {

	private Connection conn;
	public Database() {

		String dbServer = "jdbc:mysql://127.0.0.1:3306/PlannerDB?allowPublicKeyRetrieval=true&useSSL=false";

		String userName = "planneruser";
		String password = "planner1234";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbServer, userName, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int addCellData(String text, int day, int month, int year, boolean complete) {
		int id = 0;
		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			ResultSet rs;
			
			rs = stmt.executeQuery("select max(cid) from celldata");
			while (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			stmt.close();

			PreparedStatement inststmt = conn
					.prepareStatement(" insert into celldata (cid,cellText,cellDay,cellMonth,cellYear,isComplete) values(?,?,?,?,?,?) ");

			// first column has the new cell id that is unique
			inststmt.setInt(1, id + 1);
			// second ? has the text of the cell
			inststmt.setString(2, text);
			// third ? has the day
			inststmt.setInt(3, day);
			// fourth ? has the month
			inststmt.setInt(4, month);
			// fifth ? has the year
			inststmt.setInt(5, year);
			// sixth ? has if completed
			inststmt.setBoolean(6, complete);
			
			inststmt.executeUpdate();
			
			inststmt.close();
			conn.commit();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return id + 1;
		
	}
	
	public void deleteCellData(int cid) {
		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM celldata WHERE cid = " + cid + ";");
			stmt.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setComplete(int cid, boolean complete)
	{
		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE celldata SET isComplete =" + complete + " WHERE cid = " + cid + ";");
			stmt.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getTask(int day, int month, int year)
	{
		ArrayList<String> toShow = new ArrayList<String>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM celldata WHERE cellDay = " + day + " AND cellMonth = " + month + " AND cellYear = " + year + ";");
			int i = 1;
			while(rs.next())
			{
				toShow.add(rs.getString(i));
				toShow.add(rs.getString(i++));
				toShow.add(rs.getString(i+=4));	
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(toShow);
		return toShow;
	}
	

}
