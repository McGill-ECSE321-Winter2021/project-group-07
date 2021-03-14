package ca.mcgill.ecse321.repairsystem.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.dao.ImageRepository;

public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	////////////////////SERVICE IMAGE METHODS //////////////////// 
	@Transactional
	public Image createImage(String url, Appointment a) {
		if(url == null || url.trim().length() == 0) throw new IllegalArgumentException("Image url cannot be empty!");
		if(a == null) throw new IllegalArgumentException("Image appointment cannot be empty!");

		int id = url.hashCode();
		Image i = new Image(id, url, a);
		imageRepository.save(i);
		return i;
	}

	@Transactional List<Image> getImagesByAppointment(Appointment a) {
		List<Image> i = toList(imageRepository.findByAppointment(a));
		return i;
	}

	@Transactional
	public List<Image> getAllImages() {
		return toList(imageRepository.findAll());
	}

	/* 
	 * helper method
	 */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
