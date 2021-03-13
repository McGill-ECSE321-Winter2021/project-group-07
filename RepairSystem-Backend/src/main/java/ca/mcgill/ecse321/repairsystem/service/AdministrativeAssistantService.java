package ca.mcgill.ecse321.repairsystem.service;

import java.util.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;

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


public class AdministrativeAssistantService {
	@Autowired
	private AdministrativeAssistantRepository administrativeAssistantRepository;

	@Transactional
	public AdministrativeAssistant createAdmin(String aName, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem) {
		int id = aName.hashCode() * aPassword.hashCode();
		AdministrativeAssistant admin = new AdministrativeAssistant(aName, id, aPassword, aPhone, aEmail, aRepairSystem);
		administrativeAssistantRepository.save(admin);
		return admin;
	}

	@Transactional 
	public AdministrativeAssistant getAdminById(int id) {
		AdministrativeAssistant admin = administrativeAssistantRepository.findById(id);
		return admin;
	}

	@Transactional 
	public List<AdministrativeAssistant> getAdminsByName(String name) {
		List<AdministrativeAssistant> admins = toList(administrativeAssistantRepository.findByName(name));
		return admins;
	}

	@Transactional 
	public AdministrativeAssistant getAdminByNumber(int number) {
		AdministrativeAssistant admin = administrativeAssistantRepository.findByPhone(number);
		return admin;
	}

	@Transactional 
	public AdministrativeAssistant getAdminByEmail(String email) {
		AdministrativeAssistant admin = administrativeAssistantRepository.findByEmail(email);
		return admin;
	}

	@Transactional
	public List<AdministrativeAssistant> getAllAdmins() {
		return toList(administrativeAssistantRepository.findAll());
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