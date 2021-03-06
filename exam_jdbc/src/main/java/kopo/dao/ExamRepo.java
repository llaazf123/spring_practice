package kopo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kopo.domain.ExamRIO;


@Repository	//Repository임을 선언
public class ExamRepo {
	private static final Logger logger = LoggerFactory.getLogger(ExamRepo.class);

	@Autowired	//자동 주입 기능을 사용하면 스프링이 알아서 의존 객체 bean을 찾아서 주입 
	private JdbcTemplate jdbcTemplate; //jdbc 활용하기 위해 변수 정의

	public void createDB() throws Exception {	//DB생성, throws는 자신을 호출한 쪽으로  예외처리를 넘겨버리는 역할을 함
		logger.info("DAO--- createDB");
		String query = "create table examtable("	
				+ "name varchar(20), studentid int not null primary key,"
				+ " kor int, eng int, mat int) DEFAULT CHARSET=utf8";
		jdbcTemplate.execute(query);		
	}

	public void dropDB() throws Exception{	//DB삭제
		logger.info("DAO--- dropDB");
		String query="drop table examtable";
		jdbcTemplate.execute(query);		
	}

	public int insert(ExamRIO u) throws Exception{	//데이터 삽입
		logger.info("DAO insert");
		return jdbcTemplate.update(
				"insert into examtable(name, studentid, kor, eng, mat) values(?,?,?,?,?)"
				, u.getName(), u.getStudentid(), u.getKor(), u.getEng(), u.getMat());
	}

	public void update(ExamRIO u) throws Exception{		//데이터 업데이트
		String query="update examtable set name=?,studentid=?,kor=?,eng=?,math=? where studentid=?;";
		jdbcTemplate.update(query, u.getName(), u.getStudentid(), u.getKor(), u.getEng(), u.getMat(), u.getStudentid());
	}

	public void delete(ExamRIO u) throws Exception{		//데이터 삭제
		String query="delete from examtable where studentid=?";
		jdbcTemplate.update(query, u.getStudentid());
	}

	public List<ExamRIO> getAllRecords() throws Exception{	//전체보기
		logger.info("DAO getAllRecords");
		List<ExamRIO> results=jdbcTemplate.query(
				"select * from examtable",
				new RowMapper<ExamRIO>() {
					@Override
					public ExamRIO mapRow(ResultSet rs, int rowNum) throws SQLException{
						ExamRIO u = new ExamRIO();
						u.setName(rs.getString("name"));
						u.setStudentid(rs.getInt("studentid"));
						u.setKor(rs.getInt("kor"));
						u.setEng(rs.getInt("eng"));
						u.setMat(rs.getInt("mat"));
						
						return u;	//u 리턴한 것을 ExamRIO에 집어넣음
					}		
				});
		return results;	
	}

	public ExamRIO getRecordById(int id) throws Exception{	//한명보기
		List<ExamRIO> results=jdbcTemplate.query(
				"select * from examtable where studentid=?",
				new RowMapper<ExamRIO>() {
					@Override
					public ExamRIO mapRow(ResultSet rs, int rowNum) throws SQLException{
						ExamRIO u = new ExamRIO();
						u.setName(rs.getString("name"));
						u.setStudentid(rs.getInt("studentid"));
						u.setKor(rs.getInt("kor"));
						u.setEng(rs.getInt("eng"));
						u.setMat(rs.getInt("mat"));

						return u;
					}
				}, id);
		return results.isEmpty()? null:results.get(0);	//조건 ? true:false(false면 0번째를 가져와라)
	}
}
