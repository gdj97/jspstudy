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
}
