package ca.mcgill.ecse321.repairsystem.service;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse321.repairsystem.dao.TimeSlotRepository;
import ca.mcgill.ecse321.repairsystem.model.Appointment;
import ca.mcgill.ecse321.repairsystem.model.Mechanic;
import ca.mcgill.ecse321.repairsystem.model.TimeSlot;

@Service
public class TimeSlotService {
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	@Transactional 
	public TimeSlot createTimeSlot(LocalDateTime aStartTime, LocalDateTime aEndTime, List<Mechanic> mechanics, List<Appointment> appointments) {
		int id = aStartTime.hashCode() * aEndTime.hashCode();
		TimeSlot timeslot = new TimeSlot(aStartTime, aEndTime, id, mechanics, appointments);
		timeSlotRepository.save(timeslot);
		return timeslot;
	}
	
	@Transactional
	public TimeSlot getTimeSlotById(int id) { 
		return timeSlotRepository.findById(id);
	}
	
	@Transactional
	public List<TimeSlot> getTimeSlotsByStartTime(LocalDateTime time) { 
		return toList(timeSlotRepository.findByStartTime(time));
	}
	
	@Transactional
	public List<TimeSlot> getTimeSlotsByEndTime(LocalDateTime time) { 
		return toList(timeSlotRepository.findByEndTime(time));
	}
	
	@Transactional
	public List<TimeSlot> getAllTimeSlots() { 
		return toList(timeSlotRepository.findAll());
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
