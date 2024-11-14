package services;

import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBManager.DBM;
import DBManager.DBMUtil;
import DBModule.reservation;

public class reservationFlight implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		ServiceForward forward = null;
		Connection conn = null;
		String memberID = req.getParameter("memberID");
		String flightCode = req.getParameter("flightCode");
		String seatsClass = null;
		int seats = Integer.parseInt(req.getParameter("seats"));
		String tmpseatsClass = req.getParameter("seatsClass");
		if(tmpseatsClass.equalsIgnoreCase("First")) seatsClass = "firstclassseats";
		if(tmpseatsClass.equalsIgnoreCase("Business")) seatsClass = "businessseats";
		if(tmpseatsClass.equalsIgnoreCase("Economy")) seatsClass = "economyseats";
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHHmm:ss");
		String strDate = format.format(date);
				
		String codeDate = strDate.substring(7, 14);
		
		String reservationCode = flightCode + memberID + codeDate;
		
		reservation reservation = new reservation();
		reservation.setReservationcode(reservationCode);
		reservation.setID(memberID);
		reservation.setFlightcode(flightCode);
		reservation.setSeatsclass(seatsClass);
		reservation.setSeatsnum(seats);
		
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strDate = format.format(date);
		
		reservation.setCreatedate(strDate);
		
		try {
			conn = DBMUtil.getConnection();
			
			boolean insertResult = DBM.reservationFlight(conn, reservation);
			
			if(!insertResult) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("  alert('예약 중 오류 발생')");
				out.println("  history.back()");
				out.println("</script>");
			} else {
				forward = new ServiceForward();
				forward.setRedirect(true);
				forward.setPath("./reservationSuccess.jsp");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
