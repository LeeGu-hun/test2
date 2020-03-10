package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBPKG.DaoBoard;
import bean.BoardVO;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ActionForward aforward = new ActionForward();
		int num = Integer.parseInt(request.getParameter("num"));
		BoardVO board = new DaoBoard().selectByNum(num);
		request.setAttribute("board", board);
		
		aforward.setRedirect(false);
		aforward.setPath("./board/board_detail.jsp");
		return aforward;
	}

}
