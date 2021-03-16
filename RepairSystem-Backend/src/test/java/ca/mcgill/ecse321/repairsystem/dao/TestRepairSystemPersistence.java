package ca.mcgill.ecse321.repairsystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.repairsystem.RepairSystemApplication;
import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;

@ExtendWith(SpringExtension.class)

@SpringBootTest(classes = RepairSystemApplication.class)
public class TestRepairSystemPersistence {
	
	@Autowired
	private AdministrativeAssistantRepository administrativeAssistantRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private MechanicRepository mechanicRepository;
	@Autowired 
	private CarRepository carRepository;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	
	@AfterEach
	public void clearDatabase() {
		timeSlotRepository.deleteAll();
		serviceRepository.deleteAll();
		imageRepository.deleteAll();
		appointmentRepository.deleteAll();
		carRepository.deleteAll();
		customerRepository.deleteAll();
		administrativeAssistantRepository.deleteAll();
		mechanicRepository.deleteAll();
	
	}
	
	@Test
	public void testPersistAndLoadAdministrativeAssistant() {
		AdministrativeAssistant assistant = new AdministrativeAssistant();
		int Id = 100;
		assistant.setId(Id);
		administrativeAssistantRepository.save(assistant);
		
		assistant = null;
		assistant = administrativeAssistantRepository.findById(Id);
		
		assertNotNull(assistant);
		assertEquals(Id, assistant.getId());
		
	}
		
	@Test
	public void testPersistAndLoadCustomer() {
		Customer customer = new Customer();
		int Id = 200;
		customer.setId(Id);
		customerRepository.save(customer);
		
		customer = null;
		customer = customerRepository.findById(Id);
		
		assertNotNull(customer);
		assertEquals(Id, customer.getId());
		
	}
	
	@Test
	public void testPersistAndLoadMechanic() {
		Mechanic mechanic = new Mechanic();
		int Id = 300;
		mechanic.setId(Id);
		mechanicRepository.save(mechanic);
		
		mechanic = null;
		mechanic = mechanicRepository.findById(Id);
		
		assertNotNull(mechanic);
		assertEquals(Id, mechanic.getId());
	}
	
	@Test
	public void testPersistAndLoadAppointment() {
		int customerId = 400;
		Customer customer = new Customer();
		customer.setId(customerId);
		customerRepository.save(customer);
		
		Appointment appointment = new Appointment();
		int appointmentId = 401;
		appointment.setId(appointmentId);
		appointment.setCustomer(customer);
		appointmentRepository.save(appointment);
		
		appointment = null;
		appointment = appointmentRepository.findByCustomer(customer).get(0);
		
		assertNotNull(appointment);
		assertEquals(appointmentId, appointment.getId());
	}
	
	@Test
	public void testPersistAndLoadCar() {
		int customerId = 500;
		Customer customer = new Customer();
		customer.setId(customerId);
		customerRepository.save(customer);
		
		Car car = new Car();
		int carId = 501;
		car.setId(carId);
		car.setCustomer(customer);
		carRepository.save(car);
		
		car = null;
		car = carRepository.findByCustomer(customer).get(0);
		
		assertNotNull(car);
		assertEquals(carId, car.getId());
	}
	
	@Test
	public void testPersistAndLoadImage() {
		Appointment appointment = new Appointment();
		int appointmentId = 601;
		appointment.setId(appointmentId);
		appointmentRepository.save(appointment);
		
		Image image = new Image();
		int  imageId = 601;
		image.setId(imageId);
		image.setAppointment(appointment);
		imageRepository.save(image);
		
		image = null;
		image = imageRepository.findByAppointment(appointment).get(0);
		
		assertNotNull(image);
		assertEquals(imageId, image.getId());
	
	}
	
	@Test
	public void testPersistAndLoadService() {
		Mechanic mechanic = new Mechanic();
		int Id = 700;
		mechanic.setId(Id);
		mechanicRepository.save(mechanic);
		
		Service service = new Service();
		int price = 701;
		service.setPrice(price);
		ServiceType serviceType = ServiceType.OilChange;
		service.setServiceType(serviceType);
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		mechanics.add(mechanic);
		service.setMechanics(mechanics);
		serviceRepository.save(service);
		
		service = null;
		service = serviceRepository.findByServiceType(ServiceType.OilChange);
		assertNotNull(service);
		assertEquals(price, service.getPrice());
	}
	
	@Test
	public void testPersistAndLoadTimeSlot() {
		TimeSlot timeSlot = new TimeSlot();
		int timeSlotId = 800;
		timeSlot.setId(timeSlotId);
		LocalDateTime start = LocalDateTime.now();
		timeSlot.setStartTime(start);
		timeSlotRepository.save(timeSlot);
		
		timeSlot = null;
		timeSlot = timeSlotRepository.findByStartTime(start).get(0);
		
		assertNotNull(timeSlot);
		assertEquals(timeSlotId, timeSlot.getId());
	}

}
