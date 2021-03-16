package ca.mcgill.ecse321.repairsystem.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;

@Service
public class ServiceService {
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Transactional
	public ca.mcgill.ecse321.repairsystem.model.Service createService(ServiceType aType, int price, List<Mechanic> mechanics, List<Appointment> appointment) {
		
		if(aType == null)
		{
			throw new IllegalArgumentException("Service type cannot be null");
		}else if (mechanics == null)
		{
			throw new IllegalArgumentException("List of mechanics cannnot be null");
		}else if (appointment == null)
		{
			throw new IllegalArgumentException("List of appointments cannot be null");
		}
		
		ca.mcgill.ecse321.repairsystem.model.Service service = new ca.mcgill.ecse321.repairsystem.model.Service(aType, price, mechanics, appointment);
		serviceRepository.save(service);
		return service;
	}
	
	@Transactional
	public ca.mcgill.ecse321.repairsystem.model.Service getServiceByServiceType(ServiceType type) {
		return serviceRepository.findByServiceType(type);
	}
	
	@Transactional
	public List<ca.mcgill.ecse321.repairsystem.model.Service> getAllServices() {
		return serviceRepository.findAll();
	}
	
}