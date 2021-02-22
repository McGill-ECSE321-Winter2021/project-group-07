package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.Car;
import ca.mcgill.ecse321.repairsystem.model.Customer;

public interface CarRepository extends CrudRepository<Car, String>{

	Car findByCustomer(Customer customer);
	
}