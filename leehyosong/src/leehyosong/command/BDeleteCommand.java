package leehyosong.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leehyosong.dao.BoardDao;

public class BDeleteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException{
		
		String title = request.getParameter("title");
		
		BoardDao dao = new BoardDao();
		dao.bdelete(title);
		

	}

}
