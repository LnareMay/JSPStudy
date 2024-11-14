package services;

import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBManager.DBM;
import DBManager.DBMUtil;
import DBModule.member;
import DBModule.reservation;

public class UpdateReservation implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		Connection conn = null;
		ServiceForward forward = null;
		HttpSession session = req.getSession();
		member member = (member) session.getAttribute("Member");
		String memberID = req.getParameter("memberID");
		String flightCode = req.getParameter("flightCode");
		String reservationCode = req.getParameter("reservationCode");
		String seatsClass = null;
		int seats = Integer.parseInt(req.getParameter("seats"));
		String tmpseatsClass = req.getParameter("seatsClass");
		if(tmpseatsClass.equalsIgnoreCase("First")) seatsClass = "firstclassseats";
		if(tmpseatsClass.equalsIgnoreCase("Business")) seatsClass = "businessseats";
		if(tmpseatsClass.equalsIgnoreCase("Economy")) seatsClass = "economyseats";
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = format.format(date);
		reservation reservation = new reservation();
		reservation.setReservationcode(reservationCode);
		reservation.setID(memberID);
		reservation.setFlightcode(flightCode);
		reservation.setSeatsclass(seatsClass);
		reservation.setSeatsnum(seats);
		reservation.setLastupdatedate(strDate);
		
		try {
			conn = DBMUtil.getConnection();
			boolean isResult = DBM.updateReservation(conn, reservation);
			
			if(!isResult) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("  alert('예약 변경 중 오류 발생')");
				out.println("  history.back()");
				out.println("</script>");
			} else {
				forward = new ServiceForward();
				forward.setRedirect(true);
				forward.setPath("./reservationSuccess.jsp");
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(conn, null, null, null);
		}
		
		
		return forward;
	}

}
