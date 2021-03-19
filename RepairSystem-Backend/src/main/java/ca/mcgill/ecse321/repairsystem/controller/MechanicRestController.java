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
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;
import ca.mcgill.ecse321.repairsystem.service.*;

@CrossOrigin(origins = "*")
@RestController
public class MechanicRestController {

	@Autowired
	private ServiceService serviceService;
	@Autowired
	private MechanicService mechanicService;
	/**
	 *restful controller for getting mechanic
	 * */
	@GetMapping(value = { "/mechanics", "/mechanics/"})
	public List<MechanicDto> getAllMechanics() {
		List<Mechanic> mechanics = mechanicService.getAllMechanics();
		List<MechanicDto> mechanicsDto = new ArrayList<MechanicDto>();
		for(Mechanic mechanic: mechanics) {
			mechanicsDto.add(Converter.convertToDto(mechanic));
		}
		return mechanicsDto;
	}
	
	/**
	 *restful controller for getting id
	 * */
	@GetMapping(value = { "/mechanic/{id}", "/mechanic/{id}/"})
	public MechanicDto getMechanicById(String id) {
		return Converter.convertToDto(mechanicService.getMechanicById(Integer.parseInt(id)));
	}
	/**
	 *restful controller for creating mechanic
	 * */
	@PostMapping(value = { "/mechanic/{name}", "/mechanic/{name}/" })
	public MechanicDto createMechanic(@PathVariable("name") String name, @RequestParam String password, @RequestParam String phone, @RequestParam String email) throws IllegalArgumentException {
		Mechanic mechanic = mechanicService.createMechanic(name, password, Integer.parseInt(phone), email, new ArrayList<Service>());
		return Converter.convertToDto(mechanic);
	}
	/**
	 *restful controller for editing mechanic
	 * */
	@PutMapping(value = { "/mechanic/{oldEmail}", "/mechanic/{oldEmail}/" })
	public MechanicDto editMechanic(@PathVariable("oldEmail") String oldEmail, @RequestParam String name, @RequestParam String password, @RequestParam String phone, @RequestParam String email) throws IllegalArgumentException {
		Mechanic mechanic = mechanicService.getMechanicByEmail(oldEmail);
		mechanic.setName(name);
		mechanic.setId(email.hashCode());
		mechanic.setPassword(password);
		mechanic.setPhone(Integer.parseInt(phone));
		mechanic.setEmail(email);
		return Converter.convertToDto(mechanic);
	}
	/**
	 *restful controller for editing service
	 * */
	@PutMapping(value = { "/mechanic/editService/{serviceType}", "/mechanic/editService/{serviceType}/" })
	public MechanicDto editService(@PathVariable("serviceType") String serviceType, @RequestParam String addRemove, @RequestParam String oldEmail) throws IllegalArgumentException {
		Mechanic mechanic = mechanicService.getMechanicByEmail(oldEmail);
		ServiceType service = ServiceType.valueOf(serviceType);
		Service s = serviceService.getServiceByServiceType(service);
		if(addRemove.equals("add")) {
			mechanicService.addService(s, mechanic);
		} else if(addRemove.equals("remove")) {
			mechanicService.removeService(s, mechanic);
		}
		return Converter.convertToDto(mechanic);
	}
	
	

}




