package in.flipkart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

public class Settings {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@Column(name = "key", length = 255)
	private String key;
	
	@Column(name = "value", length = 255)
	private String value;

	public Settings(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public Settings(){}
	
	
}
