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
import ca.mcgill.ecse321.repairsystem.service.*;

@CrossOrigin(origins = "*")
@RestController
public class AppointmentRestController {

	@Autowired
	private AppointmentService appointmentService;
	private CustomerService customerService;
	private TimeSlotService timeSlotService;
	private MechanicService mechanicService;
	private ImageService imageService;
	private ServiceService serviceService;
	private CarService carService;
	
	@GetMapping(value = { "/appointment/{id}", "/appointment/{id}/"})
	public AppointmentDto getAppointmentById(int id) {
		return Converter.convertToDto(appointmentService.getAppointmentById(id));
	}

	@PostMapping(value = { "/appointment/{customer}", "/appointment/{customer}/"})
	public AppointmentDto createAppointment(@PathVariable("customer") CustomerDto customerDto, @RequestParam TimeSlotDto timeslotDto, @RequestParam List<MechanicDto> mechanicsDto, @RequestParam CarDto carDto, @RequestParam List<ImageDto> imagesDto, @RequestParam List<ServiceDto> servicesDto, @RequestParam String note, @RequestParam AppointmentStatus appointmentStatus) throws IllegalArgumentException {
		Customer customer = customerService.getCustomerById(customerDto.getId());
		TimeSlot timeslot = timeSlotService.getTimeSlotById(timeslotDto.getId());
		Car car = carService.getCarById(carDto.getId());
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		for(MechanicDto mechanic: mechanicsDto) {
			mechanics.add(mechanicService.getMechanicById(mechanic.getId()));
		}
		List<Image> images = new ArrayList<Image>();
		for(ImageDto image: imagesDto) {
			images.add(imageService.getImageByUrl(image.getUrl()));
		}
		List<Service> services = new ArrayList<Service>();
		for(ServiceDto service : servicesDto) {
			services.add(serviceService.getServiceByServiceType(service.getServiceType()));
		}
		Appointment appointment = appointmentService.createApp(customer, timeslot, mechanics, car, images, services, note, appointmentStatus);
		return Converter.convertToDto(appointment);
	}
	
	@PutMapping(value = { "/appointment/{customer}", "/appointment/{customer}/"})
	public AppointmentDto editAppointment(@PathVariable("customer") CustomerDto customerDto, @RequestParam TimeSlotDto oldTimeSlotDto, @RequestParam TimeSlotDto timeslotDto, @RequestParam List<MechanicDto> mechanicsDto, @RequestParam CarDto carDto, @RequestParam List<ImageDto> imagesDto, @RequestParam List<ServiceDto> servicesDto, @RequestParam String note, @RequestParam AppointmentStatus appointmentStatus) throws IllegalArgumentException {
		TimeSlot oldTimeSlot = timeSlotService.getTimeSlotById(oldTimeSlotDto.getId());
		Customer customer = customerService.getCustomerById(customerDto.getId());
		TimeSlot timeslot = timeSlotService.getTimeSlotById(timeslotDto.getId());
		
		Appointment appointment = appointmentService.getAppointmentById(customer.hashCode()*oldTimeSlot.hashCode());
		
		Car car = carService.getCarById(carDto.getId());
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		for(MechanicDto mechanic: mechanicsDto) {
			mechanics.add(mechanicService.getMechanicById(mechanic.getId()));
		}
		List<Image> images = new ArrayList<Image>();
		for(ImageDto image: imagesDto) {
			images.add(imageService.getImageByUrl(image.getUrl()));
		}
		List<Service> services = new ArrayList<Service>();
		for(ServiceDto service : servicesDto) {
			services.add(serviceService.getServiceByServiceType(service.getServiceType()));
		}
		appointment.setCar(car);
		appointment.setImages(images);
		appointment.setMechanics(mechanics);
		appointment.setServices(services);
		appointment.setId(customer.hashCode()*timeslot.hashCode());
		appointment.setNote(note);
		appointment.setStatus(appointmentStatus);
		appointment.setTimeSlot(timeslot);
		return Converter.convertToDto(appointment);
	}

}




