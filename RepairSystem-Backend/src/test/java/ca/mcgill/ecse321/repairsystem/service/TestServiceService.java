package ca.mcgill.ecse321.repairsystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
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
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;

@ExtendWith(MockitoExtension.class)
public class TestServiceService {
	@Mock
	private ServiceRepository serviceDao;

	@InjectMocks
	private ServiceService serviceService;
	
	private static ServiceType SERVICE_TYPE= ServiceType.CarRepair;
	private static int PRICE = 340;
	private static List<Mechanic> MECHANICS = new ArrayList<Mechanic>();
	private static List<Appointment> APPOINTMENTS = new ArrayList<Appointment>();
	
	@BeforeEach
	public void setMockOutput() {
		
		lenient().when(serviceDao.findByServiceType(any(Service.ServiceType.class))).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(SERVICE_TYPE)) {
				Service service = new Service();
				service.setServiceType(SERVICE_TYPE);
				service.setPrice(PRICE);
				service.setAppointments(APPOINTMENTS);
				service.setMechanics(MECHANICS);
				return service;
			} else {
				return null;
			}
		});
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		
		lenient().when(serviceDao.save(any(Service.class))).thenAnswer(returnParameterAsAnswer);	
	}
	
	@Test
	/**
	 * Verifies the creation of a service object
	 */
	public void testCreateService()
	{
		
		ServiceType type = ServiceType.RegularCheckup;
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		int price = 142;
		Service service = null;
		try {
			service = serviceService.createService(type, price, mechanics, appointments);
		}catch(IllegalArgumentException e)
		{
			fail();
		}
		
		assertNotNull(service);
		assertEquals(type, service.getServiceType());
		assertEquals(mechanics, service.getMechanics());
		assertEquals(appointments, service.getAppointments());
	}

	@Test
	/**
	 * Verifies that the service type of a service object is not null
	 */
	public void testCreateServiceTypeNull()
	{
		
		ServiceType type = null;
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		int price = 142;
		Service service = null;
		String error = null;
		
		try {
			service = serviceService.createService(type, price, mechanics, appointments);
		}catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertNull(service);
		assertEquals("Service type cannot be null", error);
	}
	
	@Test
	/**
	 * check if the list of mechanics is null
	 */
	public void testCreateMechanicsNull()
	{
		
		ServiceType type = ServiceType.RoadsideAssistance;
		List<Mechanic> mechanics = null;
		List<Appointment> appointments = new ArrayList<Appointment>();
		int price = 142;
		Service service = null;
		String error = null;
		
		try {
			service = serviceService.createService(type, price, mechanics, appointments);
		}catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertNull(service);
		assertEquals("List of mechanics cannnot be null", error);
	}
	
	@Test
	/**
	 * Verifies that the list of appointments associated to a particular service is not null
	 */
	public void testCreateAppointmentsNull()
	{
		
		ServiceType type = ServiceType.RoadsideAssistance;
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = null;
		int price = 142;
		Service service = null;
		String error = null;
		
		try {
			service = serviceService.createService(type, price, mechanics, appointments);
		}catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertNull(service);
		assertEquals(0, mechanics.size());
		assertEquals("List of appointments cannot be null", error);
	}
}
