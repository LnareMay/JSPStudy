package DBModule;

public class reservation {
	private String reservationcode;
	private String ID;
	private String flightcode;
	private String seatsclass;
	private int seatsnum;
	private String createdate;
	private String lastupdatedate;
	
	
	public String getReservationcode() {
		return reservationcode;
	}
	public void setReservationcode(String reservationcode) {
		this.reservationcode = reservationcode;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFlightcode() {
		return flightcode;
	}
	public void setFlightcode(String flightcode) {
		this.flightcode = flightcode;
	}
	public String getSeatsclass() {
		return seatsclass;
	}
	public void setSeatsclass(String seatsclass) {
		this.seatsclass = seatsclass;
	}
	public int getSeatsnum() {
		return seatsnum;
	}
	public void setSeatsnum(int seatsnum) {
		this.seatsnum = seatsnum;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getLastupdatedate() {
		return lastupdatedate;
	}
	public void setLastupdatedate(String lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

}
