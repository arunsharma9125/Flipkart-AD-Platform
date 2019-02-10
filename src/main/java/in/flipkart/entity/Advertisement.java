package in.flipkart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import in.flipkart.entity.User.Gender;

@Entity
public class Advertisement {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;

	private String message;

	@Column(name = "start_age")
	private int startAge;

	@Column(name = "end_age")
	private int endAge;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "advertiser_id")
	private Advertiser advertiser;

	private Integer bid;

	private Integer budget;

	public Advertisement() {
	}

	public Advertisement(String message, int startAge, int endAge, Gender gender, Advertiser advertiser, Integer bid,
			Integer budget) {
		super();
		this.message = message;
		this.startAge = startAge;
		this.endAge = endAge;
		this.gender = gender;
		this.advertiser = advertiser;
		this.bid = bid;
		this.budget = budget;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStartAge() {
		return startAge;
	}

	public void setStartAge(int startAge) {
		this.startAge = startAge;
	}

	public int getEndAge() {
		return endAge;
	}

	public void setEndAge(int endAge) {
		this.endAge = endAge;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Advertiser getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(Advertiser advertiser) {
		this.advertiser = advertiser;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Integer getBudget() {
		return budget;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}

}
