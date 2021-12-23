package leehyosong.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leehyosong.command.BCommand;
import leehyosong.command.BDeleteCommand;
import leehyosong.command.BInsertCommand;
import leehyosong.command.BListCommand;
import leehyosong.command.BUpdateCommand;
import leehyosong.command.BViewCommand;

@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String com = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf(".do"));
		String viewPage = "";
		
		System.out.println(com);
		
		if(com != null && com.trim().equals("blist.do")) {
			System.out.println("blist를 찾았다!");
			BCommand command = new BListCommand();
			command.execute(request, response);
			viewPage="/WEB-INF/view/BList.jsp";
			
		}else if(com != null && com.trim().equals("binsertForm.do")) {
			viewPage = "/WEB-INF/view/bInsertForm.jsp";	
			System.out.println(viewPage);
			
		}else if(com != null && com.trim().equals("binsert.do")){
			BCommand command = new BInsertCommand();
			command.execute(request, response);
			viewPage = "/WEB-INF/view/blist.do";
			
		}else if(com != null && com.trim().equals("bview.do")) {
			//1.BViewCommand 생성
			BCommand command = new BViewCommand();
			//2. execute 메소드 실행
			command.execute(request, response);
			//3. 해당되는 ViewPage 설정
			viewPage = "/WEB-INF/view/bView.jsp";
			
		}else if(com != null && com.trim().equals("bupdate.do")) {
			BCommand command = new BUpdateCommand();
			command.execute(request, response);
			viewPage = "blist.do";
			
		}else if(com != null && com.trim().equals("bdelete.do")) {
			BCommand command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "blist.do";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
