package ca.mcgill.ecse321.repairsystem.service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Appointment.AppointmentStatus;
import ca.mcgill.ecse321.repairsystem.model.Car.CarType;
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;

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
	
	//////////////////// SERVICE ADMIN METHODS //////////////////// 
	@Transactional
	public AdministrativeAssistant createAdmin(String aName, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem) {
		int id = aName.hashCode() * aPassword.hashCode();
		AdministrativeAssistant admin = new AdministrativeAssistant(aName, id, aPassword, aPhone, aEmail, aRepairSystem);
		administrativeAssistantRepository.save(admin);
		return admin;
	}
	
	@Transactional 
	public AdministrativeAssistant getAdminById(int id) {
		AdministrativeAssistant admin = administrativeAssistantRepository.findById(id);
		return admin;
	}
	
	@Transactional 
	public List<AdministrativeAssistant> getAdminsByName(String name) {
		List<AdministrativeAssistant> admins = toList(administrativeAssistantRepository.findByName(name));
		return admins;
	}
	
	@Transactional 
	public AdministrativeAssistant getAdminByNumber(int number) {
		AdministrativeAssistant admin = administrativeAssistantRepository.findByPhone(number);
		return admin;
	}
	
	@Transactional 
	public AdministrativeAssistant getAdminByEmail(String email) {
		AdministrativeAssistant admin = administrativeAssistantRepository.findByEmail(email);
		return admin;
	}
	
	@Transactional
	public List<AdministrativeAssistant> getAllAdmins() {
		return toList(administrativeAssistantRepository.findAll());
	}
	
	////////////////////SERVICE MECHANIC METHODS //////////////////// 
	
	@Transactional
	public Mechanic createMechanic(String aName, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem, List<ca.mcgill.ecse321.repairsystem.model.Service> allCapabilities) {
		int id = aName.hashCode() * aPassword.hashCode();
		Mechanic mechanic = new Mechanic(aName, id, aPassword, aPhone, aEmail, aRepairSystem, allCapabilities);
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
	public List<Mechanic> getAllMechanics() {
		return toList(mechanicRepository.findAll());
	}
	
	////////////////////SERVICE CUSTOMER METHODS //////////////////// 
	
	@Transactional
	public Customer createCustomer(String aName, String aPassword, int aPhone, String aEmail, Calendar lastDate, RepairSystem aRepairSystem, String credit, String debit, String add) {
		int id = aName.hashCode() * aPassword.hashCode();
		Customer customer = new Customer(aName, id, aPassword, aPhone, aEmail, lastDate, aRepairSystem, credit, debit, add);
		customerRepository.save(customer);
		return customer;
	}
	
	@Transactional 
	public Customer getCustomerById(int id) {
		Customer customer = customerRepository.findById(id);
		return customer;
	}
	
	@Transactional 
	public List<Customer> getCustomersByName(String name) {
		List<Customer> customers = toList(customerRepository.findByName(name));
		return customers;
	}
	
	@Transactional 
	public Customer getCustomerByNumber(int number) {
		Customer customer = customerRepository.findByPhone(number);
		return customer;
	}
	
	@Transactional 
	public Customer getCustomerByEmail(String email) {
		Customer customer = customerRepository.findByEmail(email);
		return customer;
	}
	
	@Transactional 
	public List<Customer> getCustomersByAddress(String address) {
		List<Customer> customers = toList(customerRepository.findByAddress(address));
		return customers;
	}
	
	@Transactional
	public List<Customer> getAllCustomers() {
		return toList(customerRepository.findAll());
	}
	
	////////////////////SERVICE APPOINTMENT METHODS //////////////////// 
	
	@Transactional 
	public Appointment createApp(Customer customer, TimeSlot time, List<Mechanic> mechanics, Car car, List<Image> images, List<ca.mcgill.ecse321.repairsystem.model.Service> services, String note, AppointmentStatus status) {
		int id = customer.hashCode() * time.hashCode();
		Appointment app = new Appointment(customer, id, time, mechanics, car, images, services, note, status);
		appointmentRepository.save(app);
		return app;
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByCustomer(Customer customer) {
		List<Appointment> appointments = toList(appointmentRepository.findByCustomer(customer));
		return appointments;
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByCar(Car car) {
		List<Appointment> appointments = toList(appointmentRepository.findByCar(car));
		return appointments;
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByStatus(AppointmentStatus status) {
		List<Appointment> appointments = toList(appointmentRepository.findByStatus(status));
		return appointments;
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByTimeSlot(TimeSlot time) {
		List<Appointment> appointments = toList(appointmentRepository.findByTimeSlot(time));
		return appointments;
	}
	
	@Transactional
	public Appointment getAppointmentsById(int id) {
		Appointment appointment = appointmentRepository.findById(id);
		return appointment;
	}
	
	@Transactional
	public List<Appointment> getAllAppointments() {
		return toList(appointmentRepository.findAll());
	}
	
	//////////////////// SERVICE CAR METHODS //////////////////// 
	
	@Transactional
	public Car createCar(CarType type, boolean winterTires, int numOfKm, List<Appointment> appointments, Customer customer) {
		int id = (Integer.toString(numOfKm)).hashCode() * customer.hashCode();
		Car c = new Car(id, type, winterTires, numOfKm, appointments, customer);
		carRepository.save(c);
		return c;
	}
	
	@Transactional 
	public Car getCarById(int id) {
		Car c = carRepository.findById(id);
		return c;
	}
	
	@Transactional
	public List<Car> getCarsByCustomer(Customer customer) {
		return toList(carRepository.findByCustomer(customer));
	}
	
	@Transactional
	public List<Car> getCarsByCarType(CarType type) {
		return toList(carRepository.findByCarType(type));
	}
	
	@Transactional
	public List<Car> getCarsByWinterTires(boolean winterTires) {
		return toList(carRepository.findByWinterTires(winterTires));
	}
	
	@Transactional
	public List<Car> getAllCars() {
		return toList(carRepository.findAll());
	}
	
	////////////////////SERVICE IMAGE METHODS //////////////////// 
	
	@Transactional
	public Image createImage(String url, Appointment a) {
		int id = url.hashCode();
		Image i = new Image(id, url, a);
		imageRepository.save(i);
		return i;
	}
	
	@Transactional List<Image> getImagesByAppointment(Appointment a) {
		List<Image> i = toList(imageRepository.findByAppointment(a));
		return i;
	}
	
	@Transactional
	public List<Image> getAllImages() {
		return toList(imageRepository.findAll());
	}
	
	////////////////////SERVICE SERVICE METHODS //////////////////// 
	
	@Transactional
	public ca.mcgill.ecse321.repairsystem.model.Service createService(ServiceType aType, int price, List<Mechanic> mechanics, List<Appointment> appointment) {
		ca.mcgill.ecse321.repairsystem.model.Service service = new ca.mcgill.ecse321.repairsystem.model.Service(aType, price, mechanics, appointment);
		serviceRepository.save(service);
		return service;
	}
	
	@Transactional
	public ca.mcgill.ecse321.repairsystem.model.Service getServiceByServiceType(ServiceType type) {
		return serviceRepository.findByServiceType(type);
	}
	
	////////////////////SERVICE TIMESLOT METHODS //////////////////// 
	
	@Transactional 
	public TimeSlot createTimeSlot(LocalDateTime aStartTime, LocalDateTime aEndTime, List<Mechanic> mechanics, List<Appointment> appointments) {
		int id = aStartTime.hashCode() * aEndTime.hashCode();
		TimeSlot timeslot = new TimeSlot(aStartTime, aEndTime, id, mechanics, appointments);
		timeSlotRepository.save(timeslot);
		return timeslot;
	}
	
	@Transactional
	public TimeSlot getTimeSlotById(int id) { 
		return timeSlotRepository.findById(id);
	}
	
	@Transactional
	public List<TimeSlot> getTimeSlotByStartTime(LocalDateTime time) { 
		return timeSlotRepository.findByStartTime(time);
	}
	
	@Transactional
	public List<TimeSlot> getTimeSlotByEndTime(LocalDateTime time) { 
		return timeSlotRepository.findByEndTime(time);
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
