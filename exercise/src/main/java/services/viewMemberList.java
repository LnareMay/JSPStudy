package services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBM.DBM;
import DBM.DBMUtil;
import DBModule.Member;

public class viewMemberList implements Service{
	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		Connection conn = null;
		List<Member> listMember = null;
		
		try {
			conn = DBMUtil.getConnection();
			
			listMember = DBM.selectList(conn);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		req.setAttribute("memberList", listMember);

		ServiceForward forward = new ServiceForward();
		forward.setPath("member/member_list.jsp");
		return forward;
	}
}
