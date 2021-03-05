package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.*;
import java.util.*;

public interface MechanicRepository extends CrudRepository<Mechanic, String>{

	Mechanic findById(int Id);
	List<Mechanic> findByName(String name);
	Mechanic findByPhone(int aPhone);
	Mechanic findByEmail(String email);
	

}