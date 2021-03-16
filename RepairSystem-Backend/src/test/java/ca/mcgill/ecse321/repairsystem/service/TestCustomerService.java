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
public class TestCustomerService {
	@Mock
	private CustomerRepository customerDao;

	@InjectMocks
	private CustomerService customerService;

	private static String CUSTOMER_KEY = "TestCustomer";
	private static int CUSTOMER_ID = 1234;
	private static String PASSWORD = "customer";
	private static int PHONE = 123000000;
	private static String EMAIL = "customer@repairsystem.com";
	private static RepairSystem REPAIR_SYSTEM = new RepairSystem();
	private static Calendar LAST_DATE = Calendar.getInstance();
	private static String DEBIT = "4321";
	private static String CREDIT = "1234";
	private static String ADD = "add";
	private static List<Car> CARS = new ArrayList<Car>();
	private static List<Appointment> APPOINTMENTS = new ArrayList<Appointment>();	

	@BeforeEach
	public void setMockOutput()
	{
		lenient().when(customerDao.findByName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			Customer customer = new Customer();

			if(invocation.getArgument(0).equals(CUSTOMER_KEY)) {
				customer.setName(CUSTOMER_KEY);
				customer.setEmail(EMAIL);
				customer.setId(CUSTOMER_ID);
				customer.setPassword(PASSWORD);
				customer.setPhone(PHONE);
				customer.setAddress(ADD);
				customer.setAppointments(APPOINTMENTS);
				customer.setCars(CARS);
				customer.setCreditHash(CREDIT);
				customer.setDebitHash(DEBIT);
				customer.setLastActive(LAST_DATE);
				return customer;
			} else {
				return null;
			}
		});


		lenient().when(customerDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			Customer customer = new Customer();

			if (invocation.getArgument(0).equals(CUSTOMER_ID)) {
				customer.setName(CUSTOMER_KEY);
				customer.setEmail(EMAIL);
				customer.setId(CUSTOMER_ID);
				customer.setPassword(PASSWORD);
				customer.setPhone(PHONE);
				customer.setAddress(ADD);
				customer.setAppointments(APPOINTMENTS);
				customer.setCars(CARS);
				customer.setCreditHash(CREDIT);
				customer.setDebitHash(DEBIT);
				customer.setLastActive(LAST_DATE);
				return customer;
			} else {
				return null;
			}
		});

		lenient().when(customerDao.findByPhone(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			Customer customer = new Customer();

			if (invocation.getArgument(0).equals(PHONE)) {
				customer.setName(CUSTOMER_KEY);
				customer.setEmail(EMAIL);
				customer.setId(CUSTOMER_ID);
				customer.setPassword(PASSWORD);
				customer.setPhone(PHONE);
				customer.setAddress(ADD);
				customer.setAppointments(APPOINTMENTS);
				customer.setCars(CARS);
				customer.setCreditHash(CREDIT);
				customer.setDebitHash(DEBIT);
				customer.setLastActive(LAST_DATE);
				return customer;
			} else {
				return null;
			}
		});

		lenient().when(customerDao.findByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			Customer customer = new Customer();

			if (invocation.getArgument(0).equals(EMAIL)) {
				customer.setName(CUSTOMER_KEY);
				customer.setEmail(EMAIL);
				customer.setId(CUSTOMER_ID);
				customer.setPassword(PASSWORD);
				customer.setPhone(PHONE);
				customer.setAddress(ADD);
				customer.setAppointments(APPOINTMENTS);
				customer.setCars(CARS);
				customer.setCreditHash(CREDIT);
				customer.setDebitHash(DEBIT);
				customer.setLastActive(LAST_DATE);
				return customer;
			} else {
				return null;
			}
		});
		
		lenient().when(customerDao.findByAddress(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			Customer customer = new Customer();

			if (invocation.getArgument(0).equals(EMAIL)) {
				customer.setName(CUSTOMER_KEY);
				customer.setEmail(EMAIL);
				customer.setId(CUSTOMER_ID);
				customer.setPassword(PASSWORD);
				customer.setPhone(PHONE);
				customer.setAddress(ADD);
				customer.setAppointments(APPOINTMENTS);
				customer.setCars(CARS);
				customer.setCreditHash(CREDIT);
				customer.setDebitHash(DEBIT);
				customer.setLastActive(LAST_DATE);
				return customer;
			} else {
				return null;
			}
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};

		lenient().when(customerDao.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
	}

	@Test
	public void testCreateCustomerNull() {

		Customer customer = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		Calendar lastDate = Calendar.getInstance();
		int customerId = name.hashCode() * aPassword.hashCode();

		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, lastDate, name, aPassword, aEmail);	
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(customer);
		assertEquals(customerId, customer.getId());
	}
	@Test
	public void testCreateNameNull() {

		Customer customer = null;
		String name = null;
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = new RepairSystem();
		Calendar lastDate = Calendar.getInstance();
		String error = null;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, lastDate, name, aPassword, aEmail);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer name cannot be empty!", error);
	}
	@Test
	public void testCreateNameEmpty() {

		Customer customer = null;
		String name = "";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = new RepairSystem();
		Calendar lastDate = Calendar.getInstance();
		String error = null;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, lastDate, name, aPassword, aEmail);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer name cannot be empty!", error);
	}
	@Test
	public void testCreatePasswardNull() {

		Customer customer = null;
		String name = "Oscar";
		String aPassword = null;
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = new RepairSystem();
		Calendar lastDate = Calendar.getInstance();
		String error = null;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, lastDate, name, aPassword, aEmail);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer passward cannot be empty!", error);
	}
	@Test
	public void testCreatePasswardEmpty() {

		Customer customer = null;
		String name = "Oscar";
		String aPassword = "";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = new RepairSystem();
		Calendar lastDate = Calendar.getInstance();
		String error = null;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, lastDate, name, aPassword, aEmail);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer passward cannot be empty!", error);
	}
	@Test
	public void testCreateEmailNull() {

		Customer customer = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = null;
		RepairSystem system = new RepairSystem();
		Calendar lastDate = Calendar.getInstance();
		String error = null;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, lastDate, name, aPassword, aEmail);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer email cannot be empty!", error);
	}
	@Test
	public void testCreateEmailEmpty() {

		Customer customer = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "";
		RepairSystem system = new RepairSystem();
		Calendar lastDate = Calendar.getInstance();
		String error = null;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, lastDate, name, aPassword, aEmail);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer email cannot be empty!", error);
	}
	@Test
	public void testCreateSystemNull() {

		Customer customer = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = null;
		Calendar lastDate = Calendar.getInstance();
		String error = null;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, lastDate, name, aPassword, aEmail);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer repair system cannot be empty!", error);
	}
	@Test
	public void testCreateLastDateNull() {

		Customer customer = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = new RepairSystem();
		Calendar lastDate = null;
		String error = null;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, lastDate, name, aPassword, aEmail);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer last date cannot be empty!", error);
	}
}
