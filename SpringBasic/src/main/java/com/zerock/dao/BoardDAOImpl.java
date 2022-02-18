package com.zerock.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.zerock.command.BoardVO;

public class BoardDAOImpl implements BoardDAO {

	//DB로 가정할 ArrayList처리,DB자체를 만드는 것으로 가정
	//ArrayList<BoardVO> DB= new ArrayList<>(); 
	
	//JDBC처리  
	//1. 아티팩트 설치(ojdbc8.jar)-maven 에서...(pom.xml) 
	//2. DB내에 board01테이블 생성
	/*
	create table board1 (	
	NUM INT DEFAULT BOARD_NUM.nextval PRIMARY KEY, 
	NAME VARCHAR(20), 
	TITLE VARCHAR(30),
	CONTENT VARCHAR(50) 
	); */

	// 3. DB 연결정보 생성 
	private String url="jdbc:oracle:thin:@localhost:1521/XEPDB1";
	private String user="myjsp";
	private String password="myjsp";
	
	// 4. 각 메소드별 처리
	private Connection conn= null;
	private PreparedStatement pstmt=null; 
	private ResultSet rs= null; 
	
	//게시글 등록처리
	@Override
	public void boardInsert(String name, String title, String content) {

		String sql="INSERT INTO BOARD01(name,title,content) values (?,?,?)";
		try {
			/*System.out.println("----DAO계층----");
			System.out.println(name);
			System.out.println(title);
			System.out.println(content);
			*/
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection(url,user,password); 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name );
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.executeUpdate(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {  
			try {
				if (conn!=null) conn.close(); 
				if (pstmt!=null) pstmt.close(); 
				if (rs!=null) rs.close(); 
				
			}catch(Exception e2) { 
				
			} 
			
		}
	/*
		BoardVO vo = new BoardVO(); 
		vo.setName(name);
		vo.setTitle(title);
		vo.setContent(content);
		
		DB.add(vo);
		System.out.println("현재 게시물 수: "+ DB.size());
	*/
	}
	
	
	@Override
	public ArrayList<BoardVO> boardSelect() {
		
		ArrayList<BoardVO> list= new ArrayList<BoardVO>(); 

		String sql="SELECT * FROM BOARD01 ORDER BY NUM ASC";
		
		try {	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection(url,user,password); 
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery(); 
			
			while(rs.next()) {
			int num= rs.getInt("num");
			String name =rs.getString("name");	
			String title= rs.getString("title");
			String content= rs.getString("content"); 
			
			BoardVO vo= new BoardVO(); 
			vo.setNum(num);
			vo.setName(name);
			vo.setTitle(title);
			vo.setContent(content);
			
			list.add(vo);
			
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {  
			try {
				if (conn!=null) conn.close(); 
				if (pstmt!=null) pstmt.close(); 
				if (rs!=null) rs.close(); 
				
			}catch(Exception e2) { 
			} 	
		}
		return list; 
	}

	@Override
	public void boardDelete(String num) {
		
		String sql="DELETE FROM BOARD01 WHERE NUM=?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection(url,user,password); 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num );
			pstmt.executeUpdate(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {  
			try {
				if (conn!=null) conn.close(); 
				if (pstmt!=null) pstmt.close(); 
				if (rs!=null) rs.close(); 
				
			}catch(Exception e2) { 
				
			} 
			
		}

}}