package Modelo;

import java.sql.Date;
import java.sql.Time;

public class Turno {
	
	private int idTurno;
	private Date fecha;
	private Time hora;
	private String tipoTurno;
	private boolean estado;
	private Integer idPersona;
	
	
	public Turno(int idTurno, Date fecha, Time hora) {
		super();
		this.idTurno = idTurno;
		this.fecha = fecha;
		this.hora = hora;
	}

	public Turno(int idTurno, Date fecha, Time hora, Character tipoTurno, boolean estado, int idPersona) {
		super();
		this.idTurno = idTurno;
		this.fecha = fecha;
		this.hora = hora;
		this.estado = estado;
		this.idPersona = idPersona;
	}
	
	public int getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	
	// constructo vacio turno
	
	public Turno() {
		
	}
	
	// set TipoTurno
	
	public void setTipoTurno(String tipoTurno) {
	
		this.tipoTurno=tipoTurno;

	}
	public String getTipoTurno() {
		
		return (""+this.tipoTurno);
	}

}
