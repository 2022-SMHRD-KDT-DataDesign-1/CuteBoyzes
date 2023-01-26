package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {

	// DB연결에 필요한 라이브러리 가져오기
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	// db에 연결하기 위한 과정을 getCon으로 만듦
	public void getCon() {
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@gjaischool-b.ddns.net:1525:xe";
			String user = "campus_d_0120_5";
			String password = "smhrd5";

			// 권한이 있는지 없는지를 Connection 객체에 담기
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		}
	}
	
	public void getClose() {
		// 자원반납
		// psmt -> conn
		// select
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int join(PlayerDTO dto) {
		int row = 0;
		// 해당 db에 연결하기 위한 과정을 적어놓기
		getCon();

		try {

			// 회원가입에 필요한 SQL문 작성
			String sql = "INSERT INTO PLAYER VALUES (?, ?, ?, ? )";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getNick());
			psmt.setInt(4, dto.getScore());

			// delete, insert, update.. : 영향을 받는 행이 있음
			// ---> executeUpdate()
			row = psmt.executeUpdate();// 영향을 받는 행이 있는지 없는지 int 결과값

			// row : JDBC 코드를 통해 최종적으로 받아온 결과값

		} catch (SQLException e) {
			System.out.println("회원 가입 : SQL 전송 실패");
			e.printStackTrace();
		} finally {
			//finally를 나나탠내용
			getClose();
		}

		return row;
	}
	
	
	
	
	public boolean login(PlayerDTO dto) {
		boolean res = false;

		getCon();

		try {
			// sql문 : select
			String sql = "select pw from player where id = ?";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dto.getId());

			// select : excuteQuery()
			rs = psmt.executeQuery();

			while (rs.next()) {
				// rs.next()가 true일동안 실행시킬 코드
				// 꺼내오는 데이터의 데이터 타입(컬럼의 이름)

				if (dto.getPw().equals(rs.getString("pw"))) {
					res = true;
				} else {
					res = false;
				}

			}

		} catch (SQLException e) {
			System.out.println("SQL 전송 실패");
			e.printStackTrace();
		} finally {
			getClose();
		}

		return res;
	}

	public int result(PlayerDTO dto) {
		int row = 0;
		getCon();

		try {
			String sql = "update PLAYER set score = ? where id = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getScore());
			psmt.setString(2, dto.getId());

			// delete, insert, update.. : 영향을 받는 행이 있음
			// ---> executeUpdate()
			row = psmt.executeUpdate();// 영향을 받는 행이 있는지 없는지 int 결과값

			// row : JDBC 코드를 통해 최종적으로 받아온 결과값

		} catch (SQLException e) {
			System.out.println("회원 가입 : SQL 전송 실패");
			e.printStackTrace();
		} finally {
			getClose();
		}

		return row;

	}


}
