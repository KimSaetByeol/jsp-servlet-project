package com.saetbyeol.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

public class Util {
	
	//ip 가져오기는 선생님거 보고 그대로 가져왔어요!! -샛별
	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if(ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if(ip == null) {
			ip = request.getRemoteAddr();
		}
		
		if(ip == "0:0:0:0:0:0:0:1") {
			ip = "192.168.0.1";
		}
		return ip;
	}

	//str2Int String을 Int로 바꿔주는 메소드는 간단하게 parseInt로 했습니다! -샛별
	public static int str2Int2(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}


	public static String str2Replace(String str) {
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll("/", "&#47;");
		str = str.replaceAll("\n", "<br>");
		
		return str;
	}
	
	//rs, pstmt, conn 닫아주는 메소드는 불편하시면 사용 안하셔도 됩니다 편하신대로~~~ -샛별
	public static void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null) {rs.close();}
			if (pstmt != null) {pstmt.close();}
			if (conn != null) {conn.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}