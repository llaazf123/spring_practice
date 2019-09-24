package kr.co.uclick.repository;

import java.util.Collection;
import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import kr.co.uclick.entity.PhoneEntity;
import kr.co.uclick.entity.Sample;
import kr.co.uclick.entity.UserEntity;

public interface PhoneEntityRepository
		extends JpaRepository<PhoneEntity, Long>, QuerydslPredicateExecutor<PhoneEntity>, CustomRepository {	//implements는 구현한다, extends는 사용할 것이다.
	
	@QueryHints(value= {	//Query Cache
			@QueryHint(name="org.hibernate.cacheable", value="true"),
			@QueryHint(name="org.hibernate.cacheMode", value="NORMAL"),
			@QueryHint(name="org.hibernate.cacheRegion", value="PhoneEntity")
	})
	public PhoneEntity findPhoneEntityByPhoneNumber(String phoneNumber);	//휴대폰 번호로 PhoneEntity 찾기
	
	public PhoneEntity findPhoneEntityByPhoneId(Long phoneId);	//휴대폰 id로 phoneEntity 찾기
	
	public boolean existsByPhoneNumber(String phoneNumber);	//휴대폰 번호 중복성 체크
}

