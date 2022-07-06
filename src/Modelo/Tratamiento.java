package Modelo;

import java.sql.Date;

public class Tratamiento {
	
	// Atributos
	
	private int idTratamiento;
	private Date fecha;
	private String nombreTratamiento;
	private String detalle;
	private int idFichaMedica;
	
	// Constructor sin parametros
	
	public Tratamiento() {
	
	}

	// Constructor con Parametros
	public Tratamiento(Date fecha, String nombreTratamiento, String detalle) {
		super();
		this.fecha = fecha;
		this.nombreTratamiento = nombreTratamiento;
		this.detalle = detalle;
	}
	
	public Tratamiento(int idTratamiento, Date fecha, String nombreTratamiento, String detalle, int idFichaMedica) {
		this.idTratamiento = idTratamiento;
		this.fecha = fecha;
		this.nombreTratamiento = nombreTratamiento;
		this.detalle = detalle;
		this.idFichaMedica = idFichaMedica;
	}

	// Getters

	public int getIdTratamiento() {
		return idTratamiento;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getNombreTratamiento() {
		return nombreTratamiento;
	}

	public String getDetalle() {
		return detalle;
	}

	public int getIdFichaMedica() {
		return idFichaMedica;
	}

	
	// Setters
	
	public void setIdTratamiento(int idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setNombreTratamiento(String nombreTratamiento) {
		this.nombreTratamiento = nombreTratamiento;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public void setIdFichaMedica(int idFichaMedica) {
		this.idFichaMedica = idFichaMedica;
	}

	
	// toString
	@Override
	public String toString() {
		return "Tratamiento [idTratamiento=" + idTratamiento + ", fecha=" + fecha + ", nombreTratamiento="
				+ nombreTratamiento + ", detalle=" + detalle + ", idFichaMedica=" + idFichaMedica + "]";
	}
	
	public void mostrarDatos() {
		System.out.println("Nombre: " + this.nombreTratamiento);
		System.out.println("Detalle :" + this.detalle);
		System.out.println("Fecha: " + this.fecha);
		
	}
}
