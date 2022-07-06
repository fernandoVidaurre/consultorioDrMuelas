package Modelo;
import java.sql.Date;

public class Paciente extends Persona {  // Marca error por que falta crear la clase persona asignada a luis ser� presentada hasta el d�a 05-07

	// Atributos
	private int fichaMedica;

	// Constructor sin Parametros
	public Paciente() {
		super();
	
	}


	public Paciente(String dni, String nombre, String apellido, Date fechaNac, String telefono, String email,
			String domicilio, Integer idCuenta) {
		super(dni, nombre, apellido, fechaNac, telefono, email, domicilio, idCuenta);
		this.fichaMedica = -1;
		// TODO Auto-generated constructor stub
	}


	public Paciente(String dni, String nombre, String apellido, Date fechaNac, String sexo, String telefono,
			String email, String domicilio) {
		super(dni, nombre, apellido, fechaNac, sexo, telefono, email, domicilio);
		this.fichaMedica = -1;
		// TODO Auto-generated constructor stub
	}

	// Constructor con parametros propios y de su SuperClase
	public Paciente(int fichaMedica,int idPersona,String dni,String apellido,String nombre,Date fechaNac,String sexo,String telefono,
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
