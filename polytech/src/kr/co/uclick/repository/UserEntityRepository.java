package kr.co.uclick.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.persistence.QueryHint;
import kr.co.uclick.entity.PhoneEntity;
import kr.co.uclick.entity.Sample;
import kr.co.uclick.entity.UserEntity;

public interface UserEntityRepository
		extends JpaRepository<UserEntity, Long>, QuerydslPredicateExecutor<UserEntity>, CustomRepository {	//implements는 구현한다, extends는 사용할 것이다.

	@QueryHints(value= {	//Query Cache
			@QueryHint(name="org.hibernate.cacheable", value="true"),
			@QueryHint(name="org.hibernate.cacheMode", value="NORMAL"),
			@QueryHint(name="org.hibernate.cacheRegion", value="UserEntity")
	})
	public List<UserEntity> findUserEntityByNameContaining(String name);	//이름으로 UserEntity 찾기(Containing은 like, 포함됨을 의미)
	
	public UserEntity findUserEntityByUserId(Long userId);	//사용자 id로 UserEntity 찾기

}

