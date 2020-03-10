package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBPKG.DaoBoard;
import controller.Action;
import controller.ActionForward;

public class BoardList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward aforward = new ActionForward();
		DaoBoard daoBoard = new DaoBoard();
		
		int page = 1;
		int limit = 10;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		List boardList = daoBoard.getBoardList(page,limit);
		
		int listCnt = daoBoard.getListCount();
		int maxPage = (int)(Math.ceil((double)listCnt/limit));
		int startPage = (int)(Math.ceil((double)page/limit)-1)*10+1;
		int endPage = startPage+10-1;
		endPage = (maxPage<endPage)?maxPage:endPage;
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("listCnt", listCnt);
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		aforward.setRedirect(false);
		aforward.setPath("./board/board_list.jsp");
		return aforward;
	}

}
