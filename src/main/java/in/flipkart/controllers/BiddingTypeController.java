package in.flipkart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.flipkart.entity.BiddingType;
import in.flipkart.entity.BiddingType.BIDDING_TYPE;
import in.flipkart.service.BiddingTypeService;

@RequestMapping(value = "/admin/biddingType")
@Controller
public class BiddingTypeController {
	@Autowired
	private BiddingTypeService biddingTypeService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBidding(ModelMap modelmap) {
		modelmap.addAttribute("biddingTypeValues", BIDDING_TYPE.values());
		return "admin/biddingTypeAdd";
	}
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String getBidding(ModelMap modelmap) {
		BiddingType biddingType = biddingTypeService.getBiddingType();
		modelmap.addAttribute("biddingType", biddingType);
		modelmap.addAttribute("biddingTypeValues", BIDDING_TYPE.values());
		return "admin/biddingType";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addBiddingPOST(@RequestParam("biddingType") BIDDING_TYPE biddingType){
		BiddingType biddingTypeObj = new BiddingType(biddingType);
		biddingTypeService.saveOrUpdate(biddingTypeObj);
		return "redirect:/admin/biddingType";
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateBiddingPOST(
									@RequestParam("biddingId") String biddingId,
									@RequestParam("biddingType") BIDDING_TYPE biddingType){
		BiddingType biddingTypeObj = biddingTypeService.getBiddingType();
		biddingTypeObj.setBiddingType(biddingType);
		biddingTypeService.saveOrUpdate(biddingTypeObj);
		return "redirect:/admin/biddingType";
	}
}
