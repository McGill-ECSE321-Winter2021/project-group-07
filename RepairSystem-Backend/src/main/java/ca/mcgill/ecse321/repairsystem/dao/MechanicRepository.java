package ca.mcgill.ecse321.repairsystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.Mechanic;
import ca.mcgill.ecse321.repairsystem.model.Service;

public interface MechanicRepository extends CrudRepository<Mechanic, String>{

	Mechanic findMechanicByName(String name);
	

}