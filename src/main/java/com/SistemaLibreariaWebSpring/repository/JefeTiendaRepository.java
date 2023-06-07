package com.SistemaLibreariaWebSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SistemaLibreariaWebSpring.entity.JefeTienda;

@Repository
public interface JefeTiendaRepository extends JpaRepository<JefeTienda,Integer> {
	JefeTienda findByDni(String dni);
	List<JefeTienda> findByApellidoPaternoContains(String apellidoPaterno);
	void deleteByCodigoJ(int codigo);
	JefeTienda findByCodigoJ(int codigo);
	List<JefeTienda> findAll();
}
