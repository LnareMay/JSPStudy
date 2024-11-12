package DBModule;

public class reservation_his {
	private int his_SEQ;
	private String reservationcode;
	private String ID;
	private String flightcode;
	private String seatsclass;
	private int seatsnum;
	private String his_date;
	
	
	public int getHis_SEQ() {
		return his_SEQ;
	}
	public void setHis_SEQ(int his_SEQ) {
		this.his_SEQ = his_SEQ;
	}
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
	public String getHis_date() {
		return his_date;
	}
	public void setHis_date(String his_date) {
		this.his_date = his_date;
	}
}
