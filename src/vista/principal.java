package vista;

import java.sql.Date;
import java.sql.Time;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import Modelo.AdministradorDao;
import Modelo.Cuenta;
import Modelo.CuentaDao;
import Modelo.Informe;
import Modelo.Paciente;
import Modelo.PacienteDao;
import Modelo.Persona;
import Modelo.Tratamiento;
import Modelo.Turno;

public class principal {

	private static Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opc;
		
		do {
			System.out.println("*-*-*-* DR MUELAS *-*-*-*");
			System.out.println("-------------------------");
			opc = menuPrincipal();
			
			switch (opc) {
			case 1:
				// logear 
				Cuenta cuenta = cargarCuenta();
				CuentaDao cuentaDao = new CuentaDao();
				int id = cuentaDao.logIn(cuenta);
				
				switch (id) {
				case -1:
					int opcA;
					do {
						opcA = menuAdmin();
						AdministradorDao administradorDao = new AdministradorDao();
						switch (opcA) {
						case 1:
							// mostrar fichas de pacientes
							verTratamientosPacientes(administradorDao);
							break;
						case 2:
							// cargar paciente a emerfencias
							cargarEmergencia(administradorDao);
							/*
							 * el metodo cargarEmergencia, solo permite la eleccion del primer turno
							 * que este disponible en el dia, y que la hora a la que lo esta pidiendo 
							 * este entre las horas 8 a 12 y 14 a 18 horas, si no hay turnos devolvera al menu
							 * y si hay algun turno devolver la hora a la que fue asignado
							 */
							break;
						case 3:
							// generar reporte
							generarInforme(administradorDao);
							break;
						case 4:
							generarTurnos(administradorDao);
						}
					} while (opcA != 0);
					
					break;
				case -2:
					System.out.println("Error no existe este usuario");
					break;
				default:
					int opcP;
					
					do {
						opcP = menuPaciente();
						PacienteDao pacienteDao = new PacienteDao();
						switch (opcP) {
						case 1:
							// Pedir turno
							pedirTurno(id, pacienteDao);
							break;
						case 2:
							// dar de baja un turno
							cancelarTurno(id, pacienteDao);
							break;
						case 3:
							// ver fichas de tratamientos
							verTratamientos(id, pacienteDao);
							break;
						}
					} while (opcP != 0);
					break;
				}
				
				break;
			case 2:
				// registrar paciente
				registrar();
				break;
			}
		} while (opc != 0);

		System.out.println("Programa Finalizado");
	}
	
	public static int menuPrincipal() {
		int opc;
		
		System.out.println("");
		System.out.println("1-Iniciar Sesion");
		System.out.println("2-Registrarse");
		System.out.println("0-Salir");
		opc = teclado.nextInt();
		
		return opc;
		
	}
	
	public static int menuAdmin() {
		int opc;
		
		System.out.println("");
		System.out.println("1-Mostrar Fichas Medicas de Todos los Pacientes");
		System.out.println("2-Cargar Paciente en Emergencias");
		System.out.println("3-Generar Reporte Mensual");
		System.out.println("4-Generar turnos");
		System.out.println("0-Salir");
		opc = teclado.nextInt();
		
		return opc;
		
	}
	
	public static int menuPaciente() {
		int opc;
		
		System.out.println("");
		System.out.println("1-Pedir Turno");
		System.out.println("2-Dar de Baja un Turno");
		System.out.println("3-Ver fichas de tratamientos");
		System.out.println("0-Salir");
		opc = teclado.nextInt();
		return opc;
	}
	
	public static Cuenta cargarCuenta() {
		String usuario;
		String password;
		
		teclado.nextLine();
		System.out.println("Usuario: ");
		usuario = teclado.nextLine();
		//teclado.nextLine();
		System.out.println("Contraseña: ");
		password = teclado.nextLine();
		
		Cuenta cuenta = new Cuenta(usuario, password);
		
		return cuenta;
	}
	
	private static Persona cargarPersona() {
		Persona nuevaPersona;
		//cargar los datos
		teclado.nextLine();
		System.out.println("Nombre: ");
		String nombre = teclado.nextLine();
		System.out.println("Apellido: ");
		String apellido = teclado.nextLine();
		System.out.println("Dni: ");
		String dni = teclado.nextLine();
		System.out.println("telefono: ");
		String telefono = teclado.nextLine();
		System.out.println("email: ");
		String email = teclado.nextLine();
		System.out.println("domicilio: ");
		String domicilio = teclado.nextLine();
		System.out.println("Sexo: M:Masculino, F:Femenino, X:?");
		String sexo = teclado.nextLine();
		System.out.println("Fecha nacimientos: ");
		System.out.println("Dia: ");
		int dia = teclado.nextInt();
		System.out.println("Mes: ");
		int mes = teclado.nextInt();
		System.out.println("Año: ");
		int anio = teclado.nextInt();
		Date fechaNac = Date.valueOf(LocalDate.of(anio, mes, dia));
		
		nuevaPersona = new Persona(dni, nombre, apellido, fechaNac, sexo, telefono, email, domicilio);
		
		return nuevaPersona;
		
	}
	public static void registrar() {
		Persona nuevaPersona = cargarPersona();
		Cuenta cuenta = cargarCuenta();
		CuentaDao cuentaDao = new CuentaDao();
		boolean cargado = false;
		
		
		cargado = cuentaDao.registrar(nuevaPersona, cuenta);
			
		if (cargado) {
			System.out.println("Se ha registrado con exito");
		} else {
			System.out.println("No se ha podido registrar, usuario o paciente ya existente");
		}		
	}
	
	private static boolean verificarDisponible(Turno turno) {
		if (turno.isEstado() == false) {
			return true;
		}
		
		return false;
	}
	
	public static void pedirTurno(int id, PacienteDao pacienteDao) {
		List<Date> dias = pacienteDao.listarDias();
		int i, j, k;
		int opcT, opcD;
		boolean cargado = false;
		
		do {
			k = 0;
			System.out.println("Seleccione un dia");
			for (i = 0; i < dias.size(); i++) {
				k++;
				System.out.println(k + "-" + "Dia: " + dias.get(i));
			}
			
			System.out.println("0-Salir");
			opcD = teclado.nextInt();
			
			if (opcD >= 1 && opcD <= dias.size() && opcD != 0) {
				List<Turno> listaTurnos = pacienteDao.consultarTurno(dias.get(opcD - 1));
				System.out.println("Seleccione un horario");
				do {
					j=0;
					for (i = 0; i < listaTurnos.size(); i++) {
						j++;
						if (listaTurnos.get(i).isEstado() == false) {
							System.out.println(j + "-" + "Hora: " + listaTurnos.get(i).getHora() + "- Disponible");
						} else {
							System.out.println(j + "-" + "Hora: " + listaTurnos.get(i).getHora() + "- No Disponible");
						}
					}
					System.out.println("0-Salir");
					// usuario debe elegir un turno
					opcT = teclado.nextInt();
					
					if (opcT >= 1 && opcT <= listaTurnos.size() && verificarDisponible(listaTurnos.get(opcT - 1))) {
						pacienteDao.cargarTurno(listaTurnos.get(opcT - 1), id);
						cargado = true;
						System.out.println("Se ha cargado con exito");
					} else if (opcT != 0) {
						System.out.println("No es una opcion valida");
					} else {
						cargado = true;
					}
					
				} while (!cargado);
				opcD = 0;
			}
		} while (opcD != 0);

	}
	
	public static void verTratamientos(int id, PacienteDao pacienteDao) {
		List<Tratamiento> tratamientos = pacienteDao.verTratamientos(id);
		
		if (!tratamientos.isEmpty()) {
			int i;
			for (i = 0; i < tratamientos.size(); i++) {
				System.out.println("**********************************");
				tratamientos.get(i).mostrarDatos();
				System.out.println("**********************************");
			}
		} else {
			System.out.println("No tiene ningun tratamiento");
		}
	}
	
	public static void cancelarTurno(int id, PacienteDao pacienteDao) {
		List<Turno> turnos = pacienteDao.consultarTurno(Date.valueOf(LocalDate.now()), id);
		int i, j, opcT;
		boolean eliminado = false;
	
		if (!turnos.isEmpty()) {
			System.out.println("Seleccione un turno a dar de baja");
			do {
				j=0;
				for (i = 0; i < turnos.size(); i++) {
					j++;
					System.out.println(j+ "-"+ turnos.get(i).getFecha() +" - " + "Hora: " + turnos.get(i).getHora());
				}
				System.out.println("0-Salir");
				opcT = teclado.nextInt();
				if (opcT >= 1 && opcT <= turnos.size() && opcT != 0) {
					eliminado = pacienteDao.cancelarTurno(turnos.get(opcT - 1));
				}
				
			} while (!eliminado && opcT != 0);
		} else {
			System.out.println("No tiene turnos pendientes");
		}
		
	}
	
	public static void generarTurnos(AdministradorDao admin) {
		int opc;
		int dia, mes, anio;
		do {
			System.out.println("Ingrese fecha a cargar");
			System.out.println("Dia: ");
			dia = teclado.nextInt();
			System.out.println("Mes: ");
			mes = teclado.nextInt();
			System.out.println("Año: ");
			anio = teclado.nextInt();
			
			Date fecha = Date.valueOf(LocalDate.of(anio, mes, dia));
			admin.generarTurnos(fecha);
			teclado.nextLine();
			System.out.println("Desea seguir ingresando? 1-SI/2-NO");
			opc = teclado.nextInt();
		} while (opc != 2);
	}
	
	public static void generarInforme(AdministradorDao admin) {
		int mes;
		Informe informe;
		
		System.out.println("Ingrese mes del que quiere generar informe");
		mes = teclado.nextInt();
		
		informe = admin.generarInforme(mes);
		
		informe.reportar();
	}
	
	private static Persona cargarPersona(String dni) {
		Persona nuevaPersona;
		//cargar los datos
		teclado.nextLine();
		System.out.println("Nombre: ");
		String nombre = teclado.nextLine();
		System.out.println("Apellido: ");
		String apellido = teclado.nextLine();
		String dniNuevo = dni;
		System.out.println("telefono: ");
		String telefono = teclado.nextLine();
		System.out.println("email: ");
		String email = teclado.nextLine();
		System.out.println("domicilio: ");
		String domicilio = teclado.nextLine();
		System.out.println("Sexo: M:Masculino, F:Femenino, X:?");
		String sexo = teclado.nextLine();
		System.out.println("Fecha nacimientos: ");
		System.out.println("Dia: ");
		int dia = teclado.nextInt();
		System.out.println("Mes: ");
		int mes = teclado.nextInt();
		System.out.println("Año: ");
		int anio = teclado.nextInt();
		Date fechaNac = Date.valueOf(LocalDate.of(anio, mes, dia));
		
		nuevaPersona = new Persona(dni, nombre, apellido, fechaNac, sexo, telefono, email, domicilio);
		
		return nuevaPersona;
		
	}
	
	public static Cuenta cargarCuenta(String usuario, String password) {		
		Cuenta cuenta = new Cuenta(usuario, password);
		
		return cuenta;
	}
	public static void cargarEmergencia(AdministradorDao admin) {
		PacienteDao pacienteDao = new PacienteDao();
		List<Turno> turnos = pacienteDao.consultarTurno(Date.valueOf(LocalDate.now()));
		boolean disponible = false;
		Time horaActual = Time.valueOf(LocalTime.now());
		
		int i=0;
		
		while (i < turnos.size() && !disponible) {
			if (turnos.get(i).getHora().getTime() > horaActual.getTime() && !turnos.get(i).isEstado()) {
				disponible = true;
			} 
			i++;
		}
		
		if (disponible) {
			teclado.nextLine();
			System.out.println("Ingrese dni: ");
			String dni = teclado.nextLine();
			
			if (!admin.verificaPersona(dni)) {
				CuentaDao cuentaDao = new CuentaDao();
				Persona persona = cargarPersona(dni);
				Cuenta cuenta = cargarCuenta(persona.getDni(), persona.getDni());
				
				cuentaDao.registrar(persona, cuenta);
			}
			
			Turno turno = admin.cargarEmergencia(dni);
			
			System.out.println("hora: " + turno.getHora());
		} else {
			System.out.println("No hay turnos disponibles para hoy");
		}			
	}
	
	public static void verTratamientosPacientes(AdministradorDao admin) {
		PacienteDao paciente = new PacienteDao();
		int opcP;
		List<Paciente> personas = admin.listarPacientes();
		int i,j,k;
		do {
			j = 0;
			System.out.println("Seleccione una opcion");
			for (i=0; i < personas.size(); i++) {
				j++;
				System.out.println(j + "- Nombre:" + personas.get(i).getNombre() + " " + personas.get(i).getApellido() + " DNI: " + personas.get(i).getDni());
			}
			System.out.println("0-Salir");
			opcP = teclado.nextInt();
			
			if (opcP != 0) {
				verTratamientos(personas.get(opcP - 1).getIdPersona(), paciente);
			}
		} while (opcP != 0);

		
	}
}
