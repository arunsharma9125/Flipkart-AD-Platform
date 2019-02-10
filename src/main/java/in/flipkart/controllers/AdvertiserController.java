package in.flipkart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.flipkart.entity.Advertiser;
import in.flipkart.service.AdvertiserService;

@RequestMapping("/admin/advertiser")
@Controller
public class AdvertiserController {
	@Autowired 
	private AdvertiserService advertiserService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addAdvertiser() {
		return "admin/advertiser/addAdvertiser";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAdvertiser(@RequestParam("advertiserName") String advertiserName) {
		Advertiser advertiser= new Advertiser(advertiserName);
		advertiserService.saveOrUpdate(advertiser);
		return "redirect:/admin/advertiser/list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listAdvertisers(ModelMap model){
		List<Advertiser> advertiserList= advertiserService.list();
		model.addAttribute("advertiserList", advertiserList);
		return "admin/advertiser/listAdvertiser";
	}

}
