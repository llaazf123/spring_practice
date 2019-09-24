package kr.co.uclick.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.uclick.entity.PhoneEntity;
import kr.co.uclick.entity.Sample;
import kr.co.uclick.entity.UserEntity;
import kr.co.uclick.service.PhoneEntityService;
import kr.co.uclick.service.UserEntityService;

@Controller
public class PhoneEntityController {

	private static final Logger logger = LoggerFactory.getLogger(PhoneEntityController.class);

	@Autowired	//자동 주입 기능을 사용하면 스프링이 알아서 의존 객체 bean을 찾아서 주입 
	private PhoneEntityService phoneEntityService;

	@GetMapping(value = "list2.html")	//@RequestMapping(method = RequestMethod.GET) 의 축약형, //list.html 실행 시 아래의 코드들을 실행한다는 의미

	public String list(Model model) {
		model.addAttribute("list", phoneEntityService.findAll());
		return "list2";
	}
	

	@GetMapping(value = "newForm2.html")
	public String newForm(Model model) {
		return "newForm2";
	}

	@GetMapping(value = "editForm2.html")	//Id로 값 찾아서 변경
	public String editForm(Long phoneId, Model model) {
		phoneEntityService.findPhoneEntityByPhoneId(phoneId);
		model.addAttribute("editForm", phoneEntityService.findPhoneEntityByPhoneId(phoneId));
		
		
		return "editForm2";
	}

	@PostMapping(value = "save2.html")	//update, insert
	public String save(Model model, PhoneEntity phoneEntity) {
		phoneEntityService.save(phoneEntity);
		return "redirect:list2.html";	// Controller에서 클라이언트의 요청을 처리한 후 다른 페이지로 Redirect 하고 싶을 경우
	}
	
	
	
	@GetMapping(value = "delete2.html")	//@DeleteMapping
	public String delete(Long phoneId, Model model) {
		
		phoneEntityService.delete(phoneId);
		return "redirect:list2.html";
	}

	
	@RequestMapping(value = "/", method = RequestMethod.GET)	// "/"는 제일 처음 초기화면일 시 아래의 코드들을 실행한다는 의미
	public String index() {
		logger.info("index2.jsp start");
		
		return "index2";
	}
	

	
	@GetMapping(value = "search2.html")	//이름을 통해서 검색.
	public String search(String name, PhoneEntity phoneEntity, Model model) {

		model.addAttribute("findPhoneEntityByName", phoneEntityService.findPhoneEntityByNameContaining(name));
		return "search2";
	}
	
	
	
}
