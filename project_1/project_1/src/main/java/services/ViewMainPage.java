package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ViewMainPage implements Service {
	boolean isAdmin = false;
	public ViewMainPage() {
	}
	public ViewMainPage(boolean b) {
		isAdmin = b;
	}

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		ServiceForward forward = new ServiceForward();
		if(isAdmin) {
			forward.setRedirect(true);
			forward.setPath("./page/adminPage.jsp");
		} else {
			forward.setRedirect(true);
			forward.setPath("./page/mainPage.jsp");
		}
		return forward;
	}

}
