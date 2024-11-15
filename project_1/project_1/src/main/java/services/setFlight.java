package services;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBManager.DBM;
import DBManager.DBMUtil;
import DBModule.flight;

public class setFlight implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		ServiceForward forward = null;
		Connection conn = null;
		List<flight> flightList = null;
		
		try {
			conn = DBMUtil.getConnection();
			flightList = DBM.getAllFlightList(conn);
			
			if(flightList.isEmpty()) {
				try {
					res.setContentType("text/html; charset=utf-8");
					PrintWriter out = res.getWriter();
					out.println("<script>");
					out.println("  alert('오류 발생 관리자 문의바람')");
					out.println("  history.back()");
					out.println("</script>");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		forward = new ServiceForward();
		req.setAttribute("flightList", flightList);
		forward.setPath("./searchFlightResult.jsp");
		return forward;
	}

}
