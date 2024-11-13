package services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBManager.DBM;
import DBManager.DBMUtil;
import DBModule.member;
import DBModule.reservation_his;

public class ViewReservationHis implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		ServiceForward forward = null;
		Connection conn = null;
		List<reservation_his> listRes = null;
		
		HttpSession session = req.getSession();
		member member = (member) session.getAttribute("Member");
		String id = member.getID();
		
		try {
			conn = DBMUtil.getConnection();
			listRes = DBM.getReservationList(conn, id);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		req.setAttribute("resList", listRes);
		
		forward = new ServiceForward();
		forward.setPath("./reservationHis.jsp?resList="+listRes);
		
		return forward;
	}

}
