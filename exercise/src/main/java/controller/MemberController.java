package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.*;

@WebServlet("*.do")
public class MemberController extends HttpServlet{
	Service service = null;
	ServiceForward forward = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		req.setCharacterEncoding("utf-8");
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1, requestURI.length() - 3);
		
		RequestDispatcher dispatcher = null;
		
		switch (command.toLowerCase()) {
		case "viewmemberlist":
			service = new viewMemberList();
			forward = service.execute(req, res);
			break;
		
		case "insertmember":
			service = new InsertMember();
			forward = service.execute(req, res);
			break;
			
		case "insertmemberform":
			forward = new ServiceForward();
			forward.setPath("/member/member_insert.jsp");
			break;
			
		case "memberdetail":
			service = new viewMemeberDetail();
			forward = service.execute(req, res);
			break;
			
		case "membermodifyform":
			service = new viewMemeberDetailForModify();
			forward = service.execute(req, res);
			break;
			
		case "membermodify":
			service = new UpdateMember();
			forward = service.execute(req, res);
			break;
			
		case "memberdeleteform":
			service = new viewMemberForDelete();
			forward = service.execute(req, res);
			break;
			
		case "memberdelete":
			service = new DeleteMember();
			forward = service.execute(req, res);
			break;
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				res.sendRedirect(forward.getPath());
			} else {
				dispatcher = req.getRequestDispatcher(forward.getPath());
				dispatcher.forward(req, res);
			}
		}
	}
}
