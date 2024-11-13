package services;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBManager.DBM;
import DBManager.DBMUtil;
import DBModule.flight;
import DBModule.member;

public class ViewReservationDetail implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		ServiceForward forward = null;
		Connection conn = null;
		HttpSession session = req.getSession();
		member member = (member) session.getAttribute("Member");
		String ID = member.getID();
		String flightCode = req.getParameter("flightcode");
		String seatsNum = req.getParameter("seatsNum");
		String seatsClass = req.getParameter("seatsClass");
		flight flight = null;
		
		try {
			conn = DBMUtil.getConnection();
			flight = DBM.getReservationFlightDetail(conn, ID, flightCode);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(conn, null, null, null);
		}
		
		req.setAttribute("flight", flight);
		
		forward = new ServiceForward();
		forward.setPath("./reservationHisDetail.jsp?flight="+flight+"&seatsNum="+seatsNum+"&seatsClass="+seatsClass);
		return forward;
	}

}
