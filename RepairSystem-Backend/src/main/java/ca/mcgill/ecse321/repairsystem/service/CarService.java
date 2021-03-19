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

	/**
	 * Creating a car object
	 * @param type
	 * @param winterTires
	 * @param numOfKm
	 * @param customer
	 * @return car object
	 */
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
		customer.addCar(c);
		carRepository.save(c);
		customerRepository.save(customer);
		return c;
	}

	/**
	 * Getter method to obtain a car by searching by id
	 * @param id
	 * @return the car associated to the id
	 */
	@Transactional 
	public Car getCarById(int id) {
		Car c = carRepository.findById(id);
		return c;
	}

	/**
	 * Getter method to obtain the list of cars associated by searching by customer
	 * @param customer
	 * @return list of cars associated to input customer
	 */
	@Transactional
	public List<Car> getCarsByCustomer(Customer customer) {
		return toList(carRepository.findByCustomer(customer));
	}

	/**
	 * Getter method to obtain the list of cars associated by searching by car type
	 * @param type
	 * @return list of cars associated by inputed type
	 */
	@Transactional
	public List<Car> getCarsByCarType(CarType type) {
		return toList(carRepository.findByCarType(type));
	}

	/**
	 * Getter methods to obtain the list of cars associated by searching if a car has winter tires or not 
	 * @param winterTires
	 * @return list of cars
	 */
	@Transactional
	public List<Car> getCarsByWinterTires(boolean winterTires) {
		return toList(carRepository.findByWinterTires(winterTires));
	}

	/**
	 * Getter method to obtain the list of car existing in the database
	 * @return list of cars
	 */
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
