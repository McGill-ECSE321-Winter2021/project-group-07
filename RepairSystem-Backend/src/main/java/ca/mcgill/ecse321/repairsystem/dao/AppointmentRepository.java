package ca.mcgill.ecse321.repairsystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.Appointment;
import ca.mcgill.ecse321.repairsystem.model.Customer;

public interface AppointmentRepository extends CrudRepository<Appointment, String>{
	
	List<Appointment> findByCustomer(Customer customer);
	List<Appointment> findByMechanic(Mechanic mechanic);
	List<Appointment> findByService(Service service);
	List<Appointment> findByTimeSlot(TimeSlot time);
	List<Appointment> findByCar(Car car);
	List<Appointment> findByStatus(Status status);
	Appointment findById(int id);
	
}
