package ca.mcgill.ecse321.eventregistration.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.Appointment;
import ca.mcgill.ecse321.repairsystem.model.Customer;
import ca.mcgill.ecse321.repairsystem.model.TimeSlot;

public interface AppointmentRepository extends CrudRepository<Appointment, String>{

	List<Appointment> findByCustomer(Customer customerId);
	
	Appointment findByCustomerAndTimeSlot(Customer customerId, TimeSlot timeSlotId);

}
