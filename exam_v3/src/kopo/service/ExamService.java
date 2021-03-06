package kopo.service;

import java.util.List;

import kopo.dto.ExamSIO;

public interface ExamService {	//ExamServiceImpl을 위해 존재 (interface)
	//createDB & dropDB
	void createDB();
	void dropDB();
	
	void allsetDB();
	//Read
	ExamSIO selectOne(int id);
	List<ExamSIO> selectAll();
	List<ExamSIO> selectAllByName(String name);
	//Update
	int insert(ExamSIO examScore);
	int update(int id, ExamSIO examScore);
	int update(ExamSIO examScore);
	//Delete
	int delete(int id);
	int delete(ExamSIO examScore);
}