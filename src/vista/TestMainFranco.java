package vista;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import Modelo.AdministradorDao;
import Modelo.Cuenta;
import Modelo.CuentaDao;
import Modelo.Paciente;
import Modelo.PacienteDao;
import Modelo.Persona;
import Modelo.Tratamiento;
import Modelo.Turno;

public class TestMainFranco {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		AdministradorDao adm = new AdministradorDao();
		CuentaDao cuentaDao = new CuentaDao();
		
		Paciente paciente = cargarPaciente();
		Cuenta cuenta = crearCuenta();
		
		boolean exito = cuentaDao.registrar(paciente, cuenta);
		
		if (exito) {
			System.out.print("Se registro con exito");
		} else {
			System.out.println("Esta cuenta ya existe");
		}
		*
		*/
		
		//iniciarSesion();
		/*
		PacienteDao pacienteDao = new PacienteDao();
		List<Tratamiento> tratamientos = pacienteDao.verTratamientos(2);
		
		int i;
		for (i=0; i < tratamientos.size(); i++) {
			System.out.println(tratamientos.get(i).getNombreTratamiento());
		}
		*/
		
		AdministradorDao adminDao = new AdministradorDao();
		Date fecha = Date.valueOf(LocalDate.of(2022, 7, 9));
		adminDao.generarTurnos(fecha);
		
	}
	
	public static Paciente cargarPaciente() {
		String nombre = "Franco";
		String apellido = "Avendaño";
		String dni = "31425023";
		Date fechaNac = Date.valueOf(LocalDate.of(1993, 8, 2));
		String sexo = "M";
		String telefono = "123421";
		String email = "franco@mail.com";
		String domicilio = "avenida siempreviva";
		
		Paciente nueva = new Paciente(dni, nombre, apellido, fechaNac, sexo, telefono, email, domicilio);
		
		return nueva;
	}
	
	public static Cuenta crearCuenta() {
		String username = "Francoco";
		String password = "123456";
		
		Cuenta cuenta = new Cuenta(username, password);
		
		return cuenta;
	}

	public static void iniciarSesion() {
		String usuario = "Francoco";
		String password = "123456";
		
		Cuenta cuenta = new Cuenta(usuario, password);
		CuentaDao cuentaDao = new CuentaDao();
		
		int id = cuentaDao.logIn(cuenta);
		
		switch (id) {
		case -1:
			System.out.println("Menu de Admin");
			break;
		case -2:
			System.out.println("No existe cuenta con ese usuario");
			break;
		default:
			System.out.println("Menu de paciente");
			break;
		}
		
	}
}
