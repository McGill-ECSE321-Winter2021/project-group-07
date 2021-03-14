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
public class TestImageService {
	@Mock
	private ImageRepository imageDao;

	@InjectMocks
	private ImageService imageService;
	
	private static String IMAGE_URL = "TestImage";
	private static int IMAGE_ID = 123;
	private static Appointment APPOINTMENT = new Appointment();

	@BeforeEach
	public void setMockOutput()
	{
		lenient().when(imageDao.findByAppointment(any(Appointment.class))).thenAnswer( (InvocationOnMock invocation) -> {
			Image image = new Image();

			if(invocation.getArgument(0).equals(APPOINTMENT)) {
				image.setAppointment(APPOINTMENT);
				image.setId(IMAGE_ID);
				image.setUrl(IMAGE_URL);
				return image;
			} else {
				return null;
			}
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};

		lenient().when(imageDao.save(any(Image.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateImageNull() {
		Image image = null;
		String url = "url";
		Appointment appointment = new Appointment();
		int imageId = url.hashCode();
		try {
			image = imageService.createImage(url, appointment);	
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(image);
		assertEquals(imageId, image.getId());
	}
	
	@Test
	public void testCreateUrlNull() {
		Image image = null;
		String url = null;
		Appointment appointment = new Appointment();
		String error = null;
		try {
			image = imageService.createImage(url, appointment);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(image);
		assertEquals("Image url cannot be empty!", error);
	}
	
	@Test
	public void testCreateUrlEmpty() {
		Image image = null;
		String url = "";
		Appointment appointment = new Appointment();
		String error = null;
		try {
			image = imageService.createImage(url, appointment);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(image);
		assertEquals("Image url cannot be empty!", error);
	}

	@Test
	public void testCreateAppointmentNull() {
		Image image = null;
		String url = "url";
		Appointment appointment = null;
		String error = null;
		try {
			image = imageService.createImage(url, appointment);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(image);
		assertEquals("Image appointment cannot be empty!", error);
	}
}
