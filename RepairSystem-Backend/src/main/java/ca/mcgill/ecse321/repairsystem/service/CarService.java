package ca.mcgill.ecse321.repairsystem.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Car.CarType;

@Service
public class CarService {

	@Autowired 
	private CarRepository carRepository;
	@Autowired 
	private CustomerRepository customerRepository;

	//////////////////// SERVICE CAR METHODS //////////////////// 

	@Transactional
	public Car createCar(CarType type, boolean winterTires, int numOfKm,  Customer customer) {
		
		if(type == null)
		{
			throw new IllegalArgumentException("Car Type cannot be null");
		}else if ( customer == null)
		{
			throw new IllegalArgumentException("Customer cannot be null");
		}
		int id = (Integer.toString(numOfKm)).hashCode() * customer.hashCode();
		Car c = new Car(id, type, winterTires, numOfKm, customer);
		//customerRepository.delete(customer);
		customer.addCar(c);
		carRepository.save(c);
		customerRepository.save(customer);
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
