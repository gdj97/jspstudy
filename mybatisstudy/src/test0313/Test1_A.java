package test0313;
/*
 * StudentMapper1.xml 또는 StudentMapper2.xml 이용해도 좋음 
 * 또는 StudentMapper3.xml 파일을 생성해도 됨
 * 1. 학생테이블의 등록된 레코드의 건수를 출력하기
 * 2. 학생테이블의 등록된 레코드의 정보를 출력하기
 * 3. 학생테이블의 등록된 레코드의 1학년 학생의 정보를 출력하기
 * 4. 학생테이블의 등록된 레코드의 성이 김씨인 학생의 정보를 출력하기
 * 5. 학생테이블의 등록된 레코드의 3학년 학생 중 주민번호 기준 여학생 정보를 출력하기
 */

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import main.Student;

public class Test1_A {
	private final static SqlSessionFactory sqlMap;
	static {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mapper/mybatis-config.xml");
		} catch(IOException e) {
			e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(reader);
	}

	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		int cnt = (Integer)session.selectOne("student3.count");
		System.out.println("student 테이블의 레코드 건수:"+cnt);
		System.out.println
		    ("* 2. 학생테이블에 등록된 레코드 모든 정보를 출력하기");
		List<Student> list = session.selectList("student3.list");
		for(Student s : list) 	System.out.println(s);
		System.out.println
		("* 3. 학생테이블의 등록된 레코드의 1학년 학생의 정보를 출력하기");
		list = session.selectList("student3.selectgrade",1);
		for(Student s : list) System.out.println(s);
		System.out.println
		   ("4. 학생테이블의 등록된 레코드의 성이 김씨인 학생의 정보");
		list = session.selectList("student3.selectname","김");
		for(Student s : list) System.out.println(s);
		System.out.println
   ("5. 학생테이블의 등록된 레코드의 3학년 학생 중 주민번호 기준 여학생 정보를 출력하기");
		Map<String, Object> map = new HashMap<>();
		map.put("grade", 3);
		map.put("gender1", 2);
		map.put("gender2", 4);
		list = session.selectList("student3.selectgradegender",map);
		for(Student s : list) System.out.println(s);

	}

}
