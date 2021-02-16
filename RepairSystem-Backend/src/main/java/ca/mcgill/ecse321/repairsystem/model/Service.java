/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/
package ca.mcgill.ecse321.repairsystem.model;

import java.util.*;

// line 63 "model.ump"
// line 129 "model.ump"
public class Service
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum ServiceType { CarRepair, OilChange, RegularCheckup, CarWash, TireChange, RoadsideAssistance, Towing, CarInspection, Other }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Service Attributes
  private ServiceType type;
  private int price;

  //Service Associations
  private RepairSystem repairSystem;
  private List<Mechanic> mechanics;
  private List<Appointment> appointments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Service(ServiceType aType, int aPrice, RepairSystem aRepairSystem)
  {
    type = aType;
    price = aPrice;
    boolean didAddRepairSystem = setRepairSystem(aRepairSystem);
    if (!didAddRepairSystem)
    {
      throw new RuntimeException("Unable to create service due to repairSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    mechanics = new ArrayList<Mechanic>();
    appointments = new ArrayList<Appointment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setType(ServiceType aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public ServiceType getType()
  {
    return type;
  }

  public int getPrice()
  {
    return price;
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
      existingRepairSystem.removeService(this);
    }
    repairSystem.addService(this);
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
    if (aMechanic.indexOfCapability(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMechanic.addCapability(this);
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
    if (aMechanic.indexOfCapability(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMechanic.removeCapability(this);
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
  /* Code from template association_AddManyToManyMethod */
  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointments.contains(aAppointment)) { return false; }
    appointments.add(aAppointment);
    if (aAppointment.indexOfRequireService(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAppointment.addRequireService(this);
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
    if (aAppointment.indexOfRequireService(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAppointment.removeRequireService(this);
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
    RepairSystem placeholderRepairSystem = repairSystem;
    this.repairSystem = null;
    if(placeholderRepairSystem != null)
    {
      placeholderRepairSystem.removeService(this);
    }
    ArrayList<Mechanic> copyOfMechanics = new ArrayList<Mechanic>(mechanics);
    mechanics.clear();
    for(Mechanic aMechanic : copyOfMechanics)
    {
      if (aMechanic.numberOfCapabilities() <= Mechanic.minimumNumberOfCapabilities())
      {
        aMechanic.delete();
      }
      else
      {
        aMechanic.removeCapability(this);
      }
    }
    ArrayList<Appointment> copyOfAppointments = new ArrayList<Appointment>(appointments);
    appointments.clear();
    for(Appointment aAppointment : copyOfAppointments)
    {
      if (aAppointment.numberOfRequireServices() <= Appointment.minimumNumberOfRequireServices())
      {
        aAppointment.delete();
      }
      else
      {
        aAppointment.removeRequireService(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "repairSystem = "+(getRepairSystem()!=null?Integer.toHexString(System.identityHashCode(getRepairSystem())):"null");
  }
}