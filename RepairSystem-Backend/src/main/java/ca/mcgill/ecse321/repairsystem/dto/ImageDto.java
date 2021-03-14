package ca.mcgill.ecse321.dto;

public class ImageDto {
	private Appointment appointment;

	public Image(int id, String url, Appointment a) {
		  this.imageId = id;
		  this.url = url;
		  this.appointment = a;
	  }
	
	public Image() {
	}
	 
	  @ManyToOne
	  public Appointment getAppointment()
	  {
	    return this.appointment;
	  }
	  
	  public void setAppointment(Appointment aAppointment)
	  {
	    this.appointment = aAppointment;
	  }
	  
	  private int imageId;
	  
	  @Id
	  public int getId() {
		  return this.imageId;
	  }
	  
	  public void setId(int aId) {
		  this.imageId = aId;
	  }
	  
	  private String url;
	  
	  public String getUrl() {
		  return this.url;
	  }
	  
	  public void setUrl(String newUrl) {
		  this.url = newUrl;
	  }
	  

}
