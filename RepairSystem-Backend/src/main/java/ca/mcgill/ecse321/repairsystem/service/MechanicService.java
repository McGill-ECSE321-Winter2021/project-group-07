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

	@Transactional 
	public Mechanic getMechanicById(int id) {
		Mechanic mechanic = mechanicRepository.findById(id);
		return mechanic;
	}

	@Transactional 
	public List<Mechanic> getMechanicsByName(String name) {
		List<Mechanic> mechanics = toList(mechanicRepository.findByName(name));
		return mechanics;
	}

	@Transactional 
	public Mechanic getMechanicByNumber(int aPhone) {
		Mechanic mechanic = mechanicRepository.findByPhone(aPhone);
		return mechanic;
	}


	@Transactional 
	public Mechanic getMechanicByEmail(String email) {
		Mechanic mechanic = mechanicRepository.findByEmail(email);
		return mechanic;
	}

	@Transactional 
	public Mechanic addService(ca.mcgill.ecse321.repairsystem.model.Service service, Mechanic mechanic) {
		mechanic.addService(service);
		service.addMechanic(mechanic);
		mechanicRepository.save(mechanic);
		serviceRepository.save(service);
		return mechanic;
	}

	@Transactional 
	public Mechanic removeService(ca.mcgill.ecse321.repairsystem.model.Service service, Mechanic mechanic) {
		mechanic.removeService(service);
		service.removeMechanic(mechanic);
		mechanicRepository.save(mechanic);
		serviceRepository.save(service);
		return mechanic;
	}

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