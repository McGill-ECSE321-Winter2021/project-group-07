package ca.mcgill.ecse321.repairsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class TestAdministrativeAssistantService {
	@Mock
	private AdministrativeAssistantRepository administrativeAssistantDao;

	@InjectMocks
	private AdministrativeAssistantService administrativeAssistantService;
	
	private static String ADMINISTRATIVEASSISTANT_KEY = "TestAdministrativeAssistant";
	
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(administrativeAssistantDao.findByName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(ADMINISTRATIVEASSISTANT_KEY)) {
				AdministrativeAssistant administrativeAssistant = new AdministrativeAssistant();
				administrativeAssistant.setName(ADMINISTRATIVEASSISTANT_KEY);
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

}
