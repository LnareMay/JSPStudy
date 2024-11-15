package services;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBManager.DBM;
import DBManager.DBMUtil;
import DBModule.member;

public class UpdateUserForAdmin implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		ServiceForward forward = null;
		Connection conn = null;
		
		member member = null;
		String id = req.getParameter("ID");
		String role = req.getParameter("role");
		boolean result = false;
		
		try {
			conn = DBMUtil.getConnection();
			member = DBM.selectOneById(conn, id);
			if(role.equalsIgnoreCase("driver")) {
				member.setIsdriver("C");
			} else if(role.equalsIgnoreCase("manager")) {
				member.setIsmanager("C");
			}
			
			result = DBM.updateMemberForAdmin(conn, member);
			
			if(result) {
				forward = new ServiceForward();
				forward.setRedirect(true);
				forward.setPath("./updateMemberSuccess.jsp");
				
			} else {
				try {
					res.setContentType("text/html; charset=utf-8");
					PrintWriter out = res.getWriter();
					out.println("<script>");
					out.println("  alert('회원수정에 실패 했습니다!!')");
					out.println("  history.back()");
					out.println("</script>");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(conn, null, null, null);
		}
		return forward;
	}

}
