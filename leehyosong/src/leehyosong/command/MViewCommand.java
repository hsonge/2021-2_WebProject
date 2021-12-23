package leehyosong.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leehyosong.dao.MemberDao;
import leehyosong.dto.MemberDto;

public class MViewCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		System.out.println(id);
		//1. MemberDao 객체 생성
		MemberDao dao = new MemberDao();
		//2. view 내용에 들어갈 래코드를 검색하는 메소드 호출
			MemberDto dto = dao.view(id);
			request.setAttribute("dto", dto);   //requestScope에 dto 저장
												//이는 mView.jsp에서 사용

	}

}
