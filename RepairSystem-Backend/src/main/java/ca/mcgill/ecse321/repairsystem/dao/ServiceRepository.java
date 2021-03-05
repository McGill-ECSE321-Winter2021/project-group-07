package ca.mcgill.ecse321.repairsystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.Mechanic;
import ca.mcgill.ecse321.repairsystem.model.Service;

public interface ServiceRepository extends CrudRepository<Service, String>{
	
	List<Service> findServicesByMechanic(Mechanic mechanic);
	List<Service> findServicesByAppointment(Appointment appointment);
	Service findServicesByServiceType(ServiceType type);
}
