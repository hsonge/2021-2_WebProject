package leehyosong.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leehyosong.dao.MemberDao;

public class MDeleteCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException{
		
		String id = request.getParameter("id");
		
		MemberDao dao = new MemberDao();
		dao.delete(id);
		

	}

}
