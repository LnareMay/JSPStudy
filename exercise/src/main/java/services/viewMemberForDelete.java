package services;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBM.DBM;
import DBM.DBMUtil;
import DBModule.Member;

public class viewMemberForDelete implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		Connection conn = null;
		Member member = null;
		
		conn = DBMUtil.getConnection();
		String id = req.getParameter("id");
		
		member = DBM.selectOneById(conn, id);
		req.setAttribute("pw", member.getPw());
		ServiceForward forward = new ServiceForward();
		forward.setPath(String.format("/member/member_delete.jsp?id=%s", id));
		return forward;
	}

}
