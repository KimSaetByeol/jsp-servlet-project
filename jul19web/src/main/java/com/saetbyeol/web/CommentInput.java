package com.saetbyeol.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saetbyeol.dao.CommentDAO;
import com.saetbyeol.util.Util;

@WebServlet("/commentInput")
public class CommentInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CommentInput() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		System.out.println(request.getParameter("fcontent"));
		System.out.println(request.getParameter("fno"));
		
		//세션 확인, 없으면 안됨!
		if(session.getAttribute("id") != null
				&& session.getAttribute("name") != null
				&& request.getParameter("fcontent") != null
				&& request.getParameter("fno") != null
				&& (Util.str2Int2(request.getParameter("fno"))) != 0) {
			
			String fccontent = Util.str2Replace(request.getParameter("fcontent"));
			
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("fccontent", fccontent);
			map.put("fno", request.getParameter("fno"));
			map.put("fcip", Util.getIP(request));
			map.put("id", session.getAttribute("id"));
			
			CommentDAO dao = CommentDAO.getInstance(); //싱글턴
			int result = dao.commentInput(map);
			
			if(result == 1) {
				System.out.println(result + "개 comment 삽입 완료");
				response.sendRedirect("./detail?fno=" + request.getParameter("fno"));
			} else {
				System.out.println("comment 삽입 실패");
				response.sendRedirect("./error");
			}
		} else {
			System.out.println("세션 및 댓글 확인 오류");
			response.sendRedirect("./error");
		}
	}

}
