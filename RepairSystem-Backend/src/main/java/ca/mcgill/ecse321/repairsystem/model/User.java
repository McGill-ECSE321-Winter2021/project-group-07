package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Entity
@Inheritance(strategy =InheritanceType.JOINED)
public abstract class User{

	private String name;
	private String password;
	private RepairSystem repairSystem;
	private int userId;
	private int phone;
	private String email;
	
	public User(String aName, int id,String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem){
		name = aName;
		password = aPassword;
		userId = id;
		phone = aPhone;
		email = aEmail;
		repairSystem = aRepairSystem;
	}

	@Id
	public int getId()
	{
		return this.userId;
	}

	public void setId(int aId)
	{
		this.userId = aId;

	}
	

	public String getName()
	{
		return name;
	}
	

	public void setName(String aName){
		this.name = aName;
	}
	
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String aPassword)
	{
		password = aPassword;

	}
	

	public int getPhone()
	{
		return phone;
	}

	public void setPhone(int aPhone)
	{
		phone = aPhone;

	}

	public String getEmail()
	{
		return email;
	}
	public void setEmail(String aEmail)
	{
		email = aEmail;
	}


}