package ca.mcgill.ecse321.repairsystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

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
	private static Calendar LAST_DATE = Calendar.getInstance();
	private static String DEBIT = "4321";
	private static String CREDIT = "1234";
	private static String ADD = "456 Swan Lake";
	private static List<Car> CARS = new ArrayList<Car>();
	private static List<Appointment> APPOINTMENTS = new ArrayList<Appointment>();	
	
	private static String CUSTOMER2_NAME = "customer2";
	private static String PASSWORD2="customer2";
	private static int PHONE2 = 967854321;
	private static String EMAIL2 = "customer2@gmail.com";
	private static Calendar LAST_DATE2=Calendar.getInstance();
	private static String DEBIT2="09985641";
	private static String CREDIT2="45632145";
	

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
	public void testCreateCustomer() {

		Customer customer = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		int customerId = aEmail.hashCode();

		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail,  credit, debit, address);	
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
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String error = null;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
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
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		String error = null;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
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
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		String error = null;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer password cannot be empty!", error);
	}
	@Test
	public void testCreatePasswardEmpty() {

		Customer customer = null;
		String name = "Oscar";
		String aPassword = "";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		String error = null;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer password cannot be empty!", error);
	}
	@Test
	public void testCreateEmailNull() {

		Customer customer = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = null;
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		String error = null;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
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
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		String error = null;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer email cannot be empty!", error);
	}
	
	@Test 
	public void testResetPassword()
	{
		
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "ocascar@gmail.com";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		String newPassword = "newPassword";
		String error = null;
		Customer customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
		try {
			
			customerService.resetPassword(customer, newPassword);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(newPassword, customer.getPassword());
	}

	
	@Test
	public void testUpdateEmail()
	{
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "ocascar@gmail.com";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		String newEmail = "marie@gmail.com";
		String error = null;
		Customer customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
		try {
			
			customerService.updateCustomerEmail(customer, newEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(newEmail, customer.getEmail());
	}
}
