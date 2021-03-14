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

public class AdministrativeAssistantService {
	@Autowired
	private AdministrativeAssistantRepository administrativeAssistantRepository;

	@Transactional
	public AdministrativeAssistant createAdmin(String aName, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem) {
		if(aName == null || aName.trim().length() == 0) throw new IllegalArgumentException("Administrative assistant name cannot be empty!");
		if(aPassword == null || aPassword.trim().length() == 0) throw new IllegalArgumentException("Administrative assistant passward cannot be empty!");
		if(aEmail == null || aEmail.trim().length() == 0) throw new IllegalArgumentException("Administrative assistant email cannot be empty!");
		if(aRepairSystem == null || aEmail.trim().length() == 0) throw new IllegalArgumentException("Administrative assistant repair system cannot be empty!");

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