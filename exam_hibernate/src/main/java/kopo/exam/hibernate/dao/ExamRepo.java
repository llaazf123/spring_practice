package kopo.exam.hibernate.dao;

import java.util.List;

import kopo.exam.hibernate.domain.ExamRIO;

public interface ExamRepo {
	Long count();
	ExamRIO selectOne(long id);
	List<ExamRIO> selectAll();
	List<ExamRIO> selectAllByPagination(int page, int itemSizePerPage);
	int createOne(ExamRIO exam);
	void updateOne(ExamRIO exam);
	void deleteOne(ExamRIO exam);
	int deleteAll();
	void createDB() throws Exception;
	void dropDB() throws Exception;
}