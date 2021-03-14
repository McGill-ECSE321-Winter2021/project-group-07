package ca.mcgill.ecse321.repairsystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

@ExtendWith(MockitoExtension.class)
public class TestMechanicService {
	@Mock
	private MechanicRepository mechanicDao;

	@InjectMocks
	private MechanicService mechanicService;

	private static String MECHANIC_KEY = "TestMechanic";
	private static int MECHANIC_ID = 1234;
	private static String PASSWORD = "mechanic";
	private static int PHONE = 123000000;
	private static String EMAIL = "mechanic@repairsystem.com";
	private static RepairSystem REPAIR_SYSTEM = new RepairSystem();
	private static List<Service> ALL_CAPABILITIES = new ArrayList<Service>();
	private static List<TimeSlot> TIME_SLOTS = new ArrayList<TimeSlot>();
	private static List<Appointment> APPOINTMENTS = new ArrayList<Appointment>();

	@BeforeEach
	public void setMockOutput()
	{
		lenient().when(mechanicDao.findByName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			Mechanic mechanic = new Mechanic();

			if(invocation.getArgument(0).equals(MECHANIC_KEY)) {
				mechanic.setName(MECHANIC_KEY);
				mechanic.setAppointments(APPOINTMENTS);
				mechanic.setEmail(EMAIL);
				mechanic.setId(MECHANIC_ID);
				mechanic.setPassword(PASSWORD);
				mechanic.setPhone(PHONE);
				mechanic.setServices(ALL_CAPABILITIES);
				mechanic.setTimeSlots(TIME_SLOTS);
				return mechanic;
			} else {
				return null;
			}
		});


		lenient().when(mechanicDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			Mechanic mechanic = new Mechanic();

			if (invocation.getArgument(0).equals(MECHANIC_ID)) {
				mechanic.setName(MECHANIC_KEY);
				mechanic.setAppointments(APPOINTMENTS);
				mechanic.setEmail(EMAIL);
				mechanic.setId(MECHANIC_ID);
				mechanic.setPassword(PASSWORD);
				mechanic.setPhone(PHONE);
				mechanic.setServices(ALL_CAPABILITIES);
				mechanic.setTimeSlots(TIME_SLOTS);
				return mechanic;
			} else {
				return null;
			}
		});

		lenient().when(mechanicDao.findByPhone(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			Mechanic mechanic = new Mechanic();

			if (invocation.getArgument(0).equals(PHONE)) {
				mechanic.setName(MECHANIC_KEY);
				mechanic.setAppointments(APPOINTMENTS);
				mechanic.setEmail(EMAIL);
				mechanic.setId(MECHANIC_ID);
				mechanic.setPassword(PASSWORD);
				mechanic.setPhone(PHONE);
				mechanic.setServices(ALL_CAPABILITIES);
				mechanic.setTimeSlots(TIME_SLOTS);
				return mechanic;
			} else {
				return null;
			}
		});

		lenient().when(mechanicDao.findByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			Mechanic mechanic = new Mechanic();

			if (invocation.getArgument(0).equals(EMAIL)) {
				mechanic.setName(MECHANIC_KEY);
				mechanic.setAppointments(APPOINTMENTS);
				mechanic.setEmail(EMAIL);
				mechanic.setId(MECHANIC_ID);
				mechanic.setPassword(PASSWORD);
				mechanic.setPhone(PHONE);
				mechanic.setServices(ALL_CAPABILITIES);
				mechanic.setTimeSlots(TIME_SLOTS);
				return mechanic;
			} else {
				return null;
			}
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};

		lenient().when(mechanicDao.save(any(Mechanic.class))).thenAnswer(returnParameterAsAnswer);
	}

	@Test
	public void testCreateMechanicNull() {

		Mechanic mechanic = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = new RepairSystem();
		List<Service> allCapabilities = new ArrayList<Service>();
		int mechanicId = name.hashCode() * aPassword.hashCode();

		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, system, allCapabilities);		
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(mechanic);
		assertEquals(mechanicId, mechanic.getId());
	}


	@Test
	public void testCreateNameNull() {
		Mechanic mechanic = null;
		String name = null;
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = new RepairSystem();
		List<Service> allCapabilities = new ArrayList<Service>();
		String error = null;
		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, system, allCapabilities);		
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(mechanic);
		assertEquals("Mechanic name cannot be empty!", error);
	}

	@Test
	public void testCreateNameSpace() {
		Mechanic mechanic = null;
		String name = " ";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = new RepairSystem();
		List<Service> allCapabilities = new ArrayList<Service>();
		String error = null;
		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, system, allCapabilities);		
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(mechanic);
		assertEquals("Mechanic name cannot be empty!", error);
	}

	@Test
	public void testCreatePasswordNull() {
		Mechanic mechanic = null;
		String name = "Oscar";
		String aPassword = null;
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = new RepairSystem();
		List<Service> allCapabilities = new ArrayList<Service>();
		String error = null;
		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, system, allCapabilities);		
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(mechanic);
		assertEquals("Mechanic passward cannot be empty!", error);
	}
	
	@Test
	public void testCreatePasswordEmpty() {
		Mechanic mechanic = null;
		String name = "Oscar";
		String aPassword = "";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = new RepairSystem();
		List<Service> allCapabilities = new ArrayList<Service>();
		String error = null;
		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, system, allCapabilities);		
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(mechanic);
		assertEquals("Mechanic passward cannot be empty!", error);
	}
	
	@Test
	public void testCreateEmailNull() {
		Mechanic mechanic = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = null;
		RepairSystem system = new RepairSystem();
		List<Service> allCapabilities = new ArrayList<Service>();
		String error = null;
		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, system, allCapabilities);		
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(mechanic);
		assertEquals("Mechanic email cannot be empty!", error);
	}
	
	@Test
	public void testCreateEmailEmpty() {
		Mechanic mechanic = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "";
		RepairSystem system = new RepairSystem();
		List<Service> allCapabilities = new ArrayList<Service>();
		String error = null;
		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, system, allCapabilities);		
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(mechanic);
		assertEquals("Mechanic email cannot be empty!", error);
	}
	
	@Test
	public void testCreateSystemNull() {
		Mechanic mechanic = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = null;
		List<Service> allCapabilities = new ArrayList<Service>();
		String error = null;
		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, system, allCapabilities);		
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(mechanic);
		assertEquals("Mechanic repair system cannot be empty!", error);
	}
	
	@Test
	public void testCreateServiceNull() {
		Mechanic mechanic = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = new RepairSystem();
		List<Service> allCapabilities = null;
		String error = null;
		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, system, allCapabilities);		
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(mechanic);
		assertEquals("Mechanic service cannot be empty!", error);
	}

}
