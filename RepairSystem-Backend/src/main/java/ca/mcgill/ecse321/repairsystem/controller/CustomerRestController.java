package ca.mcgill.ecse321.repairsystem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping(value = { "/customer", "/customer/"})
	public List<CustomerDto> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		List<CustomerDto> customersDto = new ArrayList<CustomerDto>();
		for(Customer customer: customers) {
			customersDto.add(Converter.convertToDto(customer));
		}
		return customersDto;
	}
	
	@GetMapping(value = { "/customer/{id}", "/customer/{id}/"})
	public CustomerDto getCustomerById(@PathVariable("id") int id) {
		return Converter.convertToDto(customerService.getCustomerById(id));
	}

	@PostMapping(value = { "/customer/{name}", "/customer/{name}/" })
	public CustomerDto createCustomer(@PathVariable("name") String name, @RequestParam String password, @RequestParam String phone, @RequestParam String email, @RequestParam String credit, @RequestParam String debit, @RequestParam String address) throws IllegalArgumentException, ParseException {
		Customer customer = customerService.createCustomer(name, password, Integer.parseInt(phone), email, credit, debit, address);
		return Converter.convertToDto(customer);
	}
	
	@PutMapping(value = { "/customer/{oldEmail}", "/customer/{oldEmail}/" })
	public CustomerDto editCustomer(@PathVariable("oldEmail") String oldEmail, @RequestParam String email, @RequestParam String password, @RequestParam int phone, @RequestParam String name, @RequestParam Calendar lastActive, @RequestParam String credit, @RequestParam String debit, @RequestParam String address) throws IllegalArgumentException {
		Customer customer = customerService.getCustomerByEmail(oldEmail);
		customer.setName(name);
		customer.setPassword(password);
		customer.setPhone(phone);
		customer.setEmail(email);
		customer.setLastActive(lastActive);
		customer.setCreditHash(credit);
		customer.setDebitHash(debit);
		customer.setAddress(address);
		customer.setId(name.hashCode()*password.hashCode());
		return Converter.convertToDto(customer);
	}

}




