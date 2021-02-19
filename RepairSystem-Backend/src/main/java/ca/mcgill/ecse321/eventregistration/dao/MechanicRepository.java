package ca.mcgill.ecse321.eventregistration.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.Mechanic;

public interface MechanicRepository extends CrudRepository<Mechanic, Integer>{

	Mechanic findMechanicById(int Id);

}