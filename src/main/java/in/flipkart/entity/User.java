package in.flipkart.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "User")
@Cache( usage = CacheConcurrencyStrategy.READ_WRITE, region="hibernateEntityCache")
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum Gender{
		M{
			@Override
			public String getDescription(){
				return "Male";
			}
		},
		F{
			@Override
			public String getDescription(){
				return "Female";
			}
		};
		
		public String getDescription(){
			return null;
		}
	}

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;

	@Column(name = "email", length = 255, unique = true, insertable = true, updatable = false)
	private String email;

	@Column(name = "password", length = 255)
	private String password;

	@Column(name = "name", length = 255)
	private String name;
	
	private Integer age;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	public User(){}
	
	public User(String email, String password, String name, Integer age, Gender gender) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		auths.add(new SimpleGrantedAuthority("ROLE_USER"));
		return auths;
	}

	@Override
	public String getUsername() {
		return email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
}
