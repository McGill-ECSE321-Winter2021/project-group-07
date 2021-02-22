package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToMany;
import javax.persistence.Entity;


@Entity
public class AdministrativeAssistant extends Person
{
	
	public AdministrativeAssistant(String aName, int id,String aPassword, int aPhone, String aEmail,RepairSystem aRepairSystem) {
		super(aName,id, aPassword, aPhone, aEmail, aRepairSystem);
		// TODO Auto-generated constructor stub
	}
	
	

	public AdministrativeAssistant() {
		// TODO Auto-generated constructor stub
	}



	private int id;
	@Id
	public int getId()
	{
		return id;
	}

	public void setId(int aId)
	{
		this.id = aId;

	}


}