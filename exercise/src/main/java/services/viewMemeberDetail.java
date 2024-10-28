package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBM.DBM;
import DBM.DBMUtil;
import DBModule.Member;
import services.Service;
import services.ServiceForward;

public class viewMemeberDetail implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		ServiceForward forward = new ServiceForward();
		Connection conn = DBMUtil.getConnection();
		
		String id = req.getParameter("id");
		Member member = DBM.selectOneById(conn, id);
		
		DBMUtil.dipose(conn, null, null, null);
		
		if(member.getId().equals("OK")) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out;
			try {
				out = res.getWriter();
				out.println("<script>");
				out.println("  alert('존재하지 않는 ID 입니다.')");
				out.println("  history.back()");
				out.println("</script>");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			
		} else {
			req.setAttribute("member", member);
			forward.setPath(String.format("/member/member_detail.jsp?id=%s",id));
		}
		
		return forward;
	}

}
