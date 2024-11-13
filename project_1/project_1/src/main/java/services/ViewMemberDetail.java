package services;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBManager.DBM;
import DBManager.DBMUtil;
import DBModule.member;

public class ViewMemberDetail implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		ServiceForward forward = null;
		String ID = req.getParameter("ID");
		Connection conn = null;
		member member = null;
		
		try {
			member = DBM.selectOneById(conn, ID);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(conn, null, null, null);
		}
		
		req.setAttribute("member", member);
		
		forward = new ServiceForward();
		forward.setPath("./viewMemberDetail.jsp");
		return forward;
	}

}
