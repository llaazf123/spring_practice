package kopo.exam.hibernate.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kopo.exam.hibernate.dao.ExamRepo;
import kopo.exam.hibernate.domain.ExamRIO;
import kopo.exam.hibernate.dto.ExamSIO;

@Service
public class ExamServiceImpl implements ExamService {	//exam_v2 각 각의 jsp에서 했던 것들을 따로 분리해 놓은 것.

	@Autowired	//자동 주입 기능을 사용하면 스프링이 알아서 의존 객체 bean을 찾아서 주입 
	private ExamRepo dao;
	
	private static final Logger logger = LoggerFactory.getLogger(ExamServiceImpl.class);
	
	@Override
	public void createDB() throws Exception {	//ExamRepo의 createDB 메서드를 실행
		// TODO Auto-generated method stub
		dao.createDB();
	}

	@Override
	public void dropDB() throws Exception {	//ExamRepo의 dropDB 메서드를 실행
		// TODO Auto-generated method stub
		dao.dropDB();
	}

	@Override
	public void allsetDB() throws Exception {	//여기서 this는 여기 클래스의 insert 메서드를 의미
		// TODO Auto-generated method stub
		this.insert(new ExamSIO("나연",209901,95,100,95));
		this.insert(new ExamSIO("정연",209902,95,95,95));
		this.insert(new ExamSIO("모모",209903,100,100,100));
		this.insert(new ExamSIO("사나",209904,100,95,90));
		this.insert(new ExamSIO("지효",209905,80,100,70));
		this.insert(new ExamSIO("미나",209906,100,100,70));
		this.insert(new ExamSIO("다연",209907,70,70,70));
		this.insert(new ExamSIO("채영",209908,100,90,80));
		this.insert(new ExamSIO("쯔위",209909,80,100,90));
	}
	
	@Override
	public void insert(ExamSIO examScore) throws Exception {	//ExamRepo의 insert 메서드를 실행 위에 allsetDB메서드 쓸 때 사용(값을 하나하나 넣음)
		// TODO Auto-generated method stub
		dao.createOne(new ExamRIO(examScore.getName(), examScore.getStudentid(), examScore.getKor(), examScore.getEng(), examScore.getMat()));
	}
	
	@Override
	public ExamSIO selectOne(int id) throws Exception {	
		// TODO Auto-generated method stub
		ExamRIO exam = null;
		try {
			exam = dao.selectOne(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ExamSIO(	//ExamRIO를 ExamSIO 형태로 변환해서 return
				exam.getName(), exam.getStudentid(), exam.getKor(), exam.getEng(), exam.getMat()
				);	
	}

	@Override
	public List<ExamSIO> selectAll() throws Exception {
		// TODO Auto-generated method stub
		List<ExamRIO> exams = null;
		try {
			exams = dao.selectAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		List<ExamSIO> examScores = new ArrayList<ExamSIO>();
		for(ExamRIO exam : exams) {	//exams 배열 수 만큼 반복
			examScores.add(new ExamSIO(	//ExamRIO를 ExamSIO 형태로 변환해서 return
					exam.getName(), exam.getStudentid(), exam.getKor(), exam.getEng(), exam.getMat()
					));
		}
//		for(int i=0;i<exams.size();i++) {
//			examScores.add(new ExamSIO(
//					exams.get(i).getName(),
//					exams.get(i).getStudentid(),
//					exams.get(i).getKor(),
//					exams.get(i).getEng(),
//					exams.get(i).getMat()
//					));
//			
//			
//		}
		return examScores;
	}

	@Override
	public List<ExamSIO> selectAllByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int update(int id, ExamSIO examScore) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ExamSIO examScore) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(ExamSIO examScore) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}