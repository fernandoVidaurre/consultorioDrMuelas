package Modelo;

public class Informe {
	private Integer emergencia;
	private Integer menor;
	private Integer mayor;
	
	public Informe(Integer emergencia, Integer menor, Integer mayor) {
		super();
		this.emergencia = emergencia;
		this.menor = menor;
		this.mayor = mayor;
	}

	public Integer getEmergencia() {
		return emergencia;
	}

	public void setEmergencia(Integer emergencia) {
		this.emergencia = emergencia;
	}

	public Integer getMenor() {
		return menor;
	}

	public void setMenor(Integer menor) {
		this.menor = menor;
	}

	public Integer getMayor() {
		return mayor;
	}

	public void setMayor(Integer mayor) {
		this.mayor = mayor;
	}
	
	public void reportar() {
		System.out.println("**************Se atendieron en el mes*******************");
		System.out.println("Emergencia: " + this.emergencia);
		System.out.println("Menores a 13: " + this.menor);
		System.out.println("MAyores a 13: " + this.mayor);
	}
}
