package ca.mcgill.ecse321.repairsystem.service;

import java.util.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Appointment.AppointmentStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.repairsystem.dao.CarRepository;
import ca.mcgill.ecse321.repairsystem.dao.CustomerRepository;
import ca.mcgill.ecse321.repairsystem.dao.ImageRepository;
import ca.mcgill.ecse321.repairsystem.dao.MechanicRepository;
import ca.mcgill.ecse321.repairsystem.dao.ServiceRepository;
import ca.mcgill.ecse321.repairsystem.dao.TimeSlotRepository;

public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	////////////////////SERVICE CUSTOMER METHODS //////////////////// 

	@Transactional
	public Customer createCustomer(String aName, String aPassword, int aPhone, String aEmail, Calendar lastDate, RepairSystem aRepairSystem, String credit, String debit, String add) {
		int id = aName.hashCode() * aPassword.hashCode();
		Customer customer = new Customer(aName, id, aPassword, aPhone, aEmail, lastDate, aRepairSystem, credit, debit, add);
		customerRepository.save(customer);
		return customer;
	}

	@Transactional 
	public Customer getCustomerById(int id) {
		Customer customer = customerRepository.findById(id);
		return customer;
	}

	@Transactional 
	public List<Customer> getCustomersByName(String name) {
		List<Customer> customers = toList(customerRepository.findByName(name));
		return customers;
	}

	@Transactional 
	public Customer getCustomerByNumber(int number) {
		Customer customer = customerRepository.findByPhone(number);
		return customer;
	}

	@Transactional 
	public Customer getCustomerByEmail(String email) {
		Customer customer = customerRepository.findByEmail(email);
		return customer;
	}

	@Transactional 
	public List<Customer> getCustomersByAddress(String address) {
		List<Customer> customers = toList(customerRepository.findByAddress(address));
		return customers;
	}

	@Transactional
	public List<Customer> getAllCustomers() {
		return toList(customerRepository.findAll());
	}

	/* 
	 * helper method
	 */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
