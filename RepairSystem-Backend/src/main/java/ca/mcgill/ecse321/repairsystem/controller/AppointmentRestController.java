package ca.mcgill.ecse321.repairsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.repairsystem.dto.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Appointment.AppointmentStatus;
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;
import ca.mcgill.ecse321.repairsystem.service.*;

@CrossOrigin(origins = "*")
@RestController
public class AppointmentRestController {

	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private TimeSlotService timeSlotService;
	@Autowired
	private CarService carService;
	@Autowired
	private MechanicService mechanicService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private ServiceService serviceService;
	
	@GetMapping(value = { "/appointment/{id}", "/appointment/{id}/"})
	public AppointmentDto getAppointmentById(@PathVariable("id") String id) {
		return Converter.convertToDto(appointmentService.getAppointmentById(Integer.parseInt(id)));
	}
	
	@GetMapping(value = { "/appointment", "/appointment/"})
	public List<AppointmentDto> getAllAppointments() {
		List<Appointment> appointments = appointmentService.getAllAppointments();
		List<AppointmentDto> appointmentsDto = new ArrayList<AppointmentDto>();
		for(Appointment appointment: appointments) {
			appointmentsDto.add(Converter.convertToDto(appointment));		}
		return appointmentsDto;
	}

	@PostMapping(value = { "/appointment/{customerId}", "/appointment/{customerId}/"})
	public AppointmentDto createAppointment(@PathVariable("customerId") String customerId, @RequestParam String timeSlotId,  @RequestParam String carId,  @RequestParam(defaultValue = "") String note) throws IllegalArgumentException {
		Customer customer = customerService.getCustomerById(Integer.parseInt(customerId));
		TimeSlot timeslot = timeSlotService.getTimeSlotById(Integer.parseInt(timeSlotId));
		Car car = carService.getCarById(Integer.parseInt(carId));
		Appointment appointment = appointmentService.createApp(customer, timeslot, car, note);
		return Converter.convertToDto(appointment);
	}
	
	@PutMapping(value = { "/appointment/editAppointment/{customerId}", "/appointment/editAppointment/{customerId}/"})
	public AppointmentDto editAppointment(@PathVariable("customerId") String customerId, @RequestParam String timeSlotId, String carId, @RequestParam String note) throws IllegalArgumentException {
		TimeSlot timeSlot = timeSlotService.getTimeSlotById(Integer.parseInt(timeSlotId));
		Customer customer = customerService.getCustomerById(Integer.parseInt(customerId));
		Car car = carService.getCarById(Integer.parseInt(carId));
		Appointment appointment = appointmentService.editApp(customer, timeSlot, car, note);
		return Converter.convertToDto(appointment);
	}
	
	@PutMapping(value = { "/appointment/addMechanic/{mechanicId}", "/appointment/addMechanic/{mechanicId}/"})
	public AppointmentDto addMechanic(@PathVariable("mechanicId") String mechanicId, @RequestParam String timeSlotId, @RequestParam String customerId) throws IllegalArgumentException {
		TimeSlot timeSlot = timeSlotService.getTimeSlotById(Integer.parseInt(timeSlotId));
		Customer customer = customerService.getCustomerById(Integer.parseInt(customerId));		
		Appointment appointment = appointmentService.getAppointmentById(customer.hashCode()*timeSlot.hashCode());	
		Mechanic mechanic = mechanicService.getMechanicById(Integer.parseInt(mechanicId));
		appointmentService.addMechanic(appointment, mechanic);
		return Converter.convertToDto(appointment);
	}

	@PutMapping(value = { "/appointment/addService/{serviceType}", "/appointment/addService/{serviceType}/"})
	public AppointmentDto addService(@PathVariable("serviceType") String serviceType, @RequestParam String timeSlotId, @RequestParam String customerId) throws IllegalArgumentException {
		TimeSlot timeSlot = timeSlotService.getTimeSlotById(Integer.parseInt(timeSlotId));
		Customer customer = customerService.getCustomerById(Integer.parseInt(customerId));		
		Appointment appointment = appointmentService.getAppointmentById(customer.hashCode()*timeSlot.hashCode());	
		Service service = serviceService.getServiceByServiceType(ServiceType.valueOf(serviceType));
		appointmentService.addService(appointment, service);
		return Converter.convertToDto(appointment);
	}	

}




