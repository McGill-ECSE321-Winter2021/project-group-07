package ca.mcgill.ecse321.repairsystem.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

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
public class TestRepairSystemService {
	
	@Mock
	private CustomerRepository customerDao;
	
	@InjectMocks

	private CustomerService customerService;
	
	private static String CUSTOMER_KEY = "TestCustomer";
	
	@BeforeEach
	public void setMockOutput() {
	    lenient().when(customerDao.findByName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(CUSTOMER_KEY)) {
	            Customer customer = new Customer();
	            customer.setName(CUSTOMER_KEY);
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
	
	//add tests for business methods
	//when a class depend on another class, add lenient().when(xDao.existsById(anyString())).thenReturn(true); in the test
	//error handling for service classes

	@Test
	public void testCreateCustomer() {
		assertEquals(0, customerService.getAllCustomers().size());

		String name = "Oscar";
		RepairSystem system = new RepairSystem();
		Calendar calender = null;
		Customer customer = null;
		try {
			customer = customerService.createCustomer(name, name, 0, name, calender, system, name, name, name);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(customer);
		assertEquals(name, customer.getName());
	}
}
