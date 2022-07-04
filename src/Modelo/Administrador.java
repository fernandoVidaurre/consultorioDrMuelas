package Modelo;

import java.sql.Date;

public class Administrador extends Persona {  // Marca error por que falta crear la clase persona asignada a luis
	
	// Atributos
	private String cargo;

	// Constructor sin Parametros
	public Administrador() {
		super();
	}

	public Administrador(String cargo,int idPersona,String dni,String apellido,String nombre,Date fechaNac,char sexo,String telefono,
			String email,String domicilio,int idCuenta, int idFichaMedica) {
		
		super( idPersona,dni,apellido,nombre,fechaNac,sexo,telefono,email,domicilio,idCuenta);
		this.cargo = cargo;
	}

	// Getters
	public String getCargo() {
		return cargo;
	}
	
	//Setters
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	
	    
	
}
