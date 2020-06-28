package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class sqlmanager {
	String url ="jdbc:mysql://127.0.0.1:3306/onlin?user=root&password=my510813";
	
	public String select(String str,String need) {
		String res = null;
		try {
			Connection connection=DriverManager.getConnection(url);
			Statement state=connection.createStatement();
			ResultSet rs =state.executeQuery(str);
			while(rs.next()) {
				res=rs.getString(need);
			}
			
			connection.close();
			state.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(res);
		return res;
	}
	
	public boolean add(String str) {
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ptmt = connection.prepareStatement(str);
			ptmt.execute();
			
			connection.close();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean remove(String str) {
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ptmt = connection.prepareStatement(str);
			ptmt.execute();
			
			connection.close();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean set(String str) {
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ptmt = connection.prepareStatement(str);
			ptmt.execute();
			
			connection.close();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
