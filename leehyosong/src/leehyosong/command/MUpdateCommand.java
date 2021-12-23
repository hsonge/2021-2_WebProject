package leehyosong.command;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leehyosong.dao.MemberDao;
import leehyosong.dto.MemberDto;

public class MUpdateCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//1. 폼에서 입력된 데이터를 받아서 MemberDto로 만들기
		MemberDto dto = new MemberDto();
		dto.setId(request.getParameter("id"));
		dto.setPwd(request.getParameter("pwd"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		dto.setJoinDate(Date.valueOf(request.getParameter("joinDate")));
		
		//2. MemberDao 객체 생성
		MemberDao dao = new MemberDao();
		
		//3. MemeberDao 객체의 해당되는 메소드 실행(update)
		dao.update(dto);
		
	}

}
