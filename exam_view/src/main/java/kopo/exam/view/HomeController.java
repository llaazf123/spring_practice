package kopo.exam.view;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kopo.domain.ExamRIO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)	// "/"는 제일 처음 초기화면일 시 아래의 코드들을 실행한다는 의미
	public String home(Model model) {	//model 변수 정의
		logger.info("Welcome home!");	//콘솔창에 띄움
		
		//모델에 한개를 보내거나
		model.addAttribute("name", "홍필두");
		model.addAttribute("addr", "분당구"); 
		
		//모델에 클래스 구조로 보내거나
		ExamRIO exam = new ExamRIO("제니", 209901, 91, 100, 95);
		model.addAttribute("exam", exam);
		
		//모델에 클래스 구조로 보내거나
		List<ExamRIO> exams = new ArrayList<ExamRIO>();
		exams.add(new ExamRIO("나연", 209901, 91, 100, 95));
		exams.add(new ExamRIO("정연", 209902, 92, 100, 95));
		exams.add(new ExamRIO("모모", 209903, 93, 100, 95));
		exams.add(new ExamRIO("사나", 209904, 94, 100, 95));
		exams.add(new ExamRIO("지효", 209905, 95, 100, 95));
		exams.add(new ExamRIO("미나", 209906, 96, 100, 95));
		exams.add(new ExamRIO("다현", 209907, 97, 100, 95));
		exams.add(new ExamRIO("채영", 209908, 98, 100, 95));
		exams.add(new ExamRIO("쯔위", 209909, 99, 100, 95));
		model.addAttribute("exams", exams);
		
		return "home";
	}
}