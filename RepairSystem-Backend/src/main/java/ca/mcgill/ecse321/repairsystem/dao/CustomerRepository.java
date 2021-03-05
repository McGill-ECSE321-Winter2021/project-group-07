package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.*;
import java.util.*;

public interface CustomerRepository extends CrudRepository<Customer, String>{
	
	Customer findById(int Id);
	List<Customer> findByName(String name);
	Customer findByPhone(int number);
	Customer findByEmail(String email);
	List<Customer> findByAddress(String address);
	
}