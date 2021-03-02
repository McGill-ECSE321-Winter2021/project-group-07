package ca.mcgill.ecse321.repairsystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;

@Service
public class RepairSystemService {
	
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
	
	@Transactional
	public AdministrativeAssistant createAdmin(int id) {
		AdministrativeAssistant admin = new AdministrativeAssistant();
		admin.setId(id);
		administrativeAssistantRepository.save(admin);
		return admin;
	}
	
	@Transactional AdministrativeAssistant getAdmin(int id) {
		AdministrativeAssistant admin = administrativeAssistantRepository.findAdministrativeAssistantById(id);
		return admin;
	}
	
	@Transactional
	public List<AdministrativeAssistant> getAllAdmins() {
		return toList(administrativeAssistantRepository.findAll());
	}
	
	@Transactional
	public Mechanic createMechanic(int id) {
		Mechanic mechanic = new Mechanic();
		mechanic.setId(id);
		 mechanicRepository.save(mechanic);
		return mechanic;
	}
	
	@Transactional Mechanic getMechanic(int id) {
		Mechanic mechanic = mechanicRepository.findMechanicById(id);
		return mechanic;
	}
	
	@Transactional
	public List<Mechanic> getAllMechanics() {
		return toList(mechanicRepository.findAll());
	}
	
	@Transactional
	public Customer createCustomer(int id) {
		Customer customer = new Customer();
		customer.setId(id);
		customerRepository.save(customer);
		return customer;
	}
	
	@Transactional Customer getCustomer(int id) {
		Customer customer = customerRepository.findCustomerById(id);
		return customer;
	}
	
	@Transactional
	public List<Customer> getAllCustomers() {
		return toList(customerRepository.findAll());
	}
	
	@Transactional Appointment bookApp(Customer customer) {
		Appointment app = new Appointment();
		app.setAppointmentId(customer.getName().hashCode());
		app.setCustomer(customer);
		
		appointmentRepository.save(app);
		
		return app;
	}
	
	@Transactional
	public List<Appointment> getAllAppointments() {
		return toList(appointmentRepository.findAll());
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByCustomer(Customer customer) {
		List<Appointment> appointmentsByCustomer = new ArrayList<>();
		for (Appointment a : appointmentRepository.findByCustomerList(customer)) {
			appointmentsByCustomer.add(a);
		}
		return appointmentsByCustomer;
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
