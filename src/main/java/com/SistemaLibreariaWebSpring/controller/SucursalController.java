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
import com.SistemaLibreariaWebSpring.entity.Sucursal;
import com.SistemaLibreariaWebSpring.repository.JefeTiendaRepository;
import com.SistemaLibreariaWebSpring.repository.SucursalRepository;

@Controller
@RequestMapping("/sucursal")
public class SucursalController {

	@Autowired
	SucursalRepository sucursalRepository;
	
	@Autowired
	JefeTiendaRepository jefeTiendaRepository;
	
	@GetMapping("/mostrarAsignarJefe")
	public String mostrarAsignarJefe(HttpServletRequest request) {
		return "asignarJefeTienda";
	}
	
	
	@RequestMapping("/buscarxNombre")
	public String buscarxNombre(HttpServletRequest request, @RequestParam(value="nombre") String nombre, Model model) {
		List<Sucursal> listaSucursales = sucursalRepository.findByNombreContains(nombre);
		model.addAttribute("listaSucursales", listaSucursales);
		return "asignarJefeTienda";
	}
	
	@GetMapping("/mostrarAsignar/{codigo}")
	public String mostrarAsignar(HttpServletRequest request, @PathVariable("codigo") int codigo, Model model) {
		Sucursal objSucursal = sucursalRepository.findByCodigo(codigo);
		List<JefeTienda> listaJefeTienda = jefeTiendaRepository.findAll();
		model.addAttribute("objSucursal", objSucursal);
		model.addAttribute("listaJefeTienda", listaJefeTienda);
		return "asignarJefe";
	}
	
	@RequestMapping("/asignar")
	public String asignar(HttpServletRequest request, @RequestParam(value="codigoSucursal") int codigoSucursal, @RequestParam(value="codigoJefeTienda") int codigoJefeTienda, Model model){
		Sucursal objSucursal = sucursalRepository.findByCodigo(codigoSucursal);
		JefeTienda objJefeTienda = jefeTiendaRepository.findByCodigoJ(codigoJefeTienda);
		Sucursal objSucursalAux = sucursalRepository.findByObjJefeTienda(objJefeTienda);
		if(objSucursalAux==null) {
			objSucursal.setObjJefeTienda(objJefeTienda);
			sucursalRepository.save(objSucursal);
			return "asignarJefeTienda";
		}
		else {
			model.addAttribute("mensaje", "El Jefe de Tienda ya se encuentra asignado a otra Sucursal");
			model.addAttribute("objSucursal", objSucursal);
			List<JefeTienda> listaJefeTienda = jefeTiendaRepository.findAll();
			model.addAttribute("listaJefeTienda",listaJefeTienda);
			return "asignarJefe";
		}	
	}
	
	@GetMapping("/mostrarSucursal")
	public String mostrarSucursal(HttpServletRequest request) {
		return "gestionarSucursal";
	}
	
	@RequestMapping("/buscarxNombre2")
	public String buscarxNombre2(HttpServletRequest request, @RequestParam(value="nombre") String nombre, Model model) {
		List<Sucursal> listaSucursales = sucursalRepository.findByNombreContains(nombre);
		model.addAttribute("listaSucursales", listaSucursales);
		return "gestionarSucursal";
	}
	
	@GetMapping("/editar/{codigo}")
	public String editar(HttpServletRequest request, @PathVariable("codigo") int codigo, Model model) {
		Sucursal objSucursal = sucursalRepository.findByCodigo(codigo);
		model.addAttribute("objSucursal", objSucursal);
		return "editarSucursal";
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(HttpServletRequest request, Sucursal objSucursal, Model model){
			sucursalRepository.save(objSucursal);
			model.addAttribute("mensaje", "La Sucursal ha sido actualizada");
			return "gestionarSucursal";
	}
	
	@Transactional
	@GetMapping("/eliminar/{codigo}")
	public String eliminar (HttpServletRequest request, @PathVariable("codigo") int codigo, Model model) {
		sucursalRepository.deleteByCodigo(codigo);
		model.addAttribute("mensaje", "El Jefe de Tienda ha sido eliminado");
		return "gestionarSucursal";
	}
	
	@PostMapping("/nuevo")
	public String nuevo (HttpServletRequest request, Model model) {
		Sucursal objSucursal = new Sucursal();
		model.addAttribute("objSucursal", objSucursal);
		return "nuevoSucursal";
	}
	
	@PostMapping("/nuevoSucursal")
	String nuevoSucursal(HttpServletRequest request, Model model, Sucursal objSucursal) {
		Sucursal objSucursalBD= sucursalRepository.findByNombre(objSucursal.getNombre());
		if(objSucursalBD==null) {
			sucursalRepository.save(objSucursal);
			model.addAttribute("mensaje", "La sucursal ha sido registrada con éxito");
			return "gestionarSucursal";
		}
		else
		{
			model.addAttribute("mensaje","El nombre puesto ya existe");
			model.addAttribute("objSucursal",objSucursal);
			return "nuevoSucursal";
		}
	}
}