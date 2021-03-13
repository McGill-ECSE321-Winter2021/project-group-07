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

import ca.mcgill.ecse321.repairsystem.dao.CarRepository;
import ca.mcgill.ecse321.repairsystem.dao.CustomerRepository;
import ca.mcgill.ecse321.repairsystem.dao.ImageRepository;
import ca.mcgill.ecse321.repairsystem.dao.MechanicRepository;
import ca.mcgill.ecse321.repairsystem.dao.ServiceRepository;
import ca.mcgill.ecse321.repairsystem.dao.TimeSlotRepository;

public class TestMachanicService {

	@Autowired
	private MechanicRepository mechanicRepository;
	////////////////////SERVICE MECHANIC METHODS //////////////////// 
	
	@Transactional
	public Mechanic createMechanic(String aName, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem, List<ca.mcgill.ecse321.repairsystem.model.Service> allCapabilities) {
		int id = aName.hashCode() * aPassword.hashCode();
		Mechanic mechanic = new Mechanic(aName, id, aPassword, aPhone, aEmail, aRepairSystem, allCapabilities);
		mechanicRepository.save(mechanic);
		return mechanic;
	}
	
	@Transactional 
	public Mechanic getMechanicById(int id) {
		Mechanic mechanic = mechanicRepository.findById(id);
		return mechanic;
	}
	
	@Transactional 
	public List<Mechanic> getMechanicsByName(String name) {
		List<Mechanic> mechanics = toList(mechanicRepository.findByName(name));
		return mechanics;
	}
	
	@Transactional 
	public Mechanic getMechanicByNumber(int aPhone) {
		Mechanic mechanic = mechanicRepository.findByPhone(aPhone);
		return mechanic;
	}
	
	
	@Transactional 
	public Mechanic getMechanicByEmail(String email) {
		Mechanic mechanic = mechanicRepository.findByEmail(email);
		return mechanic;
	}
	
	@Transactional
	public List<Mechanic> getAllMechanics() {
		return toList(mechanicRepository.findAll());
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
