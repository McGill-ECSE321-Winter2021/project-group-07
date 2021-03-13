package ca.mcgill.ecse321.repairsystem.service;

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

public class TestServiceService {
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Transactional
	public ca.mcgill.ecse321.repairsystem.model.Service createService(ServiceType aType, int price, List<Mechanic> mechanics, List<Appointment> appointment) {
		ca.mcgill.ecse321.repairsystem.model.Service service = new ca.mcgill.ecse321.repairsystem.model.Service(aType, price, mechanics, appointment);
		serviceRepository.save(service);
		return service;
	}
	
	@Transactional
	public ca.mcgill.ecse321.repairsystem.model.Service getServiceByServiceType(ServiceType type) {
		return serviceRepository.findByServiceType(type);
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
