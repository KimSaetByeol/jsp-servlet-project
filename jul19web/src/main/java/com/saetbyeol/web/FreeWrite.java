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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.saetbyeol.dao.BoardDAO;
import com.saetbyeol.util.Util;

@WebServlet("/freeWrite")
public class FreeWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FreeWrite() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("./freeWrite.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		// 값 오는지 확인
		// System.out.println(request.getParameter("title"));
		// System.out.println(request.getParameter("fcontent"));

		//파일 업로드
		String savePath = request.getServletContext().getRealPath("/");
//			String savePath2 = request.getServletContext().getResource("/").getPath();
		System.out.println("디렉토리 : " + savePath);
		System.out.println("저장위치 : " + savePath + "upload/");
		savePath = savePath + "upload/";
		//진짜 파일 저장 경로
		
		int maxSize = 1024 * 1024 * 10; //10mb
		MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", 
				new DefaultFileRenamePolicy());
		
		/* 1. request,
		 * 2. 파일 저장 위치
		 * 3. 업로드 될 파일 크기
		 * 4. 인코딩 문자
		 * 5. 만약 파일 중복 처리?
		 */
		
		if ((multi.getParameter("ftitle")).length() == 0 
				|| (multi.getParameter("fcontent")).length() == 0) {
			response.sendRedirect("./freeWrite?code=4556");
			System.out.println("title or content length error");
		} else if (session.getAttribute("id") == null 
				|| session.getAttribute("name") == null) {
			response.sendRedirect("./login?code=write");
			System.out.println("session error");
		} else {
			String ftitle = multi.getParameter("ftitle");
			String fcontent = multi.getParameter("fcontent");
			
			String uploadFile = null;
			String realFile = null;
			if(multi.getOriginalFileName("file1") != null) {
				uploadFile = multi.getOriginalFileName("file1");//이름만
				realFile = multi.getFilesystemName("file1");//실제 저장
			}
			
			System.out.println(ftitle);
			System.out.println(fcontent);
			System.out.println(uploadFile);//사용자가 올릴때 이름
			System.out.println("실제 저장명 : " + realFile);
			
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			BoardDAO dao = BoardDAO.getInstance();
			
			fcontent = Util.str2Replace(fcontent);
			
			map.put("ftitle", ftitle);
			map.put("fcontent", fcontent);
			map.put("id", session.getAttribute("id"));
			map.put("fip", Util.getIP(request));
			map.put("ffilename", realFile);

			int result = dao.write(map);

			if (result == 1) {
				response.sendRedirect("./index");
			} else {
				response.sendRedirect("./error?code=daoError");
			}
		}
	}
}
