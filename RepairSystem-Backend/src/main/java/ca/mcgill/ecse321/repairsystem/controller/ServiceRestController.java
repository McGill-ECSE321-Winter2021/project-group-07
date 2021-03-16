package ca.mcgill.ecse321.repairsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.repairsystem.dto.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;
import ca.mcgill.ecse321.repairsystem.service.*;

@CrossOrigin(origins = "*")
@RestController
public class ServiceRestController {

	@Autowired
	private ServiceService serviceService;
	private MechanicService mechanicService;
	private AppointmentService appointmentService;
	
	@GetMapping(value = { "/services", "/services/"})
	public List<ServiceDto> getAllServices() {
		List<Service> services = serviceService.getAllServices();
		List<ServiceDto> servicesDto = new ArrayList<ServiceDto>();
		for(Service service: services) {
			servicesDto.add(Converter.convertToDto(service));
		}
		return servicesDto;
	}
	
	@GetMapping(value = { "/services/{serviceType}", "/services/{serviceType}/"})
	public ServiceDto getServiceByServiceType(ServiceType serviceType) {
		return Converter.convertToDto(serviceService.getServiceByServiceType(serviceType));
	}

	@PostMapping(value = { "/services/{serviceType}", "/services/{serviceType}/" })
	public ServiceDto createService(@PathVariable("serviceType") ServiceType serviceType, @RequestParam int price, @RequestParam List<MechanicDto> mechanicsDto, @RequestParam List<AppointmentDto> appointmentsDto) throws IllegalArgumentException {
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		for(MechanicDto mechanic: mechanicsDto) {
			mechanics.add(mechanicService.getMechanicById(mechanic.getId()));
		}
		for(AppointmentDto appointment: appointmentsDto) {
			appointments.add(appointmentService.getAppointmentById(appointment.getId()));
		}
		Service service = serviceService.createService(serviceType, price, mechanics, appointments);
		return Converter.convertToDto(service);
	}
	
	/*
	@DeleteMapping(value = { "/services/{serviceType}", "/services/{serviceType}/"})
	public void deleteService(ServiceType serviceType) {
		Service service = serviceService.getServiceByServiceType(serviceType);
		service.delete();
	}
	*/
	
}




