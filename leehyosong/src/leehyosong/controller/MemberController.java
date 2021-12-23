package leehyosong.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leehyosong.command.MCommand;
import leehyosong.command.MDeleteCommand;
import leehyosong.command.MInsertCommand;
import leehyosong.command.MListCommand;
import leehyosong.command.MUpdateCommand;
import leehyosong.command.MViewCommand;


@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String com = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf(".do"));
		String viewPage = "";
		
		System.out.println(com);
		
		if(com != null && com.trim().equals("list")) {
			System.out.println("list를 찾았다!");
			MCommand command = new MListCommand();
			command.execute(request, response);
			viewPage="/WEB-INF/view/mList.jsp";
			
		}else if(com != null && com.trim().equals("insertForm")) {
			//System.out.println("hyosong");
			viewPage = "/WEB-INF/view/mInsertForm.jsp";	
			System.out.println(viewPage);
			
		}else if(com != null && com.trim().equals("insert")){
			MCommand command = new MInsertCommand();
			command.execute(request, response);
			viewPage = "/WEB-INF/view/list.do";
			
		}else if(com != null && com.trim().equals("view")) {
			//1.MViewCommand 생성
			MCommand command = new MViewCommand();
			//2. execute 메소드 실행
			command.execute(request, response);
			//3. 해당되는 ViewPage 설정
			viewPage = "/WEB-INF/view/mView.jsp";
			
		}else if(com != null && com.trim().equals("update")) {
			MCommand command = new MUpdateCommand();
			command.execute(request, response);
			viewPage = "list.do";
			
		}else if(com != null && com.trim().equals("delete")) {
			MCommand command = new MDeleteCommand();
			command.execute(request, response);
			viewPage = "list.do";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
