package ca.mcgill.ecse321.repairsystem.dto;

public class AdministrativeAssistantDto extends PersonDto {
	private String aName;
	private int id;
	private String aPassword;
	private int aPhone;
	private String aEmail;
	
	public AdministrativeAssistantDto(String aName, int id, String aPassword, int aPhone, String aEmail) {
		super(aName,id, aPassword, aPhone, aEmail);
	}
	
	public AdministrativeAssistantDto() {
	}
	
	public String getName() {
		return aName;
	}
	
	public int getPhone() {
		return aPhone;
	}
	
	public String getPassword() {
		return aPassword;
	}
	
	public String getEmail() {
		return aEmail;
	}
	
}
