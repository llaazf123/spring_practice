package kr.co.uclick.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class Sample {	//ExamRIO에 해당

	@Id
	@TableGenerator(name = "sample")	//실제 sample 테이블과 연결
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sample")	//기본키 pk설정할 때 사용
	private Long id;

	private String name;

	private int number;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
