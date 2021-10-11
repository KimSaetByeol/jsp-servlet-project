package com.saetbyeol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.saetbyeol.db.DBConnection;
import com.saetbyeol.util.Util;

public class GalleryDAO {
	private GalleryDAO() {
	}

	private static GalleryDAO instance = new GalleryDAO();

	public static GalleryDAO getInstance() {
		return instance;
	}

	public ArrayList<HashMap<String, Object>> galleryList(int page) {
		ArrayList<HashMap<String, Object>> galleryList = null;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM galleryview LIMIT ?, 20";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page);
			rs = pstmt.executeQuery();

			if (rs != null) {
				galleryList = new ArrayList<HashMap<String,Object>>();
				
				while (rs.next()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("totalcount", rs.getInt("totalcount"));
					map.put("gno", rs.getInt("gno"));
					map.put("gtitle", rs.getString("gtitle"));
					map.put("gdate", rs.getString("gdate2"));
					map.put("gthumbnail", rs.getString("gthumbnail"));
					map.put("gcount", rs.getInt("gcount"));
					map.put("id", rs.getString("id"));
					map.put("name", rs.getString("name"));
					
					galleryList.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, conn);
		}

		return galleryList;
	}

	public int galleryWrite(HashMap<String, Object> map) {
		int result = 0;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO gallery (gtitle, gcontent, gfilename, no, gthumbnail) VALUES (?, ?, ?, (SELECT no FROM login WHERE id=?), ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) map.get("gtitle"));
			pstmt.setString(2, (String) map.get("gcontent"));
			pstmt.setString(3, (String) map.get("saveFile"));
			pstmt.setString(4, (String) map.get("id"));
			pstmt.setString(5, (String) map.get("thumbnail"));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(null, pstmt, conn);
		}
		return result;
	}

	public HashMap<String, Object> galleryDetail(int gno) {
		HashMap<String, Object> dto = null;
		Connection con = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM galleryview WHERE gno=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new HashMap<String, Object>();
				dto.put("gno", rs.getInt("gno"));
				dto.put("gtitle", rs.getString("gtitle"));
				dto.put("gcontent", rs.getString("gcontent"));
				dto.put("gdate", rs.getString("gdate"));
				dto.put("gcount", rs.getInt("gcount"));
				dto.put("gip", rs.getString("gip"));
				dto.put("no", rs.getInt("no"));
				dto.put("id", rs.getString("id"));
				dto.put("name", rs.getString("name"));
				dto.put("file", rs.getString("gfilename")); //파일명 불러오기
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeAll(rs, pstmt, con);
		}
		return dto;
	}
	
	//삭제하기 전 파일 이름 불러오기
		public ArrayList<String> findFileName(HashMap<String, Object> map) {
			ArrayList<String> filename = null;
			Connection con = DBConnection.dbConn();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT gfilename, gthumbnail FROM galleryview "
					+ "WHERE gno=? AND no=(SELECT no FROM login WHERE id=?)";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Util.str2Int2((String)map.get("gno")));
				pstmt.setString(2, (String) map.get("id"));
				rs = pstmt.executeQuery();
				if(rs.next()) {
					filename = new ArrayList<String>();

					String gfilename = rs.getString("gfilename");
					if(gfilename != null && gfilename.contains(".")) {
						filename.add(gfilename);
	//					filename = gfilename.indexOf(".") > -1 ? gfilename : null;
					} else {
						filename.add(null);
					}
					
					String gthumbnail = rs.getString("gthumbnail");
					if(gthumbnail != null && gthumbnail.contains(".")) {
						filename.add(gthumbnail);
					} else {
						filename.add(null);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Util.closeAll(rs, pstmt, con);
			}
			return filename;
		}

		public int del(HashMap<String, Object> map) {
			int result = 0;
			Connection conn = DBConnection.dbConn();
			PreparedStatement pstmt = null;
			String sql = "DELETE FROM gallery WHERE gno=? AND no=(SELECT no FROM login WHERE id=?)";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Util.str2Int2((String) map.get("gno")));
				pstmt.setString(2, (String)map.get("id"));
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Util.closeAll(null, pstmt, conn);
			}
			
			return result;
		}
}