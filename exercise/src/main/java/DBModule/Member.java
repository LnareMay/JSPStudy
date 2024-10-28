package DBModule;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Member {
	private String id;
	private String pw;
	private String name;
	private int age;
	private String gender;
	private String email;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", email=" + email + "]";
	}

	public static Member makeMemberModule(String id, String pw, String name, int age, String gender, String email) {
		Member member = new Member();
		member.setId(id);
		member.setPw(pw);
		member.setName(name);
		member.setAge(age);
		member.setGender(gender);
		member.setEmail(email);
		return member;
	}
	
	public static Member makeMemberModule(ResultSet rs) throws SQLException {
		Member member = new Member();
		member.setId(rs.getString("id"));
		member.setPw(rs.getString("pw"));
		member.setName(rs.getString("name"));
		member.setAge(rs.getInt("age"));
		member.setGender(rs.getString("gender"));
		member.setEmail(rs.getString("email"));
		return member;
	}
}
