package kr.co.uclick.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.uclick.controller.UserEntityController;
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
public class PhoneEntityService {
	
	private static final Logger logger = LoggerFactory.getLogger(PhoneEntityService.class);

	@Autowired	//자동 주입 기능을 사용하면 스프링이 알아서 의존 객체 bean을 찾아서 주입 
	private PhoneEntityRepository phoneEntityRepository;	//UserEntityRepository 정의

	public void delete(Long phoneId) {
		
		//userEntity.getListOfPhone().clear();
		
		//userEntityRepository.delete(userEntity);
		
		phoneEntityRepository.deleteById(phoneId);
	}
	
	
	@Transactional(readOnly = true)	//일반적으로 읽기 전용 트랜잭션이 시작된 이후 INSERT, UPDATE, DELETE 같은 쓰기 작업이 진행되면 예외가 발생
	public List<PhoneEntity> findAll() {	//전체 검색
		return phoneEntityRepository.findAll();
	}

	@Transactional(readOnly = true)
	public List<PhoneEntity> findPhoneEntityByNameContaining(String name) {	//이름으로 검색

		phoneEntityRepository.findAll(QPhoneEntity.phoneEntity.name.eq(name));

		return phoneEntityRepository.findPhoneEntityByNameContaining(name);
	}

	public void save(PhoneEntity phoneEntity) {
		phoneEntityRepository.save(phoneEntity);
	}

	
	public PhoneEntity findPhoneEntityByPhoneId(Long phoneId) {
		return phoneEntityRepository.findPhoneEntityByPhoneId(phoneId);
		
	}
	
	
	
}