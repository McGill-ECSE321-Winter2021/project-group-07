package ca.mcgill.ecse321.dto;

public class ImageDto {
	private Appointment appointment;
	private int imageId;
	private String url;
	
	public ImageDto(int id, String url, Appointment a) {
		  this.imageId = id;
		  this.url = url;
		  this.appointment = a;
	}
	
	public ImageDto() {
	}
	 
	public Appointment getAppointment() {
	    return appointment;
	}
	 
	public int getId() {
		return imageId;
	}
	  
	public String getUrl() {
		return url;
	}
	  
}
