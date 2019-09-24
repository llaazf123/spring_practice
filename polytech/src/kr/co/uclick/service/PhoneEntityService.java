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
public class PhoneEntityService {
	
	private static final Logger logger = LoggerFactory.getLogger(PhoneEntityService.class);

	@Autowired	//자동 주입 기능을 사용하면 스프링이 알아서 의존 객체 bean을 찾아서 주입 
	private PhoneEntityRepository phoneEntityRepository;	//PhoneEntityRepository 정의

	@Transactional(readOnly = true)
	public PhoneEntity findPhoneEntityByPhoneNumber(String phoneNumber) {	//휴대폰번호로 PhoneEntity 찾기

		return phoneEntityRepository.findPhoneEntityByPhoneNumber(phoneNumber);
	}
	
//	public void delete2(Long phoneId) {	//휴대폰 삭제
//		
//		phoneEntityRepository.findPhoneEntityByPhoneId(phoneId).getUserEntity().setListOfPhone(null);
//		
//		phoneEntityRepository.deleteById(phoneId);
//		
//	}
	
	public void save(PhoneEntity phoneEntity) {	//휴대폰 저장
		phoneEntityRepository.save(phoneEntity);
	}

	public PhoneEntity findPhoneEntityByPhoneId(Long phoneId) {	//휴대폰id로 PhoneEntity 찾기
		return phoneEntityRepository.findPhoneEntityByPhoneId(phoneId);
		
	}
	
	public boolean existsByPhoneNumber(String phoneNumber) {	//휴대폰번호 중복성 체크
		return phoneEntityRepository.existsByPhoneNumber(phoneNumber);
		
	}
	


	
}