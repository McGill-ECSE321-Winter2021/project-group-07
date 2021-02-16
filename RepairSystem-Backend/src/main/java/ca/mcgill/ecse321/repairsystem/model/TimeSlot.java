/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Time;
import java.util.*;

// line 57 "model.ump"
// line 124 "model.ump"
public class TimeSlot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TimeSlot Attributes
  private Time startTime;
  private Time endTime;

  //TimeSlot Associations
  private RepairSystem repairSystem;
  private List<Mechanic> mechanics;
  private List<Appointment> appointments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TimeSlot(Time aStartTime, Time aEndTime, RepairSystem aRepairSystem)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    boolean didAddRepairSystem = setRepairSystem(aRepairSystem);
    if (!didAddRepairSystem)
    {
      throw new RuntimeException("Unable to create timeSlot due to repairSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    mechanics = new ArrayList<Mechanic>();
    appointments = new ArrayList<Appointment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }
  /* Code from template association_GetOne */
  public RepairSystem getRepairSystem()
  {
    return repairSystem;
  }
  /* Code from template association_GetMany */
  public Mechanic getMechanic(int index)
  {
    Mechanic aMechanic = mechanics.get(index);
    return aMechanic;
  }

  public List<Mechanic> getMechanics()
  {
    List<Mechanic> newMechanics = Collections.unmodifiableList(mechanics);
    return newMechanics;
  }

  public int numberOfMechanics()
  {
    int number = mechanics.size();
    return number;
  }

  public boolean hasMechanics()
  {
    boolean has = mechanics.size() > 0;
    return has;
  }

  public int indexOfMechanic(Mechanic aMechanic)
  {
    int index = mechanics.indexOf(aMechanic);
    return index;
  }
  /* Code from template association_GetMany */
  public Appointment getAppointment(int index)
  {
    Appointment aAppointment = appointments.get(index);
    return aAppointment;
  }

  public List<Appointment> getAppointments()
  {
    List<Appointment> newAppointments = Collections.unmodifiableList(appointments);
    return newAppointments;
  }

  public int numberOfAppointments()
  {
    int number = appointments.size();
    return number;
  }

  public boolean hasAppointments()
  {
    boolean has = appointments.size() > 0;
    return has;
  }

  public int indexOfAppointment(Appointment aAppointment)
  {
    int index = appointments.indexOf(aAppointment);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setRepairSystem(RepairSystem aRepairSystem)
  {
    boolean wasSet = false;
    if (aRepairSystem == null)
    {
      return wasSet;
    }

    RepairSystem existingRepairSystem = repairSystem;
    repairSystem = aRepairSystem;
    if (existingRepairSystem != null && !existingRepairSystem.equals(aRepairSystem))
    {
      existingRepairSystem.removeTimeSlot(this);
    }
    repairSystem.addTimeSlot(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMechanics()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addMechanic(Mechanic aMechanic)
  {
    boolean wasAdded = false;
    if (mechanics.contains(aMechanic)) { return false; }
    mechanics.add(aMechanic);
    if (aMechanic.indexOfWorkHour(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMechanic.addWorkHour(this);
      if (!wasAdded)
      {
        mechanics.remove(aMechanic);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeMechanic(Mechanic aMechanic)
  {
    boolean wasRemoved = false;
    if (!mechanics.contains(aMechanic))
    {
      return wasRemoved;
    }

    int oldIndex = mechanics.indexOf(aMechanic);
    mechanics.remove(oldIndex);
    if (aMechanic.indexOfWorkHour(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMechanic.removeWorkHour(this);
      if (!wasRemoved)
      {
        mechanics.add(oldIndex,aMechanic);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMechanicAt(Mechanic aMechanic, int index)
  {  
    boolean wasAdded = false;
    if(addMechanic(aMechanic))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMechanics()) { index = numberOfMechanics() - 1; }
      mechanics.remove(aMechanic);
      mechanics.add(index, aMechanic);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMechanicAt(Mechanic aMechanic, int index)
  {
    boolean wasAdded = false;
    if(mechanics.contains(aMechanic))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMechanics()) { index = numberOfMechanics() - 1; }
      mechanics.remove(aMechanic);
      mechanics.add(index, aMechanic);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMechanicAt(aMechanic, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Appointment addAppointment(String aNote, Appointment.AppointmentStatus aStatus, Customer aCustomer, RepairSystem aRepairSystem, Car aCar, Mechanic[] allMechanics, Service[] allRequireServices)
  {
    return new Appointment(aNote, aStatus, this, aCustomer, aRepairSystem, aCar, allMechanics, allRequireServices);
  }

  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointments.contains(aAppointment)) { return false; }
    TimeSlot existingAppointmentTime = aAppointment.getAppointmentTime();
    boolean isNewAppointmentTime = existingAppointmentTime != null && !this.equals(existingAppointmentTime);
    if (isNewAppointmentTime)
    {
      aAppointment.setAppointmentTime(this);
    }
    else
    {
      appointments.add(aAppointment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAppointment(Appointment aAppointment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAppointment, as it must always have a appointmentTime
    if (!this.equals(aAppointment.getAppointmentTime()))
    {
      appointments.remove(aAppointment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAppointmentAt(Appointment aAppointment, int index)
  {  
    boolean wasAdded = false;
    if(addAppointment(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAppointmentAt(Appointment aAppointment, int index)
  {
    boolean wasAdded = false;
    if(appointments.contains(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAppointmentAt(aAppointment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    RepairSystem placeholderRepairSystem = repairSystem;
    this.repairSystem = null;
    if(placeholderRepairSystem != null)
    {
      placeholderRepairSystem.removeTimeSlot(this);
    }
    ArrayList<Mechanic> copyOfMechanics = new ArrayList<Mechanic>(mechanics);
    mechanics.clear();
    for(Mechanic aMechanic : copyOfMechanics)
    {
      aMechanic.removeWorkHour(this);
    }
    for(int i=appointments.size(); i > 0; i--)
    {
      Appointment aAppointment = appointments.get(i - 1);
      aAppointment.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "repairSystem = "+(getRepairSystem()!=null?Integer.toHexString(System.identityHashCode(getRepairSystem())):"null");
  }
}