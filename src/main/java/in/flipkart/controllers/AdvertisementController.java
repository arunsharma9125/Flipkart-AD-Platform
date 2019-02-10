package in.flipkart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.flipkart.entity.Advertisement;
import in.flipkart.entity.Advertiser;
import in.flipkart.entity.User.Gender;
import in.flipkart.service.AdvertisementService;
import in.flipkart.service.AdvertiserService;

@Controller
@RequestMapping("/admin/advertisement")
public class AdvertisementController {

	@Autowired
	private AdvertisementService advertisementService;
	
	@Autowired
	private AdvertiserService advertiserService;
	
	@RequestMapping(value = "/add/{advertiserId}", method = RequestMethod.GET)
	public String addAdvertisement(ModelMap model, @PathVariable("advertiserId") String advertiserId) {
		model.addAttribute("genderList", Gender.values());
		model.addAttribute("advertiserId", advertiserId);
		return "admin/advertisement/addAdvertisement";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAdvertiser(
								@RequestParam("message") String message, 
								@RequestParam("startAge") Integer startAge,
								@RequestParam("endAge") Integer endAge,
								@RequestParam("gender") Gender gender,
								@RequestParam("bid") Integer bid,
								@RequestParam("budget") Integer budget,
								@RequestParam("advertiserId") String advertiserId){
		
		Advertiser advertiser = advertiserService.findById(advertiserId);
								
		Advertisement advertisement= new Advertisement(message, startAge, endAge, gender, advertiser, bid, budget);
		advertisementService.saveOrUpdate(advertisement);
		return "redirect:/admin/advertisement/list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listAdvertisers(ModelMap model){
		List<Advertisement> advertisementList = advertisementService.listAdvertisement();
		model.addAttribute("advertisementList", advertisementList);
		return "admin/advertisement/listAdvertisement";
	}
}
