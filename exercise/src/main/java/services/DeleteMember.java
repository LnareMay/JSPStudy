package services;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBM.DBM;
import DBM.DBMUtil;

public class DeleteMember implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		ServiceForward forward = null;
		Connection conn = null;
		
		boolean isRight = false;
		boolean isDeleteSuccess = false;
		String msg = null;
		
		String pw = req.getParameter("pw");
		String id = req.getParameter("id");
		
		conn = DBMUtil.getConnection();
		isRight = DBM.checkIdPw(conn, id, pw);
		if(isRight) {
			isDeleteSuccess = DBM.delete(conn, id, pw);
			
			if(isDeleteSuccess) {
				try {				
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				forward = new ServiceForward();
				forward.setRedirect(true);
				forward.setPath("viewmemberlist.do");
			} else {
				try {
					msg = "회원 정보 삭제에 실패하였습니다.";
					res.setContentType("text/html; charset=utf-8");
					PrintWriter out = res.getWriter();
					out.println("<script>");
					out.println("  alert('" + msg + "')");
					out.println("  history.back()");
					out.println("</script>");					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			
		} else {
			try {
				msg = "올바르지 않은 비밀번호 입니다.";
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("  alert('" + msg + "')");
				out.println("  history.back()");
				out.println("</script>");					
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}	
		}
		
		return forward;
	}

}
