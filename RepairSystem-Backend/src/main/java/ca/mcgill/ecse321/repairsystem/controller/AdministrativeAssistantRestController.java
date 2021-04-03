package ca.mcgill.ecse321.repairsystem.controller;

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
public class AdministrativeAssistantRestController {

	@Autowired
	private AdministrativeAssistantService adminService;
	
	/**
	 *restful controller for getting customer by id
	 * */
	@GetMapping(value = { "/admin/login/{email}", "/admin/login/{email}/"})
	public AdministrativeAssistantDto getAdminFromLogin(@PathVariable("email") String email, @RequestParam String password) {
		AdministrativeAssistant a = adminService.getAdminByEmail(email);
		if(a != null && password.equals(a.getPassword())) {
			return Converter.convertToDto(a);
		}
		return null;
	}
	
	/**
	 *Rest controller for creating admin assistant
	 * */
	@PostMapping(value = { "/admin/{name}", "/admin/{name}/" })
	public AdministrativeAssistantDto createAdmin(@PathVariable("name") String name, @RequestParam String password, @RequestParam String phone, @RequestParam String email) throws IllegalArgumentException {
		AdministrativeAssistant admin = adminService.createAdmin(name, password, Long.parseLong(phone), email);
		return Converter.convertToDto(admin);
	}
	/**
	 *Rest controller for editing admin assistant
	 * */
	@PutMapping(value = { "/admin/{oldEmail}", "/admin/{oldEmail}/" })
	public AdministrativeAssistantDto editAdmin(@PathVariable("oldEmail") String oldEmail, @RequestParam String name, @RequestParam String password, @RequestParam String phone) throws IllegalArgumentException {
		AdministrativeAssistant admin = adminService.getAdminByEmail(oldEmail);
		admin = adminService.editAdmin(admin, name, password, Long.parseLong(phone));
		return Converter.convertToDto(admin);
	}

}


