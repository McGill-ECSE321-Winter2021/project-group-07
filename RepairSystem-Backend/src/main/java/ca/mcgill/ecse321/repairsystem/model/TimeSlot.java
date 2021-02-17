package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
//	TODO: @id?? 
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
	
	private Time startTime;
	private Time endTime;


	public TimeSlot(Time aStartTime, Time aEndTime, int Id){
		startTime = aStartTime;
		endTime = aEndTime;
		timeSlotId = Id;
	}



	public void setStartTime(Time aStartTime){
		this.startTime = aStartTime;
	}

	public void setEndTime(Time aEndTime)  {
		this.endTime = aEndTime;
	}

	public Time getStartTime(){
		return startTime;
	}

	public Time getEndTime(){
		return endTime;
	}


	private Mechanic mechanic;
	@ManyToMany
	public Mechanic getMechanic(){
		return this.mechanic;
	}
	public void setMechanic(Mechanic mechanic){
		this.mechanic=mechanic;
	}

	private Appointment appointment;
	@ManyToMany
	public Appointment getAppointment(){
		return this.appointment;
	}
	public void setAppointment(Appointment appointment){
		this.appointment=appointment;
	}
	
	public void addTimeSlot() {
		
	}
	
	public void deleteTimeSlot() {
		
	}
	
	public void moveTimeSlot() {
		
	}
}


