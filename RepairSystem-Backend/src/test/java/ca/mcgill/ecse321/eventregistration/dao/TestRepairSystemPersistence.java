package ca.mcgill.ecse321.eventregistration.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.repairsystem.model.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestRepairSystemPersistence {
	
	@Autowired
	private AdministrativeAssistantRepository administrativeAssistantRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private MechanicRepository mechanicRepository;
	
	@AfterEach
	public void clearDatabase() {
		// Fisrt, we clear appointments to avoid exceptions due to inconsistencies
		appointmentRepository.deleteAll();
		// Then we can clear the other tables
		customerRepository.deleteAll();
		administrativeAssistantRepository.deleteAll();
		mechanicRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadCustomer() {
		RepairSystem repairSystem = new RepairSystem();
		int Id = 260928845;
		Customer customer = new Customer("Annabelle Dion", Id, "12345", 5502441, "annabelle.dion@mail.mcgill.ca", repairSystem);
		customerRepository.save(customer);
		
		customer = null;
		customer = customerRepository.findCustomerById(Id);
		
		assertNotNull(customer);
		assertEquals(Id, customer.getId());
	}
	/*
	 * example of test
	@Test
	public void testPersistAndLoadPerson() {
		String name = "TestPerson";
		// First example for object save/load
		Person person = new Person();
		// First example for attribute save/load
		person.setName(name);
		personRepository.save(person);

		person = null;

		person = personRepository.findPersonByName(name);
		assertNotNull(person);
		assertEquals(name, person.getName());
	}
	*/

}
