package ca.mcgill.ecse321.repairsystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.Appointment;
import ca.mcgill.ecse321.repairsystem.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String>{
	
	Customer findCustomerById(int Id);
	
}