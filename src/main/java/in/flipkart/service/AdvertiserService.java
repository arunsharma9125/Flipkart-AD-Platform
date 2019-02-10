package in.flipkart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.flipkart.dao.AdvertiserDao;
import in.flipkart.entity.Advertiser;

@Service
public class AdvertiserService {

	@Autowired
	private AdvertiserDao advertiserDao;

	public void saveOrUpdate(Advertiser advertiser) {
		advertiserDao.saveOrUpdate(advertiser);
	}

	public List<Advertiser> list() {
		return advertiserDao.listAdvertiser();
	}
	
	public Advertiser findById(String id){
		return advertiserDao.findById(id);
	}
}
