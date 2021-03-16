package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;

@Entity
public class Customer extends Person
{
	public Customer(String aName,  int id, String aPassword, int aPhone, String aEmail, Calendar lastDate, String credit, String debit, String add)
	{
		super(aName, id, aPassword, aPhone, aEmail);
		creditHash = credit;
		debitHash = debit;
		address = add;
		lastActive = lastDate;
	}

	public Customer() {
	}

	private String creditHash;

	public String getCreditHash() {
		return creditHash;
	}

	public void setCreditHash(String aCreditHash)
	{
		creditHash = aCreditHash;
	}

	private String debitHash;

	public String getDebitHash() {
		return debitHash;
	}

	public void setDebitHash(String aDebitHash)
	{
		debitHash = aDebitHash;
	}

	private String address;

	public String getAddress(){
		return address;
	}

	public void setAddress(String aAddress)
	{
		address = aAddress;
	}

	private List<Appointment> appointments;

	@OneToMany(cascade={CascadeType.ALL})
	public List<Appointment> getAppointments()
	{
		return appointments;
	}

	public void setAppointments(List<Appointment> appointmentList) {
		appointments = appointmentList;
	}

	private List<Car> cars;

	@OneToMany(cascade={CascadeType.ALL})
	public List<Car> getCars()
	{
		return cars;
	}

	public void setCars(List<Car> carList){
		cars = carList;
	}

	public void addCar(Car car) {
		cars.add(car);
	}
	
	private Calendar lastActive;
	
	public Calendar getLastActive()
	{
		return lastActive;
	}

	public void setLastActive(Calendar last)
	{
		this.lastActive = last;
	}
	
}