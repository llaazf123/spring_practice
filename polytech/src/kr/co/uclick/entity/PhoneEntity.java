package kr.co.uclick.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(region="PhoneEntity", 
usage = CacheConcurrencyStrategy.READ_WRITE)
public class PhoneEntity {

	@Id
	@TableGenerator(name = "phoneEntity", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "phoneEntity")
	private Long phoneId;

	@Column(name="phoneNumber" , unique=true)
	private String phoneNumber;
	
	private String carrier;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private UserEntity userEntity;

	public Long getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	
	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
}
