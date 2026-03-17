package model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import model.mapper.BookMapper;

public class BookDao {
	private static final Class<BookMapper> cls = BookMapper.class;
	
	public boolean insert(Book book) {
		SqlSession session = DBConnection.getConnection();
		try {
		 int cnt = session.getMapper(cls).insert(book); //추가된 레코드의 건수 리턴. 1
		 return cnt > 0; //true
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return false;
	}
	public Book selectOne(int no) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).selectOne(no);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return null;
	}
	public void update(Book book) {
		SqlSession session = DBConnection.getConnection();
		try {
		 int cnt = session.getMapper(cls).update(book); //추가된 레코드의 건수 리턴. 1
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
	}
	public void delete(int no) {
		SqlSession session = DBConnection.getConnection();
		try {
		 int cnt = session.getMapper(cls).delete(no);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
	}
	 public List<Book> list() {
		SqlSession session = DBConnection.getConnection();
		 try {
			 return session.getMapper(cls).selectList();
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			 DBConnection.close(session);
		 }
		 return null;
	 }
}
