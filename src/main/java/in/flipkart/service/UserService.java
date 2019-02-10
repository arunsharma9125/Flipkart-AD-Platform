package in.flipkart.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.flipkart.dao.UserDao;
import in.flipkart.entity.Advertisement;
import in.flipkart.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AdvertisementService advertisementService;
	
	public void saveOrUpdate(User user) {
		userDao.saveOrUpdate(user);
	}
	
	public User getByEmail(String email){
		return userDao.getByEmail(email);
	}
	
	public List<User> listUser(){
		return userDao.listUser();
	}
	
	public String getMessage(User user){
		List<Advertisement> advertisements = advertisementService.listAdvertisement(user.getGender(), user.getAge());
		Collections.sort(advertisements, new Comparator<Advertisement>(){

			@Override
			public int compare(Advertisement advertisement1, Advertisement advertisement2) {
				return advertisement2.getBid().compareTo(advertisement1.getBid());
			}
			
		});
		return advertisements.get(0).getMessage();
	}
}
