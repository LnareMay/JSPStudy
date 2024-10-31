package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewMainPage implements Service {

	@Override
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res) {
		HttpSession sesseion = req.getSession();
		String id = (String) sesseion.getAttribute("ID");
		String pw = (String) sesseion.getAttribute("password");
		System.out.println(id);
		
		
		
		
		
		return null;
	}

}
