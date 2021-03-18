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
	@Autowired
	private MechanicRepository mechanicRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	
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
	
	@Transactional
	public void addMechanic(Mechanic mechanic, ca.mcgill.ecse321.repairsystem.model.Service service) {
		service.addMechanic(mechanic);
		mechanic.addService(service);
		serviceRepository.save(service);
		mechanicRepository.save(mechanic);
	}
	
	@Transactional
	public void addAppointment(Appointment appointment, ca.mcgill.ecse321.repairsystem.model.Service service) {
		service.addAppointment(appointment);
		appointment.addService(service);
		serviceRepository.save(service);
		appointmentRepository.save(appointment);
	}
	
	@Transactional
	public void removeMechanic(Mechanic mechanic, ca.mcgill.ecse321.repairsystem.model.Service service) {
		service.removeMechanic(mechanic);
		mechanic.removeService(service);
		serviceRepository.save(service);
		mechanicRepository.save(mechanic);
	}
	
	@Transactional
	public void removeAppointment(Appointment appointment, ca.mcgill.ecse321.repairsystem.model.Service service) {
		service.removeAppointment(appointment);
		appointment.removeService(service);
		serviceRepository.save(service);
		appointmentRepository.save(appointment);
	}
	
}
