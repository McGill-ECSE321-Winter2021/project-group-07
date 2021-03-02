package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.AdministrativeAssistant;

//@RepositoryRestResource(collectionResourceRel = "administrativeAssistant_data", path = "administrativeAssistant_data")
public interface AdministrativeAssistantRepository extends CrudRepository<AdministrativeAssistant, String>{
	
	AdministrativeAssistant findAdministrativeAssistantById(int Id);

}