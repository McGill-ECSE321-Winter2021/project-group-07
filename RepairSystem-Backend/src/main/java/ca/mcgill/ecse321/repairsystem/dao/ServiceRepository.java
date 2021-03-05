package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;

public interface ServiceRepository extends CrudRepository<Service, String>{
	
	Service findByServiceType(ServiceType type);
}
