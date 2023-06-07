package com.SistemaLibreariaWebSpring.controller;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.SistemaLibreariaWebSpring.entity.Usuario;
import com.SistemaLibreariaWebSpring.repository.UsuarioRepository;

@Controller
@RequestMapping("/home")
public class LoginController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/mostrarRecuperarContrasenha")
	public String mostrarRecuperarContraseña(HttpServletRequest request) {
		return "RecuperarContrasenha";
	}
	
	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		return "index";
	}
	
	@RequestMapping(value = "/principal", method=RequestMethod.POST, params = "ingresar")
	public String principal(HttpServletRequest request, @RequestParam(value="correo") String correo, @RequestParam(value="password") String password, Model model) {
		Usuario objUsuarioBD = usuarioRepository.findByCorreoAndPassword(correo, password);
		if(objUsuarioBD == null) {
			model.addAttribute("mensaje", "El correo o contraseña ingresados son incorrectos");
			return"index"; 
		}
		else {
			if(objUsuarioBD.getEstado() == 0) {
				model.addAttribute("mensaje", "El usuario no se encuentra activo. Para su activación revise su correo");
				return "index";
			}
			else {
				return"principal";
			}
		}
	}
	
}
