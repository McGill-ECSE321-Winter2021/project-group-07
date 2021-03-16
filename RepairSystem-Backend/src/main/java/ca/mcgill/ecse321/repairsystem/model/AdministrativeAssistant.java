package ca.mcgill.ecse321.repairsystem.model;

import javax.persistence.Entity;


@Entity
public class AdministrativeAssistant extends Person
{
	
	public AdministrativeAssistant(String aName, int id, String aPassword, int aPhone, String aEmail) {
		super(aName,id, aPassword, aPhone, aEmail);
	}
	
	public AdministrativeAssistant() {
		// TODO Auto-generated constructor stub
	}

}