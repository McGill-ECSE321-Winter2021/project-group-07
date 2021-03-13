package ca.mcgill.ecse321.repairsystem.service;
import java.time.LocalDateTime;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Appointment.AppointmentStatus;
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.repairsystem.dao.TimeSlotRepository;
import ca.mcgill.ecse321.repairsystem.model.Appointment;
import ca.mcgill.ecse321.repairsystem.model.Mechanic;
import ca.mcgill.ecse321.repairsystem.model.TimeSlot;

public class TestTimeSlotService {
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	@Transactional 
	public TimeSlot createTimeSlot(LocalDateTime aStartTime, LocalDateTime aEndTime, List<Mechanic> mechanics, List<Appointment> appointments) {
		int id = aStartTime.hashCode() * aEndTime.hashCode();
		TimeSlot timeslot = new TimeSlot(aStartTime, aEndTime, id, mechanics, appointments);
		timeSlotRepository.save(timeslot);
		return timeslot;
	}
	
	@Transactional
	public TimeSlot getTimeSlotById(int id) { 
		return timeSlotRepository.findById(id);
	}
	
	@Transactional
	public List<TimeSlot> getTimeSlotByStartTime(LocalDateTime time) { 
		return timeSlotRepository.findByStartTime(time);
	}
	
	@Transactional
	public List<TimeSlot> getTimeSlotByEndTime(LocalDateTime time) { 
		return timeSlotRepository.findByEndTime(time);
	}
	
	/* 
	 * helper method
	 */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
