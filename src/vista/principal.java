package vista;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import Modelo.Cuenta;
import Modelo.CuentaDao;
import Modelo.PacienteDao;
import Modelo.Persona;
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
						
						switch (opcA) {
						case 1:
							// mostrar fichas de pacientes
							break;
						case 2:
							// cargar paciente a emerfencias
							break;
						case 3:
							// generar reporte
							break;
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
							break;
						case 3:
							// ver fichas de tratamientos
							break;
						}
					} while (opcP != 0);
					break;
				}
				
				break;
			case 2:
				// registrar paciente
				break;
			}
		} while (opc != 0);

		System.out.println("Programa Finalizado");
	}
	
	public static int menuPrincipal() {
		int opc;
		
		System.out.println("1-Iniciar Sesion");
		System.out.println("2-Registrarse");
		System.out.println("0-Salir");
		opc = teclado.nextInt();
		
		return opc;
		
	}
	
	public static int menuAdmin() {
		int opc;
		
		System.out.println("1-Mostrar Fichas Medicas de Todos los Pacientes");
		System.out.println("2-Cargar Paciente en Emergencias");
		System.out.println("3-Generar Reporte Mensual");
		System.out.println("0-Salir");
		opc = teclado.nextInt();
		
		return opc;
		
	}
	
	public static int menuPaciente() {
		int opc;
		
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
		Persona nueva = new Persona();
		//cargar los datos
		
		return nueva;
		
	}
	public static void registrar() {
		
	}
	
	private static boolean verificarDisponible(Turno turno) {
		if (turno.isEstado() == false) {
			return true;
		}
		
		return false;
	}
	
	public static void pedirTurno(int id, PacienteDao pacienteDao) {
		List<Turno> listaTurnos = pacienteDao.consultarTurno(Date.valueOf(LocalDate.now()));
		int i, j;
		int opcT;
		boolean cargado = false;
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
	}
}
