package ca.mcgill.ecse321.repairsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.repairsystem.service.ServiceService;

@CrossOrigin(origins = "*")
@RestController
public class ServiceRestController {

	@Autowired
	private ServiceService service;

}




