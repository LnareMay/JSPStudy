package services;

import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBManager.DBM;
import DBManager.DBMUtil;
import DBModule.member;
import DBModule.member_his;

public class UpdateMember implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		Connection conn = null;
		member member = null;
		member_his his = null;
		ServiceForward forward = null;
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String tel = req.getParameter("tel");
		tel = tel.replace("-", "");
		
		Date dtLastUpdateDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastUpdateDate = format.format(dtLastUpdateDate);
		
		try {
			conn = DBMUtil.getConnection();
			member = new member();
			member.setID(id);
			member.setPassword(pw);
			member.setName(name);
			member.setEmail(email);
			member.setTel(tel);
			member.setLastupdatedate(lastUpdateDate);
			
			boolean insertResult = DBM.updateMember(conn, member);
			
			if(insertResult) {
				forward = new ServiceForward();
				forward.setRedirect(true);
				forward.setPath("updateMemberSuccess.jsp");
			} else {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("  alert('회원수정에 실패 했습니다!!')");
				out.println("  history.back()");
				out.println("</script>");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
