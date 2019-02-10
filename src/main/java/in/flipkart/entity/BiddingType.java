package in.flipkart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import in.flipkart.config.ApplicationContextProvider;
import in.flipkart.service.UserService;

@Entity
@Table(name="Bidding_Type")
public class BiddingType {

	public enum BIDDING_TYPE{
		THE_ADVERTISER_PAYS_THE_QUOTED_BID{
			@Override
			public String getDescription(){
				return "THE ADVERTISER PAYS THE QUOTED BID";
			}
			
			@Override
			public String getMessage(User user){
				UserService userService = ApplicationContextProvider.getBean(UserService.class);
				return userService.getMessage1(user);
			}
		},
		THE_HIGHEST_BID_WINS_BUT_THE_ADVERTISER_IS_CHARGED_THE_PRICE_OF_THE_SECOND_HIGHEST_BID{
			@Override
			public String getDescription(){
				return "THE HIGHEST BID WINS BUT THE ADVERTISER IS CHARGED THE PRICE OF THE SECOND HIGHEST BID";
			}
			
			@Override
			public String getMessage(User user){
				UserService userService = ApplicationContextProvider.getBean(UserService.class);
				return userService.getMessage2(user);
			}
		};
		
		public String getDescription(){
			return null;
		}
		
		public String getMessage(User user){
			return null;
		}
	};
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name="bidding_type")
	@Enumerated(EnumType.STRING)
	private BIDDING_TYPE biddingType;
	
	public BiddingType(){}
	public BiddingType(BIDDING_TYPE biddingType) {
		super();
		this.biddingType = biddingType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BIDDING_TYPE getBiddingType() {
		return biddingType;
	}

	public void setBiddingType(BIDDING_TYPE biddingType) {
		this.biddingType = biddingType;
	}
	
	
}
