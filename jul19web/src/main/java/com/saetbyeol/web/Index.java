package com.saetbyeol.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saetbyeol.dao.BoardDAO;
import com.saetbyeol.dao.LogDAO;
import com.saetbyeol.util.Util;

@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Index() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DB호출 -> DAO -> DTO(MAP)
		// ArrayList<HashMap<String, Object>>
		// BoardDAO dao = new BoardDAO();
//		BoardDAO dao = BoardDAO.getInstance();
		
		//paging
//		System.out.println("page : " + request.getParameter("page"));
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Util.str2Int2(request.getParameter("page"));
		}
		
		// log남기기
		HashMap<String, Object> log = new HashMap<String, Object>();
		log.put("ip", Util.getIP(request));
		log.put("id", "");
		log.put("target", "index");
		log.put("etc", request.getHeader("User-Agent"));
		LogDAO.insertLog(log);

		// RD
		RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");

		ArrayList<HashMap<String, Object>> list = BoardDAO.getInstance().boardList((page - 1) * 20);
		
		request.setAttribute("dto", list);
		if(list.size() > 0) {
			request.setAttribute("totalCount", list.get(0).get("totalcount"));
		}
		//page 보내기
		request.setAttribute("page", page);
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
