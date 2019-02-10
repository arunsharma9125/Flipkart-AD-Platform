package in.flipkart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.flipkart.dao.AdvertisementDao;
import in.flipkart.entity.Advertisement;
import in.flipkart.entity.User.Gender;

@Service
public class AdvertisementService {
	@Autowired
	private AdvertisementDao advertisementDao;

	public void saveOrUpdate(Advertisement advertisement) {
		advertisementDao.saveOrUpdate(advertisement);
	}
	
	public List<Advertisement> listAdvertisement(){
		return advertisementDao.listAdvertisement();
	}
	
	public List<Advertisement> listAdvertisement(Gender gender, Integer age){
		return advertisementDao.listAdvertisement(gender, age);
	}
	
	
}
