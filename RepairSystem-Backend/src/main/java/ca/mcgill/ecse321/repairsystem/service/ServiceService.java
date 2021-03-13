package ca.mcgill.ecse321.repairsystem.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;


public class ServiceService {
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Transactional
	public ca.mcgill.ecse321.repairsystem.model.Service createService(ServiceType aType, int price, List<Mechanic> mechanics, List<Appointment> appointment) {
		ca.mcgill.ecse321.repairsystem.model.Service service = new ca.mcgill.ecse321.repairsystem.model.Service(aType, price, mechanics, appointment);
		serviceRepository.save(service);
		return service;
	}
	
	@Transactional
	public ca.mcgill.ecse321.repairsystem.model.Service getServiceByServiceType(ServiceType type) {
		return serviceRepository.findByServiceType(type);
	}
	
	/* 
	 * helper method
	 */	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
