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
	private CarService carService;
	@Autowired
	private CustomerService customerService;
	
	@GetMapping(value = { "/cars/{customerId}", "/cars/{customerId}/"})
	public List<CarDto> getCarsByCustomer(@PathVariable("customerId") String customerId) {
		Customer customer = customerService.getCustomerById(Integer.parseInt(customerId));
		List<CarDto> carsDto = new ArrayList<CarDto>();
		for(Car car: carService.getCarsByCustomer(customer)) {
			carsDto.add(Converter.convertToDto(car));
		}
		return carsDto;
	}

	@PostMapping(value = { "/car/{customerId}", "/car/{customerId}/" })
	public CarDto createCar(@PathVariable("customerId") String customerId, @RequestParam String carType, @RequestParam String winterTires, @RequestParam String numOfKilometers) throws IllegalArgumentException {
		Customer customer = customerService.getCustomerById(Integer.parseInt(customerId));
		Car car = carService.createCar(CarType.valueOf(carType), Boolean.valueOf(winterTires), Integer.parseInt(numOfKilometers), new ArrayList<Appointment>(), customer);
		return Converter.convertToDto(car);
	}
	
}




