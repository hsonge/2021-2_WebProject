package leehyosong.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leehyosong.dao.BoardDao;
import leehyosong.dto.BoardDto;


public class BViewCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		System.out.println(title);
		//1. BoardDao 객체 생성
		BoardDao dao = new BoardDao();
		//2. view 내용에 들어갈 래코드를 검색하는 메소드 호출
			BoardDto dto = dao.bview(title);
			request.setAttribute("dto", dto);   //requestScope에 dto 저장
												//이는 mView.jsp에서 사용

	}

}
