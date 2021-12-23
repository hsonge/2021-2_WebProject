package leehyosong.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leehyosong.dao.BoardDao;
import leehyosong.dto.BoardDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("BListCommand에 도달했어요");
		BoardDao dao = new BoardDao();
		
		ArrayList<BoardDto> dtos = dao.blist();
		
		request.setAttribute("dtos", dtos);

	}

}
