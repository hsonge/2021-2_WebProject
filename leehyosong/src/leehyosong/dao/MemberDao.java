package leehyosong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import leehyosong.dto.MemberDto;

public class MemberDao {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//생성자에서 커넥션풀과 연동
	public MemberDao() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/LHS");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void close() {
		try {
			if(con !=null) {
				con.close();
				con=null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<MemberDto> list() {
		String sql = "select * from member";
		
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setJoinDate(rs.getDate("joinDate"));
				dtos.add(dto);
			}
			rs.close(); pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dtos;
	}
	//MemberDto를 DB에 저장하는 insert메소드 구현
	
	public void insert(MemberDto dto) throws Exception {
		//1. SQL문
		String sql = "insert into Member(id, pwd, name, email, joinDate) values(?,?,?,?,SYSDATE)";
		
		//2. Connection Pool에서 Connection 가져오기
		con = ds.getConnection();
		pstmt= con.prepareStatement(sql);
		
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getPwd());
		pstmt.setString(3, dto.getName());
		pstmt.setString(4, dto.getEmail());
		
		//3. Connection을 통해서 MemberDto의 데이터를 SQL문에 넣기
		pstmt.executeUpdate();  //SQL을 수행하고
		
		
	}
	//view : 선택된 하나의 레코드를 데이터베이스에서 검색하는 기능
	public MemberDto view(String id){
		String sql = "SELECT * FROM MEMBER WHERE ID=?";
		MemberDto dto = new MemberDto();
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			
			dto.setId(id);
			dto.setPwd(rs.getString("pwd"));
			dto.setName(rs.getString("name"));
			dto.setEmail(rs.getString("email"));
			dto.setJoinDate(rs.getDate("joinDate"));
			
			pstmt.close();
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;
	
	}
	public void update(MemberDto dto) {
		String sql = "UPDATE MEMBER SET PWD=?, NAME=?, EMAIL=?, JOINDATE=? WHERE ID=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setDate(4, dto.getJoinDate());
			pstmt.setString(5, dto.getId());
			
			pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException ex) {
			
		}finally {
			close();
		}
		
	}
	//delete메소드
	public void delete(String id) {
		String sql = "DELETE FROM MEMBER WHERE ID=?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException ex) {
			
		}finally {
			close();
		}
	}
	
}
