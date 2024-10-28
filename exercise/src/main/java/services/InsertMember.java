package services;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBM.DBM;
import DBM.DBMUtil;
import DBModule.Member;

public class InsertMember implements Service{

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		Connection conn = null;
		Member member = null;
		
		ServiceForward forward = null;
		
		try {
			conn = DBMUtil.getConnection();
			member = new Member();
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			String name = req.getParameter("name");
			int age = Integer.parseInt(req.getParameter("age"));
			String gender = req.getParameter("gender");
			String email = req.getParameter("email");
			
			member = DBM.selectOneById(conn, id);
			if(!member.getId().equals("OK")) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("  alert('이미 존재하는 ID 입니다.')");
				out.println("  history.back()");
				out.println("</script>");
			} else {
				member = Member.makeMemberModule(id, pw, name, age, gender, email);
				int row = DBM.insert(conn, member);
				
				if(row > 0) {
					forward = new ServiceForward();
					forward.setRedirect(true);
					forward.setPath("viewMemberList.do");
				} else {
					res.setContentType("text/html; charset=utf-8");
					PrintWriter out = res.getWriter();
					out.println("<script>");
					out.println("  alert('게시글등록이 실패 했습니다!!')");
					out.println("  history.back()");
					out.println("</script>");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return forward;
	}
	
}
