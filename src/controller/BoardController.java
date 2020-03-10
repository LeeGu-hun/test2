package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.BoardList;
import action.BoardWriteAction;

@WebServlet("*.bo")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() { }

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String cmd = requestURI.substring(contextPath.length());
		ActionForward aforward = null;
		Action action = null;
		
		if(cmd.equals("/BoardWrite.bo")) {
			aforward = new ActionForward();
			aforward.setRedirect(false);
			aforward.setPath("./board/board_write.jsp");
		} else if(cmd.equals("/BoardWriteAction.bo")) {
			action = new BoardWriteAction();
			try {
				aforward = action.execute(request, response);
			} catch (Exception e) {e.printStackTrace();}
		} else if(cmd.equals("/BoardReply.bo")) {
		} else if(cmd.equals("/BoardReplyAction.bo")) {
		} else if(cmd.equals("/BoardDetailAction.bo")) {
			action = new BoardDetailAction();
			try {
				aforward = action.execute(request, response);
			} catch (Exception e) {e.printStackTrace();}
		} else if(cmd.equals("/BoardModifyAction.bo")) {
		} else if(cmd.equals("/BoardDeleteAction.bo")) {
		} else if(cmd.equals("/BoardList.bo")) {
			action = new BoardList();
			try {
				aforward = action.execute(request, response);
			} catch (Exception e) {e.printStackTrace();}
		}
		if(aforward != null) {
			if(aforward.isRedirect()) {
				response.sendRedirect(aforward.getPath());
			} else {
				RequestDispatcher dispatcher = 
					request.getRequestDispatcher(aforward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

}
