package services;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBManager.DBM;
import DBManager.DBMUtil;
import DBModule.flight;

public class ViewFligthDetail implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		ServiceForward forward = null;
		Connection conn = null;
		
		String flightCode = req.getParameter("flightcode");
		
		try {
			conn = DBMUtil.getConnection();
			flight flight = DBM.getFlightDetail(conn, flightCode);
			if(flight == null) {
				try {
					res.setContentType("text/html; charset=utf-8");
					PrintWriter out = res.getWriter();
					out.println("<script>");
					out.println("  alert('해당하는 항공편이 없습니다.')");
					out.println("  history.back()");
					out.println("</script>");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}else {
				req.setAttribute("flight", flight);
				
				forward = new ServiceForward();
				forward.setPath("./flightDetail.jsp?flight="+flight);
			}
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		} finally {
			DBMUtil.dipose(conn, null, null, null);
		}
		return forward;
	}

}
