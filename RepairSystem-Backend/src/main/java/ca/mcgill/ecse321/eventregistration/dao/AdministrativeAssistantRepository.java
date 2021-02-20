package ca.mcgill.ecse321.eventregistration.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.AdministrativeAssistant;

public interface AdministrativeAssistantRepository extends CrudRepository<AdministrativeAssistant, String>{

	AdministrativeAssistant findAdministrativeAssistantById(int Id);

}