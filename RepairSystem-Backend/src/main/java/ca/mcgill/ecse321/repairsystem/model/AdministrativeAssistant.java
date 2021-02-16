/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/
package ca.mcgill.ecse321.repairsystem.model;


// line 38 "model.ump"
// line 113 "model.ump"
public class AdministrativeAssistant extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AdministrativeAssistant(String aName, int aId, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem)
  {
    super(aName, aId, aPassword, aPhone, aEmail, aRepairSystem);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}