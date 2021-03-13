package ca.mcgill.ecse321.repairsystem.model;

import javax.persistence.Id;
import javax.persistence.Entity;


@Entity
public class AdministrativeAssistant extends Person
{
	
	public AdministrativeAssistant(String aName, int id, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem) {
		super(aName,id, aPassword, aPhone, aEmail, aRepairSystem);
		
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