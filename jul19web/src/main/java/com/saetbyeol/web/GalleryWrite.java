package com.saetbyeol.web;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.saetbyeol.dao.GalleryDAO;
import com.saetbyeol.util.Util;


@WebServlet("/galleryWrite")
public class GalleryWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public GalleryWrite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("galleryWrite.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String path = request.getSession().getServletContext().getRealPath("/");
		String realPath = path + "upload/";
		int size = 1024 * 1024 * 5; //10mb
		MultipartRequest multi = new MultipartRequest(request, realPath, size, "UTF-8", 
				new DefaultFileRenamePolicy());
		
		String gtitle = multi.getParameter("gtitle");
		String gcontent = multi.getParameter("gcontent");
		String upFile = multi.getOriginalFileName("file"); //실제 업로드
		String saveFile = multi.getFilesystemName("file"); //저장된 이름
		gcontent = Util.str2Replace(gcontent);
		
		System.out.println(gtitle);
		System.out.println(gcontent);
		//System.out.println("업로드 이름 : " + upFile);
		System.out.println("저장 시 이름 : " + saveFile);
		System.out.println("경로 : " + realPath);
		
		//저장하기
		//썸네일 만들기
		String thumbnailPath = path + "thumbnail/";
		
		BufferedImage inputImg = ImageIO.read(new File(realPath + saveFile));
		
		//가로세로 크기 지정
		int width = 60;
		int height = 60;
		
		//이미지 확장자 확인
		String[] imgs = {"gif", "png", "jpg", "jpeg", "bmp"};
		
		for(String format : imgs) {
			BufferedImage outputImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D gr2d = outputImg.createGraphics();
			gr2d.drawImage(inputImg, 0, 0, width, height, null);
			
			//파일 쓰기
			File thumb = new File(thumbnailPath, saveFile);

			//그래픽스로 그린 그림을 실제로 넣어주기
			FileOutputStream fos = new FileOutputStream(thumb);
			ImageIO.write(outputImg, format, thumb);
			fos.close();
		}
		
		
		//객체 생성
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("gtitle", gtitle);
		map.put("gcontent", gcontent);
		map.put("saveFile", saveFile);
		map.put("thumbnail", saveFile); //썸네일입니다. 변경할겁니다.
		map.put("id", session.getAttribute("id"));
		
		//dao 호출
		int result = GalleryDAO.getInstance().galleryWrite(map);
		
		//페이지 이동
		if(result == 1) {
			response.sendRedirect("gallery");
		} else {
			response.sendRedirect("error");
		}
	}
}
