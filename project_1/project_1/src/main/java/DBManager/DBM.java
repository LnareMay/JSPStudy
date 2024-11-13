package DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DBModule.flight;
import DBModule.member;
import DBModule.member_his;
import DBModule.reservation;
import DBModule.reservation_his;

public class DBM {

	// Id로 한건 검색
	public static member selectOneById(Connection conn, String id) {
		member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from member where ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = makeMemberModule(rs);
			} else {
				member = new member();
				member.setID("OK");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(null, pstmt, null, rs);
		}

		return member;
	}

	private static member makeMemberModule(ResultSet rs) throws SQLException {
		member member = new member();
		member.setID(rs.getString("id"));
		member.setPassword(rs.getString("password"));
		member.setName(rs.getString("name"));
		member.setEmail(rs.getString("email"));
		member.setTel(rs.getString("tel"));
		member.setIsmanager(rs.getString("ismanager"));
		member.setIsdriver(rs.getString("isdriver"));
		member.setCreatedate(rs.getString("createdate"));
		member.setLastupdatedate(rs.getString("lastupdatedate"));
		member.setDeleteflag(rs.getString("deleteflag"));
		return member;
	}

	public static boolean insert(Connection conn, member member) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_his = null;
		ResultSet rs = null;
		int row = 0;
		
		boolean resultInsert = false;
		boolean resultHis = false;
		
		try {
			String sql = "insert into member(ID, password, name, email, tel, createdate) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getID());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getTel());
			pstmt.setString(6, member.getCreatedate());
			
			row = pstmt.executeUpdate();
			
			if(row > 0) resultInsert = true;
			
			sql = "insert into member_his(his_SEQ, ID, password, name, email, tel, his_date) values(1, ?,?,?,?,?,?)";
			pstmt_his = conn.prepareStatement(sql);
			pstmt_his.setString(1, member.getID());
			pstmt_his.setString(2, member.getPassword());
			pstmt_his.setString(3, member.getName());
			pstmt_his.setString(4, member.getEmail());
			pstmt_his.setString(5, member.getTel());
			pstmt_his.setString(6, member.getCreatedate());
			
			row = pstmt_his.executeUpdate();
			
			if(row > 0) resultHis = true;
			
			if(resultInsert && resultHis) {
				DBMUtil.commit(conn);
				return true;
			} else {
				DBMUtil.rollback(conn);
				return false;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			DBMUtil.rollback(conn);
			return false;
		}
	}

	public static List<flight> getFlightList(Connection conn, String from, String to, String date) {
		List<flight> flightList = new ArrayList<flight>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		date = date + "%";
		
		String sql = "select * from flight where fromairport = ? and toairport = ? and starttime like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, from);
			pstmt.setString(2, to);
			pstmt.setString(3, date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				flight flight = new flight();
				flight.setFlightcode(rs.getString("flightcode"));
				flight.setFlightname(rs.getString("flightname"));
				flight.setFromairport(rs.getString("fromairport"));
				flight.setToairport(rs.getString("toairport"));
				flight.setFirstclassseats(rs.getInt("firstclassseats"));
				flight.setBusinessseats(rs.getInt("businessseats"));
				flight.setEconomyseats(rs.getInt("economyseats"));
				flight.setStarttime(rs.getString("starttime"));
				flight.setEndtime(rs.getString("endtime"));
				flight.setReservationstarttime(rs.getString("reservationstarttime"));
				flight.setReservationendtime(rs.getString("reservationendtime"));
				flight.setComment(rs.getString("comment"));
				
				flightList.add(flight);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(null, pstmt, null, rs);
		}
		
		if(flightList.isEmpty()) return null;
		return flightList;
	}

	public static flight getFlightDetail(Connection conn, String flightCode) {
		flight flight = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from flight where flightcode=?";
		
		try {
			flight = new flight();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, flightCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				flight.setFlightcode(rs.getString("flightcode"));
				flight.setFlightname(rs.getString("flightname"));
				flight.setFromairport(rs.getString("fromairport"));
				flight.setToairport(rs.getString("toairport"));;
				flight.setFirstclassseats(rs.getInt("firstclassseats"));
				flight.setBusinessseats(rs.getInt("businessseats"));
				flight.setEconomyseats(rs.getInt("economyseats"));
				flight.setStarttime(rs.getString("starttime"));
				flight.setEndtime(rs.getString("endtime"));
				flight.setReservationstarttime(rs.getString("reservationstarttime"));
				flight.setReservationendtime(rs.getString("reservationendtime"));
				flight.setCreatedate(rs.getString("createdate"));
				flight.setLastupdatedate(rs.getString("lastupdatedate"));
				flight.setComment(rs.getString("comment"));
			} else {
				DBMUtil.dipose(null, pstmt, null, rs);
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(null, pstmt, null, rs);
		}
		return flight;
	}

	public static boolean reservationFlight(Connection conn, reservation reservation) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_his = null;
		ResultSet rs = null;
		int row = 0;
		
		boolean isResult = false;
		boolean hisResult = false;
		
		String sql = "insert into reservation (reservationcode, ID, flightcode, seatsclass, seatsnum, createdate) "
				+ "values(?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservation.getReservationcode());
			pstmt.setString(2, reservation.getID());
			pstmt.setString(3, reservation.getFlightcode());
			pstmt.setString(4, reservation.getSeatsclass());
			pstmt.setInt(5, reservation.getSeatsnum());
			pstmt.setString(6, reservation.getCreatedate());
			
			row = pstmt.executeUpdate();
			
			if(row > 0) isResult = true;
			
			sql = "insert into reservation_his (his_SEQ, reservationcode, ID, flightcode, seatsclass, seatsNum, his_date) "
					+ "values(1, ?, ?, ?, ?, ?, ?)";
			
			pstmt_his = conn.prepareStatement(sql);
			pstmt_his.setString(1, reservation.getReservationcode());
			pstmt_his.setString(2, reservation.getID());
			pstmt_his.setString(3, reservation.getFlightcode());
			pstmt_his.setString(4, reservation.getSeatsclass());
			pstmt_his.setInt(5, reservation.getSeatsnum());
			pstmt_his.setString(6, reservation.getCreatedate());
			
			row = 0;
			row = pstmt_his.executeUpdate();
			
			if(row > 0) hisResult = true;
			
			if(!isResult || !hisResult){
				DBMUtil.rollback(conn);
				return false;
			} else {
				DBMUtil.commit(conn);
				return true;
			}
			
		} catch (Exception e) {
			DBMUtil.rollback(conn);
			System.out.println(e.getMessage());
			return false;
		} finally {
			DBMUtil.dipose(null, pstmt, null, rs);
		}
	}

	public static boolean updateMember(Connection conn, member member) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_seq = null;
		PreparedStatement pstmt_his = null;
		ResultSet rs = null;
		int seq = 0;
		int row = 0;
		
		boolean resultInsert = false;
		boolean resultHis = false;
		
		String sql = "update member "
				+ "set password=?, name=?, email=?, tel=?, lastupdatedate=? "
				+ "where id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getTel());
			pstmt.setString(5, member.getLastupdatedate());
			pstmt.setString(6, member.getID());
			
			row = pstmt.executeUpdate();
			
			if(row > 0) resultInsert = true;
			row = 0;
			
			sql = "select * from member_his where ID = ? order by 1 desc limit 1";
			pstmt_seq = conn.prepareStatement(sql);
			pstmt_seq.setString(1, member.getID());
			rs = pstmt_seq.executeQuery();
			if(rs.next()) seq = rs.getInt(1);
			
			sql = "insert into member_his(his_SEQ, ID, password, name, email, tel, his_date) values(?,?,?,?,?,?,?)";
			pstmt_his = conn.prepareStatement(sql);
			pstmt_his.setInt(1, seq + 1);
			pstmt_his.setString(2, member.getID());
			pstmt_his.setString(3, member.getPassword());
			pstmt_his.setString(4, member.getName());
			pstmt_his.setString(5, member.getEmail());
			pstmt_his.setString(6, member.getTel());
			pstmt_his.setString(7, member.getLastupdatedate());
			
			row = pstmt_his.executeUpdate();
			
			if(row > 0) resultHis = true;
			
			if(!resultHis || !resultInsert) {
				DBMUtil.rollback(conn);
				return false;
			} else {
				DBMUtil.commit(conn);
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			DBMUtil.rollback(conn);
			return false;
		} finally {
			DBMUtil.dipose(conn, pstmt, pstmt_his, rs);
		}
	}

	public static List<reservation_his> getReservationList(Connection conn, String id) {
		List<reservation_his> list = new ArrayList<reservation_his>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from reservation_his where ID =? order by 1 desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				reservation_his rsh = new reservation_his();
				rsh.setHis_SEQ(rs.getInt(1));
				rsh.setReservationcode(rs.getString("reservationcode"));
				rsh.setID(rs.getString("ID"));
				rsh.setFlightcode(rs.getString("flightcode"));
				rsh.setSeatsclass(rs.getString("seatsclass"));
				rsh.setSeatsnum(rs.getInt("seatsNum"));
				rsh.setHis_date(rs.getString("his_date"));
				
				list.add(rsh);
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(conn, pstmt, null, rs);
		}
		
		return list;
	}

	public static flight getReservationFlightDetail(Connection conn, String iD, String flightCode) {
		flight flight = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select f.* "
				+ "from reservation_his rh, "
				+ "flight f "
				+ "where rh.ID =? "
				+ "and rh.flightcode =? "
				+ "and rh.flightcode = f.flightcode";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iD);
			pstmt.setString(2, flightCode);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				flight = new flight();
				flight.setFlightcode(rs.getString("flightcode"));
				flight.setFlightname(rs.getString("flightname"));
				flight.setFromairport(rs.getString("fromairport"));
				flight.setToairport(rs.getString("toairport"));;
				flight.setFirstclassseats(rs.getInt("firstclassseats"));
				flight.setBusinessseats(rs.getInt("businessseats"));
				flight.setEconomyseats(rs.getInt("economyseats"));
				flight.setStarttime(rs.getString("starttime"));
				flight.setEndtime(rs.getString("endtime"));
				flight.setReservationstarttime(rs.getString("reservationstarttime"));
				flight.setReservationendtime(rs.getString("reservationendtime"));
				flight.setCreatedate(rs.getString("createdate"));
				flight.setLastupdatedate(rs.getString("lastupdatedate"));
				flight.setComment(rs.getString("comment"));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(null, pstmt, null, rs);
		}
		
		
		return flight;
	}

	public static boolean updateReservation(Connection conn, reservation reservation) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_his = null;
		ResultSet rs = null;
		int row = 0;
		String createDate = null;
		
		boolean isResult = false;
		boolean hisResult = false;
		
		String selectsql = "select * from reservation where reservationcode=?";
		
		String sql = "UPDATE reservation "
				+ "SET ID=?, flightcode=?, seatsclass=?, seatsnum=?, createdate=?, lastupdatedate=? "
				+ "WHERE reservationcode=?";
		
		try {
			pstmt = conn.prepareStatement(selectsql);
			pstmt.setString(1, reservation.getReservationcode());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reservation.setCreatedate(rs.getString("createdate"));
			}
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservation.getID());
			pstmt.setString(2, reservation.getFlightcode());
			pstmt.setString(3, reservation.getSeatsclass());
			pstmt.setInt(4, reservation.getSeatsnum());
			pstmt.setString(5, reservation.getCreatedate());
			pstmt.setString(6, reservation.getLastupdatedate());
			pstmt.setString(7, reservation.getReservationcode());
			
			row = pstmt.executeUpdate();
			
			if(row > 0) isResult = true;
			
			selectsql = "select * from reservation_his where reservationcode=? order by 1 desc limit 1";
			
			pstmt_his = conn.prepareStatement(selectsql);
			pstmt_his.setString(1, reservation.getReservationcode());
			rs = pstmt_his.executeQuery();
			
			int seq = 0;
			if(rs.next()) {
				seq = rs.getInt("his_SEQ");
			}
			
			sql = "insert into reservation_his (his_SEQ, reservationcode, ID, flightcode, seatsclass, seatsNum, his_date) "
					+ "values(?, ?, ?, ?, ?, ?, ?)";
			
			pstmt_his = conn.prepareStatement(sql);
			pstmt_his.setInt(1, seq+1);
			pstmt_his.setString(2, reservation.getReservationcode());
			pstmt_his.setString(3, reservation.getID());
			pstmt_his.setString(4, reservation.getFlightcode());
			pstmt_his.setString(5, reservation.getSeatsclass());
			pstmt_his.setInt(6, reservation.getSeatsnum());
			pstmt_his.setString(7, reservation.getLastupdatedate());
			
			row = 0;
			row = pstmt_his.executeUpdate();
			
			if(row > 0) hisResult = true;
			
			if(!isResult || !hisResult){
				DBMUtil.rollback(conn);
				return false;
			} else {
				DBMUtil.commit(conn);
				return true;
			}
			
		} catch (Exception e) {
			DBMUtil.rollback(conn);
			System.out.println(e.getMessage());
			return false;
		} finally {
			DBMUtil.dipose(null, pstmt, null, rs);
		}
	}

	public static List<member> getMemberList(Connection conn) {
		List<member> memberList = new ArrayList<member>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from member";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member member = new member();
				member = makeMemberModule(rs);
				
				memberList.add(member);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(null, pstmt, null, rs);
		}
		
		return memberList;
	}

}
