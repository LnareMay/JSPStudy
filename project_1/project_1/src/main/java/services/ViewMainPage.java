package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewMainPage implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		ServiceForward forward = new ServiceForward();
		
		
		forward.setRedirect(true);
		forward.setPath("./page/mainPage.jsp");
		return forward;
	}

}
