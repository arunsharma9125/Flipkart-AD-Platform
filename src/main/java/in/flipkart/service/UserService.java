package in.flipkart.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.flipkart.dao.UserDao;
import in.flipkart.entity.Advertisement;
import in.flipkart.entity.BiddingType;
import in.flipkart.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AdvertisementService advertisementService;

	@Autowired
	private BiddingTypeService biddingTypeService;
	
	public void saveOrUpdate(User user) {
		userDao.saveOrUpdate(user);
	}

	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}

	public List<User> listUser() {
		return userDao.listUser();
	}

	public String getMessage1(User user) {
		List<Advertisement> advertisements = advertisementService.listAdvertisement(user.getGender(), user.getAge());
		if (advertisements.size() == 0) {
			return null;
		}
		Collections.sort(advertisements, new Comparator<Advertisement>() {

			@Override
			public int compare(Advertisement advertisement1, Advertisement advertisement2) {
				return advertisement2.getBid().compareTo(advertisement1.getBid());
			}

		});
		Advertisement advertisement = advertisements.get(0);
		int bid = advertisement.getBid();
		int budget = advertisement.getBudget();
		budget -= bid;
		advertisement.setBudget(budget);
		advertisementService.saveOrUpdate(advertisement);

		return advertisement.getMessage();
	}

	public String getMessage2(User user) {
		List<Advertisement> advertisements = advertisementService.listAdvertisement(user.getGender(), user.getAge());
		if(advertisements.size()==0){
			return null;
		}
		Collections.sort(advertisements,new Comparator<Advertisement>(){

			@Override
			public int compare(Advertisement advertisement1, Advertisement advertisement2) {
				
				return advertisement2.getBid().compareTo(advertisement1.getBid());
			}
		
		});
		if(advertisements.size() == 1){
			Advertisement advertisement = advertisements.get(0);
			int bid = advertisement.getBid();
			int budget = advertisement.getBudget();
			budget -= bid;
			advertisement.setBudget(budget);
			advertisementService.saveOrUpdate(advertisement);

			return advertisement.getMessage();
		}else{
			Advertisement advertisement1 = advertisements.get(0);
			Advertisement advertisement2 = advertisements.get(1);
			
			int bid = advertisement2.getBid();
			int budget = advertisement1.getBudget();
			budget -= bid;
			advertisement1.setBudget(budget);
			advertisementService.saveOrUpdate(advertisement1);

			return advertisement1.getMessage();
		}
	}
	
	public String getMessage(User user) {
		BiddingType biddingType = biddingTypeService.getBiddingType();
		if(biddingType == null){
			return null;
		}
		return biddingType.getBiddingType().getMessage(user);
		
	}

}
