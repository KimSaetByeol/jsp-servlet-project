package com.saetbyeol.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Logout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그아웃 하기 = 세션 끊기
		HttpSession session = request.getSession();
		if(session.getAttribute("id") != null) {
			session.removeAttribute("id");
		}
		if(session.getAttribute("name") != null) {
			session.removeAttribute("name");
		}
		if(session.getAttribute("grade") != null) {
			session.removeAttribute("grade");
		}
		
		response.sendRedirect("./index");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
