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
public class TestAdministrativeAssistantService {
	@Mock
	private AdministrativeAssistantRepository administrativeAssistantDao;

	@InjectMocks
	private AdministrativeAssistantService administrativeAssistantService;

	private static String ADMINISTRATIVE_ASSISTANT_KEY = "TestAdministrativeAssistant";
	private static int ADMINISTRATIVE_ASSISTANT_ID = 1234;
	private static String PASSWORD = "administrativeAssistant";
	private static int PHONE = 123000000;
	private static String EMAIL = "administrativeAssistant@repairsystem.com";
	private static RepairSystem REPAIR_SYSTEM = new RepairSystem();
	
	
	@BeforeEach
	public void setMockOutput()
	{
		lenient().when(administrativeAssistantDao.findByName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			AdministrativeAssistant administrativeAssistant = new AdministrativeAssistant();
			if(invocation.getArgument(0).equals(ADMINISTRATIVE_ASSISTANT_KEY)) {
				administrativeAssistant.setName(ADMINISTRATIVE_ASSISTANT_KEY);
				administrativeAssistant.setEmail(EMAIL);
				administrativeAssistant.setId(ADMINISTRATIVE_ASSISTANT_ID);
				administrativeAssistant.setPassword(PASSWORD);
				administrativeAssistant.setPhone(PHONE);
				return administrativeAssistant;
			} else {
				return null;
			}
		});


		lenient().when(administrativeAssistantDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			AdministrativeAssistant administrativeAssistant = new AdministrativeAssistant();
			if (invocation.getArgument(0).equals(ADMINISTRATIVE_ASSISTANT_ID)) {
				administrativeAssistant.setName(ADMINISTRATIVE_ASSISTANT_KEY);
				administrativeAssistant.setEmail(EMAIL);
				administrativeAssistant.setId(ADMINISTRATIVE_ASSISTANT_ID);
				administrativeAssistant.setPassword(PASSWORD);
				administrativeAssistant.setPhone(PHONE);
				return administrativeAssistant;
			} else {
				return null;
			}
		});

		lenient().when(administrativeAssistantDao.findByPhone(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			AdministrativeAssistant administrativeAssistant = new AdministrativeAssistant();
			if (invocation.getArgument(0).equals(PHONE)) {
				administrativeAssistant.setName(ADMINISTRATIVE_ASSISTANT_KEY);
				administrativeAssistant.setEmail(EMAIL);
				administrativeAssistant.setId(ADMINISTRATIVE_ASSISTANT_ID);
				administrativeAssistant.setPassword(PASSWORD);
				administrativeAssistant.setPhone(PHONE);
				return administrativeAssistant;
			} else {
				return null;
			}
		});

		lenient().when(administrativeAssistantDao.findByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			AdministrativeAssistant administrativeAssistant = new AdministrativeAssistant();
			if (invocation.getArgument(0).equals(EMAIL)) {
				administrativeAssistant.setName(ADMINISTRATIVE_ASSISTANT_KEY);
				administrativeAssistant.setEmail(EMAIL);
				administrativeAssistant.setId(ADMINISTRATIVE_ASSISTANT_ID);
				administrativeAssistant.setPassword(PASSWORD);
				administrativeAssistant.setPhone(PHONE);
				return administrativeAssistant;
			} else {
				return null;
			}
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};

		lenient().when(administrativeAssistantDao.save(any(AdministrativeAssistant.class))).thenAnswer(returnParameterAsAnswer);
	}

	@Test
	public void testCreateAdministrativeAssistantNull() {

		AdministrativeAssistant administrativeAssistant = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = new RepairSystem();
		int administrativeAssistantId = name.hashCode() * aPassword.hashCode();

		try {
			administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(administrativeAssistant);
		assertEquals(administrativeAssistantId, administrativeAssistant.getId());
	}


	@Test
	public void testCreateNameNull() {
		AdministrativeAssistant administrativeAssistant = null;
		String name = null;
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String error = null;
		try {
			administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(administrativeAssistant);
		assertEquals("Administrative assistant name cannot be empty!", error);
	}

	@Test
	public void testCreateNameSpace() {
		AdministrativeAssistant administrativeAssistant = null;
		String name = " ";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String error = null;
		try {
			administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(administrativeAssistant);
		assertEquals("Administrative assistant name cannot be empty!", error);
	}

	@Test
	public void testCreatePasswordNull() {
		AdministrativeAssistant administrativeAssistant = null;
		String name = "Oscar";
		String aPassword = null;
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String error = null;
		try {
			administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(administrativeAssistant);
		assertEquals("Administrative assistant passward cannot be empty!", error);
	}
	
	@Test
	public void testCreatePasswordEmpty() {
		AdministrativeAssistant administrativeAssistant = null;
		String name = "Oscar";
		String aPassword = "";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String error = null;
		try {
			administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(administrativeAssistant);
		assertEquals("Administrative assistant passward cannot be empty!", error);
	}
	
	@Test
	public void testCreateEmailNull() {
		AdministrativeAssistant administrativeAssistant = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = null;
		String error = null;
		try {
			administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(administrativeAssistant);
		assertEquals("Administrative assistant email cannot be empty!", error);
	}
	
	@Test
	public void testCreateEmailEmpty() {
		AdministrativeAssistant administrativeAssistant = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "";
		String error = null;
		try {
			administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(administrativeAssistant);
		assertEquals("Administrative assistant email cannot be empty!", error);
	}
	
	

}
