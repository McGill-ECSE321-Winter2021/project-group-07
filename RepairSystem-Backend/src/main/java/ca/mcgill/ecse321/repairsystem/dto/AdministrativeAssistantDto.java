package ca.mcgill.ecse321.dto;

public class AdministrativeAssistantDto extends PersonDto {
	private String aName;
	private int id;
	private String aPassword;
	private int aPhone;
	private String aEmail;
	private RepairSystem aRepairSystem;
	
	public AdministrativeAssistantDto(String aName, int id, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem) {
		super(aName,id, aPassword, aPhone, aEmail, aRepairSystem);
	}
	
	public AdministrativeAssistantDto() {
	}
	
	public String getName() {
		return aName;
	}
	
	public int getId() {
		return id;
	}
	
	public String getPassword() {
		return aPassword;
	}
	
	public String getEmail() {
		return aEmail;
	}
	
	public RepairSystem getRepairSystem() {
		return aRepairSystem;
	}
}
