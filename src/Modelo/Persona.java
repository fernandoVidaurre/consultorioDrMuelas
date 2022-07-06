package Modelo;

import java.sql.Date;

public class Persona {
	
	private Integer idPersona;
	private String dni;
	private String nombre;
	private String apellido;
	private Date fechaNac;
	private String sexo;
	private String telefono;
	private String email;
	private String domicilio;
	private Integer idCuenta;
	
	public Persona(){}
	
	
	public Persona(String dni, String nombre, String apellido, Date fechaNac, String telefono, String email,
			String domicilio, Integer idCuenta) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.telefono = telefono;
		this.email = email;
		this.domicilio = domicilio;
		this.idCuenta = idCuenta;
	}


	public Persona(String dni, String nombre, String apellido, Date fechaNac, String sexo, String telefono,
			String email, String domicilio) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.sexo = sexo;
		this.telefono = telefono;
		this.email = email;
		this.domicilio = domicilio;
	}

	public Persona(int idPersona, String dni, String nombre, String apellido, Date fechaNac, String sexo,
			String telefono, String email, String domicilio, int idCuenta) {
		super();
		this.idPersona = idPersona;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.sexo = sexo;
		this.telefono = telefono;
		this.email = email;
		this.domicilio = domicilio;
		this.idCuenta = idCuenta;
	}
	
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	
	
}
