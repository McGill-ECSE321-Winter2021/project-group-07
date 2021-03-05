package ca.mcgill.ecse321.repairsystem.dao;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.*;


public interface TimeSlotRepository extends CrudRepository<TimeSlot, String>{

	TimeSlot findById(int id);
	List<TimeSlot> findByStartTime(LocalDateTime time);
	List<TimeSlot> findByEndTime(LocalDateTime time);
}