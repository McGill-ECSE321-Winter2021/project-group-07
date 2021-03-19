package ca.mcgill.ecse321.repairsystem.controller;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
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
	/**
	 *restful controller fot getting all customers
	 * */
	@GetMapping(value = { "/customer", "/customer/"})
	public List<CustomerDto> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		List<CustomerDto> customersDto = new ArrayList<CustomerDto>();
		for(Customer customer: customers) {
			customersDto.add(Converter.convertToDto(customer));
		}
		return customersDto;
	}
	/**
	 *restful controller for getting customer by id
	 * */
	@GetMapping(value = { "/customer/{id}", "/customer/{id}/"})
	public CustomerDto getCustomerById(@PathVariable("id") String id) {
		return Converter.convertToDto(customerService.getCustomerById(Integer.parseInt(id)));
	}
	/**
	 *restful controller for creating customer by
	 * */
	@PostMapping(value = { "/customer/{name}", "/customer/{name}/" })
	public CustomerDto createCustomer(@PathVariable("name") String name, @RequestParam String password, @RequestParam String phone, @RequestParam String email, @RequestParam String credit, @RequestParam String debit, @RequestParam String address) throws IllegalArgumentException, ParseException {
		Customer customer = customerService.createCustomer(name, password, Integer.parseInt(phone), email, credit, debit, address);
		return Converter.convertToDto(customer);
	}
	/**
	 *restful controller for editing all customer credentials
	 * */
	@PutMapping(value = { "/customer/editAllCustomerCredentials/{oldEmail}", "/customer/{oldEmail}/" })
	public CustomerDto editAllCustomerCredentials(@PathVariable("oldEmail") String oldEmail, @RequestParam String newEmail, @RequestParam String newPassword, @RequestParam String newPhone,  @RequestParam String newCredit, @RequestParam String newDebit, @RequestParam String newAddress) throws IllegalArgumentException {
		Customer customer = customerService.getCustomerByEmail(oldEmail); 
		customerService.updateAllCredentials(customer, newEmail, newPassword, newPhone, newCredit, newDebit, newAddress);
		return  Converter.convertToDto(customer);
	}
	/**
	 *restful controller for editing car
	 * */
	@PutMapping(value = {"/customer/editCar/{carId}" , "/customer/{carId}/"})
	public CustomerDto editCars(@PathVariable("carId") String carId, @RequestParam String customerEmail, @RequestParam String addRemove)
	{
		Customer customer = customerService.getCustomerByEmail(customerEmail); 
		customerService.editCars(String.valueOf(customer.getId()), carId, addRemove);
		return  Converter.convertToDto(customer);
	}	
	/**
	 *restful controller for editing appointment
	 **/
	
	@PutMapping(value= {"/customer/editAppointment/{appointmentId}" , "/customer/{appointmentId}/"})
	public CustomerDto editAppointments(@PathVariable("appointmentId") String appointmentId, @RequestParam String customerId, @RequestParam String addRemove)
	{
		Customer customer = customerService.getCustomerById(Integer.parseInt(customerId));
		customerService.editAppointments(customerId, appointmentId, addRemove);
		return Converter.convertToDto(customer);
	}
	

	


}




