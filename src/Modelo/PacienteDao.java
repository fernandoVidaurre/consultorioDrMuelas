package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {
	
	// aqui se escribiran los metodos que acceden a la bd por parte de paciente
	// crear turno
	//dar de baja turno
	//ver ficha de tratamientos
	
	
	// metodo para listar turnos
	public List<Turno> consultarTurno(Date fechaActual) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Turno turno= null;
		
		List<Turno> turnos = new ArrayList<>();
		try {
			conn = Conexion.getConnection();
			String sql = "SELECT * FROM turno WHERE fecha=? order by hora asc";
			stmt = conn.prepareStatement(sql);
			stmt.setObject (1, fechaActual);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				boolean estado = false;
				 
				 if(rs.getInt("estado") == 1) {
					 
					 estado = true;
				 }
				
				
				 turno = new Turno(rs.getInt("idTurno"),rs.getDate("fecha"),rs.getTime("hora"));
				 turno.setEstado(estado);
				turnos.add(turno);
				
			}
			
			System.out.println("ok");
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return turnos;
	}
	


	// metodo para asignar un turno

	public void cargarTurno(Turno t) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		//ResultSet rs = null;
		//Turno turno= null;
		
		try {
			conn = Conexion.getConnection();
			String sql = "UPDATE turno SET tipoturno=?,estado=1 WHERE idTurno=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,t.getTipoTurno());
			stmt.setInt(2, t.getIdTurno());
			
			stmt.executeUpdate();
			
			//Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}