package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.Mechanic;

public interface MechanicRepository extends CrudRepository<Mechanic, String>{

	Mechanic findMechanicById(int Id);
	List<Mechanic> findMechanicsByName(String name);
	Mechanic findMechanicByNumber(int aPhone);
	Mechanic findMechanicByEmail(String email);
	List<Mechanic> findMechanicsByService(Service service);
	List<Mechanic> findMechanicsByAppointment(Appointment appointment);
	List<Mechanic> findMechanicsByTimeSlot(TimeSlot timeslot);
	

}