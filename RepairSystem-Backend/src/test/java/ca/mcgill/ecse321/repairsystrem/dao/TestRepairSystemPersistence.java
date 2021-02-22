package ca.mcgill.ecse321.repairsystrem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

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
		assistant = administrativeAssistantRepository.findAdministrativeAssistantById(Id);
		
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
		customer = customerRepository.findCustomerById(Id);
		
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
		mechanic = mechanicRepository.findMechanicById(Id);
		
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
		appointment.setAppointmentId(appointmentId);
		appointment.setCustomer(customer);
		appointmentRepository.save(appointment);
		
		appointment = null;
		appointment = appointmentRepository.findByCustomer(customer);
		
		assertNotNull(appointment);
		assertEquals(appointmentId, appointment.getAppointmentId());
	}
	
	@Test
	public void testPersistAndLoadCar() {
		int customerId = 500;
		Customer customer = new Customer();
		customer.setId(customerId);
		customerRepository.save(customer);
		
		Car car = new Car();
		int carId = 501;
		car.setCarId(carId);
		car.setCustomer(customer);
		carRepository.save(car);
		
		car = null;
		car = carRepository.findByCustomer(customer);
		
		assertNotNull(car);
		assertEquals(carId, car.getCarId());
	}
	
	@Test
	public void testPersistAndLoadImage() {
		Appointment appointment = new Appointment();
		int appointmentId = 601;
		appointment.setAppointmentId(appointmentId);
		appointmentRepository.save(appointment);
		
		Image image = new Image();
		int  imageId = 601;
		image.setImageId(imageId);
		image.setAppointment(appointment);
		imageRepository.save(image);
		
		image = null;
		image = imageRepository.findByAppointment(appointment);
		
		assertNotNull(image);
		assertEquals(imageId, image.getImageId());
	
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
		service.setType(serviceType);
		service.setMechanic(mechanic);
		serviceRepository.save(service);
		
		service = null;
		service = serviceRepository.findByMechanic(mechanic);
		assertNotNull(service);
		assertEquals(price, service.getPrice());
	}
	
	@Test
	public void testPersistAndLoadTimeSlot() {
		TimeSlot timeSlot = new TimeSlot();
		int timeSlotId = 800;
		timeSlot.setTimeSlotId(timeSlotId);
		LocalDateTime start = LocalDateTime.now();
		timeSlot.setStartTime(start);
		timeSlotRepository.save(timeSlot);
		
		timeSlot = null;
		timeSlot = timeSlotRepository.findByStartTime(start);
		
		assertNotNull(timeSlot);
		assertEquals(timeSlotId, timeSlot.getTimeSlotId());
	}

}