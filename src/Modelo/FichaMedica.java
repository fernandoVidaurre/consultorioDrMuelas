package Modelo;

import java.sql.Date;

public class FichaMedica {
	private Integer idFichaMedica;
	private Date fechaAlta;
	private String obraSocial;
	private String codigoAfiliado;
	
	public FichaMedica(Integer idFichaMedica, Date fechaAlta, String obraSocial, String codigoAfiliado) {
		super();
		this.idFichaMedica = idFichaMedica;
		this.fechaAlta = fechaAlta;
		this.obraSocial = obraSocial;
		this.codigoAfiliado = codigoAfiliado;
	}
	
	public Integer getIdFichaMedica() {
		return idFichaMedica;
	}
	public void setIdFichaMedica(Integer idFichaMedica) {
		this.idFichaMedica = idFichaMedica;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public String getObraSocial() {
		return obraSocial;
	}
	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}
	public String getCodigoAfiliado() {
		return codigoAfiliado;
	}
	public void setCodigoAfiliado(String codigoAfiliado) {
		this.codigoAfiliado = codigoAfiliado;
	}
	
	
}
