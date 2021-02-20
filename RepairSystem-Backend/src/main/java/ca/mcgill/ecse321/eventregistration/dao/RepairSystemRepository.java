package ca.mcgill.ecse321.eventregistration.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.repairsystem.model.*;

//things to insert in the database

@Repository
public class RepairSystemRepository {

	@Autowired
	EntityManager entityManager;

	@Transactional
	public Customer createCustomer(String name, int aID, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem, String aCreditHash, String aDebitHash, String aAddress) {
		Customer c = new Customer(name, aID, aPassword, aPhone, aEmail, aRepairSystem);
		entityManager.persist(c);
		return c;
	}
	
	@Transactional
	public AdministrativeAssistant createAdministrativeAssistant(String aName, int aId, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem) {
		AdministrativeAssistant a = new AdministrativeAssistant(aName, aId, aPassword, aPhone, aEmail, aRepairSystem);
		entityManager.persist(a);
		return a;
	}
	
	@Transactional
	public Mechanic createMechanic(String aName, int aId, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem, Service... allCapabilities) {
		Mechanic m = new Mechanic(aName, aId, aPassword, aPhone, aEmail, aRepairSystem, allCapabilities);
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
	
	//create query later for testing
	

}