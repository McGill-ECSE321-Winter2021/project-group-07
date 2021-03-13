package ca.mcgill.ecse321.repairsystem.service;

import java.util.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Appointment.AppointmentStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

public class TestCarService {
	@Autowired 
	private CarRepository carRepository;

	////////////////////SERVICE CAR METHODS //////////////////// 

	@Transactional
	public Car createCar(CarType type, boolean winterTires, int numOfKm, List<Appointment> appointments, Customer customer) {
		int id = (Integer.toString(numOfKm)).hashCode() * customer.hashCode();
		Car c = new Car(id, type, winterTires, numOfKm, appointments, customer);
		carRepository.save(c);
		return c;
	}

	@Transactional 
	public Car getCarById(int id) {
		Car c = carRepository.findById(id);
		return c;
	}

	@Transactional
	public List<Car> getCarsByCustomer(Customer customer) {
		return toList(carRepository.findByCustomer(customer));
	}

	@Transactional
	public List<Car> getCarsByCarType(CarType type) {
		return toList(carRepository.findByCarType(type));
	}

	@Transactional
	public List<Car> getCarsByWinterTires(boolean winterTires) {
		return toList(carRepository.findByWinterTires(winterTires));
	}

	@Transactional
	public List<Car> getAllCars() {
		return toList(carRepository.findAll());
	}

	/* 
	 * helper method
	 */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
