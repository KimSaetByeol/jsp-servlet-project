package com.saetbyeol.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saetbyeol.dao.LogDAO;
import com.saetbyeol.dao.LoginDAO;
import com.saetbyeol.util.Util;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("./login.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("./login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		//if 검사를 넣어주세요
		//System.out.println(id + " : " + pw);
		
		LoginDAO dao = LoginDAO.getInstance();
		//개인정보를 map에 담아오는 메소드
		HashMap<String, Object> member = dao.login(id, pw);
		
		//임시로 찍어보기 
		//System.out.println(member);//확인 되었으면 아래 작업도 해주세요.
		
		//아이디 비번이 일치한다면
		if(member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("id", member.get("id"));
			session.setAttribute("name", member.get("name"));
			int grade = (int)member.get("grade");
			if(grade == 9) {
				session.setAttribute("grade", member.get("grade"));
				System.out.println(grade + " = 9");
			} else {
				System.out.println(grade);
			}
			
			//log남기기
			HashMap<String, Object> log = new HashMap<String, Object>();
			log.put("ip", Util.getIP(request));
			log.put("id", id);
			log.put("target", "login");
			log.put("etc", request.getHeader("user-agent"));
			LogDAO.insertLog(log);
			
			response.sendRedirect("./index");
		} else {
			//아이디나 비번이 틀리다면
			response.sendRedirect("./login");
		}
		
	}
	

}
