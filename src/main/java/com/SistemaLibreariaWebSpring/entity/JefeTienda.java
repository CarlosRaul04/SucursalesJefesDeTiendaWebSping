package com.SistemaLibreariaWebSpring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JefeTienda1")
public class JefeTienda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigoJ")
	private int codigoJ;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellidoPaterno")
	private String apellidoPaterno;
	
	@Column(name="apellidoMaterno")
	private String apellidoMaterno;

	@Column(name="dni")
	private String dni;
	
	public int getCodigoJ() {
		return codigoJ;
	}
	public void setCodigoJ(int codigoJ) {
		this.codigoJ = codigoJ;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public JefeTienda() {
		
	}
	
}
