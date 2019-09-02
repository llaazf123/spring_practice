package kopo.exam.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity	//Entity를 통해 hibernate에게 테이블 정보임을 알림
@Table(name="examtable")	//실제 examtable과 연결

public class ExamRIO {
	
	@Id	//Id는 키 필드
//	@GeneratedValue(strategy = GenerationType.AUTO) //auto increase 필드
	@Column(name="studentid")	//column은 말그대로 컬럼
	private int studentid;
	
	@Column(name="name")
	private String name;
	
	@Column(name="kor")
	private int kor;
	
	@Column(name="eng")
	private int eng;
	
	@Column(name="mat")
	private int mat;
	
	public ExamRIO() {	//매개변수를 받지 않을 때
		super();
	}
	public ExamRIO(String name, int studentid, int kor, int eng, int mat) {	//매개변수 5개를 받을 때
		super();	
		this.name = name;
		this.studentid = studentid;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
}
