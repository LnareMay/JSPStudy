package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Service {
	public ServiceForward execute(HttpServletRequest req, HttpServletResponse res);
}
