package vista;

import java.util.Scanner;

import Modelo.Cuenta;
import Modelo.CuentaDao;
import Modelo.Persona;

public class principal {

	private static Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opc;
		
		System.out.println("*-*-*-* DR MUELAS *-*-*-*");
		System.out.println("-------------------------");
		do {
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
						switch (opcP) {
						case 1:
							// Pedir turno
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
}
