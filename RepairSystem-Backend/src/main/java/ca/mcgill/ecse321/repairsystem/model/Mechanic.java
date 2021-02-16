/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;

// line 31 "model.ump"
// line 108 "model.ump"
public class Mechanic extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Mechanic Associations
  private List<TimeSlot> workHours;
  private List<Service> capabilities;
  private List<Appointment> appointments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Mechanic(String aName, int aId, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem, Service... allCapabilities)
  {
    super(aName, aId, aPassword, aPhone, aEmail, aRepairSystem);
    workHours = new ArrayList<TimeSlot>();
    capabilities = new ArrayList<Service>();
    boolean didAddCapabilities = setCapabilities(allCapabilities);
    if (!didAddCapabilities)
    {
      throw new RuntimeException("Unable to create Mechanic, must have at least 1 capabilities. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    appointments = new ArrayList<Appointment>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public TimeSlot getWorkHour(int index)
  {
    TimeSlot aWorkHour = workHours.get(index);
    return aWorkHour;
  }

  public List<TimeSlot> getWorkHours()
  {
    List<TimeSlot> newWorkHours = Collections.unmodifiableList(workHours);
    return newWorkHours;
  }

  public int numberOfWorkHours()
  {
    int number = workHours.size();
    return number;
  }

  public boolean hasWorkHours()
  {
    boolean has = workHours.size() > 0;
    return has;
  }

  public int indexOfWorkHour(TimeSlot aWorkHour)
  {
    int index = workHours.indexOf(aWorkHour);
    return index;
  }
  /* Code from template association_GetMany */
  public Service getCapability(int index)
  {
    Service aCapability = capabilities.get(index);
    return aCapability;
  }

  public List<Service> getCapabilities()
  {
    List<Service> newCapabilities = Collections.unmodifiableList(capabilities);
    return newCapabilities;
  }

  public int numberOfCapabilities()
  {
    int number = capabilities.size();
    return number;
  }

  public boolean hasCapabilities()
  {
    boolean has = capabilities.size() > 0;
    return has;
  }

  public int indexOfCapability(Service aCapability)
  {
    int index = capabilities.indexOf(aCapability);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWorkHours()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addWorkHour(TimeSlot aWorkHour)
  {
    boolean wasAdded = false;
    if (workHours.contains(aWorkHour)) { return false; }
    workHours.add(aWorkHour);
    if (aWorkHour.indexOfMechanic(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aWorkHour.addMechanic(this);
      if (!wasAdded)
      {
        workHours.remove(aWorkHour);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeWorkHour(TimeSlot aWorkHour)
  {
    boolean wasRemoved = false;
    if (!workHours.contains(aWorkHour))
    {
      return wasRemoved;
    }

    int oldIndex = workHours.indexOf(aWorkHour);
    workHours.remove(oldIndex);
    if (aWorkHour.indexOfMechanic(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aWorkHour.removeMechanic(this);
      if (!wasRemoved)
      {
        workHours.add(oldIndex,aWorkHour);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWorkHourAt(TimeSlot aWorkHour, int index)
  {  
    boolean wasAdded = false;
    if(addWorkHour(aWorkHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWorkHours()) { index = numberOfWorkHours() - 1; }
      workHours.remove(aWorkHour);
      workHours.add(index, aWorkHour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWorkHourAt(TimeSlot aWorkHour, int index)
  {
    boolean wasAdded = false;
    if(workHours.contains(aWorkHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWorkHours()) { index = numberOfWorkHours() - 1; }
      workHours.remove(aWorkHour);
      workHours.add(index, aWorkHour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWorkHourAt(aWorkHour, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfCapabilitiesValid()
  {
    boolean isValid = numberOfCapabilities() >= minimumNumberOfCapabilities();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCapabilities()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addCapability(Service aCapability)
  {
    boolean wasAdded = false;
    if (capabilities.contains(aCapability)) { return false; }
    capabilities.add(aCapability);
    if (aCapability.indexOfMechanic(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCapability.addMechanic(this);
      if (!wasAdded)
      {
        capabilities.remove(aCapability);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeCapability(Service aCapability)
  {
    boolean wasRemoved = false;
    if (!capabilities.contains(aCapability))
    {
      return wasRemoved;
    }

    if (numberOfCapabilities() <= minimumNumberOfCapabilities())
    {
      return wasRemoved;
    }

    int oldIndex = capabilities.indexOf(aCapability);
    capabilities.remove(oldIndex);
    if (aCapability.indexOfMechanic(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCapability.removeMechanic(this);
      if (!wasRemoved)
      {
        capabilities.add(oldIndex,aCapability);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setCapabilities(Service... newCapabilities)
  {
    boolean wasSet = false;
    ArrayList<Service> verifiedCapabilities = new ArrayList<Service>();
    for (Service aCapability : newCapabilities)
    {
      if (verifiedCapabilities.contains(aCapability))
      {
        continue;
      }
      verifiedCapabilities.add(aCapability);
    }

    if (verifiedCapabilities.size() != newCapabilities.length || verifiedCapabilities.size() < minimumNumberOfCapabilities())
    {
      return wasSet;
    }

    ArrayList<Service> oldCapabilities = new ArrayList<Service>(capabilities);
    capabilities.clear();
    for (Service aNewCapability : verifiedCapabilities)
    {
      capabilities.add(aNewCapability);
      if (oldCapabilities.contains(aNewCapability))
      {
        oldCapabilities.remove(aNewCapability);
      }
      else
      {
        aNewCapability.addMechanic(this);
      }
    }

    for (Service anOldCapability : oldCapabilities)
    {
      anOldCapability.removeMechanic(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCapabilityAt(Service aCapability, int index)
  {  
    boolean wasAdded = false;
    if(addCapability(aCapability))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCapabilities()) { index = numberOfCapabilities() - 1; }
      capabilities.remove(aCapability);
      capabilities.add(index, aCapability);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCapabilityAt(Service aCapability, int index)
  {
    boolean wasAdded = false;
    if(capabilities.contains(aCapability))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCapabilities()) { index = numberOfCapabilities() - 1; }
      capabilities.remove(aCapability);
      capabilities.add(index, aCapability);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCapabilityAt(aCapability, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointments.contains(aAppointment)) { return false; }
    appointments.add(aAppointment);
    if (aAppointment.indexOfMechanic(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAppointment.addMechanic(this);
      if (!wasAdded)
      {
        appointments.remove(aAppointment);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAppointment(Appointment aAppointment)
  {
    boolean wasRemoved = false;
    if (!appointments.contains(aAppointment))
    {
      return wasRemoved;
    }

    int oldIndex = appointments.indexOf(aAppointment);
    appointments.remove(oldIndex);
    if (aAppointment.indexOfMechanic(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAppointment.removeMechanic(this);
      if (!wasRemoved)
      {
        appointments.add(oldIndex,aAppointment);
      }
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
    ArrayList<TimeSlot> copyOfWorkHours = new ArrayList<TimeSlot>(workHours);
    workHours.clear();
    for(TimeSlot aWorkHour : copyOfWorkHours)
    {
      aWorkHour.removeMechanic(this);
    }
    ArrayList<Service> copyOfCapabilities = new ArrayList<Service>(capabilities);
    capabilities.clear();
    for(Service aCapability : copyOfCapabilities)
    {
      aCapability.removeMechanic(this);
    }
    ArrayList<Appointment> copyOfAppointments = new ArrayList<Appointment>(appointments);
    appointments.clear();
    for(Appointment aAppointment : copyOfAppointments)
    {
      if (aAppointment.numberOfMechanics() <= Appointment.minimumNumberOfMechanics())
      {
        aAppointment.delete();
      }
      else
      {
        aAppointment.removeMechanic(this);
      }
    }
    super.delete();
  }

}