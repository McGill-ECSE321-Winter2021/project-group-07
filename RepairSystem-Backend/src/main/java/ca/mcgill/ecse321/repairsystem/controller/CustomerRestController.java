package ca.mcgill.ecse321.repairsystem.controller;

import java.text.ParseException;
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
	public CustomerDto getCustomerById(@PathVariable("id") String id) {
		return Converter.convertToDto(customerService.getCustomerById(Integer.parseInt(id)));
	}

	@PostMapping(value = { "/customer/{name}", "/customer/{name}/" })
	public CustomerDto createCustomer(@PathVariable("name") String name, @RequestParam String password, @RequestParam String phone, @RequestParam String email, @RequestParam String credit, @RequestParam String debit, @RequestParam String address) throws IllegalArgumentException, ParseException {
		Customer customer = customerService.createCustomer(name, password, Integer.parseInt(phone), email, credit, debit, address);
		return Converter.convertToDto(customer);
	}

	@PutMapping(value = { "/customer/editAllCustomerCredentials/{oldEmail}", "/customer/{oldEmail}/" })
	public CustomerDto editAllCustomerCredentials(@PathVariable("oldEmail") String oldEmail, @RequestParam String newPassword, @RequestParam String newPhone,  @RequestParam String newCredit, @RequestParam String newDebit, @RequestParam String newAddress) throws IllegalArgumentException {
		Customer customer = customerService.getCustomerByEmail(oldEmail); 
		customerService.updateAllCredentials(customer, newPassword, newPhone, newCredit, newDebit, newAddress);
		return  Converter.convertToDto(customer);
	}

}




