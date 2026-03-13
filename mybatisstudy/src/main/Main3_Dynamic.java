package main;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.spi.FileSystemProvider;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main3_Dynamic {
	private final static SqlSessionFactory sqlMap;
	static { //static 초기화블럭 : static 변수의 초기화담당
		InputStream input = null;
		try {
			input = Resources.getResourceAsStream("mapper/mybatis-config.xml");
		} catch(IOException e) {
			e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(input);
	}	
	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		System.out.println("=== 학생 전체 레코드 조회하기 ===");
		List<Student> list = session.selectList("student2.select1");
		for(Student s : list) System.out.println(s);
		System.out.println("=== 1년 학생 레코드 조회하기 ===");
		Map<String,Object> map = new HashMap<>();
		map.put("grade", 1);
		list = session.selectList("student2.select1",map);
		for(Student s : list) System.out.println(s);
		System.out.println("=== 972001학번인 학생의 정보 조회하기 ===");
		map.clear();
		map.put("studno", "972001");
		Student st = session.selectOne("student2.select1",map);
		System.out.println(st);
		
		System.out.println("=== 키가 180이상인 학생의 정보 조회하기 ===");
		map.clear();
		map.put("height", 180);
		list = session.selectList("student2.select1",map);
		for (Student s : list)	System.out.println(s);
/*		
		System.out.println("=== 1학년 학생 중 키가 180이상인 학생의 정보 조회하기 ===");
		map.clear();
		map.put("grade", 1);
		map.put("height", 180);
		list = session.selectList("student2.select1",map);
		for (Student s : list)	System.out.println(s);
*/
		System.out.println("=== 1학년 학생 중 키가 180이상인 학생의 정보 조회하기 ===");
		map.clear();
		map.put("grade", 1);
		map.put("height", 180);
		list = session.selectList("student2.select2",map);
		for (Student s : list)	System.out.println(s);
		System.out.println("=== 1학년 학생의  정보 조회하기 ===");
		map.clear();
		map.put("grade", 1);
		list = session.selectList("student2.select2",map);
		for (Student s : list)	System.out.println(s);
		System.out.println("=========== student2.select3로 조회하기============");
		System.out.println("전체 학생 정보 조회하기");
		list = session.selectList("student2.select3");
		for(Student s : list) System.out.println(s);
		System.out.println("1학년 학생 정보 조회하기");
		map.clear();
		map.put("grade", 1);
		list = session.selectList("student2.select3",map);
		for(Student s : list) System.out.println(s);
		System.out.println("972001 학번 학생의 정보 조회하기");
		map.clear();
		map.put("studno", "972001");
		st = session.selectOne("student2.select3",map);
		System.out.println(st);
		System.out.println("키가 180이상인 학생 정보 조회하기");
		map.clear();
		map.put("height", 180);
		list = session.selectList("student2.select3",map);
		for(Student s : list) System.out.println(s);
		System.out.println("1학년 학생 중 키가 180이상인 학생 정보 조회하기");
		map.clear();
		map.put("height", 180);
		map.put("grade", 1);
		list = session.selectList("student2.select3",map);
		for(Student s : list) System.out.println(s);
		System.out.println("1학년 학생 중 키가 180이상이고 981213 학생 정보 조회하기");
		map.clear();
		map.put("height", 180);
		map.put("grade", 1);
		map.put("studno", "981213");
		list = session.selectList("student2.select3",map);
		for(Student s : list) System.out.println(s);		
		System.out.println("=========== student2.select4로 조회하기============");
		System.out.println("101,201,301 학과에 속한 학생의 정보 조회하기");
		List<Integer> datas = Arrays.asList(101,201,301);
		map.clear();
		map.put("datas", datas);
		map.put("column", "major1");
		list = session.selectList("student2.select4",map);
		for(Student s : list) System.out.println(s);
		System.out.println("몸무게가 75,80,85인  속한 학생의 정보 조회하기");
		map.put("datas", Arrays.asList(75,80,85));
		map.put("column", "weight");
		list = session.selectList("student2.select4",map);
		for(Student s : list) System.out.println(s);
		System.out.println("전체 학생의 정보 조회하기");
		list = session.selectList("student2.select4");
		for(Student s : list) System.out.println(s);
	}
}
