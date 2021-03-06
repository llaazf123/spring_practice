package kopo.service;

import java.util.List;

import kopo.dto.ExamSIO;

public interface ExamService {	//ExamServiceImpl을 위해 존재 (interface)
	//createDB & dropDB
	public void createDB() throws Exception;
	public void dropDB() throws Exception;
	public void allsetDB() throws Exception;
	
	//Create
	public int insert(ExamSIO examScore) throws Exception;

	//Read
	public ExamSIO selectOne(int id) throws Exception;
	public List<ExamSIO> selectAll() throws Exception;
	public List<ExamSIO> selectAllByName(String name) throws Exception;
	
	//Update
	public int update(int id, ExamSIO examScore) throws Exception;
	public int update(ExamSIO examScore) throws Exception;
	
	//Delete
	public int delete(int id) throws Exception;
	public int delete(ExamSIO examScore) throws Exception;
}