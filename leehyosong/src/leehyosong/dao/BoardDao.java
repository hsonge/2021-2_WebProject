package leehyosong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import leehyosong.dto.BoardDto;
import leehyosong.dto.MemberDto;

public class BoardDao {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//생성자에서 커넥션풀과 연동
	public BoardDao() {
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
	public ArrayList<BoardDto> blist() {
		String sql = "select * from board";
		
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setTitle(rs.getString("title"));
				dto.setContext(rs.getString("context"));
				dto.setWriteDate(rs.getDate("writeDate"));
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
	
	public void binsert(BoardDto dto) throws Exception {
			//1. SQL문
			String sql = "insert into board(title, context, writedate) values(?,?,SYSDATE)";
			
			//2. Connection Pool에서 Connection 가져오기
			con = ds.getConnection();
			pstmt= con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContext());
			
			//3. Connection을 통해서 MemberDto의 데이터를 SQL문에 넣기
			pstmt.executeUpdate();  //SQL을 수행하고
		}
	
	//view : 선택된 하나의 레코드를 데이터베이스에서 검색하는 기능
		public BoardDto bview(String title){
			String sql = "select * from board WHERE title=?";
			BoardDto dto = new BoardDto();
			try {
		
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, title);
				rs = pstmt.executeQuery();
				rs.next();
				
				dto.setTitle(title);
				dto.setContext(rs.getString("context"));
				dto.setWriteDate(rs.getDate("writeDate"));
				
				pstmt.close();
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close();
			}
			return dto;
	}
		public void bupdate(BoardDto dto) {
			String sql = "UPDATE board SET context=?, writedate=? WHERE title=?";
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getContext());
				pstmt.setDate(2, dto.getWriteDate());
				pstmt.setString(3, dto.getTitle());
				
				pstmt.executeUpdate();
				pstmt.close();
			}catch(SQLException ex) {
				
			}finally {
				close();
			}
			
		}
		//delete메소드
		public void bdelete(String title) {
			String sql = "DELETE FROM board WHERE title=?";
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, title);
				
				pstmt.executeUpdate();
				pstmt.close();
			}catch(SQLException ex) {
				
			}finally {
				close();
			}
		}
		
	}



