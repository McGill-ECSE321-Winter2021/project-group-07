package ca.mcgill.ecse321.repairsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse321.repairsystem.dao.MechanicRepository;
import ca.mcgill.ecse321.repairsystem.dao.ServiceRepository;

@Service
public class MechanicService {

	@Autowired
	private MechanicRepository mechanicRepository;
	@Autowired
	private ServiceRepository serviceRepository;
	////////////////////SERVICE MECHANIC METHODS //////////////////// 

	/**
	 * Creates a mechanic and saves new mechanic object in the database
	 * @param aName
	 * @param aPassword
	 * @param aPhone
	 * @param aEmail
	 * @param allCapabilities
	 * @return
	 */
	@Transactional
	public Mechanic createMechanic(String aName, String aPassword, int aPhone, String aEmail, List<ca.mcgill.ecse321.repairsystem.model.Service> allCapabilities) {

		if(aName == null || aName.trim().length() == 0)
		{
			throw new IllegalArgumentException("Mechanic name cannot be empty!");

		}else if (aPassword == null || aPassword.trim().length() == 0)
		{
			throw new IllegalArgumentException("Mechanic password cannot be empty!");
		}else if (aEmail == null || aEmail.trim().length() == 0)
		{
			throw new IllegalArgumentException("Mechanic email cannot be empty!");
		}
		int id = aEmail.hashCode();
		Mechanic mechanic = new Mechanic(aName, id, aPassword, aPhone, aEmail, allCapabilities);
		mechanicRepository.save(mechanic);
		return mechanic;
	}

	/**
	 * Getter method to obtain mechanic by id
	 * @param id
	 * @return associated mechanic
	 */
	@Transactional 
	public Mechanic getMechanicById(int id) {
		Mechanic mechanic = mechanicRepository.findById(id);
		return mechanic;
	}
	
	/**
	 * Getter method to obtain all mechanics by searching by specific name
	 * @param name
	 * @return the list of mechanics
	 */
	@Transactional 
	public List<Mechanic> getMechanicsByName(String name) {
		List<Mechanic> mechanics = toList(mechanicRepository.findByName(name));
		return mechanics;
	}

	
	/**
	 * Getter method to obtain a mechanic object by searching by a specific phone number
	 * @param aPhone
	 * @return
	 */
	@Transactional 
	public Mechanic getMechanicByNumber(int aPhone) {
		Mechanic mechanic = mechanicRepository.findByPhone(aPhone);
		return mechanic;
	}

	/**
	 * Getter method to obtain a mechanic by searching by a specific email address
	 * @param email
	 * @return mechanic object associated to the email
	 */
	@Transactional 
	public Mechanic getMechanicByEmail(String email) {
		Mechanic mechanic = mechanicRepository.findByEmail(email);
		return mechanic;
	}

	/**
	 * Add a service to a mechanic and updating the mechanic and service tables in database
	 * @param service
	 * @param mechanic
	 * @return mechanic object
	 */
	@Transactional 
	public Mechanic addService(ca.mcgill.ecse321.repairsystem.model.Service service, Mechanic mechanic) {
		mechanic.addService(service);
		service.addMechanic(mechanic);
		mechanicRepository.save(mechanic);
		serviceRepository.save(service);
		return mechanic;
	}

	/**
	 * Removing a service from a mechanic and updating the mechanic and service table in database
	 * @param service
	 * @param mechanic
	 * @return mechanic
	 */
	@Transactional 
	public Mechanic removeService(ca.mcgill.ecse321.repairsystem.model.Service service, Mechanic mechanic) {
		mechanic.removeService(service);
		service.removeMechanic(mechanic);
		mechanicRepository.save(mechanic);
		serviceRepository.save(service);
		return mechanic;
	}

	/**
	 * Obtain all the mechanics of the database
	 * @return mechanic
	 */
	@Transactional
	public List<Mechanic> getAllMechanics() {
		return toList(mechanicRepository.findAll());
	}


	@Transactional
	public Mechanic editMachanic(Mechanic mechanic, String name, String password, int phone, String email, List<ca.mcgill.ecse321.repairsystem.model.Service> service) { 
		mechanic.setName(name);
		mechanic.setId(email.hashCode());
		mechanic.setPassword(password);
		mechanic.setPhone(phone);
		mechanic.setEmail(email);
		mechanic.setServices(service);
		return mechanic;
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