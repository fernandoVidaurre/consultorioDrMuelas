package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDao {
	// aqui se escribiran los metodos que acceden a la bd por parte de Administrador
	
	// ver fichas de pacientes
	//public List<FichaMedica> traerFichas() {}

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

	// generar Turnos
	
	private List<Time> cargarHorario() {
		List<Time> horarios = new ArrayList<Time>();
		horarios.add(Time.valueOf(LocalTime.of(8, 0)));
		horarios.add(Time.valueOf(LocalTime.of(8, 30)));
		horarios.add(Time.valueOf(LocalTime.of(9, 0)));
		horarios.add(Time.valueOf(LocalTime.of(9, 30)));
		horarios.add(Time.valueOf(LocalTime.of(10, 0)));
		horarios.add(Time.valueOf(LocalTime.of(10, 30)));
		horarios.add(Time.valueOf(LocalTime.of(11, 0)));
		horarios.add(Time.valueOf(LocalTime.of(11, 30)));
		horarios.add(Time.valueOf(LocalTime.of(12, 0)));
		horarios.add(Time.valueOf(LocalTime.of(14, 0)));
		horarios.add(Time.valueOf(LocalTime.of(14, 30)));
		horarios.add(Time.valueOf(LocalTime.of(15, 0)));
		horarios.add(Time.valueOf(LocalTime.of(15, 30)));
		horarios.add(Time.valueOf(LocalTime.of(16, 0)));
		horarios.add(Time.valueOf(LocalTime.of(16, 30)));
		horarios.add(Time.valueOf(LocalTime.of(17, 0)));
		horarios.add(Time.valueOf(LocalTime.of(18, 0)));
		
		return horarios;
	}
	
	public boolean generarTurnos(Date fecha) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int i;
		List<Time> horarios = cargarHorario();
		
		try {
			conn = Conexion.getConnection();
			String sql = "INSERT INTO turno(fecha, estado, hora) VALUES(?, 0, ?)";
			stmt = conn.prepareStatement(sql);
			for (i = 0; i < horarios.size(); i++) {
				stmt.setDate(1, fecha);
				stmt.setTime(2, horarios.get(i));
				stmt.executeUpdate();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;

	}
	// generar informe
	public Informe generarInforme(int mes) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int emergencia = 0; 
		int chico = 0; 
		int mayor = 0;
		Informe informe = null;
		
		try {
			conn = Conexion.getConnection();
			String sql = "SELECT * FROM turno WHERE DATE_FORMAT(fecha, '%m')=? AND estado=1";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mes);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				String tipoTurno = rs.getString("tipoTurno");
				
				switch (tipoTurno) {
				case "E":
					emergencia++;
					break;
				case "M":
					mayor++;
					break;
				case "C":
					chico++;
					break;
				}
			}
			
			informe = new Informe(emergencia, chico, mayor);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return informe;
	}
	// SELECT * FROM turno WHERE date_format(fecha, '%m')= 7
}
