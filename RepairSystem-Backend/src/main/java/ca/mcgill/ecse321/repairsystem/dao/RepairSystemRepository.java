package ca.mcgill.ecse321.repairsystem.dao;

import java.util.*;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.repairsystem.model.*;

@Repository
public class RepairSystemRepository {

	@Autowired
	EntityManager entityManager;

	@Transactional
	public Customer createCustomer(String aName, int id, String aPassword, int aPhone, String aEmail,Calendar lastActive,  RepairSystem aRepairSystem, String credit, String debit, String address) {
		Customer c = new Customer(aName, id,aPassword, aPhone, aEmail, lastActive, aRepairSystem, credit, debit, address);
		entityManager.persist(c);
		return c;
	}
	
	@Transactional
	public AdministrativeAssistant createAdministrativeAssistant(String aName, int id, String aPassword, int aPhone, String aEmail,Calendar lastActive,  RepairSystem aRepairSystem) {
		AdministrativeAssistant a = new AdministrativeAssistant(aName, id,aPassword, aPhone, aEmail, aRepairSystem);
		entityManager.persist(a);
		return a;
	}
	
	@Transactional
	public Mechanic createMechanic(String aName, int id, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem, List<Service> allCapabilities) {
		Mechanic m = new Mechanic(aName,id, aPassword, aPhone, aEmail, aRepairSystem, allCapabilities);
		return m;
	}

	@Transactional
	public Customer getCustomer(int aId) {
		Customer c = entityManager.find(Customer.class, aId);
		return c;
	}
	
	@Transactional
	public AdministrativeAssistant getAdminiatrativeAssistant(int aId) {
		AdministrativeAssistant a = entityManager.find(AdministrativeAssistant.class, aId);
		return a;
	}
	
	@Transactional
	public Mechanic getMechanic(int aId) {
		Mechanic m = entityManager.find(Mechanic.class, aId);
		return m;
	}
	

}