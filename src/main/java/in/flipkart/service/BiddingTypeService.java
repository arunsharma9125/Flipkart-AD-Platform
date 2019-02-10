package in.flipkart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.flipkart.dao.BiddingTypeDao;
import in.flipkart.entity.BiddingType;

@Service
public class BiddingTypeService {
	@Autowired
	private BiddingTypeDao biddingTypeDao;

	public void saveOrUpdate(BiddingType biddingType) {
		biddingTypeDao.saveOrUpdate(biddingType);
	}

	public BiddingType getBiddingType() {
		return biddingTypeDao.getBiddingType();
	}
}
