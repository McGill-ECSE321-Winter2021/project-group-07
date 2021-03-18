package ca.mcgill.ecse321.repairsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.repairsystem.service.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.dto.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class TimeSlotRestController {

	@Autowired
	private TimeSlotService timeSlotService;
	@Autowired
	private MechanicService mechanicService;
	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping(value = { "/timeslots", "/timeslots/"})
	public List<TimeSlotDto> getAllTimeSlot() {
		List<TimeSlot> timeslots = timeSlotService.getAllTimeSlots();
		List<TimeSlotDto> timeslotsDto = new ArrayList<TimeSlotDto>();
		for(TimeSlot timeslot: timeslots) {
			timeslotsDto.add(Converter.convertToDto(timeslot));
		}
		return timeslotsDto;
	}
	
	@GetMapping(value = { "/timeslot/{id}", "/timeslot/{id}/"})
	public TimeSlotDto getTimeSlotById(@PathVariable("id") String id) {
		return Converter.convertToDto(timeSlotService.getTimeSlotById(Integer.parseInt(id)));
	}

	@PostMapping(value = { "/timeslot/{startTime}", "/timeslot/{startTime}/" })
	public TimeSlotDto createTimeSlot(@PathVariable("startTime") String startTime, @RequestParam String endTime) throws IllegalArgumentException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
		LocalDateTime start = LocalDateTime.parse(startTime, formatter);
		LocalDateTime end = LocalDateTime.parse(endTime, formatter);
		TimeSlot timeslot = timeSlotService.createTimeSlot(start, end, new ArrayList<Mechanic>(), new ArrayList<Appointment>());
		return Converter.convertToDto(timeslot);
	}
	
	@PutMapping(value = { "/timeslot/{oldStartTime}", "/timeslot/{oldStartTime}/" })
	public TimeSlotDto editTimeSlot(@PathVariable("oldStartTime") String oldStartTime, @RequestParam String oldEndTime, @RequestParam String startTime, @RequestParam String endTime) throws IllegalArgumentException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
		LocalDateTime oldStart = LocalDateTime.parse(oldStartTime, formatter);
		LocalDateTime oldEnd = LocalDateTime.parse(oldEndTime, formatter);
		LocalDateTime newStart = LocalDateTime.parse(startTime, formatter);
		LocalDateTime newEnd = LocalDateTime.parse(endTime, formatter);
		TimeSlot timeslot = timeSlotService.getTimeSlotById(oldStart.hashCode()*oldEnd.hashCode());
		timeslot.setStartTime(newStart);
		timeslot.setEndTime(newEnd);
		timeslot.setId(newStart.hashCode()*newEnd.hashCode());
		return Converter.convertToDto(timeslot);
	}
	
	@PutMapping(value = { "/timeslot/{mechanicId}", "/timeslot/{mechanicId}/" })
	public TimeSlotDto editMechanic(@PathVariable("mechanicId") String mechanicId, @RequestParam String startTime, @RequestParam String endTime, @RequestParam String addRemove) throws IllegalArgumentException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
		LocalDateTime start = LocalDateTime.parse(startTime, formatter);
		LocalDateTime end = LocalDateTime.parse(endTime, formatter);
		TimeSlot timeslot = timeSlotService.getTimeSlotById(start.hashCode()*end.hashCode());
		Mechanic mechanic = mechanicService.getMechanicById(Integer.parseInt(mechanicId));
		if(addRemove.equals("add")) {
			timeSlotService.addMechanic(mechanic, timeslot);
		} else if(addRemove.equals("remove")){
			timeSlotService.removeMechanic(mechanic, timeslot);
		}
		return Converter.convertToDto(timeslot);
	}
	
	@PutMapping(value = { "/timeslot/{appointmentId}", "/timeslot/{appointmentId}/" })
	public TimeSlotDto editAppointment(@PathVariable("appointmentId") String appointmentId, @RequestParam String startTime, @RequestParam String endTime, @RequestParam String addRemove) throws IllegalArgumentException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
		LocalDateTime start = LocalDateTime.parse(startTime, formatter);
		LocalDateTime end = LocalDateTime.parse(endTime, formatter);
		TimeSlot timeslot = timeSlotService.getTimeSlotById(start.hashCode()*end.hashCode());
		Appointment appointment = appointmentService.getAppointmentById(Integer.parseInt(appointmentId));
		if(addRemove.equals("add")) {
			timeSlotService.addAppointment(appointment, timeslot);
		} else if(addRemove.equals("remove")){
			timeSlotService.removeAppointment(appointment, timeslot);
		}
		return Converter.convertToDto(timeslot);
	}

}




