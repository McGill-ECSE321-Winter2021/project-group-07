package ca.mcgill.ecse321.repairsystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyBoolean;

import static org.mockito.Mockito.lenient;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import ca.mcgill.ecse321.repairsystem.model.Car.CarType;

@ExtendWith(MockitoExtension.class)
public class TestCarService {
	@Mock
	private CarRepository carDao;

	@InjectMocks
	private CarService carService;
	private static int CAR_ID= 231424;
	private static CarType CAR_TYPE= CarType.Sports;
	private static int NUMBER_KILOMETERS= 143290;
	private static boolean WINTER_TIRES=false;
	private static List<Appointment> APPOINTMENTS = new ArrayList<Appointment>();
	private static Customer CUSTOMER = new Customer("TestPerson", 2001, "123abc", 76523455,"TestPerson@gmail.com", "123456789","987654321", "123 Street Avenue");

	@BeforeEach
	public void setMockOutput()
	{
		lenient().when(carDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(CAR_ID)) {
				Car car = new Car();
				car.setId(CAR_ID);
				car.setCarType(CAR_TYPE);
				car.setNumOfKilometers(NUMBER_KILOMETERS);
				car.setWinterTires(WINTER_TIRES);
				car.setAppointments(APPOINTMENTS);
				car.setCustomer(CUSTOMER);
				return car;
			} else {
				return null;
			}
		});
		
		lenient().when(carDao.findByCustomer(any(Customer.class))).thenAnswer((InvocationOnMock invocation) -> {
			Car car = new Car();
			Customer customer = invocation.getArgument(0);
			if (customer.getId() == CUSTOMER.getId()) {
				car.setId(CAR_ID);
				car.setCarType(CAR_TYPE);
				car.setNumOfKilometers(NUMBER_KILOMETERS);
				car.setWinterTires(WINTER_TIRES);
				car.setAppointments(APPOINTMENTS);
				car.setCustomer(CUSTOMER);
				return car;
			} else {
				return null;
			}
		});
		
		lenient().when(carDao.findByCarType(any(Car.CarType.class))).thenAnswer((InvocationOnMock invocation) -> {
			
			if (invocation.getArgument(0).equals(CAR_TYPE)) {
				Car car = new Car();
				car.setId(CAR_ID);
				car.setCarType(CAR_TYPE);
				car.setNumOfKilometers(NUMBER_KILOMETERS);
				car.setWinterTires(WINTER_TIRES);
				car.setAppointments(APPOINTMENTS);
				car.setCustomer(CUSTOMER);
				return car;
			} else {
				return null;
			}
		});
		
	lenient().when(carDao.findByWinterTires(anyBoolean())).thenAnswer((InvocationOnMock invocation) -> {
			
			if (invocation.getArgument(0).equals(WINTER_TIRES)) {
				Car car = new Car();
				car.setId(CAR_ID);
				car.setCarType(CAR_TYPE);
				car.setNumOfKilometers(NUMBER_KILOMETERS);
				car.setWinterTires(WINTER_TIRES);
				car.setAppointments(APPOINTMENTS);
				car.setCustomer(CUSTOMER);
				return car;
			} else {
				return null;
			}
		});
		
	
	Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
		return invocation.getArgument(0);
	};
	
	lenient().when(carDao.save(any(Car.class))).thenAnswer(returnParameterAsAnswer);
	
		
	}
	
	
	@Test
	/**
	 * Test the creation of a car object 
	 */
	public void testCreateCar()
	{
		assertEquals(0, carService.getAllCars().size());
		CarType type = CarType.Convertible;
		boolean winterTires = true;
		int numOfKm = 53467;
		List<Appointment> appointment = new ArrayList<Appointment>();
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com", "123456", "678954", "123 avenue street");
		Car car = null;
		try
		{
			car = carService.createCar(type, winterTires, numOfKm, appointment, customer);
		}catch(IllegalArgumentException e)
		{
			fail();
		}
		assertNotNull(car);
		assertEquals(type, car.getCarType());
		assertEquals(winterTires, car.getWinterTires());
		assertEquals(numOfKm,car.getNumOfKilometers());
		assertEquals(appointment, car.getAppointments());
		assertEquals(customer, car.getCustomer());
	}

	@Test
	/**
	 * Verifies that the car type is not null
	 */
	public void testCreateCarTypeNull()
	{
		String error = null;
		CarType type = null;
		boolean winterTires = false;
		int numOfKm = 53467;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		Car car = null;
		
		try {
			car = carService.createCar(type, winterTires, numOfKm, APPOINTMENTS, customer);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNull(car);
		assertEquals("Car Type cannot be null", error);
	}
	
	@Test
	/**
	 * Verifies that there is a list of appointments associated to a car object
	 */
	public void testCreateAppointmentsNull()
	{
		String error = null;
		CarType type = CarType.Truck;
		boolean winterTires = false;
		int numOfKm = 455679;
		APPOINTMENTS = null;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com", "123456", "678954", "123 avenue street");
		Car car = null;
		
		try {
			car = carService.createCar(type, winterTires, numOfKm, APPOINTMENTS, customer);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNull(car);
		assertEquals("List of Appointments cannot be null", error);
	}
	
	@Test
	/**
	 * Verifies that a customer object is associated to a car
	 */
	public void testCreateCustomerNull()
	{
		String error = null;
		CarType type = CarType.Truck;
		boolean winterTires = false;
		int numOfKm = 455679;
		Customer customer  = null;
		List<Appointment> appointments = new ArrayList<Appointment>();
		Car car = null;
		
		try {
			car = carService.createCar(type, winterTires, numOfKm, appointments, customer);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNull(car);
		assertEquals("Customer cannot be null", error);
	}
	
}
