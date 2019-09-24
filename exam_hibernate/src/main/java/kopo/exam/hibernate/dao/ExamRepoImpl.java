package kopo.exam.hibernate.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kopo.exam.hibernate.domain.ExamRIO;

@Transactional	//트랜잭션으로 묶인 쿼리 중 하나라도 실패하면 전체 쿼리를 실패한 것으로 판단하고 롤백, 모두 성공적으로 동작하면 커밋하여 DB에 실제 반영할 때 사용

@Repository	//Repository임을 선언
public class ExamRepoImpl implements ExamRepo {
	
	@Autowired	//자동 주입 기능을 사용하면 스프링이 알아서 의존 객체 bean을 찾아서 주입 
	private SessionFactory sessionFactory;	//하이버네이트는 SessionFactory에서 Session을 생성해서 (jdbcTemplate과 유사)데이타베이스에 대한 명령을 처리
	
	private static final Logger logger = LoggerFactory.getLogger(ExamRepoImpl.class);
	
	private Session getSession() {	//getSession함수로 DB통신세션을 가져오는데 아마 세션에 대한 관리는 하이버네이트가 담당
//		return sessionFactory.openSession();
		logger.info("getSession().start");
		Session ss = null;
		try {
			ss = sessionFactory.getCurrentSession();	//세션이 존재하면 get으로 가져옴
			
		}catch(org.hibernate.HibernateException he) {
			ss = sessionFactory.openSession();	//세션이 없으면 session을 open함
		}
		return ss;
	}

	@Override
	public Long count() {		//갯수를 셀 때 사용
		// TODO Auto-generated method stub
		logger.info("count().start");
		String hql = "SELECT COUNT(*) FROM ExamRIO"; //Hibernate Query
		Query query = getSession().createQuery(hql);
		Long totalCount = (Long) query.uniqueResult();
		return totalCount;
	}

	@Override
	public ExamRIO selectOne(long id) {		//한명 보기
		// TODO Auto-generated method stub
		String hql = "FROM ExamRIO e WHERE e.studentid = " + id;
		Query query = getSession().createQuery(hql);
		return (ExamRIO) query.uniqueResult();	//ExamRIO의 형태로 메서드가 선언되어 있음
		
//		return (User) query.list()get(0);
//		return (users) getSession().get(Users.class, id);
	}

	@SuppressWarnings("unchecked")	//컴파일 경고를 @SuppressWarnings(“unchecked”) 어노테이션(annotation)을 사용해 억제
	@Override
	public List<ExamRIO> selectAll() {	//전체 보기
		// TODO Auto-generated method stub
		String hql = "FROM ExamRIO";
		Query query = getSession().createQuery(hql);
		return query.list();	//query를 list형태로 return
		
//		return getSession().createCriteria(ExamRIO.class).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExamRIO> selectAllByPagination(int page, int itemSizePerPage) {	//페이지네이션
		// TODO Auto-generated method stub
		String hql = "FROM ExamRIO ORDER BY studentid";
		Query query = getSession().createQuery(hql);
		query.setFirstResult((page - 1) * itemSizePerPage);
		query.setMaxResults(itemSizePerPage);
		return query.list();
	}

	@Override
	public int createOne(ExamRIO exam) {	//값을 하나하나 넣을 때
		// TODO Auto-generated method stub
		return (int) getSession().save(exam);
		
//		session.flush();
//		session.close();
	}

	@Override
	public void updateOne(ExamRIO exam) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(exam);
	}

	@Override
	public void deleteOne(ExamRIO exam) {
		// TODO Auto-generated method stub
		getSession().delete(exam);
	}

	@Override
	public int deleteAll() {	//데이터 삭제
		// TODO Auto-generated method stub
		String hql = "DELETE FROM ExamRIO";
		Query query = getSession().createQuery(hql);
		return query.executeUpdate();
	}

	@Override
	public void createDB() throws Exception {	//테이블 생성
		// TODO Auto-generated method stub
		// 하이버네이트 세션상태에서 테이블을 생성하고 지우고하는 DDL-sql을 적용할수 없는듯.
		// 하이버네이트 세션에서 커넥션을 얻어다 sql을 날림
		// 세션 내 커넥션이 있는 것이라 커넥션을 close하면 안될듯.
		// 이런식으로 쓴다면 sql도 하이버네이트에서 쓰는 것이 가능.
		
		Statement stmt;
		
			stmt = ((SessionImpl) getSession()).connection().createStatement();
			stmt.execute("create table examtable(name varchar(20), studentid int not null primary key,"
			+ " kor int, eng int, mat int) DEFAULT CHARSET=utf8;");
			stmt.close();
		
	}

	@Override
	public void dropDB() throws Exception {	//테이블 삭제
		// TODO Auto-generated method stub
		//createDB와 같은 방법으로 한다.
		Statement stmt;
	
			stmt = ((SessionImpl) getSession()).connection().createStatement();
			stmt.execute("drop table examtable;");
			stmt.close();
		
	}
}
	
	


