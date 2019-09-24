package kr.co.uclick.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.uclick.controller.EntityController;
import kr.co.uclick.entity.PhoneEntity;
import kr.co.uclick.entity.QPhoneEntity;
import kr.co.uclick.entity.QSample;
import kr.co.uclick.entity.QUserEntity;
import kr.co.uclick.entity.Sample;
import kr.co.uclick.entity.UserEntity;
import kr.co.uclick.repository.PhoneEntityRepository;
import kr.co.uclick.repository.UserEntityRepository;

@Service	//service임을 선언
@Transactional	//트랜잭션으로 묶인 쿼리 중 하나라도 실패하면 전체 쿼리를 실패한 것으로 판단하고 롤백, 모두 성공적으로 동작하면 커밋하여 DB에 실제 반영할 때 사용
public class UserEntityService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserEntityService.class);

	@Autowired	//자동 주입 기능을 사용하면 스프링이 알아서 의존 객체 bean을 찾아서 주입 
	private UserEntityRepository userEntityRepository;	//UserEntityRepository 정의

	public void delete(Long userId) {	//사용자 삭제
		
		userEntityRepository.deleteById(userId);
	}
	
	@Transactional(readOnly = true)	//일반적으로 읽기 전용 트랜잭션이 시작된 이후 INSERT, UPDATE, DELETE 같은 쓰기 작업이 진행되면 예외가 발생
	public List<UserEntity> findAll() {	//전체 검색
		return userEntityRepository.findAll();
	}
	
	@Transactional(readOnly = true)	
	public Page<UserEntity> findAll(Pageable pageable) {	//전체 검색
		return userEntityRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public List<UserEntity> findUserEntityByNameContaining(String name) {	//이름으로 UserEntity 검색
		
		return userEntityRepository.findUserEntityByNameContaining(name);
	}

	public void save(UserEntity userEntity) {	//사용자 저장
		userEntityRepository.save(userEntity);
	}

	public UserEntity findUserEntityByUserId(Long userId) {	//사용자id로 userEntity 찾기
		return userEntityRepository.findUserEntityByUserId(userId);
		
	}
	
	public long userCount() {	//전체 사용자 수 count
		return userEntityRepository.count();
		
	}
}