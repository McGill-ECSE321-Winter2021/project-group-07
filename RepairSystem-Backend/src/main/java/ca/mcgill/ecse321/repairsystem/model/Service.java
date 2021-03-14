 package ca.mcgill.ecse321.repairsystem.model;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import java.util.*;

@Entity
public class Service{

	public enum ServiceType { CarRepair, OilChange, RegularCheckup, CarWash, TireChange, RoadsideAssistance, Towing, CarInspection, Other }

	public Service(ServiceType aType, int aPrice, List<Mechanic> mechanics, List<Appointment> a){
		this.serviceType = aType;
		this.price = aPrice;
		this.mechanics = mechanics;
		this.appointments = a;
	}
	
	public Service() {
		
	}
	
	private ServiceType serviceType;
	public void setServiceType(ServiceType aType){
		serviceType = aType;
	}
	@Id
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

	private List<Appointment> appointments;
	@ManyToMany
	public List<Appointment> getAppointments(){
		return this.appointments;
	}
	public void setAppointments(List<Appointment> appointments){
		this.appointments=appointments;
	}

	private List<Mechanic> mechanics;
	@ManyToMany
	public List<Mechanic> getMechanics(){
		return this.mechanics;
	}
	public void setMechanics(List<Mechanic> mechanics){
		this.mechanics = mechanics;
	}
	

}