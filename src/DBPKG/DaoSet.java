package DBPKG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoSet {
	
	protected Connection con;
	protected PreparedStatement pstmt;
	protected ResultSet rs;

	public Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection
		  ("jdbc:oracle:thin:@localhost:1521:xe","db7","1234");
		return con;
	}
	public void closeDB() {
		if(rs!=null) try {rs.close();}catch(SQLException e) {}
		if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
	}
}
