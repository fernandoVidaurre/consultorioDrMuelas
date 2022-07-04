package Modelo;

public class Cuenta {
	
	private int idCuenta;
	private String usuario;
	private String password;
	private Character tipoUsuario;
	
	public Cuenta(String usuario, String password, Character tipoUsuario) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
	}
	
	public Cuenta(int idCuenta, String usuario, String password, Character tipoUsuario) {
		super();
		this.idCuenta = idCuenta;
		this.usuario = usuario;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
	}
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Character getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(Character tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	
}
