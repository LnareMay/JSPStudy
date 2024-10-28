package services;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBM.DBM;
import DBM.DBMUtil;
import DBModule.Member;

public class viewMemeberDetailForModify implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		Connection conn = DBMUtil.getConnection();
		
		Member member = DBM.selectOneById(conn, id);
		
		req.setAttribute("member", member);
		ServiceForward forward = new ServiceForward();
		forward.setPath(String.format("/member/member_modify.jsp?id=%s", id));
		return forward;
	}

}
