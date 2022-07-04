package Modelo;
import java.sql.Date;

public class Paciente extends Persona {  // Marca error por que falta crear la clase persona asignada a luis ser� presentada hasta el d�a 05-07

	// Atributos
	private int fichaMedica;

	// Constructor sin Parametros
	public Paciente() {
		super();
	
	}

	// Constructor con parametros propios y de su SuperClase
	public Paciente(int fichaMedica,int idPersona,String dni,String apellido,String nombre,Date fechaNac,char sexo,String telefono,
			String email,String domicilio,int idCuenta, int idFichaMedica ) {
		
		super(idPersona,dni,apellido,nombre,fechaNac,sexo,telefono,email,domicilio,idCuenta);
		this.fichaMedica = fichaMedica;
	
	}

	// Getters
	public int getFichaMedica() {
		return fichaMedica;
	}

	// Setters
	public void setFichaMedica(int fichaMedica) {
		this.fichaMedica = fichaMedica;
	}

	

}
