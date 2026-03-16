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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import main.Student;

public class Test1_B {
	private final static SqlSessionFactory sqlMap;
	static {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader
					 ("mapper/mybatis-config.xml");
		} catch(IOException e) {
			e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(reader);
	}

	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		Map<String,Object> map = new HashMap<>();
		int cnt = (Integer)session.selectOne("student3.count");
		System.out.println("student 테이블의 레코드 건수:"+cnt);
		
		System.out.println ("* 2. 학생테이블에 등록된 레코드 모든 정보를 출력하기");
		List<Student> list = session.selectList("student3.selectall");
		for(Student s : list) 	System.out.println(s);
		
		System.out.println("* 3. 학생테이블의 등록된 레코드의 1학년 학생의 정보를 출력하기");
		map.put("grade", 1);
		list = session.selectList("student3.selectall",map);
		for(Student s : list) System.out.println(s);

		System.out.println("4. 학생테이블의 등록된 레코드의 성이 김씨인 학생의 정보");
		map.clear();
		map.put("name", "김");
		list = session.selectList("student3.selectall",map);
		for(Student s : list) System.out.println(s);
		
		System.out.println  ("5. 학생테이블의 등록된 레코드의 3학년 학생 중 주민번호 기준 여학생 정보를 출력하기");
		map.clear();
		map.put("column", "substr(jumin,7,1)");
		map.put("datas", Arrays.asList(2,4));
		map.put("grade", 3);
		list = session.selectList("student3.selectall",map);
		for(Student s : list) System.out.println(s);

	}

}
