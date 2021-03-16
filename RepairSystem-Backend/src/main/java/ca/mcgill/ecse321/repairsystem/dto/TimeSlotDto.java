package ca.mcgill.ecse321.repairsystem.dto;

import java.util.*;
import java.time.LocalDateTime;

public class TimeSlotDto{
	private int id;
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
	

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	public TimeSlotDto(LocalDateTime aStartTime, LocalDateTime aEndTime, int Id, List<MechanicDto> mechanics, List<AppointmentDto> appointments){
		startTime = aStartTime;
		endTime = aEndTime;
		this.id = Id;
		this.mechanics = mechanics;
		this.appointments = appointments;
	}
	
	public TimeSlotDto(int id){
		this.id = id;
	}

	public TimeSlotDto() {
		
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

	private List<MechanicDto> mechanics;
	
	public List<MechanicDto> getMechanic(){
		return this.mechanics;
	}
	public void setMechanic(List<MechanicDto> mechanic){
		this.mechanics=mechanic;
	}
	
	private List<AppointmentDto> appointments;
	
	public List<AppointmentDto> getAppointment(){
		return this.appointments;
	}
	public void setAppointment(List<AppointmentDto> appointment){
		this.appointments=appointment;
	}
	
}


