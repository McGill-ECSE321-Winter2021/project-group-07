package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.Appointment;
import ca.mcgill.ecse321.repairsystem.model.Image;

public interface ImageRepository extends CrudRepository<Image, String>{

	Image findByAppointment(Appointment appointment);
	
}