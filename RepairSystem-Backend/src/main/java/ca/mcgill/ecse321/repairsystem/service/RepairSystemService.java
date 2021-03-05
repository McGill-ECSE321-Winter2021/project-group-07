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
		AdministrativeAssistant admin = administrativeAssistantRepository.findAdministrativeAssistantById(id);
		return admin;
	}
	
	@Transactional 
	public List<AdministrativeAssistant> getAdminsByName(String name) {
		List<AdministrativeAssistant> admins = toList(administrativeAssistantRepository.findAdministrativeAssistantsByName(name));
		return admin;
	}
	
	@Transactional 
	public AdministrativeAssistant getAdminByNumber(int number) {
		AdministrativeAssistant admin = administrativeAssistantRepository.findAdministrativeAssistantByNumber(number);
		return admin;
	}
	
	@Transactional 
	public AdministrativeAssistant getAdminByEmail(String email) {
		AdministrativeAssistant admin = administrativeAssistantRepository.findAdministrativeAssistantByEmail(email);
		return admin;
	}
	
	@Transactional
	public List<AdministrativeAssistant> getAllAdmins() {
		return toList(administrativeAssistantRepository.findAll());
	}
	
	////////////////////SERVICE MECHANIC METHODS //////////////////// 
	
	@Transactional
	public Mechanic createMechanic(String aName, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem, List<Service> allCapabilities) {
		int id = aName.hashCode() * aPassword.hashCode();
		Mechanic mechanic = new Mechanic(aName, id, aPassword, aPhone, aEmail, aRepairSystem, allCapabilities);
		mechanicRepository.save(mechanic);
		return mechanic;
	}
	
	@Transactional 
	public Mechanic getMechanicById(int id) {
		Mechanic mechanic = mechanicRepository.findMechanicById(id);
		return mechanic;
	}
	
	@Transactional 
	public List<Mechanic> getMechanicsByName(String name) {
		List<Mechanic> mechanics = toList(MechanicRepository.findMechanicsByName(name));
		return mechanics;
	}
	
	@Transactional 
	public Mechanic getMechanicByNumber(int aPhone) {
		Mechanic mechanic = MechanicRepository.findMechanicByNumber(aPhone);
		return mechanic;
	}
	
	
	@Transactional 
	public Mechanic getMechanicByEmail(String email) {
		Mechanic mechanic = MechanicRepository.findMechanicByEmail(email);
		return mechanic;
	}
	
	@Transactional 
	public List<Mechanic> getMechanicByService(Service service) {
		List<Mechanic> mechanics = toList(MechanicRepository.findMechanicsByService(service));
		return mechanics;
	}
	
	@Transactional 
	public List<Mechanic> getMechanicByAppointment(Appointment appointment) {
		List<Mechanic> mechanics = toList(MechanicRepository.findMechanicsByAppointment(appointment));
		return mechanics;
	}
	
	@Transactional 
	public List<Mechanic> getMechanicByTimeSlot(TimeSlot timeslot) {
		List<Mechanic> mechanics = toList(MechanicRepository.findMechanicsByTimeSlot(timeslot));
		return mechanics;
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
		Customer customer = customerRepository.findCustomerById(id);
		return customer;
	}
	
	@Transactional 
	public List<Customer> getCustomersByName(String name) {
		List<Customer> customers = toList(customerRepository.findCustomersByName(name));
		return customers;
	}
	
	@Transactional 
	public Customer getCustomerByNumber(int number) {
		Customer customer = customerRepository.findCustomerByNumber(number);
		return customer;
	}
	
	@Transactional 
	public Customer getCustomerByEmail(String email) {
		Customer customer = customerRepository.findCustomerByEmail(email);
		return customer;
	}
	
	@Transactional 
	public Customer getCustomerByAddress(String address) {
		List<Customer> customers = toList(customerRepository.findCustomersByAddress(address));
		return customers;
	}
	
	@Transactional 
	public Customer getCustomerByAppointment(Appointment app) {
		Customer customer = customerRepository.findCustomerByAppointment(app);
		return customer;
	}
	
	@Transactional 
	public Customer getCustomerByCar(Car car) {
		Customer customer = customerRepository.findCustomerByCar(car);
		return customer;
	}
	
	@Transactional
	public List<Customer> getAllCustomers() {
		return toList(customerRepository.findAll());
	}
	
	////////////////////SERVICE APPOINTMENT METHODS //////////////////// 
	
	@Transactional 
	public Appointment createApp(Customer customer, Timeslot time, List<Mechanic> mechanics, Car car, List<Image> image, List<Service> services, String note, AppointmentStatus status) {
		int id = customer.getId().hashCode() * time.getId().hashCode();
		Appointment app = new Appointment(customer, id, time, mechanics, car, images, services, note, status);
		appointmentRepository.save(app);
		return app;
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByCustomer(Customer customer) {
		List<Appointment> appointments = toList(appointmentRepository.getAppointmentsByCustomer(customer));
		return appointments;
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByCar(Car car) {
		List<Appointment> appointments = toList(appointmentRepository.getAppointmentsByCar(car));
		return appointments;
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByStatus(AppointmentStatus status) {
		List<Appointment> appointments = toList(appointmentRepository.getAppointmentsByStatus(status));
		return appointments;
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByService(Service service) {
		List<Appointment> appointments = toList(appointmentRepository.getAppointmentsByService(service));
		return appointments;
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByTimeSlot(TimeSlot time) {
		List<Appointment> appointments = toList(appointmentRepository.getAppointmentsByTimeSlot(time));
		return appointments;
	}
	
	@Transactional
	public List<Appointment> getAppointmentsByMechanic(Mechanic mechanic) {
		List<Appointment> appointments = toList(appointmentRepository.getAppointmentsByMechanics(mechanic));
		return appointments;
	}
	
	@Transactional
	public Appointment getAppointmentsById(int id) {
		Appointment appointment = appointmentRepository.getAppointmentById(id);
		return appointment;
	}
	
	@Transactional
	public List<Appointment> getAllAppointments() {
		return toList(appointmentRepository.findAll());
	}
	
	//////////////////// SERVICE CAR METHODS //////////////////// 
	
	@Transactional
	public Car createCar(int id, CarType type, boolean winterTires, int numOfKm, List<Appointment> appointments, Customer customer) {
		int id = numOfKm.hashCode() * customer.getId().hashCode();
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
	public Car getCarByAppointment(Appointment a) {
		return carRepository.findByAppointment(a);
	}
	
	@Transactional
	public List<Car> getCarsByCustomer(Customer customer) {
		return toList(carRepository.findCarsByCustomer(customer));
	}
	
	@Transactional
	public List<Car> getCarsByCarType(CarType type) {
		return toList(carRepository.findCarsByCarType(type));
	}
	
	@Transactional
	public List<Car> getCarsByWinterTires(boolean winterTires) {
		return toList(carRepository.findCarsByWinterTires(boolean winterTires));
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
		List<Image> i = toList(imageRepository.findImagesByAppointment(a));
		return i;
	}
	
	@Transactional
	public List<Image> getAllImages() {
		return toList(imageRepository.findAll());
	}
	
	////////////////////SERVICE SERVICE METHODS //////////////////// 
	
	@Transactional
	public Service createService(ServiceType aType, List<Mechanics> mechanics, List<Appointments> a) {
		int id = aType.hashCode();
		Service service = new Service(aType, mechanics, a);
		ServiceRepository.save(service);
		return service;
	}
	
	@Transactional
	public List<Service> getServicesByMechanic(Mechanic mechanic) {
		return toList(serviceRepository.findServicesByMechanic(mechanic));
	}
	
	@Transactional
	public List<Service> getServicesByAppointment(Appointment a) {
		return toList(serviceRepository.findServicesByAppointment(a));
	}
	
	@Transactional
	public Service getServiceByServiceType(ServiceType type) {
		return serviceRepository.findServiceByServiceType(type);
	}
	
	////////////////////SERVICE TIMESLOT METHODS //////////////////// 
	
	@Transactional 
	public TimeSlot createTimeSlot(LocalDateTime aStartTime, LocalDateTime aEndTime, List<Mechanics> mechanics, List<Appointments> appointments) {
		int id = aStartTime.hashCode() * aEndTime.hashCode();
		TimeSlot timeslot = new TimeSlot(aStartTime, aEndTime, id, mechanics, appointments);
		TimeSlotRepository.save(timeslot);
		return timeslot;
	}
	
	@Transactional
	public List<TimeSlot> getTimeSlotsByMechanic(Mechanic m) { 
		return toList(TimeSlotRepository.findTimeSlotByMechanic(m));
	}
	
	@Transactional
	public TimeSlot getTimeSlotById(int id) { 
		return TimeSlotRepository.findTimeSlotById(id);
	}
	
	@Transactional
	public TimeSlot getTimeSlotByAppointment(Appointment a) { 
		return TimeSlotRepository.findTimeSlotById(a);
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
