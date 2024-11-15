package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.*;

@WebServlet("*.form")
public class formController extends HttpServlet{
	Service service = null;
	ServiceForward forward = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1, requestURI.length() - 5);
		RequestDispatcher dispatcher = null;
		
		System.out.println(command);
		
		if(command.equalsIgnoreCase("viewMainPage")) {
			service = new ViewMainPage();
			forward = service.execute(req, resp);
		}
		if(command.equalsIgnoreCase("viewAdminPage")) {
			service = new ViewMainPage(true);
			forward = service.execute(req, resp);
		}
		if(command.equalsIgnoreCase("page/flightDetail")) {
			service = new ViewFligthDetail();
			forward = service.execute(req, resp);
		}
		if(command.equalsIgnoreCase("page/reservationhis")) {
			service = new ViewReservationHis();
			forward = service.execute(req, resp);
		}
		if(command.equalsIgnoreCase("page/reservationDetail")) {
			service = new ViewReservationDetail();
			forward = service.execute(req, resp);
		}
		if(command.equalsIgnoreCase("page/viewAllMember")) {
			service = new ViewAllMember();
			forward = service.execute(req, resp);
		}
		if(command.equalsIgnoreCase("page/viewMemberDetail")) {
			service = new ViewMemberDetail();
			forward = service.execute(req, resp);
		}
		if(command.equalsIgnoreCase("page/setFlight")) {
			service = new setFlight();
			forward = service.execute(req, resp);
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				resp.sendRedirect(forward.getPath());
			} else {
				dispatcher = req.getRequestDispatcher(forward.getPath());
				dispatcher.forward(req, resp);
			}
		}
	}
}
