package ca.mcgill.ecse321.repairsystrem.dao;

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

import ca.mcgill.ecse321.repairsystem.RepairSystemApplication;
import ca.mcgill.ecse321.repairsystem.dao.AdministrativeAssistantRepository;
import ca.mcgill.ecse321.repairsystem.dao.AppointmentRepository;
import ca.mcgill.ecse321.repairsystem.dao.CustomerRepository;
import ca.mcgill.ecse321.repairsystem.dao.MechanicRepository;
import ca.mcgill.ecse321.repairsystem.model.*;

@ExtendWith(SpringExtension.class)

@SpringBootTest(classes = RepairSystemApplication.class)
public class TestRepairSystemPersistence {
	
	@Autowired
	private AdministrativeAssistantRepository administrativeAssistantRepository;
	//@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private MechanicRepository mechanicRepository;
	
	@AfterEach
	public void clearDatabase() {
		// Fisrt, we clear appointments to avoid exceptions due to inconsistencies
		//appointmentRepository.deleteAll();
		// Then we can clear the other tables
		customerRepository.deleteAll();
		administrativeAssistantRepository.deleteAll();
		mechanicRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadCustomer() {
		int Id = 260928845;
		Customer customer = new Customer();
		customer.setId(Id);
		customerRepository.save(customer);
		
		customer = null;
		customer = customerRepository.findCustomerById(Id);
		
		assertNotNull(customer);
		assertEquals(Id, customer.getId());
	}
	

}
