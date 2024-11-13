package services;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBManager.DBM;
import DBManager.DBMUtil;
import DBModule.member;

public class ViewAllMember implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		ServiceForward forward = null;
		Connection conn = null;
		List<member> memberList = null;
		
		try {
			conn = DBMUtil.getConnection();
			memberList = DBM.getMemberList(conn);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(conn, null, null, null);
		}
		
		req.setAttribute("memberList", memberList);
		
		forward = new ServiceForward();
		forward.setPath("./memberList.jsp?");
		return forward;
	}

}
