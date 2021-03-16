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
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class TimeSlotRestController {

	@Autowired
	private TimeSlotService timeSlotService;
	private MechanicService mechanicService;
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
	
	@GetMapping(value = { "/timeslot/{startTime}", "/timeslot/{startTime}/"})
	public TimeSlotDto getTimeSlotById(int id) {
		return Converter.convertToDto(timeSlotService.getTimeSlotById(id));
	}

	@PostMapping(value = { "/timeslot/{startTime}", "/timeslot/{startTime}/" })
	public TimeSlotDto createTimeSlot(@PathVariable("startTime") LocalDateTime startTime, @RequestParam LocalDateTime endTime, @RequestParam List<MechanicDto> mechanicsDto, @RequestParam List<AppointmentDto> appointmentsDto) throws IllegalArgumentException {
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		for(MechanicDto mechanic: mechanicsDto) {
			mechanics.add(mechanicService.getMechanicById(mechanic.getId()));
		}
		for(AppointmentDto appointment: appointmentsDto) {
			appointments.add(appointmentService.getAppointmentById(appointment.getId()));
		}
		TimeSlot timeslot = timeSlotService.createTimeSlot(startTime, endTime, mechanics, appointments);
		return Converter.convertToDto(timeslot);
	}
	
	@PutMapping(value = { "/timeslot/{oldStartTime}", "/timeslot/{oldStartTime}/" })
	public TimeSlotDto editTimeSlot(@PathVariable("oldStartTime") LocalDateTime oldStartTime, @RequestParam LocalDateTime oldEndTime, @RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime, @RequestParam List<MechanicDto> mechanicsDto, @RequestParam List<AppointmentDto> appointmentsDto) throws IllegalArgumentException {
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		for(MechanicDto mechanic: mechanicsDto) {
			mechanics.add(mechanicService.getMechanicById(mechanic.getId()));
		}
		for(AppointmentDto appointment: appointmentsDto) {
			appointments.add(appointmentService.getAppointmentById(appointment.getId()));
		}
		TimeSlot timeslot = timeSlotService.getTimeSlotById(oldStartTime.hashCode()*oldEndTime.hashCode());
		timeslot.setStartTime(startTime);
		timeslot.setEndTime(endTime);
		timeslot.setAppointments(appointments);
		timeslot.setMechanics(mechanics);
		timeslot.setId(startTime.hashCode()*endTime.hashCode());
		return Converter.convertToDto(timeslot);
	}

}




