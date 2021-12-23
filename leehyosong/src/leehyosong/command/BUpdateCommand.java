package leehyosong.command;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leehyosong.dao.BoardDao;
import leehyosong.dao.MemberDao;
import leehyosong.dto.BoardDto;
import leehyosong.dto.MemberDto;

public class BUpdateCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//1. 폼에서 입력된 데이터를 받아서 MemberDto로 만들기
		BoardDto dto = new BoardDto();
		dto.setTitle(request.getParameter("title"));
		dto.setContext(request.getParameter("context"));
		dto.setWriteDate(Date.valueOf(request.getParameter("writeDate")));
		
		//2. MemberDao 객체 생성
		BoardDao dao = new BoardDao();
		
		//3. MemeberDao 객체의 해당되는 메소드 실행(update)
		dao.bupdate(dto);
		
	}

}
