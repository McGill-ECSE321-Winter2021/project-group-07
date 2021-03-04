package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.*;

import java.util.*;

public interface CarRepository extends CrudRepository<Car, String>{

	List<Car> findCarsByCustomer(Customer customer);
	
	Car findById(int Id);
	
	Car findByAppointment(Appointment a);
	
}