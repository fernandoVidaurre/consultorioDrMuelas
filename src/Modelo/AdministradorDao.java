package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public class AdministradorDao {
	// aqui se escribiran los metodos que acceden a la bd por parte de Administrador
	
	// ver fichas de pacientes
	// cargar un paciente a emergencias
	
	
	private void cargarTurno(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = Conexion.getConnection();
			String sql = "UPDATE turno SET tipoturno='E', estado=1 WHERE idTurno=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
			
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public Turno cargarEmergencia() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Turno turno = null;
		Date dia = Date.valueOf(LocalDate.now());
		int idTurno = 0;
		
		try {
			conn = Conexion.getConnection();
			String sql = "SELECT * FROM turno WHERE estado=false";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			
			if (rs.next()) {
				idTurno = rs.getInt("idTurno");
				cargarTurno(idTurno);
				int idnuevoTurno = idTurno;
				Date fecha = rs.getDate("fecha");
				Time hora = rs.getTime("hora");
				turno = new Turno(idnuevoTurno, fecha, hora);
			}

			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return turno;
	}
	// generar informe
}
