package com.example.qlcovid.string;
import com.example.qlcovid.model.*;
import com.example.qlcovid.string.*;
import java.sql.*;

public class AdminString {
	public static String dbURL; //= "jdbc:sqlserver://NIERA-ASUS\\SQLEXPRESS; databaseName=HeThongQuanLiCovid";
	public static String username; //= "sa";
	public static String password; //= "12345678";
	
	public AdminString() {
		UtilsString dtb = new UtilsString();
		//dbURL = dtb.dbURL+"; databaseName="+dtb.DATABASENAME;
		dbURL = dtb.dbURL;
		username = dtb.username;
		password = dtb.password;
	}
	//Check Supevisor-id exists in ql_user
	public int checkAccountExist(String id) throws SQLException {
		String query = "SELECT * FROM ql_user WHERE username='"+id+"'";
		Connection conn = DriverManager.getConnection(dbURL, username, password);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		if(rs.next())
			return 1;
		return 0;
	}
	
	//Check 
	public int checkManagerAccountExist(String id) throws SQLException {
		String query = "SELECT * FROM ql_user WHERE username='"+id+"' AND user_role='supevisor'";
		System.out.println(query);
		Connection conn = DriverManager.getConnection(dbURL, username, password);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		if(rs.next())
			return 1;
		return 0;
	}
	
	public int checkTreatmentPlaceExist(String id) throws SQLException {
		String query = "SELECT * FROM treatment_place WHERE treatment_place_id='"+id+"'";
		//System.out.println(query);
		Connection conn = DriverManager.getConnection(dbURL, username, password);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		if(rs.next())
			return 1; // trùng ID
		return 0; // k trùng ID
	}
	
	public ResultSet searchManagerHistory(String id) throws SQLException {
		String query = "SELECT * FROM edit WHERE supevisor_id='"+id+"'";
		//System.out.println(query);
		Connection conn = DriverManager.getConnection(dbURL, username, password);
		Statement st = conn.createStatement();
		return st.executeQuery(query);
	}
	
	public void addManagerAccount(String user, String pass) throws SQLException { 
		String query = "INSERT INTO ql_user VALUES ('"+user+ "','"+Hashing.getHash(pass)+"','supevisor','Y')";
		//System.out.print(query);

		Connection conn = DriverManager.getConnection(dbURL, username, password);
		Statement st = conn.createStatement();
		st.executeUpdate(query);
	}
	
	public void banManagerAccount(String user) throws SQLException { 
		String query = "UPDATE ql_user SET user_validation='N' WHERE username='"+user+ "'";
		//System.out.print(query);

		Connection conn = DriverManager.getConnection(dbURL, username, password);
		Statement st = conn.createStatement();
		st.executeUpdate(query);
	}
	
	public ResultSet searchTreatmentPlace() throws SQLException {
		String query = "SELECT * FROM treatment_place";
		//System.out.println(query);
		Connection conn = DriverManager.getConnection(dbURL, username, password);
		Statement st = conn.createStatement();
		return st.executeQuery(query);
	}
	
	public void addTreatmentPlace(String name, String id, int capicity, int holding) throws SQLException { 
		String query = "INSERT INTO treatment_place VALUES ('"+name+ "','"+id+"','"+capicity+"','"+holding +"')";
		//System.out.print(query);

		Connection conn = DriverManager.getConnection(dbURL, username, password);
		Statement st = conn.createStatement();
		st.executeUpdate(query);
	}
	
	public void deleteTreatmentPlace(String name, String id, int capicity, int holding) throws SQLException { 
		String query = "DELETE FROM treatment_place WHERE treatment_place_name ='"+name+ "' AND treatment_place_id='"+id+"'AND capacity='"+capicity+
				"' AND current_holding='"+holding +"'";
		//System.out.print(query);

		Connection conn = DriverManager.getConnection(dbURL, username, password);
		Statement st = conn.createStatement();
		st.executeUpdate(query);
	}
	public void updateTreatmentPlace(String name, String id, int capicity, int holding) throws SQLException { 
		String query = "UPDATE treatment_place SET treatment_place_name='"+ name + "',capacity = '"+ capicity +"',current_holding='"+ holding
						+"' WHERE treatment_place_id='"+id+ "'";
		System.out.print(query);

		Connection conn = DriverManager.getConnection(dbURL, username, password);
		Statement st = conn.createStatement();
		st.executeUpdate(query);
	}
	
}
