package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import java.time.LocalDateTime;

import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



@Entity
public class TimeSlot{
	private int timeSlotId;
	
	public void setTimeSlotId(int Id) {
		this.timeSlotId = Id;
	}
	@Id
	public int getTimeSlotId() {
		return this.timeSlotId;
	}
	

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	public TimeSlot(LocalDateTime aStartTime, LocalDateTime aEndTime, int Id, List<Mechanics> mechanics, List<Appointments> appointments){
		startTime = aStartTime;
		endTime = aEndTime;
		timeSlotId = Id;
		this.mechanics = mechanics;
		this.appointments = appointments;
	}

	public TimeSlot() {
		
	}

	public void setStartTime(LocalDateTime aStartTime){
		this.startTime = aStartTime;
	}

	public void setEndTime(LocalDateTime aEndTime)  {
		this.endTime = aEndTime;
	}

	public LocalDateTime getStartTime(){
		return this.startTime;
	}

	public LocalDateTime getEndTime(){
		return this.endTime;
	}

	private List<Mechanic> mechanics;
	@ManyToMany
	public List<Mechanic> getMechanic(){
		return this.mechanics;
	}
	public void setMechanic(List<Mechanic> mechanic){
		this.mechanics=mechanic;
	}
	
	private List<Appointment> appointments;
	
	@OneToMany(cascade = {CascadeType.ALL})
	public List<Appointment> getAppointment(){
		return this.appointments;
	}
	public void setAppointment(List<Appointment> appointment){
		this.appointments=appointment;
	}
	
	
	private RepairSystem repairSystem;
	@ManyToOne
	public RepairSystem getRepairSystem() {
		return this.repairSystem;
	}
	public void setRepairSystem(RepairSystem repairSystem) {
		this.repairSystem = repairSystem;
	}
	
	
}


