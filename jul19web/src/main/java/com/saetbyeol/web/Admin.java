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

import com.saetbyeol.dao.LogDAO;
import com.saetbyeol.util.Util;

@WebServlet("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LogDAO dao = LogDAO.getInstance();

    public Admin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 검사 해주세요. grade가 있으면 아래 문장 실행
		//없으면 "접근할 수 없습니다"로 출력하기
		//admin.jsp 디스패처로 연결해주세요.
		//log내용을 가져오기
		//1.페이징 처리 꼭 해주세요.
		int page = 1;
		ArrayList<HashMap<String, Object>> list = null;
		if(request.getParameter("page") != null) {
			page = Util.str2Int2(request.getParameter("page"));
		}
		if(request.getParameter("ip") == null 
				&& request.getParameter("target") == null) {
			list = dao.logList((page - 1) * 20);
			
		}else if(request.getParameter("ip") != "" 
				&& request.getParameter("target") != "") {
			String ip = request.getParameter("ip");
			String target = request.getParameter("target");
			list = dao.selectIpTarget(ip, target, (page - 1) * 10);
			
		}else if(request.getParameter("ip") != ""
				&& request.getParameter("target") == "") {
			String ip = request.getParameter("ip");
			list = dao.selectIP(ip, (page - 1) * 10);
			
		}else if(request.getParameter("target") != ""
				&& request.getParameter("ip") == "") {
			String target = request.getParameter("target");
			list = dao.selectTarget(target, (page - 1) * 10);
		}
		
		//ipList    SELECT DISTINCT log_ip FROM logview
		ArrayList<String> ipList = dao.list("log_ip");
		
		//targetList   SELECT DISTINCT log_target FROM logview
		ArrayList<String> targetList = dao.list("log_target");
		
		RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
		//가져갈 값은 여기
		request.setAttribute("list", list);
		request.setAttribute("ipList", ipList);
		request.setAttribute("targetList", targetList);
		request.setAttribute("ip", request.getParameter("ip"));
		request.setAttribute("target", request.getParameter("target"));
		//System.out.println(list);
		//System.out.println(targetList);
		//2.totalCount
		if(list != null && list.size() > 0) {
			request.setAttribute(
					"totalCount", list.get(0).get("totalcount"));
		}
		//3.page
		request.setAttribute("page", page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Util.str2Int2(request.getParameter("page"));
		}
		
		String searchName = request.getParameter("searchname");
		String search = request.getParameter("search");
		//System.out.println(searchName);
		//System.out.println(search);
		
		ArrayList<HashMap<String, Object>> list = 
								dao.search(searchName, search, page);
		
		RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
		request.setAttribute("list", list);
		//totalCount
		if(list != null && list.size() > 0) {
			request.setAttribute(
					"totalCount", list.get(0).get("totalcount"));
		}
		//page도 보내야 합니다.
		request.setAttribute("page", page);
		rd.forward(request, response);
	}

}
