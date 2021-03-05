package ca.mcgill.ecse321.repairsystem.model;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Service{

	public enum ServiceType { CarRepair, OilChange, RegularCheckup, CarWash, TireChange, RoadsideAssistance, Towing, CarInspection, Other }

	public Service(ServiceType aType, int aPrice, List<Mechanics> mechanics, List<Appointments> a){
		this.type = aType;
		this.price = aPrice;
		this.mechanics = mechanics;
		appointments = a;
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

	private Appointment List<appointments>;
	@ManyToOne
	public Appointment getAppointments(){
		return this.appointments;
	}
	public void setAppointments(List<Appointment> appointments){
		this.appointment=appointment;
	}

	private List<Mechanic> mechanics;
	@ManyToOne
	public Mechanic getMechanics(){
		return this.mechanics;
	}
	public void setMechanics(List<Mechanic> mechanics){
		this.mechanics = mechanics;
	}
	

}