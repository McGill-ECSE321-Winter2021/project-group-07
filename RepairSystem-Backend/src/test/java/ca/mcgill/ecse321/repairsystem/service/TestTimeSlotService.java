package ca.mcgill.ecse321.repairsystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

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
public class TestTimeSlotService {
	@Mock
	private TimeSlotRepository timeslotDao;

	@InjectMocks
	private TimeSlotService timeSlotService;
	
	private static int TIME_SLOT_ID = 43123;
	private static LocalDateTime  START_TIME = LocalDateTime.of(2021, 3, 13, 10, 00);
	private static LocalDateTime END_TIME = LocalDateTime.of(2021, 3,13,12,00);
	private static List<Mechanic> MECHANICS = new ArrayList<Mechanic>();
	private static List<Appointment> APPOINTMENTS = new ArrayList<Appointment>();
	
	@BeforeEach
	public void setMockOutput()
	{
		lenient().when(timeslotDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(TIME_SLOT_ID)) {
				TimeSlot time = new TimeSlot();
				time.setId(TIME_SLOT_ID);
				time.setStartTime(START_TIME);
				time.setEndTime(END_TIME);
				time.setAppointments(APPOINTMENTS);
				time.setMechanics(MECHANICS);
				return time;
			} else {
				return null;
			}
		});
		
		lenient().when(timeslotDao.findByStartTime(any(LocalDateTime.class))).thenAnswer((InvocationOnMock invocation) -> {
			TimeSlot time = new TimeSlot();
			LocalDateTime startTime = invocation.getArgument(0);
			if (startTime == START_TIME) {
				time.setId(TIME_SLOT_ID);
				time.setStartTime(START_TIME);
				time.setEndTime(END_TIME);
				time.setAppointments(APPOINTMENTS);
				time.setMechanics(MECHANICS);
				return time;
			} else {
				return null;
			}
		});
		
		lenient().when(timeslotDao.findByEndTime(any(LocalDateTime.class))).thenAnswer((InvocationOnMock invocation) -> {
			TimeSlot time = new TimeSlot();
			LocalDateTime endTime = invocation.getArgument(0);
			if (endTime == END_TIME) {
				time.setId(TIME_SLOT_ID);
				time.setStartTime(START_TIME);
				time.setEndTime(END_TIME);
				time.setAppointments(APPOINTMENTS);
				time.setMechanics(MECHANICS);
				return time;
			} else {
				return null;
			}
		});
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		
		lenient().when(timeslotDao.save(any(TimeSlot.class))).thenAnswer(returnParameterAsAnswer);
			
	}
	
	
	@Test
	/**
	 * Verifies the creation of a time slot object
	 */
	public void testCreateTimeSlot()
	{
		
		LocalDateTime startTime = LocalDateTime.now();
		LocalDateTime endTime = LocalDateTime.of(2021, 3,14,12,00);
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		int timeId =startTime.hashCode() * endTime.hashCode();
		TimeSlot time = null;
		try {
			time = timeSlotService.createTimeSlot(startTime, endTime);
		}catch(IllegalArgumentException e)
		{
			fail();
		}
		
		assertNotNull(time);
		assertEquals(timeId, time.getId());
	}

	/**
	 * Verifies that the start time of a service object is not null
	 */
	@Test
	public void testCreateTimeSlotStartTimeNull()
	{
	
		LocalDateTime startTime = null;
		LocalDateTime endTime = LocalDateTime.of(2021, 3,14,12,00);
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		TimeSlot time = null;
		String error = null;
		
		try {
			time = timeSlotService.createTimeSlot(startTime, endTime);
		}catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertNull(time);
		assertEquals("Start time cannot be null", error);
	}
	

	/**
	 * Verifies that the end time of a service object is not null
	 */
	@Test
	public void testCreateTimeSlotEndTimeNull()
	{
	
		LocalDateTime startTime = LocalDateTime.of(2021, 3, 13, 10, 00);
		LocalDateTime endTime = null;
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		TimeSlot time = null;
		String error = null;
		
		try {
			time = timeSlotService.createTimeSlot(startTime, endTime);
		}catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertNull(time);
		assertEquals("End time cannot be null", error);
	}
	

	/**
	 * Verifies that the list of mechanics of a service object is not null
	 */
	@Test
	public void testCreateMechanicsNull()
	{
		
		LocalDateTime startTime = LocalDateTime.of(2021, 3, 13, 10, 00);
		LocalDateTime endTime =  LocalDateTime.of(2021, 3, 13, 14, 00);
		List<Mechanic> mechanics = null;
		List<Appointment> appointments = new ArrayList<Appointment>();
		TimeSlot time = null;
		String error = null;
		
		try {
			time = timeSlotService.createTimeSlot(startTime, endTime);
		}catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertNull(time);
		assertEquals("List of mechanics cannot be null", error);
	}
	
	/**
	 * Verifies that the list of appointments associated to a particular service is not null
	 */
	@Test
	public void testCreateAppointmentsNull()
	{
		
		LocalDateTime startTime = LocalDateTime.of(2021, 3, 13, 10, 00);
		LocalDateTime endTime =  LocalDateTime.of(2021, 3, 13, 14, 00);
		TimeSlot time = null;
		String error = null;
		
		try {
			time = timeSlotService.createTimeSlot(startTime, endTime);
		}catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertNull(time);
		assertEquals("List of appointments cannot be null", error);
	}
	
	

}
