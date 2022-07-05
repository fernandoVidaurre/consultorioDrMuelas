package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	
	// permite la conexion a la base de datos
	
	// cambiar el user y el pass para pruebas aunque nose que tan necesario sea
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/drmuelasbd";
	
	static final String USER = "root";
	static final String PASS = "root";
	
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}
	
	public static void close(ResultSet rs) throws SQLException {
		rs.close();
	}
	
	public static void close(Statement stmt) throws SQLException {
		stmt.close();
	}
	
	public static void close(Connection conn) throws SQLException {
		conn.close();
	}
	
	public static void close(PreparedStatement stmt) throws SQLException {
		stmt.close();
	}
}
