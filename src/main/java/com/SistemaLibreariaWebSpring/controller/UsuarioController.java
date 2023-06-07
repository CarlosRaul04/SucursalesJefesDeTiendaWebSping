package com.SistemaLibreariaWebSpring.controller;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SistemaLibreariaWebSpring.entity.Usuario;
import com.SistemaLibreariaWebSpring.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	
	@Autowired
	UsuarioRepository usuarioRepository;
	

	@GetMapping("/mostrarCrearUsuario")
	public String mostrarCrearUsuario(HttpServletRequest request, Model model) {
		Usuario objUsuario = new Usuario();
		model.addAttribute("objUsuario", objUsuario);
		return "nuevoUsuario";
	}
	
	@PostMapping("/grabarUsuario")
	public String grabarUsuario (HttpServletRequest request, Usuario objUsuario, Model model) throws AddressException, MessagingException {
		Usuario objUsuarioBD = usuarioRepository.findByCorreo(objUsuario.getCorreo());
		if(objUsuarioBD==null) {
			objUsuario.setEstado(1);
			usuarioRepository.save(objUsuario);
			model.addAttribute("mensaje", "El usuario ha sido registrado con éxito");
			Usuario objUsuarioNuevo = new Usuario();
			model.addAttribute("objUsuario", objUsuarioNuevo);
		}
		else {
			model.addAttribute("mensaje", "El usuario ya se encuentra registrado en el sistema");
			model.addAttribute("objUsuario", objUsuario);
		}
		return "nuevoUsuario";
	}
	
	@RequestMapping("/recuperarContrasenha")
	public String recuperarContraseña(HttpServletRequest request, @RequestParam(value="correo") String correo, Model model) {
		Usuario objUsuarioBD = usuarioRepository.findByCorreo(correo);
		if(objUsuarioBD == null) {
			model.addAttribute("mensaje", "El usuario no se encuentra registrado en el sistema");
		}
		else {
			model.addAttribute("password", objUsuarioBD.getPassword());
		}
		return "RecuperarContrasenha";
	}
	
	
}
