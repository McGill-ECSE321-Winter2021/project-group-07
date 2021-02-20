package ca.mcgill.ecse321.repairsystem.model;
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
public class Image
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Image Associations
  private Appointment appointment;

 
  //------------------------
  // INTERFACE
  //------------------------
 
  @ManyToOne
  public Appointment getAppointment()
  {
    return this.appointment;
  }
  
  public void setAppointment(Appointment aAppointment)
  {
    this.appointment = aAppointment;
  }
  
  
}