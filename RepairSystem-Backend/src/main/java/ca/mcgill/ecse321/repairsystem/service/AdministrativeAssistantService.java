package ca.mcgill.ecse321.repairsystem.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;

@Service
public class AdministrativeAssistantService {
	@Autowired
	private AdministrativeAssistantRepository administrativeAssistantRepository;

	@Transactional
	public AdministrativeAssistant createAdmin(String aName, String aPassword, int aPhone, String aEmail) {
		
		if(aName == null || aName.trim().length() == 0)
		{
			throw new IllegalArgumentException("Administrative assistant name cannot be empty!");
				
		}else if (aPassword == null || aPassword.trim().length() == 0)
		{
			throw new IllegalArgumentException("Administrative assistant password cannot be empty!");
		}else if (aEmail == null || aEmail.trim().length() == 0)
		{
			throw new IllegalArgumentException("Administrative assistant email cannot be empty!");
		}
		int id = aEmail.hashCode();
		AdministrativeAssistant admin = new AdministrativeAssistant(aName, id, aPassword, aPhone, aEmail);
		administrativeAssistantRepository.save(admin);
		return admin;
	}
	
	public AdministrativeAssistant editAdmin(String oldEmail, String name, String password, int phone, String email) {
		AdministrativeAssistant admin = administrativeAssistantRepository.findByEmail(oldEmail);
		admin.setEmail(email);
		admin.setId(email.hashCode());
		admin.setName(name);
		admin.setPassword(password);
		admin.setPhone(phone);
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