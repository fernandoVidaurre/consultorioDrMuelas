package vista;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import Modelo.PacienteDao;
import Modelo.Turno;

public class TestMainJulio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner entrada = new Scanner(System.in);
		 
		PacienteDao pdao = new PacienteDao();
		
		Date fecha = Date.valueOf(LocalDate.now());
		//int idTurno=0;        
        
		List<Turno> turnos = pdao.consultarTurno(fecha,1); // uso el valor del paciente 1
        		        
		for (int i = 0; i < turnos.size(); i++) {
			
			//idTurno=turnos.get(i).getIdTurno();
			String disponible = "disponible";
			
			if(turnos.get(i).isEstado()) {
				disponible = "ocupado";
				
			}
			System.out.println(turnos.get(i).getIdTurno()+" - "+turnos.get(i).getFecha()+" - "+turnos.get(i).getHora()+" - "+disponible);
		}
		
		
		// pedimos al usuario la eleccion del turno
		
		System.out.print("Ingrese el numero de turno a Cancelar: ");
		int idTurno = entrada.nextInt();

		/*
		  if( pdao.cancelarTurno(idTurno)) {
			  System.out.println("turno Cancelado");
			   }else {
				   
				   System.out.println("Error al cancelar Turno");
			   }
			   
		*/
	//	System.out.print("Ingrese '1' para atencion a menores � '2' para atencion a  mayores : ");
	//	int opc = entrada.nextInt();
		
		// creamos el objeto turno y le pasamos parametros indicados por el usuario
	//	Turno turno = new Turno();
		
		/*
		turno.setIdTurno(idTurno);
		if(opc == 1) {
			turno.setTipoTurno('C');
			}else {
			
				turno.setTipoTurno('M');
			}
		turno.setIdPersona(1);
		
		pdao.cargarTurno(turno);
	
		*/
	}

}
