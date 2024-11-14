package services;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBManager.DBM;
import DBManager.DBMUtil;
import DBModule.member;

public class LogInService implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		Connection conn = null;
		member member = null;
		ServiceForward forward = null;
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		try {
			conn = DBMUtil.getConnection();
			member = DBM.selectOneById(conn, id);
			if(member.getPassword() == null || member.getID().equals("OK")) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("  alert('로그인 정보가 없습니다.')");
				out.println("  history.back()");
				out.println("</script>");
			} else if(member.getPassword().equals(pw)){
				HttpSession session = req.getSession();
				session.setAttribute("Member", member);
				forward = new ServiceForward();
				forward.setRedirect(true);
				if(member.getIsmanager() != null && member.getIsmanager().equalsIgnoreCase("C")) {
					forward.setPath("viewAdminPage.form");
				} else {
					forward.setPath("viewMainPage.form");
				}
			} else {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("  alert('비밀번호가 다릅니다.')");
				out.println("  history.back()");
				out.println("</script>");
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return forward;
	}

}
