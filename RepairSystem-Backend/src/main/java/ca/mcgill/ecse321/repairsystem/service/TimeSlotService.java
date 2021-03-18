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
	public TimeSlot createTimeSlot(LocalDateTime aStartTime, LocalDateTime aEndTime) {
	
		if(aStartTime == null)
		{
			throw new IllegalArgumentException("Start time cannot be null");
		}else if (aEndTime == null)
		{
			throw new IllegalArgumentException("End time cannot be null");
		}
		//check that endtime is not before start time
		int id = aStartTime.hashCode() * aEndTime.hashCode();
		TimeSlot timeslot = new TimeSlot(aStartTime, aEndTime, id);
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
	
	@Transactional
	public void addMechanic(Mechanic mechanic, TimeSlot timeslot) {
		timeslot.addMechanic(mechanic);
		timeSlotRepository.save(timeslot);
	}
	
	@Transactional
	public void addAppointment(Appointment appointment, TimeSlot timeslot) {
		timeslot.addAppointment(appointment);
		timeSlotRepository.save(timeslot);
	}
	
	@Transactional
	public void removeMechanic(Mechanic mechanic, TimeSlot timeslot) {
		timeslot.removeMechanic(mechanic);
		timeSlotRepository.save(timeslot);
	}
	
	@Transactional
	public void removeAppointment(Appointment appointment, TimeSlot timeslot) {
		timeslot.removeAppointment(appointment);
		timeSlotRepository.save(timeslot);
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
