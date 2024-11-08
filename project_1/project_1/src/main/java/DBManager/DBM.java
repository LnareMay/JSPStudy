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
		}
		
		if(flightList.isEmpty()) return null;
		return flightList;
	}

}
