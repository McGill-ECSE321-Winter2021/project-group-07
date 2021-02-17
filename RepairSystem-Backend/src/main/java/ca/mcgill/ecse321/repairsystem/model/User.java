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
public class User{

	private String name;
	private int id;
	private String password;
	private int phone;
	private String email;



	public User(String aName, int aId, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem){
		name = aName;
		id = aId;
		password = aPassword;
		phone = aPhone;
		email = aEmail;
	}


	public void setName(String aName){
		this.name = aName;
	}

	public void setId(int aId)
	{
		id = aId;

	}

	public void setPassword(String aPassword)
	{
		password = aPassword;

	}

	public void setPhone(int aPhone)
	{
		phone = aPhone;

	}

	public void setEmail(String aEmail)
	{
		email = aEmail;
	}

	public String getName()
	{
		return name;
	}
	@Id
	public int getId()
	{
		return id;
	}

	public String getPassword()
	{
		return password;
	}

	public int getPhone()
	{
		return phone;
	}

	public String getEmail()
	{
		return email;
	}

}