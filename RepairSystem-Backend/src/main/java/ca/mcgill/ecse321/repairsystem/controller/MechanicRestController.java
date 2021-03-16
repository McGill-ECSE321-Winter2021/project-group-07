package ca.mcgill.ecse321.repairsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.repairsystem.dto.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.service.*;

@CrossOrigin(origins = "*")
@RestController
public class MechanicRestController {

	@Autowired
	private ServiceService serviceService;
	private MechanicService mechanicService;
	
	@GetMapping(value = { "/mechanics", "/mechanics/"})
	public List<MechanicDto> getAllMechanics() {
		List<Mechanic> mechanics = mechanicService.getAllMechanics();
		List<MechanicDto> mechanicsDto = new ArrayList<MechanicDto>();
		for(Mechanic mechanic: mechanics) {
			mechanicsDto.add(Converter.convertToDto(mechanic));
		}
		return mechanicsDto;
	}
	
	@GetMapping(value = { "/mechanics/{id}", "/Mechanics/{id}/"})
	public MechanicDto getMechanicById(int id) {
		return Converter.convertToDto(mechanicService.getMechanicById(id));
	}

	@PostMapping(value = { "/mechanics/{name}", "/Mechanics/{name}/" })
	public MechanicDto createMechanic(@PathVariable("name") String name, @RequestParam String password, @RequestParam int phone, @RequestParam String email, @RequestParam List<ServiceDto> servicesDto) throws IllegalArgumentException {
		List<Service> services = new ArrayList<Service>();
		for(ServiceDto service: servicesDto) {
			services.add(serviceService.getServiceByServiceType(service.getServiceType()));
		}
		Mechanic mechanic = mechanicService.createMechanic(name, password, phone, email, services);
		return Converter.convertToDto(mechanic);
	}
	
	@PutMapping(value = { "/mechanic/{oldEmail}", "/mechanic/{oldEmail}/" })
	public MechanicDto editMechanic(@PathVariable("oldEmail") String oldEmail, @RequestParam String name, @RequestParam String password, @RequestParam int phone, @RequestParam String email, @RequestParam List<ServiceDto> servicesDto) throws IllegalArgumentException {
		Mechanic mechanic = mechanicService.getMechanicByEmail(oldEmail);
		List<Service> services = new ArrayList<Service>();
		for(ServiceDto service: servicesDto) {
			services.add(serviceService.getServiceByServiceType(service.getServiceType()));
		}
		mechanic.setName(name);
		mechanic.setId(name.hashCode()*password.hashCode());
		mechanic.setPassword(password);
		mechanic.setPhone(phone);
		mechanic.setEmail(email);
		mechanic.setServices(services);
		return Converter.convertToDto(mechanic);
	}

}




