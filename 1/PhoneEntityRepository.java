package kr.co.uclick.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import kr.co.uclick.entity.PhoneEntity;
import kr.co.uclick.entity.Sample;
import kr.co.uclick.entity.UserEntity;

public interface PhoneEntityRepository
		extends JpaRepository<PhoneEntity, Long>, QuerydslPredicateExecutor<PhoneEntity>, CustomRepository {	//implements는 구현한다, extends는 사용할 것이다.

	public List<PhoneEntity> findPhoneEntityByNameContaining(String name);
	public PhoneEntity findPhoneEntityByPhoneId(Long phoneId);

}
