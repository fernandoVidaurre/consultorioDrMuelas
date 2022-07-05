package vista;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import Modelo.PacienteDao;
import Modelo.Turno;

public class TestMainJulio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("se ejecuto");
	       
		PacienteDao pdao = new PacienteDao();
		
		
		
		
		Date fecha = Date.valueOf(LocalDate.now());
		
       
       
       
        System.out.println(fecha);
        
        
        List<Turno> turnos = pdao.consultarTurno(fecha);
        System.out.print(turnos);
		
        //System.out.println(turno);
		for (int i = 0; i < turnos.size(); i++) {
			System.out.println(turnos.get(i));
		}
		
		
	}

}
