package com.saetbyeol.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saetbyeol.dao.BoardDAO;
import com.saetbyeol.util.Util;

@WebServlet("/commentModify")
public class CommentModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CommentModify() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
//		System.out.println(request.getParameter("fcontent"));
//		System.out.println(request.getParameter("fcno"));
//		System.out.println(request.getParameter("fno"));
		
		String fccontent = Util.str2Replace(request.getParameter("fcontent"));
		
		HashMap<String, Object> dto = new HashMap<String, Object>();
		dto.put("fcontent", fccontent);
		dto.put("fno", request.getParameter("fno"));
		dto.put("fcno", request.getParameter("fcno"));
		
		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.commentModify(dto);
		
		if(result != 0) {
			response.sendRedirect("./detail?fno="+request.getParameter("fno"));
		} else {
			response.sendRedirect("./error");
		}
	}

}
