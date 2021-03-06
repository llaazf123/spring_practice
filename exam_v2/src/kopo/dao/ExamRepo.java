package kopo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kopo.domain.ExamRIO;

public class ExamRepo {
	public static Connection getConnection() {	//db연결부
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://192.168.23.104:3306/kopoctc?characterEncoding=UTF-8&serverTimezone=UTC", "root", "kopo17");
		}catch(Exception e) {System.out.println(e);}
		return con;
	}
	
	public static int createDB() {	//DB생성
		int status=0;
		try {
			Connection con=getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(
					"create table examtable(name varchar(20), studentid int not null primary key, kor int, eng int, mat int) DEFAULT CHARSET=utf8;");
			stmt.close();
			con.close();
		
		}catch(Exception e) {System.out.println(e);}
		return status;	//지금 당장은 return값이 필요하지 않음
	}
	
	public static int dropDB() {	//DB삭제
		int status=0;
		try {
			Connection con=getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(
					"drop table examtable;");
			stmt.close();
			con.close();
			
		}catch(Exception e) {System.out.println(e);}
			return status;
	}
	
	public static int insert(ExamRIO u) {	//매개변수로 값을 받아 데이터를 삽입(ExamRIO에 set을 하는 역할)
		int status=0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(
					"insert into examtable(name, studentid, kor, eng, mat) values(?,?,?,?,?)");
			ps.setString(1, u.getName());
			ps.setInt(2, u.getStudentid());
			ps.setInt(3, u.getKor());
			ps.setInt(4, u.getEng());
			ps.setInt(5, u.getMat());
			status=ps.executeUpdate();
			ps.close();
			con.close();
		}catch(Exception e) {System.out.println(e);}
		return status;
	}
	
	public static int update(ExamRIO u) {	//update는 지금 당장은 사용하지 않고 있음
		int status=0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(
					"update examtable set name=?,studentid=?,kor=?,eng=?,math=? where studentid=?");
			ps.setString(1, u.getName());
			ps.setInt(2, u.getStudentid());
			ps.setInt(3, u.getKor());
			ps.setInt(4, u.getEng());
			ps.setInt(5, u.getMat());
			ps.setInt(6, u.getStudentid());
			status = ps.executeUpdate();
			ps.close();
			con.close();
		}catch(Exception e) {System.out.println(e);}
		return status;
	}
	
	public static int delete(ExamRIO u) {	//delete는 지금 당장은 사용하지 않고 있음
		int status=0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("delete from examtable where studentid=?");
			ps.setInt(1, u.getStudentid());
			status=ps.executeUpdate();
			ps.close();
			con.close();
		}catch(Exception e) {System.out.println(e);}
		
		return status;
	
	}
	
	public static List<ExamRIO> getAllRecords(){
		List<ExamRIO> list = new ArrayList<ExamRIO>();	//ExamRIO 형태의 ArrayList 생성
		try {
			Connection con =getConnection();
			PreparedStatement ps = con.prepareStatement("select * from examtable");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ExamRIO u = new ExamRIO();	//ExamRio 형태의 변수 u 생성
				u.setName(rs.getString("name"));	//각 db에 맞는 값들을 name, studentid 등등 set을 함
				u.setStudentid(rs.getInt("studentid"));
				u.setKor(rs.getInt("kor"));
				u.setEng(rs.getInt("eng"));
				u.setMat(rs.getInt("mat"));
				list.add(u);	//u 묶음으로 ArrayList에 더함
			}
			rs.close();
			ps.close();
			con.close();
		}catch(Exception e) {System.out.println(e);}
		return list;	//list를 리턴
	}
	
	public static ExamRIO getRecordById(int id) {	//getAllRecords와 동일하지만, 하나의 db만을 필요로 하기 때문에 리스트가 필요 없음
		ExamRIO u = new ExamRIO();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from examtable where studentid=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u.setName(rs.getString("name"));
				u.setStudentid(rs.getInt("studentid"));
				u.setKor(rs.getInt("kor"));
				u.setEng(rs.getInt("eng"));
				u.setMat(rs.getInt("mat"));
			}
			rs.close();
			ps.close();
			con.close();
		}catch(Exception e) {System.out.println(e);}
		return u;
	}
}
