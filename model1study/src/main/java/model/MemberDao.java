package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberDao {
	public boolean insert(Member mem) { //mem : 화면 입력데이터
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into member (id, pass,name,gender,tel,email,picture) "
				+ " values (?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getPass());
			pstmt.setString(3, mem.getName());
			pstmt.setInt(4, mem.getGender());
			pstmt.setString(5, mem.getTel());
			pstmt.setString(6, mem.getEmail());
			pstmt.setString(7, mem.getPicture());
			//executeUpdate() : 변경된 레코드 갯수 리턴
			if(pstmt.executeUpdate() > 0) return true;
			else return false;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally { //정상,오류 모두 실행되는 블럭. return을 만나도 실행됨
			DBConnection.close(conn, pstmt, null);
		}
		return false;
	}

}
