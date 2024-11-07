package services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBManager.DBM;
import DBManager.DBMUtil;
import DBModule.flight;

public class SearchFlightService implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		ServiceForward forward = null;
		List<flight> flightList = new ArrayList<flight>();
		
		Connection conn = null;
		String from = req.getParameter("from");
		String to = req.getParameter("to");
		String date = req.getParameter("date");
		
		try {
			conn = DBMUtil.getConnection();
			flightList = DBM.getFlightList(conn, from, to, date);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return forward;
	}

}
