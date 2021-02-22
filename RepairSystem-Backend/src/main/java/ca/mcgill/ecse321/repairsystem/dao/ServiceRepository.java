package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.Mechanic;
import ca.mcgill.ecse321.repairsystem.model.Service;

public interface ServiceRepository extends CrudRepository<Service, String>{

	Service findByMechanic(Mechanic mechanic);
	
}
