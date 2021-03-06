package kopo.service;

import java.util.ArrayList;
import java.util.List;

import kopo.dao.ExamRepo;
import kopo.domain.ExamRIO;
import kopo.dto.ExamSIO;

public class ExamServiceImpl implements ExamService {	//exam_v2 각 각의 jsp에서 했던 것들을 따로 분리해 놓은 것.

	@Override
	public void createDB() {	//ExamRepo의 createDB 메서드를 실행
		// TODO Auto-generated method stub
		ExamRepo.createDB();
	}

	@Override
	public void dropDB() {	//ExamRepo의 dropDB 메서드를 실행
		// TODO Auto-generated method stub
		ExamRepo.dropDB();
	}

	@Override
	public void allsetDB() {	//여기서 this는 여기 클래스의 insert 메서드를 의미
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
	public ExamSIO selectOne(int id) {	
		// TODO Auto-generated method stub
		ExamRIO exam = ExamRepo.getRecordById(id);	//ExamRepo의 getRecordById 메서드를 실행
		return new ExamSIO(	//ExamRIO를 ExamSIO 형태로 변환해서 return
				exam.getName(), exam.getStudentid(), exam.getKor(), exam.getEng(), exam.getMat()
				);	
	}

	@Override
	public List<ExamSIO> selectAll() {
		// TODO Auto-generated method stub
		List<ExamRIO> exams = ExamRepo.getAllRecords();
		List<ExamSIO> examScores = new ArrayList<ExamSIO>();
		for(ExamRIO exam : exams) {	//exams 배열 수 만큼 반복
			examScores.add(new ExamSIO(	//ExamRIO를 ExamSIO 형태로 변환해서 return
					exam.getName(), exam.getStudentid(), exam.getKor(), exam.getEng(), exam.getMat()
					));
		}
		
		return examScores;
	}

	@Override
	public List<ExamSIO> selectAllByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ExamSIO examScore) {	//ExamRepo의 insert 메서드를 실행
		// TODO Auto-generated method stub
		return ExamRepo.insert(	//ExamSIO형태의 매개변수를 가져와서 ExamRIO에 넣어 준 뒤, 그 넣어준 값들을 ExamRepo의 insert 메서드를 실행
				new ExamRIO(examScore.getName(), examScore.getStudentid(),
						examScore.getKor(), examScore.getEng(),examScore.getMat())
						);
	}

	@Override
	public int update(int id, ExamSIO examScore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ExamSIO examScore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(ExamSIO examScore) {
		// TODO Auto-generated method stub
		return 0;
	}

}
