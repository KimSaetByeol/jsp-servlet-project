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
import com.saetbyeol.util.Util;

@WebServlet("/detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Detail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getParameter("fno"));
		//값 찍히면 if문으로 값이 있을때와 없을때를 구분해주세요.
		//fno가 오는지, 숫자인지 검사
		if(request.getParameter("fno") != null 
				&& Util.str2Int2(request.getParameter("fno")) != 0
				){
			//올바른 숫자
			//데이터베이스에 질의 DAO
			int fno = Util.str2Int2(request.getParameter("fno"));
			BoardDAO dao = BoardDAO.getInstance();
			HashMap<String, Object> dto = dao.detail(fno);
			
			//혹시 댓글이 0보다 크다면 댓글도 불러와야 합니다.
			//댓글이 0보다 큰지 작은지 어떻게 알까요? 
			//System.out.println(dto.get("commentcount"));
			//System.out.println(((int)dto.get("commentcount")) > 0);
			if(((int)dto.get("commentcount")) > 0) {
				//System.out.println(dto.get("commentcount"));
				//있는지 확인 되었으면 서버에 질의하기 DAO
				ArrayList<HashMap<String, Object>> commentList 
							= dao.commentList(fno);
				//디스패쳐가 가져갈 수 있도록 붙이기
				request.setAttribute("commentList", commentList);
				//System.out.print(commentList);
			}
			//RD 붙여넣기 dto 여기에 담아주세요.
			RequestDispatcher rd = request.getRequestDispatcher("./freeDetail.jsp");
			request.setAttribute("dto", dto);//이름, 값
			//페이지 이동 ./detail.jsp
			rd.forward(request, response);			
		} else {
			response.sendRedirect("./index");//다시 리스트로 이동
			//우리는 이제 페이지 이동은 서블릿 = controller
			//						DB		   = model
			//						화면 jsp   = view
			//model1 model2 mvc
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
