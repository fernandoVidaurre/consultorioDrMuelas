package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.NullType;

public class PacienteDao {
	
	// aqui se escribiran los metodos que acceden a la bd por parte de paciente
	
	// metodo que muestre fechas no turnos
	public List<Date> listarDias() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Date dia;
		List<Date> dias = new ArrayList<>();
		try {
			conn = Conexion.getConnection();
			String sql = "SELECT DISTINCT fecha FROM turno WHERE fecha > ? LIMIT 5";
			stmt = conn.prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(LocalDate.now()));
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				dias.add(rs.getDate("fecha"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dias;
	}
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
			
			//System.out.println("ok");
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

	// consulta la edad de una persona, para poder escribir que tipo de turno pertenece
	private int consultarEdad(int idPersona) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int edad = 0;
		Date fechaNac = null;
		
		try {
			conn = Conexion.getConnection();
			String sql = "SELECT fechaNac FROM persona WHERE idPersona=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idPersona);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				fechaNac = rs.getDate("fechaNac");
			}
			
			edad =  LocalDate.now().getYear() - fechaNac.getYear();
			
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return edad;
		
	}
	
	public void cargarTurno(Turno t, int idPersona) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int edad;
		//ResultSet rs = null;
		//Turno turno= null;
		
		try {
			edad = consultarEdad(idPersona);
			conn = Conexion.getConnection();
			String sql = "UPDATE turno SET tipoturno=?,estado=1, persona_idPersona="+idPersona+" WHERE idTurno=?"; // tira error si uso el setInt
			stmt = conn.prepareStatement(sql);
			if (edad <= 13) {
				stmt.setString(1, "C");
			} else {
				stmt.setString(1, "M");
			}
			
			stmt.setInt(2, t.getIdTurno());
			//stmt.setInt(3, idPersona);
			
			stmt.executeUpdate();
			
			//Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ver ficha de tratamientos
	private int verificarFichaMedica(int idPersona) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int idFicha = -1;
		
		try {
			conn = Conexion.getConnection();
			String sql = "SELECT fichamedica_idFichaMedica FROM persona WHERE idPersona=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idPersona);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				idFicha = rs.getInt("fichamedica_idFichaMedica");
			}
			
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return idFicha;
	}
	
	public List<Tratamiento> verTratamientos(int idPersona) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Tratamiento> tratamientos = new ArrayList<>();

		try {
			int idFicha = verificarFichaMedica(idPersona);
			if (idFicha > -1){
				conn = Conexion.getConnection();
				String sql = "SELECT * FROM tratamiento WHERE fichamedica_idFichaMedica=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, idFicha);
				rs = stmt.executeQuery();
				
				while (rs.next()) {
					Date fecha = rs.getDate("fecha");
					String nombreTratamiento = rs.getString("nombreTratamiento");
					String detalle = rs.getString("detalle");
					Tratamiento tratamiento = new Tratamiento(fecha, nombreTratamiento, detalle);
					tratamientos.add(tratamiento);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tratamientos;
	}

	// metodo que trae los turnos de un paciente
	
	public List<Turno> consultarTurno(Date fechaActual, int idPersona) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Turno turno= null;
		
		List<Turno> turnos = new ArrayList<>();
		try {
			conn = Conexion.getConnection();
			String sql = "SELECT * FROM turno WHERE persona_idPersona=? order by hora asc";
			stmt = conn.prepareStatement(sql);
			//stmt.setObject (1, fechaActual);
			stmt.setInt(1,idPersona );
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
			
			//System.out.println("ok");
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return turnos;
	}
	
	public boolean cancelarTurno(Turno turno) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean salida= false;
		
		try {
			
			conn = Conexion.getConnection();
			String sql = "UPDATE turno SET estado=0, persona_idPersona=? WHERE idTurno=?"; 
			stmt = conn.prepareStatement(sql);
			stmt.setNull(1, Types.NULL);
			stmt.setInt(2,turno.getIdTurno());						
			if( stmt.executeUpdate() == 1) {
				salida = true;
			}else {
				salida = false;
			}
			
			
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return salida;
	}
	
}