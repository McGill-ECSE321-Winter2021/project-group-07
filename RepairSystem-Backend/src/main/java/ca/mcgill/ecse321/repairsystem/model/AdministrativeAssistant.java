package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;


@Entity
public class AdministrativeAssistant extends User
{

	public AdministrativeAssistant(String aName, int aId, String aPassword, int aPhone, String aEmail,
			RepairSystem aRepairSystem) {
		super(aName, aId, aPassword, aPhone, aEmail, aRepairSystem);
		// TODO Auto-generated constructor stub
	}

}