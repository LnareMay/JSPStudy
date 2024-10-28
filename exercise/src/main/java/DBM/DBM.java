package DBM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import DBModule.Member;

public class DBM {	
	public static int insert(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int row = 0;
		String sql = "insert into member(id, pw, name, age, gender, email) values(?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			pstmt.setInt(4, member.getAge());
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getEmail());
			
			row = pstmt.executeUpdate();
			
			DBMUtil.commit(conn);
		} catch (Exception e) {
			// TODO: handle exception
			DBMUtil.rollback(conn);
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(conn, pstmt, pstmt, null);
		}
		
		return row;
	}
	public static List<Member> selectList(Connection conn){
		List<Member> memberList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from member";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					memberList.add(Member.makeMemberModule(rs));
				}while(rs.next());
				return memberList;
			}else {
				return Collections.emptyList();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Collections.emptyList();
		} finally {
			DBMUtil.dipose(conn, null, pstmt, rs);
		}
		
	}
	
	public static Member selectOneById(Connection conn, String id) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from member where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = Member.makeMemberModule(rs);
			} else {
				member = new Member();
				member.setId("OK");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(null, pstmt, null, rs);
		}

		return member;
	}
	
	public static boolean delete(Connection conn, String id, String pw) {
		PreparedStatement pstmt = null;
		int row = 0;
		boolean result = false;
		
		String sql = "delete from member where id = ? and pw = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			row = pstmt.executeUpdate();
			
			if(row > 0) {
				result = true;
				DBMUtil.commit(conn);
			} else {
				result = false;
				DBMUtil.rollback(conn);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			DBMUtil.rollback(conn);
		} finally {
			DBMUtil.dipose(conn, pstmt, null, null);
		}
		
		return result;
	}
	
	public static int Update(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int row = 0;
		String sql = "update member set pw=?, name=?, age=?, gender=?, email=? where id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getName());
			pstmt.setInt(3, member.getAge());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getId());
			
			row = pstmt.executeUpdate();
			
			if(row > 0) {
				DBMUtil.commit(conn);
			} else {
				DBMUtil.rollback(conn);
				return 0;
			}
		} catch (Exception e) {
			DBMUtil.rollback(conn);
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(null, pstmt, null, null);
		}
		
		return row;
	}
	
	public static boolean checkIdPw(Connection conn, String id, String pw) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from member where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = Member.makeMemberModule(rs);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			DBMUtil.dipose(null, pstmt, null, rs);
		}
		if(member.getPw().equals(pw)) return true;
		else return false;

	}
}
