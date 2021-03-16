package ca.mcgill.ecse321.repairsystem.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.repairsystem.dto.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Car.CarType;
import ca.mcgill.ecse321.repairsystem.service.*;

@CrossOrigin(origins = "*")
@RestController
public class CarRestController {

	@Autowired
	private AppointmentService appointmentService;
	private CarService carService;
	private CustomerService customerService;
	
	@GetMapping(value = { "/cars/{customer}", "/cars/{customer}/"})
	public List<CarDto> getCarsByCustomer(CustomerDto customerDto) {
		Customer customer = customerService.getCustomerById(customerDto.getId());
		List<CarDto> carsDto = new ArrayList<CarDto>();
		for(Car car: carService.getCarsByCustomer(customer)) {
			carsDto.add(Converter.convertToDto(car));
		}
		return carsDto;
	}

	@PostMapping(value = { "/car/{customer}", "/car/{customer}/" })
	public CarDto createCar(@PathVariable("customer") Customer customer, @RequestParam CarType type, @RequestParam boolean winterTires, @RequestParam int numOfKilometers, @RequestParam List<AppointmentDto> appointmentsDto) throws IllegalArgumentException {
		List<Appointment> appointments = new ArrayList<Appointment>();
		for(AppointmentDto appointment: appointmentsDto) {
			appointments.add(appointmentService.getAppointmentById(appointment.getId()));
		}
		Car car = carService.createCar(type, winterTires, numOfKilometers, appointments, customer);
		return Converter.convertToDto(car);
	}
	
}




