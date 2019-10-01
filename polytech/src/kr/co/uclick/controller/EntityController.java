package kr.co.uclick.controller;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.uclick.entity.PhoneEntity;
import kr.co.uclick.entity.Sample;
import kr.co.uclick.entity.UserEntity;
import kr.co.uclick.service.PhoneEntityService;
import kr.co.uclick.service.UserEntityService;

@Controller
public class EntityController {

	private static final Logger logger = LoggerFactory.getLogger(EntityController.class);

	@Autowired	//자동 주입 기능을 사용하면 스프링이 알아서 의존 객체 bean을 찾아서 주입 
	private UserEntityService userEntityService;

	@Autowired	
	private PhoneEntityService phoneEntityService;
	
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)	// "/"는 제일 처음 초기화면일 시 아래의 코드들을 실행한다는 의미
	public String index(@PathVariable("page") Integer page, Model model) {
		logger.info("index.jsp start");
		//model.addAttribute("list", userEntityService.findAll());
		
	      Pageable pageable = PageRequest.of(page, 10);
	      
	      //뽑혀야 할 전체 페이지버튼 수
	      long cntTmp = userEntityService.userCount();
	      double cntTmp2 = cntTmp/10.0;
	      long finalVal = (long) (Math.ceil(cntTmp2));
	      
	      //한 화면에 보여줄 첫번째 버튼과 마지막 버튼(첫째숫자,마지막숫자 ex.1과5, 6과 10..)
	      long startTmp = page/5;
	      long startRange = startTmp*5;
	      long endRange =((startTmp+1)*5)-1;
	      if (endRange > finalVal) { //맨 마지막 화면에서는 다섯개 뿌리면 안되니 마지막 숫자에서 끊어주는 작업
	         endRange = finalVal-1;
	      }
	      if(endRange<0) {
	    	  endRange=0;
	      }
	      
	      model.addAttribute("startRange", startRange);
	      model.addAttribute("endRange", endRange);
	      model.addAttribute("pageNum", finalVal);
	      model.addAttribute("users", userEntityService.findAll(pageable));
	   
		return "index";
	}
		
	@GetMapping(value = "oneView.html/{userId}")	//@RequestMapping(method = RequestMethod.GET) 의 축약형, //상세보기
	public String list(@PathVariable("userId") Long userId, UserEntity userEntity, Model model) {
		model.addAttribute("oneView", userEntityService.findUserEntityByUserId(userId));
		
		return "oneView";
	}
	
	@GetMapping(value = "newForm.html")	//유저 생성 화면
	public String newForm(Model model) {
		return "newForm";
	}
	
	@PostMapping(value = "editForm.html")	//유저 수정 화면
	public String editForm(Long userId, Model model) {
		model.addAttribute("editForm", userEntityService.findUserEntityByUserId(userId));

		return "editForm";
	}
	
	@PostMapping(value = "editForm2.html")	//휴대폰 수정 화면
	public String editForm2(Long phoneId, Model model) {
		model.addAttribute("editForm2", phoneEntityService.findPhoneEntityByPhoneId(phoneId));
		
		return "editForm2";
	}
	
	@PostMapping(value = "save.html")	//사용자 생성
	public String save(UserEntity userEntity, Model model) {
		String address = userEntity.getAddress().replace(" ", "&nbsp");
		userEntity.setAddress(address);
		userEntityService.save(userEntity);
		
		return "/0";
	}
	
	@PostMapping(value = "save2.html")	//휴대폰 생성
	public String save3(Model model, PhoneEntity phoneEntity, String carrier, String phoneNumber) {	
			UserEntity userEntityy= userEntityService.findUserEntityByUserId(phoneEntity.getUserEntity().getUserId());
			
			phoneEntity.setCarrier(carrier);
			phoneEntity.setPhoneNumber(phoneNumber);
			phoneEntity.setUserEntity(userEntityy);
			userEntityy.getListOfPhone().add(phoneEntity);
			
			userEntityService.save(userEntityy);

		return "redirect:/oneView.html/" + phoneEntity.getUserEntity().getUserId();	// Controller에서 클라이언트의 요청을 처리한 후 다른 페이지로 Redirect 하고 싶을 경우
	}
	
	@PostMapping(value = "edit.html")	//사용자 수정
	public String save4(UserEntity userEntity, Model model) {
		UserEntity userEntityy = userEntityService.findUserEntityByUserId(userEntity.getUserId());
		
		String address = userEntity.getAddress().replace(" ", "&nbsp");
		userEntity.setAddress(address);
		userEntityy.setAddress(userEntity.getAddress());
		userEntityy.setCreatedDate(userEntity.getCreatedDate());
		userEntityy.setName(userEntity.getName());
		userEntityy.setOld(userEntity.getOld());
		
		userEntityService.save(userEntityy);
		return "/0";
	}
	
	@PostMapping(value = "edit2.html")	//휴대폰  수정
	public String save2(Model model, PhoneEntity phoneEntity) {
		phoneEntityService.save(phoneEntity);

		return "redirect:/oneView.html/" + phoneEntity.getUserEntity().getUserId();	// Controller에서 클라이언트의 요청을 처리한 후 다른 페이지로 Redirect 하고 싶을 경우
	}
	
	@GetMapping(value = "delete.html")	//사용자 삭제
	public String delete(Long userId, Model model) {
		userEntityService.delete(userId);
		
		return "redirect:/0";
	}
	
	@GetMapping(value = "delete2.html")	//휴대폰 삭제
	public String delete2(Long phoneId, Model model) {
		Long userId = phoneEntityService.findPhoneEntityByPhoneId(phoneId).getUserEntity().getUserId();
		PhoneEntity phoneEntity = phoneEntityService.findPhoneEntityByPhoneId(phoneId);
		UserEntity userEntity= userEntityService.findUserEntityByUserId(userId);

//		int count = 0;
//		for(int i = 0 ;i < userEntity.getListOfPhone().size(); i++) {
//			if(userEntity.getListOfPhone().get(i).getPhoneNumber().equals(phoneEntity.getPhoneNumber())) {
//				count = i;
//			}
//		}

		userEntity.getListOfPhone().removeIf(oneofListphone -> oneofListphone.getPhoneId()==phoneEntity.getPhoneId());
		userEntityService.save(userEntity);
		
//		Long userId = phoneEntityService.findPhoneEntityByPhoneId(phoneId).getUserEntity().getUserId();
		//phoneEntityService.delete2(phoneId);
		
		return "redirect:/oneView.html/" + userId;
	}

	@GetMapping(value = "search.html")	//이름, 휴대폰번호로 검색하는 화면
	public String list(String keyword, Model model, String select) {
		model.addAttribute("keyword", keyword);
		try {
			int aa = Integer.parseInt(keyword);
			model.addAttribute("findPhoneEntityByPhoneNumber", phoneEntityService.findPhoneEntityByPhoneNumber(keyword));	//번호로만 입력할 시
		}catch(Exception e) {
			try {
				keyword = keyword.replace("-", "");	
				keyword = keyword.replace(" ", "");
				int aa = Integer.parseInt(keyword);	//번호 사이에 -또는 띄어쓰기를 입력할 시
				model.addAttribute("findPhoneEntityByPhoneNumber", phoneEntityService.findPhoneEntityByPhoneNumber(keyword));	
			}catch(Exception a) {
				model.addAttribute("findUserEntityByName", userEntityService.findUserEntityByNameContaining(keyword));	//이름으로 검색
			}
		}
		
		return "search";
	}
	
	@PostMapping(value = "phoneCheck.html")	//휴대폰 중복성 체크
	public String phoneCheck(Model model, PhoneEntity phoneEntity) {
		//logger.debug("aaaaaaaaaa" + phoneEntity.getPhoneNumber());
		model.addAttribute("phoneCheck", phoneEntityService.existsByPhoneNumber(phoneEntity.getPhoneNumber()));
		
		if(phoneEntityService.existsByPhoneNumber(phoneEntity.getPhoneNumber()) && phoneEntity.getPhoneId() != phoneEntityService.findPhoneEntityByPhoneNumber(phoneEntity.getPhoneNumber()).getPhoneId()) {

		}else {
			model.addAttribute("phoneCheck2", phoneEntity);
		}
		
		return "phoneCheck";	
	}
	
}
