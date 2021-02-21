package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.Appointment;
import ca.mcgill.ecse321.repairsystem.model.Customer;

public interface AppointmentRepository extends CrudRepository<Appointment, String>{

	Appointment findByCustomer(Customer customer);
	
}
