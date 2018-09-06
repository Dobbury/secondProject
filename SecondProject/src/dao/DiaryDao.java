package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.JobHoldUntil;

import Impl.DiaryImpl;
import db.DBClose;
import db.DBConnection;
import dto.DiaryDto;
import dto.DiarycommentDto;
import dto.JournalDto;

public class DiaryDao implements DiaryImpl {

	private static DiaryDao dao = new DiaryDao();

	private DiaryDao() {
	}

	public static DiaryDao getInstance() {
		return dao;
	}

	@Override
	public boolean addDiary(DiaryDto dto) {
<<<<<<< HEAD
		
		String sql = "INSERT INTO DIARY(CONTENT,TITLE,TDAY,ID,SEQ) VALUES(?,?,?,?,?,SEQ_DIARY.NEXTVAL)";
		
		Connection conn =null;
=======

		String sql = "INSERT INTO DIARY(CONTENT,TITLE,TDAY,ID,SEQ) VALUES(?,?,?,?,SEQ_DIARY.NEXTVAL)";

		Connection conn = null;
>>>>>>> 6befd792eeaec0f189fa5ed291f5166935bde9ec
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.makeConnection();
			psmt = conn.prepareStatement(sql);
<<<<<<< HEAD
			
=======
>>>>>>> 6befd792eeaec0f189fa5ed291f5166935bde9ec

			psmt.setString(1, dto.getContent());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getTday());
			psmt.setString(4, dto.getId());
<<<<<<< HEAD
			
=======

>>>>>>> 6befd792eeaec0f189fa5ed291f5166935bde9ec
			count = psmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return count > 0 ? true : false;
	}
<<<<<<< HEAD
	
	public List<DiaryDto> getDiaryList(){
		
		String sql = " SELECT SEQ, ID, TDAY, TITLE, CONTENT FROM DIARY ";
		
=======

	public List<JournalDto> getJournalList() {


		String sql = " SELECT SEQ, START_DATE, END_DATE, READCOUNT, ID, LIKE_CNT, WDATE, TITLE FROM JOURNAL ";

>>>>>>> 6befd792eeaec0f189fa5ed291f5166935bde9ec
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<JournalDto> list = new ArrayList<>();

		try {
			conn = DBConnection.makeConnection();
			System.out.println("1/6 getMemInfo suceess");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/6  getDiaryList suceess");

			rs = psmt.executeQuery();

			while (rs.next()) {
<<<<<<< HEAD
				list.add(new DiaryDto(rs.getString(5), rs.getString(4), rs.getString(3), rs.getString(2), rs.getInt(1)));
=======
				list.add(new JournalDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getInt(6), rs.getString(7), rs.getString(8)));
>>>>>>> 6befd792eeaec0f189fa5ed291f5166935bde9ec

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("getDiaryList failed");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
<<<<<<< HEAD
	
	public DiaryDto getDiaryDto(int seq) {
		String sql = " SELECT SEQ, ID, TDAY, TITLE, CONTENT FROM DIARY WHERE SEQ = ? ";
		
=======

	public JournalDto getJournalDto(int seq) {


		String sql = " SELECT SEQ, START_DATE, END_DATE, READCOUNT, ID, LIKE_CNT, WDATE, TITLE "
				+ "FROM JOURNAL WHERE SEQ = ? ";

>>>>>>> 6befd792eeaec0f189fa5ed291f5166935bde9ec
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		JournalDto dto = null;

		try {
			conn = DBConnection.makeConnection();
			System.out.println("1/6 getDiaryDto suceess");

			psmt = conn.prepareStatement(sql);
<<<<<<< HEAD
			System.out.println("2/6 getDiaryDto suceess");
			
=======
			System.out.println("2/6 getMemInfo suceess");
>>>>>>> 6befd792eeaec0f189fa5ed291f5166935bde9ec
			psmt.setInt(1, seq);
			
			rs = psmt.executeQuery();

			if (rs.next()) {
				dto = new JournalDto(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8));
			}
		} catch (SQLException e) {
			System.out.println("get information failed");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;

	}

	public List<DiaryDto> getDiaryList(String startdate, String enddate, String id) {
		String sql = " SELECT SEQ, ID, TDAY, TITLE, CONTENT FROM DIARY WHERE ? < TDAY AND ? > TDAY AND ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<DiaryDto> list = new ArrayList<>();

		try {
			conn = DBConnection.makeConnection();
			System.out.println("1/6 getMemInfo suceess");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getMemInfo suceess");

			psmt.setString(1, startdate);
			psmt.setString(2, enddate);
			psmt.setString(3, id);
			
			System.out.println("3/6 getMemInfo suceess");
			rs = psmt.executeQuery();
			System.out.println("4/6 getMemInfo suceess");
			while (rs.next()) {
<<<<<<< HEAD
				dto = new DiaryDto(rs.getString(5), rs.getString(4), rs.getString(3), rs.getString(2), rs.getInt(1));
=======
				list.add(new DiaryDto(rs.getString(5), rs.getString(4), rs.getString(3), rs.getString(2),
						rs.getInt(1)));
>>>>>>> 6befd792eeaec0f189fa5ed291f5166935bde9ec

			}
			System.out.println("5/6 getMemInfo suceess");
		} catch (SQLException e) {
			System.out.println("getDiaryDto failed");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}


	public int CommantWrite(int seq, String id, String dcomment) {
		String sql = " INSERT INTO DIARYCOMMENT(SEQ, DSEQ, ID, DCOMMENT,DDAY) " 
				+	 " VALUES(SEQ_DCOMMENT.NEXTVAL,?,?,?,SYSDATE)";
		
		Connection conn = null; 
		PreparedStatement psmt = null; 

		int count = 0; 

		try { 
		conn = DBConnection.makeConnection();
		psmt = conn.prepareStatement(sql);
		System.out.println("1/6");
		
		
		psmt.setInt(1, seq);
		System.out.println("aa");
		psmt.setString(2, id.trim());
		System.out.println("bb");
		psmt.setString(3, dcomment.trim());
		System.out.println("cc");
		count = psmt.executeUpdate(); 
		System.out.println("2/6");
		} catch (SQLException e) { 
		// TODO Auto-generated catch block 
		e.printStackTrace(); 
		} finally { 
		DBClose.close(psmt, conn, null); 
		System.out.println("3/6");
		} 

		return count; 
	}

	@Override
	public List<DiarycommentDto> Commantview(int seq) {
		String sql = " SELECT SEQ,ID,DCOMMENT,DDAY "
				+ " FROM DIARYCOMMENT "
				+ " WHERE DSEQ = ? "
				+ " ORDER BY SEQ ASC ";
		

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<DiarycommentDto> list = new ArrayList<>();

		try {
			conn = DBConnection.makeConnection();
			System.out.println("1/6 Commantview suceess");

			psmt = conn.prepareStatement(sql);
<<<<<<< HEAD
			System.out.println("2/6 Commantview suceess");
			
=======
			System.out.println("2/6 getMemInfo suceess");

>>>>>>> 6befd792eeaec0f189fa5ed291f5166935bde9ec
			psmt.setInt(1, seq);

			rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new DiarycommentDto(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));


			}
		} catch (SQLException e) {
			System.out.println("get information failed");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
<<<<<<< HEAD
			
	}
	
	public DiaryDto getMyDiaryDto(int seq, String id) {
		String sql = " SELECT SEQ, ID, TDAY, TITLE, CONTENT FROM DIARY WHERE SEQ = ? AND ID= ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		DiaryDto dto = null;
		
		try {
			conn = DBConnection.makeConnection();
			System.out.println("1/6 getMyDiaryDto suceess");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getMyDiaryDto suceess");
			
			psmt.setInt(1, seq);
			psmt.setString(2, id);

			rs = psmt.executeQuery();

			while (rs.next()) {
				dto = new DiaryDto(rs.getString(5), rs.getString(4), rs.getString(3), rs.getString(2), rs.getInt(1));

			}
		} catch (SQLException e) {
			System.out.println("getMyDiaryDto failed");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		System.out.println("getMyDiaryDto end");
		return dto;
=======

	}
	// ��ۻ���
	@Override
	public int CommentDelete(int seq) {

		String sql = " DELETE DIARYCOMMENT "
				+	" WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.makeConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
>>>>>>> 6befd792eeaec0f189fa5ed291f5166935bde9ec
	}
	

}
   
  
   
   
