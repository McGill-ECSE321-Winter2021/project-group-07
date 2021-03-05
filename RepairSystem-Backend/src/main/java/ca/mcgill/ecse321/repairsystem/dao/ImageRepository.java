package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.Appointment;
import ca.mcgill.ecse321.repairsystem.model.Image;

import java.util.*;

public interface ImageRepository extends CrudRepository<Image, String>{

	List<Image> findByAppointment(Appointment appointment);
	
}