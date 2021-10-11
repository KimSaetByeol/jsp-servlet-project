package com.saetbyeol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.saetbyeol.db.DBConnection;
import com.saetbyeol.util.Util;

public class BoardDAO {
	// 생성자 : 메소드와의 차이점은? 1.리턴 타입이 없어요!
	
	//싱글턴으로 생성해봅시다
	//1. 아무나 객체 생성 할 수 없게 일반 생성자를 private로 묶어놓는다.
	private BoardDAO() { }
	
	//2. 내가 객체 생성을 해주겠습니다. (하나만 만들기 위해 static)
	private static BoardDAO instance = new BoardDAO();
	// 인스턴스 변수는 각각 객체가 가진 고유의 변수.. 
	// 다른데서 dao라는 변수를 끌어다 쓰면 각각의  dao를 변경해도
	// 또 다른곳의 dao는 변하지 않는다. (인스턴스 변수!)
	//지역변수 <-> 멤버변수(인스턴스 변수와 비슷하긴 하다)
	//인스턴스에서 사용할 목적 - 인스턴스 변수 != static 변수(클래스 변수, 공유하는 변수)
	
	//3. 생성된 인스턴스 반환하게 하겠습니다.
	public static BoardDAO getInstance() {
		return instance;
	}
	//객체 생성 없이 불러다 쓸 수 있도록 static 처리 해줍니다.
	//static이 없으면 new BoardDAO()로 객체 생성해줘야 하죠.
	
	//전체 글 읽어오는 method()
	public ArrayList<HashMap<String, Object>> boardList(int page){
		ArrayList<HashMap<String, Object>> boardList = null;
		//DB접속
		//conn
		Connection con = DBConnection.dbConn();
		//pstmt
		PreparedStatement pstmt = null;
		//rs
		ResultSet rs = null;
		//sql ---> 테이블 새로 만들겁니다 빈칸으로 두세요.
		String sql = "SELECT * FROM freeview LIMIT ?, 20";
		//로직은 테이블명 완성 후에 짜겠습니다.
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, page);
			rs = pstmt.executeQuery();
			
			if(rs != null) {
				boardList = new ArrayList<HashMap<String,Object>>();
				while (rs.next()) {
					//저장 DTO
					HashMap<String, Object> map = 
							new HashMap<String, Object>();
					map.put("totalcount" ,rs.getInt("totalcount"));
					map.put("commentcount", rs.getInt("commentcount"));
					map.put("fno", rs.getInt("fno"));
					map.put("ftitle", rs.getString("ftitle"));
					//map.put("fcontent", rs.getString("fcontent"));
					map.put("fdate", rs.getDate("fdate"));
					map.put("fcount", rs.getInt("fcount"));
					map.put("fip", rs.getString("fip"));
					map.put("no", rs.getInt("no"));
					map.put("id", rs.getString("id"));
					map.put("name", rs.getString("name"));
					//boardList.add(dto)
					boardList.add(map);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, con);
		}
		return boardList; 
	}

	public HashMap<String, Object> detail(int fno) {
		HashMap<String, Object> dto = null;
		Connection con = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM freeview WHERE fno=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new HashMap<String, Object>();
				dto.put("commentcount", rs.getInt("commentcount"));
				dto.put("fno", rs.getInt("fno"));
				dto.put("ftitle", rs.getString("ftitle"));
				dto.put("fcontent", rs.getString("fcontent"));
				dto.put("fdate", rs.getString("fdate"));
				dto.put("fcount", rs.getInt("fcount"));
				dto.put("fip", rs.getString("fip"));
				dto.put("no", rs.getInt("no"));
				dto.put("id", rs.getString("id"));
				dto.put("name", rs.getString("name"));
				dto.put("file", rs.getString("ffilename")); //파일명 불러오기
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeAll(rs, pstmt, con);
		}
		return dto;
	}

	public ArrayList<HashMap<String, Object>> commentList(int fno) {
		ArrayList<HashMap<String, Object>> commentList = null;
		//DB접속
		//conn
		Connection con = DBConnection.dbConn();
		//pstmt
		PreparedStatement pstmt = null;
		//rs
		ResultSet rs = null;
		//sql ---> 테이블 새로 만들겁니다 빈칸으로 두세요.
		String sql = "SELECT * FROM freecommentview WHERE fno=?";
		//로직은 테이블명 완성 후에 짜겠습니다.
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fno);
			rs = pstmt.executeQuery();
			
			if(rs != null) {
				commentList = new ArrayList<HashMap<String,Object>>();
				while (rs.next()) {
					HashMap<String, Object> map = 
							new HashMap<String, Object>();
					map.put("fcno", rs.getInt("fcno"));
					map.put("fno", rs.getInt("fno"));
					map.put("fccontent", rs.getString("fccontent"));
					map.put("fcdate", rs.getDate("fcdate"));
					map.put("fclike", rs.getInt("fclike"));
					map.put("fcip", rs.getString("fcip"));
					map.put("no", rs.getInt("no"));
					map.put("id", rs.getString("id"));
					map.put("name", rs.getString("name"));
					commentList.add(map);
				}
			}
			//System.out.println(commentList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, con);
		}
		return commentList;
	}

	public int commentModify(HashMap<String, Object> dto) {
		int result = 0;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE freecomment SET fccontent=? WHERE fcno=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)dto.get("fcontent"));
			pstmt.setInt(2, Util.str2Int2((String)dto.get("fcno")));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(null, pstmt, conn);
		}
		return result;
	}

	public int write(HashMap<String, Object> map) {
		int result = 0;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO free (ftitle, fcontent, fip, no, ffilename) VALUES (?, ?, ?, (SELECT no FROM login WHERE id=?), ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) map.get("ftitle"));
			pstmt.setString(2, (String) map.get("fcontent"));
			pstmt.setString(3, (String) map.get("fip"));
			pstmt.setString(4, (String) map.get("id"));
			pstmt.setString(5, (String) map.get("ffilename"));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(null, pstmt, conn);
		}
		
		return result;
	}
	
}
