package ca.mcgill.ecse321.repairsystem.model;

// line 70 "model.ump"
// line 134 "model.ump"
public class Image
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Image Associations
  private Appointment appointment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Image(Appointment aAppointment)
  {
    boolean didAddAppointment = setAppointment(aAppointment);
    if (!didAddAppointment)
    {
      throw new RuntimeException("Unable to create image due to appointment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Appointment getAppointment()
  {
    return appointment;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAppointment(Appointment aAppointment)
  {
    boolean wasSet = false;
    if (aAppointment == null)
    {
      return wasSet;
    }

    Appointment existingAppointment = appointment;
    appointment = aAppointment;
    if (existingAppointment != null && !existingAppointment.equals(aAppointment))
    {
      existingAppointment.removeImage(this);
    }
    appointment.addImage(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Appointment placeholderAppointment = appointment;
    this.appointment = null;
    if(placeholderAppointment != null)
    {
      placeholderAppointment.removeImage(this);
    }
  }

}