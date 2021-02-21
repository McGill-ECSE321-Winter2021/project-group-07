package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String>{

	Customer findCustomerByName(String name);
	
	Customer findCustomerById(int Id);

}