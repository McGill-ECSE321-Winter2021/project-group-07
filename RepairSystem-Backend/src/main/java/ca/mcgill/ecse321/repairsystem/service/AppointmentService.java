package ca.mcgill.ecse321.repairsystem.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Appointment.AppointmentStatus;

@Service
public class AppointmentService {
	////////////////////SERVICE APPOINTMENT METHODS //////////////////// 
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Transactional 
	public Appointment createApp(Customer customer, TimeSlot time, Car car,String note) {
		//input validation
		if(customer == null)
		{
			throw new IllegalArgumentException("Customer cannot be null");
		}else if (time == null)
		{
			throw new IllegalArgumentException("TimeSlot cannot be null");
		}else if (car == null)
		{
			throw new IllegalArgumentException("Car cannot be null");
		}
		
		int id = customer.hashCode() * time.hashCode();
		Appointment app = new Appointment(customer, id, time,  car, note );
		appointmentRepository.save(app);
		return app;
	}

	@Transactional
	public List<Appointment> getAppointmentsByCustomer(Customer customer) {
		List<Appointment> appointments = toList(appointmentRepository.findByCustomer(customer));
		return appointments;
	}

	@Transactional
	public List<Appointment> getAppointmentsByCar(Car car) {
		List<Appointment> appointments = toList(appointmentRepository.findByCar(car));
		return appointments;
	}

	@Transactional
	public List<Appointment> getAppointmentsByStatus(AppointmentStatus status) {
		List<Appointment> appointments = toList(appointmentRepository.findByStatus(status));
		return appointments;
	}

	@Transactional
	public List<Appointment> getAppointmentsByTimeSlot(TimeSlot time) {
		List<Appointment> appointments = toList(appointmentRepository.findByTimeSlot(time));
		return appointments;
	}

	@Transactional
	public Appointment getAppointmentById(int id) {
		Appointment appointment = appointmentRepository.findById(id);
		return appointment;
	}

	@Transactional
	public List<Appointment> getAllAppointments() {
		return toList(appointmentRepository.findAll());
	}
	
	@Transactional
	public void addMechanic(Appointment appointment, Mechanic mechanic)
	{
		appointment.addMechanic(mechanic);
	}
	
	@Transactional 
	public void addService(Appointment appointment, ca.mcgill.ecse321.repairsystem.model.Service service)
	{
		appointment.addService(service);
	}
	
	@Transactional
	public void addImage(Appointment appointment, Image image)
	{
		appointment.addImage(image);
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
