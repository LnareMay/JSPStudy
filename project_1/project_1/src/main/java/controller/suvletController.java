package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.*;

@WebServlet("*.do")
public class suvletController extends HttpServlet{
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

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1, requestURI.length() - 3);
		RequestDispatcher dispatcher = null;

		if(command.equalsIgnoreCase("signup")) {
			service = new SignUpService();
			forward = service.execute(req, resp);
		}
		if(command.equalsIgnoreCase("login")) {
			service = new LogInService();
			forward = service.execute(req, resp);
		}
		if(command.equalsIgnoreCase("page/searchFlight")) {
			service = new SearchFlightService();
			forward = service.execute(req, resp);
		}
		if(command.equalsIgnoreCase("page/reservation")) {
			service = new reservationFlight();
			forward = service.execute(req, resp);
		}
		if(command.equalsIgnoreCase("page/updateMember")) {
			service = new UpdateMember();
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
