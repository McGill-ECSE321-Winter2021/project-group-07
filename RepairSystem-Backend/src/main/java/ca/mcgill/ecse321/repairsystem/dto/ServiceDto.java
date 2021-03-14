package ca.mcgill.ecse321.repairsystem.dto;

import java.util.*;

public class ServiceDto{

	public enum ServiceType { CarRepair, OilChange, RegularCheckup, CarWash, TireChange, RoadsideAssistance, Towing, CarInspection, Other }

	public ServiceDto(ServiceType aType, int aPrice, List<MechanicDto> mechanics, List<AppointmentDto> a){
		this.serviceType = aType;
		this.price = aPrice;
		this.mechanics = mechanics;
		this.appointments = a;
	}
	
	public ServiceDto() {
		
	}
	
	private ServiceType serviceType;
	public void setServiceType(ServiceType aType){
		serviceType = aType;
	}
	
	public ServiceType getServiceType() {
		return serviceType;  
	}

	private int price;

	public void setPrice(int aPrice){
		price = aPrice;
	}


	public int getPrice(){
		return price;
	}

	private List<AppointmentDto> appointments;
	
	public List<AppointmentDto> getAppointments(){
		return this.appointments;
	}
	public void setAppointments(List<AppointmentDto> appointments){
		this.appointments=appointments;
	}

	private List<MechanicDto> mechanics;
	
	public List<MechanicDto> getMechanics(){
		return this.mechanics;
	}
	public void setMechanics(List<MechanicDto> mechanics){
		this.mechanics = mechanics;
	}
	

}
