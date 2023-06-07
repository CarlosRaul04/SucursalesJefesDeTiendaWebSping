package com.SistemaLibreariaWebSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SistemaLibreariaWebSpring.entity.JefeTienda;
import com.SistemaLibreariaWebSpring.entity.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal,Integer> {
	List<Sucursal> findByNombreContains(String nombre);
	Sucursal findByCodigo(int codigo);
	Sucursal findByObjJefeTienda(JefeTienda objJefeTienda);
	void deleteByCodigo(int codigo);
	Sucursal findByNombre(String nombre);
}
