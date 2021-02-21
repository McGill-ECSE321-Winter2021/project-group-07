package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.Mechanic;

public interface MechanicRepository extends CrudRepository<Mechanic, String>{

	Mechanic findMechanicById(int Id);
	

}