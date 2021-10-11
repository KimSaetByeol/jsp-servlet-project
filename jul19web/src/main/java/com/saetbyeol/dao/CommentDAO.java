package com.saetbyeol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import com.saetbyeol.db.DBConnection;
import com.saetbyeol.util.Util;

public class CommentDAO {
	private CommentDAO() { }
	
	private static CommentDAO instance = new CommentDAO();
	public static CommentDAO getInstance() {
		return instance;
	}
	
	public int commentInput(HashMap<String, Object> map) {
		int result = 0;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO freecomment (fno, fccontent, fcip, no) VALUES (?, ?, ?, (SELECT no FROM login WHERE id=?))";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Util.str2Int2((String)map.get("fno")));
			pstmt.setString(2, (String) map.get("fccontent"));
			pstmt.setString(3, (String) map.get("fcip"));
			pstmt.setString(4, (String) map.get("id"));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(null, pstmt, conn);
		}
		return result;
	}
}
