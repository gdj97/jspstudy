package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDao {
	public boolean insert(Book book) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
		 pstmt = conn.prepareStatement
				("insert into book (writer,title,content,regdate) values(?,?,?,now())");
		 pstmt.setString(1, book.getWriter());
		 pstmt.setString(2, book.getTitle());
		 pstmt.setString(3, book.getContent());
		 int cnt = pstmt.executeUpdate(); //추가된 레코드의 건수 리턴. 1
		 return cnt > 0; //true
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt, null);
		}
		return false;
	}
	public Book selectOne(int no) {
		Connection conn = DBConnection.getConnection();
		String sql = "select * from book where no=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Book b = new Book();
				b.setNo(rs.getInt("no"));
				b.setWriter(rs.getString("writer"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setRegdate(rs.getDate("regdate"));
				return b;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return null;
	}
	public void update(Book book) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
		 pstmt = conn.prepareStatement("update book set writer=?,title=?,content=? where no=?");
		 pstmt.setString(1, book.getWriter());
		 pstmt.setString(2, book.getTitle());
		 pstmt.setString(3, book.getContent());
		 pstmt.setInt(4, book.getNo());
		 int cnt = pstmt.executeUpdate(); //추가된 레코드의 건수 리턴. 1
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt, null);
		}
	}
	public void delete(int no) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
		 pstmt = conn.prepareStatement("delete from book where no=?");
		 pstmt.setInt(1,no);
		 int cnt = pstmt.executeUpdate(); //추가된 레코드의 건수 리턴. 1
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt, null);
		}
	}
}
