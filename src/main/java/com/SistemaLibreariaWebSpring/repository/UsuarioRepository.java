package com.SistemaLibreariaWebSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SistemaLibreariaWebSpring.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	Usuario findByCorreo(String correo);
	Usuario findByCorreoAndPassword(String correo, String password);
}
