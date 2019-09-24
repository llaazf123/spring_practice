package kr.co.uclick.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(region="UserEntity", 
usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserEntity {

	@Id	//primary key를 잡기 위해
	@TableGenerator(name = "userEntity", allocationSize=1)	//아래 GeneratedValue가 auto-increment니까 얼마씩 증가시킬지 + 자체 테이블 생성
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "userEntity")	//auto-increment(generator가 그 테이블을 참조)
	private Long userId;

	@Column(length=20)
	private String name;

	private int old;
	
	private String address;
	
	@Column(name="createdDate", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp createdDate;

	@org.hibernate.annotations.Cache(region="UserEntity.listOfPhone", usage = CacheConcurrencyStrategy.READ_WRITE)
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Collection<PhoneEntity> listOfPhone;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOld() {
		return old;
	}

	public void setOld(int old) {
		this.old = old;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	public Collection<PhoneEntity> getListOfPhone() {
		return listOfPhone;
	}
	
	public void setListOfPhone(Collection<PhoneEntity> listOfPhone) {
		this.listOfPhone = listOfPhone;
	}


}
