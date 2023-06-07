package com.SistemaLibreariaWebSpring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SistemaLibreariaWebSpring.entity.JefeTienda;
import com.SistemaLibreariaWebSpring.repository.JefeTiendaRepository;

@Controller
@RequestMapping("/jefeTienda")
public class JefeTiendaController {
	
	@Autowired
	JefeTiendaRepository jefeTiendaRepository;
	
	@GetMapping("/gestionar")
	public String gestionar(HttpServletRequest request) {
		return "gestionarJefeTienda";
	}
	
	@PostMapping("/nuevo")
	public String nuevoJefeTienda(HttpServletRequest request, Model model) {
		JefeTienda objJefeTienda = new JefeTienda();
		model.addAttribute("objJefeTienda", objJefeTienda);
		return "NuevoJefeTienda";
	}
	
	@PostMapping("/nuevoJefe")
	public String nuevoJefe(HttpServletRequest request, JefeTienda objJefeTienda, Model model) {
		JefeTienda objJefeTiendaBD = jefeTiendaRepository.findByDni(objJefeTienda.getDni());
		String dni = objJefeTienda.getDni();
		if(objJefeTiendaBD == null && dni.length()==8) {
			jefeTiendaRepository.save(objJefeTienda);
			model.addAttribute("mensaje", "Datos ingresados Correctamente");
			JefeTienda objJefeTiendaNuevo = new JefeTienda();
			model.addAttribute("objJefeTienda", objJefeTiendaNuevo);
		}
		else { 
			model.addAttribute("mensaje", "El DNI ingresado ya existe o contiene más de 8 digitos");
			model.addAttribute("objJefeTienda", objJefeTienda);
		}
		return "NuevoJefeTienda";
	}
	
	@RequestMapping("/buscarxApellido")
	public String buscarxApellido (HttpServletRequest request, @RequestParam(value="apellidoPaterno") String apellidoPaterno, Model model) {
		List<JefeTienda> listaJefesTienda = jefeTiendaRepository.findByApellidoPaternoContains(apellidoPaterno);
		model.addAttribute("listaJefesTienda", listaJefesTienda);
		return "gestionarJefeTienda";
	}
	
	@Transactional
	@GetMapping("/eliminar/{codigo}")
	public String eliminar (HttpServletRequest request, @PathVariable("codigo") int codigo, Model model) {
		jefeTiendaRepository.deleteByCodigoJ(codigo);
		model.addAttribute("mensaje", "El Jefe de Tienda ha sido eliminado");
		return "gestionarJefeTienda";
	}
	
	@GetMapping("/editar/{codigo}")
	public String editar(HttpServletRequest request, @PathVariable("codigo") int codigo, Model model) {
		JefeTienda objJefeTienda = jefeTiendaRepository.findByCodigoJ(codigo);
		model.addAttribute("objJefeTienda", objJefeTienda);
		return "editarJefeTienda";
	}
	
	@PostMapping("/actualizar")
	public String actualizar (HttpServletRequest request, JefeTienda objJefeTienda, Model model) {
		jefeTiendaRepository.save(objJefeTienda);
		return "gestionarJefeTienda";
	}
}
