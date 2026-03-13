package main;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/*
 * IBatis : Mybatis이전에 사용하던 프레임워크
 */

public class Main1 {
	public static void main(String[] args) {
		SqlSessionFactory sqlMap = null;
		Reader reader = null;
		try {
			//mybatis-config.xml 파일을 읽기
			reader = Resources.getResourceAsReader("mapper/mybatis-config.xml");
			//sqlMap : sql구문들을 저장. id속성값으로 sql구문을 저장. id 속성값은 중복불가
			sqlMap = new SqlSessionFactoryBuilder().build(reader);
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("Mybatis 설정 성공");
		int x = 0;
		//session : mybatis에서 사용되는 연결객체(Connection 객체)
		SqlSession session = sqlMap.openSession();
		//selectOne(id속성값) : 조회되는 레코드가 한개인 경우 호출되는 메서드. 
		x = (Integer)session.selectOne("member.count");
		System.out.println("member 테이블의 레코드 갯수:" + x);
		System.out.println("=== member 테이블에 전체 레코드 조회하기 ===");
		List<Member> list = session.selectList("member.list");
		for(Member m : list) {
			System.out.println(m);
		}
		System.out.println("=== member 테이블에서 id가 admin인 레코드 조회하기 ===");
		Member mem = session.selectOne("member.selectid","admin");
		System.out.println(mem);
		System.out.println("=== member 테이블에서 이름이 테스트를 포함한 레코드 조회하기 ===");
		list = session.selectList("member.selectname","테스트");
		for(Member m : list) {
			System.out.println(m);
		}
		System.out.println("=== member 테이블에서 id가 test를 포함한 레코드 조회하기 ===");
		list = session.selectList("member.selectid2","%test%");
		for(Member m : list) {
			System.out.println(m);
		}
		System.out.println("=== member 테이블에서 이름에 테스트를 포함한 레코드 중 성별이 여자인 레코드 조회하기 ===");
		Map<String,Object> map = new HashMap<>();
		map.put("name", "테스트");
		map.put("gender", 2);
		list = session.selectList("member.selectname2",map);
		for(Member m : list) {
			System.out.println(m);
		}
		
	}
}
