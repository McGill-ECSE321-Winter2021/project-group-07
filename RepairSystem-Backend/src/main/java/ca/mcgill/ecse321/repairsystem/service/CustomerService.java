package ca.mcgill.ecse321.repairsystem.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.dao.CustomerRepository;

public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	////////////////////SERVICE CUSTOMER METHODS //////////////////// 

	@Transactional
	public Customer createCustomer(String aName, String aPassword, int aPhone, String aEmail, Calendar lastDate, RepairSystem aRepairSystem, String credit, String debit, String add) {
		if(aName == null || aName.trim().length() == 0) {
			throw new IllegalArgumentException("Customer name cannot be empty!");
		}
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
