package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

@Entity
public class Service{

	public enum ServiceType { CarRepair, OilChange, RegularCheckup, CarWash, TireChange, RoadsideAssistance, Towing, CarInspection, Other }

	public Service(ServiceType aType, int aPrice){
		this.type = aType;
		this.price = aPrice;
	}
	
	public Service() {
		
	}
	private ServiceType type;
	public void setType(ServiceType aType){
		type = aType;
	}
	@Id
	public ServiceType getType() {
		return type;  
	}

	private int price;

	public void setPrice(int aPrice){
		price = aPrice;
	}


	public int getPrice(){
		return price;
	}

	private Appointment appointment;
	@ManyToOne
	public Appointment getAppointment(){
		return this.appointment;
	}
	public void setAppointment(Appointment appointment){
		this.appointment=appointment;
	}

	private Mechanic mechanic;
	@ManyToOne
	public Mechanic getMechanic(){
		return this.mechanic;
	}
	public void setMechanic(Mechanic mechanic){
		this.mechanic=mechanic;
	}
	

}