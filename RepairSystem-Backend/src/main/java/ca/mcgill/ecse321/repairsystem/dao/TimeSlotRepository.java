package ca.mcgill.ecse321.repairsystem.dao;

import java.sql.Time;
import java.time.LocalDateTime;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.TimeSlot;


public interface TimeSlotRepository extends CrudRepository<TimeSlot, String>{

	TimeSlot findById(int id);
	TimeSlot findByAppointment(Appointment a);
	List<TimeSlot> findByMechanic(Mechanic m);
}