/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/



// line 14 "model.ump"
// line 98 "model.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String name;
  private int id;
  private String password;
  private int phone;
  private String email;

  //User Associations
  private RepairSystem repairSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aName, int aId, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem)
  {
    name = aName;
    id = aId;
    password = aPassword;
    phone = aPhone;
    email = aEmail;
    boolean didAddRepairSystem = setRepairSystem(aRepairSystem);
    if (!didAddRepairSystem)
    {
      throw new RuntimeException("Unable to create user due to repairSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhone(int aPhone)
  {
    boolean wasSet = false;
    phone = aPhone;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getId()
  {
    return id;
  }

  public String getPassword()
  {
    return password;
  }

  public int getPhone()
  {
    return phone;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template association_GetOne */
  public RepairSystem getRepairSystem()
  {
    return repairSystem;
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
      existingRepairSystem.removeUser(this);
    }
    repairSystem.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    RepairSystem placeholderRepairSystem = repairSystem;
    this.repairSystem = null;
    if(placeholderRepairSystem != null)
    {
      placeholderRepairSystem.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "id" + ":" + getId()+ "," +
            "password" + ":" + getPassword()+ "," +
            "phone" + ":" + getPhone()+ "," +
            "email" + ":" + getEmail()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "repairSystem = "+(getRepairSystem()!=null?Integer.toHexString(System.identityHashCode(getRepairSystem())):"null");
  }
}