package services;

import java.sql.Connection;

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
		String id = req.getParameter("id");
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
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(conn, null, null, null);
		}
		
		
		
		return forward;
	}

}
