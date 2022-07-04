/*
package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import principal.Conexion;
import principal.Persona;
import principal.PersonaDao;
import principal.Turno;

public class Ejemplo {
	public Persona traerDatos(String nombre) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Persona p1 = null;
		try {
			conn = Conexion.getConnection();
			String sql = "SELECT * FROM persona WHERE nombre=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nombre);
			//stmt.setInt(1, 1);			
			rs = stmt.executeQuery();
	
			while (rs.next()) {
				int id = rs.getInt("id_persona");
				String nuevoNombre = rs.getString("nombre");
				String dni = rs.getString("dni");
				p1 = new Persona(id, nombre, dni);
			}
		
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p1;
	}
	
	public List<Turno> consultarTurnoByDni(String dni) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Turno turno= null;
		int id = 0;
		Date fecha = null;
		Time hora = null;
		List<Turno> turnos = new ArrayList<>();
		
		try {
			conn = Conexion.getConnection();
			String sql = "SELECT id_persona FROM persona WHERE dni=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dni);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				id = rs.getInt("id_persona");
			}
			
			sql = "SELECT * FROM turno WHERE id_persona=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				fecha = rs.getDate("fecha");
				hora = rs.getTime("hora");
				turno = new Turno(fecha, hora);
				turnos.add(turno);
			}
			
			
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return turnos;
	}
	
	private boolean buscar(String dni) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean resultado = false;
		
		try {
			conn = Conexion.getConnection();
			String sql = "SELECT * FROM persona WHERE dni=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dni);
			rs = stmt.executeQuery();
			
			// verifica si el resultSet esta vacio 
			// true si no lo encontro, false si lo encontro
			 
			if (!rs.next()) // esto no tiene datos
				resultado = true;

			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;

	}
	
	public void registrar(Persona persona) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			// no lo encuentra entra al if, ver metodo buscar
			if (buscar(persona.getDni())) {
				conn = Conexion.getConnection();
				String sql = "INSERT INTO persona(nombre, dni) VALUES(?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, persona.getNombre());
				stmt.setString(2, persona.getDni());
				
				stmt.executeUpdate();
				
				//Conexion.close(rs);
				Conexion.close(stmt);
				Conexion.close(conn);
			} else {
				System.out.println("No se pudo registrar, ya existe una persona con ese dni");
			}
						
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void registrarTurno(Turno turno, int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		// esto debo arreglar, no puedo eliminar el turno
		// hay que ponerle un id propio a la tabla turno, para poder eliminarla, sino creo que la hace dependiente de la otra tabla
		try {
			conn = Conexion.getConnection();
			String sql = "INSERT INTO turno(id_persona, fecha, hora) VALUES(?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setDate(2, turno.getFecha());
			stmt.setTime(3, turno.getHora());
			
			stmt.executeUpdate();
			
			Conexion.close(stmt);
			Conexion.close(conn);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		PersonaDao pdao = new PersonaDao();
		
		// trae el dato de una persona y muestra el dni
		Persona p1 = pdao.traerDatos("Franco");
		
		System.out.println(p1.getDni());

		// muestra el turno de una persona con un dni
		//Turno turno = pdao.consultarTurnoByDni(p1.getDni());
		List<Turno> turnos = pdao.consultarTurnoByDni(p1.getDni());
		//System.out.println(turno);
		for (int i = 0; i < turnos.size(); i++) {
			System.out.println(turnos.get(i));
		}
		// intenta registrar una persona ya existente
		Persona p2 = new Persona("Jose", "123456789");
		
		pdao.registrar(p2);
		
		// registrar nuevo turno dando un id
		Turno turnoTest = new Turno(Date.valueOf("2022-8-1"), Time.valueOf("15:00:00"));
		
		pdao.registrarTurno(turnoTest, 1);
		
	}
}
*/
