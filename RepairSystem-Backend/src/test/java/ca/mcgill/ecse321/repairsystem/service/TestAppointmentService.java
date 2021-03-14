package ca.mcgill.ecse321.repairsystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;


import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Appointment.AppointmentStatus;
import ca.mcgill.ecse321.repairsystem.model.Car.CarType;


@ExtendWith(MockitoExtension.class)
public class TestAppointmentService {
	@Mock
	private AppointmentRepository appointmentDao;
	
	@Mock
	private CustomerRepository customerDao;
	
	
	@InjectMocks
	private AppointmentService service;
	
	//fields for creating an appointment 
	private static  int APPOINTMENT_ID= 45;
	private static AppointmentStatus APPOINTMENT_STATUS= AppointmentStatus.InRepair;
	private static String APPOINTMENT_NOTE = "SomDescription"; 

	//fields for creating a customer 
	private static RepairSystem repair = new RepairSystem();
	private static Calendar c =  new GregorianCalendar(2021,3,13);
	private static Customer CUSTOMER = new Customer("TestPerson", 2001, "123abc", 76523455,"TestPerson@gmail.com", c, repair, "123456789","987654321", "123 Street Avenue");

	//fields for creating timeslot 
	private static TimeSlot TIME_SLOT = new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240, new ArrayList<Mechanic>(), new ArrayList<Appointment>() );

	private static Car CAR = new Car(40567, CarType.Sedan,false, 170000, new ArrayList<Appointment>(), CUSTOMER);
	@BeforeEach
	public void setMockOutput() {
		
		
		lenient().when(appointmentDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(APPOINTMENT_ID)) {
				Appointment appointment = new Appointment();
				appointment.setId(APPOINTMENT_ID);
				appointment.setNote(APPOINTMENT_NOTE);
				appointment.setStatus(APPOINTMENT_STATUS);
				appointment.setImages(new ArrayList<Image>());
				appointment.setCustomer(CUSTOMER);
				appointment.setCar(CAR);
				appointment.setTimeSlot(TIME_SLOT);
				appointment.setMechanics(new ArrayList<Mechanic>());
				appointment.setStatus(APPOINTMENT_STATUS);
				return appointment;
			} else {
				return null;
			}
		});
		
		lenient().when(appointmentDao.findByCustomer(any(Customer.class))).thenAnswer((InvocationOnMock invocation) -> {
		
			Appointment appointment = new Appointment();
			Customer customer = invocation.getArgument(0);
			if (customer.getId() == CUSTOMER.getId()) {
				appointment.setId(APPOINTMENT_ID);
				appointment.setNote(APPOINTMENT_NOTE);
				appointment.setStatus(APPOINTMENT_STATUS);
				appointment.setImages(new ArrayList<Image>());
				appointment.setCustomer(CUSTOMER);
				appointment.setCar(CAR);
				appointment.setTimeSlot(TIME_SLOT);
				appointment.setMechanics(new ArrayList<Mechanic>());
				appointment.setStatus(APPOINTMENT_STATUS);
				return appointment;
			} else {
				return null;
			}
		});
		
		lenient().when(appointmentDao.findByTimeSlot(any(TimeSlot.class))).thenAnswer((InvocationOnMock invocation) -> {
			
			Appointment appointment = new Appointment();
			TimeSlot timeSlot = invocation.getArgument(0);
			if (timeSlot.getId() == TIME_SLOT.getId()) {
				appointment.setId(APPOINTMENT_ID);
				appointment.setNote(APPOINTMENT_NOTE);
				appointment.setStatus(APPOINTMENT_STATUS);
				appointment.setImages(new ArrayList<Image>());
				appointment.setCustomer(CUSTOMER);
				appointment.setCar(CAR);
				appointment.setTimeSlot(TIME_SLOT);
				appointment.setMechanics(new ArrayList<Mechanic>());
				appointment.setStatus(APPOINTMENT_STATUS);
				return appointment;
			} else {
				return null;
			}
		});
		
		lenient().when(appointmentDao.findByCar(any(Car.class))).thenAnswer((InvocationOnMock invocation) -> {
			
			Appointment appointment = new Appointment();
			Car car = invocation.getArgument(0);
			if (car.getId() == CAR.getId()) {
				appointment.setId(APPOINTMENT_ID);
				appointment.setNote(APPOINTMENT_NOTE);
				appointment.setStatus(APPOINTMENT_STATUS);
				appointment.setImages(new ArrayList<Image>());
				appointment.setCustomer(CUSTOMER);
				appointment.setCar(CAR);
				appointment.setTimeSlot(TIME_SLOT);
				appointment.setMechanics(new ArrayList<Mechanic>());
				appointment.setStatus(APPOINTMENT_STATUS);
				return appointment;
			} else {
				return null;
			}
		});
		
		lenient().when(appointmentDao.findByStatus(any(Appointment.AppointmentStatus.class))).thenAnswer((InvocationOnMock invocation) -> {
			
			Appointment appointment = new Appointment();
			AppointmentStatus status  = invocation.getArgument(0);
			if (invocation.getArgument(0).equals(APPOINTMENT_STATUS)) {
				appointment.setId(APPOINTMENT_ID);
				appointment.setNote(APPOINTMENT_NOTE);
				appointment.setStatus(APPOINTMENT_STATUS);
				appointment.setImages(new ArrayList<Image>());
				appointment.setCustomer(CUSTOMER);
				appointment.setCar(CAR);
				appointment.setTimeSlot(TIME_SLOT);
				appointment.setMechanics(new ArrayList<Mechanic>());
				appointment.setStatus(APPOINTMENT_STATUS);
				return appointment;
			} else {
				return null;
			}
		});
		
		
		lenient().when(appointmentDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(APPOINTMENT_ID)) {
				Appointment appointment = new Appointment();
				appointment.setId(APPOINTMENT_ID);
				appointment.setNote(APPOINTMENT_NOTE);
				appointment.setStatus(APPOINTMENT_STATUS);
				appointment.setImages(new ArrayList<Image>());
			
				return appointment;
			} else {
				return null;
			}
		});
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		
		lenient().when(appointmentDao.save(any(Appointment.class))).thenAnswer(returnParameterAsAnswer);
		
	}
	
	

	
	@Test
	/**
	 * Test the creation of the appointment object
	 */
	public void testCreateAppointment()
	{
		assertEquals(0, service.getAllAppointments().size());
		RepairSystem repairSystem = new RepairSystem();
		Customer customer  = new Customer("Marcus", 345678, "password", 6789876, "Marcus@gmail.com", new GregorianCalendar(2021,3,13), repairSystem, "123456", "678954", "123 avenue street");
		TimeSlot dummyTime = new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240, new ArrayList<Mechanic>(), new ArrayList<Appointment>() );
		Car dummyCar = new Car(451, CarType.Hatchback, true, 50000, null, customer);
		String dummyNote = "dummy Note";
		AppointmentStatus dummyStatus = AppointmentStatus.InRepair;
		int appointmentId = customer.hashCode() * dummyTime.hashCode();
		Appointment appointment = null;
		
		try {
			appointment = service.createApp(customer, dummyTime, new ArrayList<Mechanic>(), dummyCar, new ArrayList<Image>(), new ArrayList<Service>(), dummyNote, dummyStatus);
		}catch(IllegalArgumentException e)
		{
			fail();
		}
		
		assertNotNull(appointment);
		assertEquals(appointmentId, appointment.getId());
		assertEquals(dummyNote, appointment.getNote());
		assertEquals(dummyStatus, appointment.getStatus());
		assertEquals(dummyCar, appointment.getCar());
		assertEquals(dummyTime, appointment.getTimeSlot());
		
	}
	
	@Test
	/**
	 * 
	 */
	public void testCreateAppointmentNull()
	{
		
		RepairSystem repairSystem = null;
		Customer customer  = null;
		TimeSlot dummyTime = null;
		Car dummyCar = null;
		String dummyNote = null;
		AppointmentStatus dummyStatus = null;
		Appointment appointment = null;
		String error = null;
		
		try {
			appointment = service.createApp(customer, dummyTime, new ArrayList<Mechanic>(), dummyCar, new ArrayList<Image>(), new ArrayList<Service>(), dummyNote, dummyStatus);
		}catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals("Appointment cannot be empty", error);
	}

	@Test
	/**
	 * Verify if there is a status assigned to the appointment
	 */
	public void testCreateAppointionNullStatus()
	{
		String error = null;
		RepairSystem repairSystem = new RepairSystem();
		Customer customer  = new Customer("Marcus", 345678, "password", 6789876, "Marcus@gmail.com", new GregorianCalendar(2021,3,13), repairSystem, "123456", "678954", "123 avenue street");
		TimeSlot dummyTime = new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240, new ArrayList<Mechanic>(), new ArrayList<Appointment>() );
		Car dummyCar = new Car(451, CarType.Hatchback, true, 50000, null, customer);
		String dummyNote = "dummy Note";
		AppointmentStatus dummyStatus = null;
		Appointment appointment = null;
		
		try {
			appointment = service.createApp(customer, dummyTime, new ArrayList<Mechanic>(), dummyCar, new ArrayList<Image>(), new ArrayList<Service>(), dummyNote, dummyStatus);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals("Appointment status cannot be null", error);	
	}
	
	@Test
	/**
	 * Verify that the appointment object has a customer assigned to it
	 */
	public void testCreateAppointmentNullCustomer()
	{
		String error = null;
		RepairSystem repairSystem = new RepairSystem();
		Customer customer  = null;
		TimeSlot dummyTime = new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240, new ArrayList<Mechanic>(), new ArrayList<Appointment>() );
		Car dummyCar = new Car(451, CarType.Hatchback, true, 50000, null, customer);
		String dummyNote = "dummy Note";
		AppointmentStatus dummyStatus = AppointmentStatus.CarReceived;
		Appointment appointment = null;
		
		try {
			appointment = service.createApp(customer, dummyTime, new ArrayList<Mechanic>(), dummyCar, new ArrayList<Image>(), new ArrayList<Service>(), dummyNote, dummyStatus);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals("Customer cannot be null", error);	
	}
	
	@Test
	/**
	 * Verify that there is a time assigned to the appointment
	 */
	public void testCreateAppointmentNullTimeSlot()
	{
		String error = null;
		RepairSystem repairSystem = new RepairSystem();
		Customer customer  =  new Customer("Marcus", 345678, "password", 6789876, "Marcus@gmail.com", new GregorianCalendar(2021,3,13), repairSystem, "123456", "678954", "123 avenue street");
		TimeSlot dummyTime = null;
		Car dummyCar = new Car(451, CarType.Hatchback, true, 50000, null, customer);
		String dummyNote = "dummy Note";
		AppointmentStatus dummyStatus =  AppointmentStatus.CarReceived;
		Appointment appointment = null;
		
		try {
			appointment = service.createApp(customer, dummyTime, new ArrayList<Mechanic>(), dummyCar, new ArrayList<Image>(), new ArrayList<Service>(), dummyNote, dummyStatus);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals("TimeSlot cannot be null", error);	
	}
	

	@Test
	/**
	 * Verify that there is a car assigned to the appointment
	 */
	public void testCreateAppointmentNullCar()
	{
		String error = null;
		RepairSystem repairSystem = new RepairSystem();
		Customer customer  =  new Customer("Marcus", 345678, "password", 6789876, "Marcus@gmail.com", new GregorianCalendar(2021,3,13), repairSystem, "123456", "678954", "123 avenue street");
		TimeSlot dummyTime =  new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240, new ArrayList<Mechanic>(), new ArrayList<Appointment>() );
		Car dummyCar = null;
		String dummyNote = "dummy Note";
		AppointmentStatus dummyStatus =  AppointmentStatus.CarReceived;
		Appointment appointment = null;
		
		try {
			appointment = service.createApp(customer, dummyTime, new ArrayList<Mechanic>(), dummyCar, new ArrayList<Image>(), new ArrayList<Service>(), dummyNote, dummyStatus);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals("Car cannot be null", error);	
		
	}
	

	
	
	
}
