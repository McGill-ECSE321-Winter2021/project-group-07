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
	public Appointment createApp(Customer customer, TimeSlot time, List<Mechanic> mechanics, Car car, List<Image> images, List<ca.mcgill.ecse321.repairsystem.model.Service> services, String note, AppointmentStatus status) {
	
		if(customer == null)
		{
			throw new IllegalArgumentException("Customer cannot be null");
		} else if (status == null)
		{
			throw new IllegalArgumentException("Appointment status cannot be null");
		}else if (time == null)
		{
			throw new IllegalArgumentException("TimeSlot cannot be null");
		}else if (car == null)
		{
			throw new IllegalArgumentException("Car cannot be null");
		}
		
		int id = customer.hashCode() * time.hashCode();
		Appointment app = new Appointment(customer, id, time, mechanics, car, images, services, note, status);
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
