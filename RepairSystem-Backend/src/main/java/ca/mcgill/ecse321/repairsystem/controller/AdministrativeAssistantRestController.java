package ca.mcgill.ecse321.repairsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@PostMapping(value = { "/admin/{name}", "/admin/{name}/" })
	public AdministrativeAssistantDto createAdmin(@PathVariable("name") String name, @RequestParam String password, @RequestParam String phone, @RequestParam String email) throws IllegalArgumentException {
		AdministrativeAssistant admin = adminService.createAdmin(name, password, Integer.parseInt(phone), email);
		return Converter.convertToDto(admin);
	}
	
	@PutMapping(value = { "/admin/{oldEmail}", "/admin/{oldEmail}/" })
	public AdministrativeAssistantDto editAdmin(@PathVariable("oldEmail") String oldEmail, @RequestParam String name, @RequestParam String password, @RequestParam String phone, @RequestParam String email) throws IllegalArgumentException {
		AdministrativeAssistant admin = adminService.getAdminByEmail(oldEmail);
		admin.setEmail(email);
		admin.setId(name.hashCode()*password.hashCode());
		admin.setName(name);
		admin.setPassword(password);
		admin.setPhone(Integer.parseInt(phone));
		return Converter.convertToDto(admin);
	}

}


