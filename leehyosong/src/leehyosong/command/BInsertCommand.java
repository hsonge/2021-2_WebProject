package leehyosong.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leehyosong.dao.BoardDao;
import leehyosong.dto.BoardDto;

public class BInsertCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//1. 입력폼에 입력되었던 정보들을 MemberDto로 만들기
		BoardDto dto = new BoardDto();
		
		dto.setTitle(request.getParameter("title"));
		dto.setContext(request.getParameter("context"));
		
		//2. DB에서 이 dto객체를 저장할 수 있도록하는 메서드(insert) 호출
		BoardDao dao = new BoardDao();
		try {
			dao.binsert(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
