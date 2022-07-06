package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaDao {
	
	// REGISTRO DE PACIENTE
	private boolean verificarCuenta(String username) {
		// devuelve false si no se encuentra
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean verifica = true;
		
		try {
			conn = Conexion.getConnection();
			String sql = "SELECT * FROM cuenta WHERE user=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			
			if (!rs.next()) {
				verifica = false;
			}
			
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return verifica;
	}
	
	private boolean verificaPersona(String dni) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean verifica = true;
		
		try {
			conn = Conexion.getConnection();
			String sql = "SELECT * FROM persona WHERE dni=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dni);
			rs = stmt.executeQuery();
			
			if (!rs.next()) {
				verifica = false;
			}
			
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return verifica;
	}
	
	private int registrarCuenta(Cuenta cuenta) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int cuentaNueva = 0;
		
		try {
			conn = Conexion.getConnection();
			String sql = "INSERT INTO cuenta(user, tipoUsuario, password) VALUES(?,'P',?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cuenta.getUsuario());
			stmt.setString(2, cuenta.getPassword());
			stmt.executeUpdate();
			
			sql = "SELECT * FROM cuenta WHERE user=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cuenta.getUsuario());
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				cuentaNueva = rs.getInt("id_cuenta");
			} else {
				cuentaNueva = -1;
			}
			
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cuentaNueva;
	}
	
	public boolean registrar(Persona persona, Cuenta cuenta) {
		Connection conn = null;
		PreparedStatement stmtp = null;
		int nuevaCuenta;
		boolean verificar = false;
		
		try {
			if (!verificarCuenta(cuenta.getUsuario())) {
				conn = Conexion.getConnection();
				if (!verificaPersona(persona.getDni())) {
					nuevaCuenta = registrarCuenta(cuenta);
					String sql = "INSERT INTO persona(dni, apellido, nombre, fechaNac, sexo, telefono, email, domicilio, cuenta_id_cuenta) VALUES(?,?,?,?,?,?,?,?,?)";
					stmtp = conn.prepareCall(sql);
					stmtp.setString(1, persona.getDni());
					stmtp.setString(2, persona.getApellido());
					stmtp.setString(3, persona.getNombre());
					stmtp.setDate(4, persona.getFechaNac());
					switch(persona.getSexo()) {
					case "M":
						stmtp.setInt(5, 1);
						break;
					case "F":
						stmtp.setInt(5, 2);
						break;
					case "X":
						stmtp.setInt(5, 3);
					break;
					}
		
					stmtp.setString(6, persona.getTelefono());
					stmtp.setString(7, persona.getEmail());
					stmtp.setString(8, persona.getDomicilio());
					stmtp.setInt(9, nuevaCuenta);
					
					stmtp.executeUpdate();
					
					Conexion.close(stmtp);
					Conexion.close(conn);
					
					verificar = true;
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return verificar;
	}
	
	// LOGIN
	
	// devuelve un valor >0 si es paciente -1 si es administrador y -2 si no esta registrado
	
	public int logIn(Cuenta cuenta) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ResultSet rsp = null;
		int idPaciente = -2;
		int idCuenta = -1;
		
		if (verificarCuenta(cuenta.getUsuario())) {
			try {
				conn = Conexion.getConnection();
				String sql = "SELECT * FROM cuenta WHERE user=? AND password=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, cuenta.getUsuario());
				stmt.setString(2, cuenta.getPassword());
				rs = stmt.executeQuery();
				
				if (rs.next()) {
					String rol = rs.getString("tipoUsuario");
					idCuenta = rs.getInt("id_cuenta");
					switch(rol) {
					case "P":
						sql = "SELECT * FROM persona WHERE cuenta_id_cuenta=?";
						stmt = conn.prepareStatement(sql);
						stmt.setInt(1, idCuenta);
						rsp = stmt.executeQuery();
						
						
						
						if (rsp.next()) {
							idPaciente = rsp.getInt("idPersona");
							//Conexion.close(rsp);
						}
						Conexion.close(rsp);
						break;
					case "A":
						idPaciente = -1;
						break;
					}
				}
				
				Conexion.close(rs);
				Conexion.close(stmt);
				Conexion.close(conn);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return idPaciente;
	}
}
