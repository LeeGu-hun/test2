package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DBPKG.DaoBoard;
import bean.BoardVO;
import controller.Action;
import controller.ActionForward;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ActionForward aforward = new ActionForward();
		DaoBoard daoBoard = new DaoBoard();
		BoardVO b = new BoardVO();
		
		String saveFolder = "upload";
		String realFolder = request.getSession()
				.getServletContext().getRealPath(saveFolder);
		
		int fileSize = 5*1024*1024;
		MultipartRequest multi = null;
		
		try {
			multi = new MultipartRequest(
						request,
						realFolder,
						fileSize,
						"utf-8",
						new DefaultFileRenamePolicy());
			b.setName(multi.getParameter("name"));
			b.setPass(multi.getParameter("pass"));
			b.setSubject(multi.getParameter("subject"));
			b.setContent(multi.getParameter("content"));
			b.setFiles(multi.getFilesystemName(
					(String)multi.getFileNames().nextElement()));
			boolean result = daoBoard.boardInsert(b);
			if(result == false) {
				System.out.println("등록실패");
				return null;
			}
			System.out.println("등록성공");
			aforward.setRedirect(true);
			aforward.setPath("./BoardList.bo");
			return aforward;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
