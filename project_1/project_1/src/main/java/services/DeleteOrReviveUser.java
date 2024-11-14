package services;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBManager.DBM;
import DBManager.DBMUtil;

public class DeleteOrReviveUser implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		String doDelete = req.getParameter("doDelete");
		String id = req.getParameter("id");
		boolean isDelete = false;
		if(doDelete.equalsIgnoreCase("true")) {
			isDelete = true;
		} else {
			isDelete = false;
		}
		ServiceForward forward = null;
		Connection conn = null;
		boolean result = false;
		
		try {
			conn = DBMUtil.getConnection();
			result = DBM.deleteOrRevive(conn, id, isDelete);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(conn, null, null, null);
		}
		forward = new ServiceForward();
		if(result) {
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
		return forward;
	}

}
