package leehyosong.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leehyosong.dao.MemberDao;
import leehyosong.dto.MemberDto;

public class MListCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("MListCommand에 도달했어요");
		MemberDao dao = new MemberDao();
		
		ArrayList<MemberDto> dtos = dao.list();
		
		request.setAttribute("dtos", dtos);
		
		
		//1. DB에 목록을 반환하는 메소드를 호출하여 실행
		
		//2. 반환받은 목록을 requestScope에 저장

	}

}
