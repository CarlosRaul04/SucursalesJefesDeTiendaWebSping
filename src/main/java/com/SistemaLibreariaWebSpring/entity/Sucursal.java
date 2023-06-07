package com.SistemaLibreariaWebSpring.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="Sucursal")
public class Sucursal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="codigo")
	private int codigo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="estado")
	private int estado;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="codigoJefeTienda")
	private JefeTienda objJefeTienda; /*Esto se debe al FK*/

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public JefeTienda getObjJefeTienda() {
		return objJefeTienda;
	}

	public void setObjJefeTienda(JefeTienda objJefeTienda) {
		this.objJefeTienda = objJefeTienda;
	}

	public Sucursal() {
		
	}
	
	
	
}
