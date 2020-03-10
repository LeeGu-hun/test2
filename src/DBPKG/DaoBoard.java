package DBPKG;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BoardVO;

public class DaoBoard extends DaoSet {
	public DaoBoard() {
		try {con = getConnection();} 
		catch (Exception e) {e.printStackTrace();} 
	}
	
	public int getListCount() {
		int result = 0;
		String sql = "select count(*) from board ";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			result = (rs.next())?rs.getInt(1):0;
		} catch (SQLException e) {
			System.out.println("getListCount error");
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	public List getBoardList(int page, int limit) {
		
		String sql = "select * " + 
		  "from (select rownum rn,num,name,pass,subject,content, " 
		  +"files,re_ref,re_lev,re_seq,readcount,regdate " 
		  +"from (select * from board order by num)) " 
		  +"where rn>=? and rn<=? ";
		int start = (page-1)*10+1;
		int end = start+limit-1;
		List result = new ArrayList();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO b = new BoardVO();
				b.setNum(rs.getInt("num"));
				b.setName(rs.getString("name"));
				b.setPass(rs.getString("pass"));
				b.setSubject(rs.getString("subject"));
				b.setContent(rs.getString("content"));
				b.setFiles(rs.getString("files"));
				b.setRe_ref(rs.getInt("re_ref"));
				b.setRe_lev(rs.getInt("re_lev"));
				b.setRe_seq(rs.getInt("re_seq"));
				b.setReadcount(rs.getInt("readcount"));
				b.setRegdate(rs.getDate("regdate"));
				result.add(b);
			}
		} catch (SQLException e) {
			System.out.println("getListCount error");
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	public boolean boardInsert(BoardVO b) {
		boolean result = false;
		String sql = "insert into board(num,name,pass, "
				+ "subject,content,files,re_ref, " 
				+ "re_lev,re_seq,readcount,regdate) "
				+ "values((select count(*)+1 from board)"
				+ ",?,?,?,?,?,?,0,0,0,sysdate) ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, b.getName());
			pstmt.setString(2, b.getPass());
			pstmt.setString(3, b.getSubject());
			pstmt.setString(4, b.getContent());
			pstmt.setString(5, b.getFiles());
			pstmt.setInt(6, b.getRe_ref());
			int cnt = pstmt.executeUpdate();
			result = (cnt>0)?true:false;
		} catch (SQLException e) {
			System.out.println("getListCount error");
			e.printStackTrace();
		} finally {
			closeDB();
		}		
		return result;
	}
	public BoardVO selectByNum(int num) {
		BoardVO b= new BoardVO();
		String sql = "select * from board where num=? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				b.setNum(rs.getInt("num"));
				b.setName(rs.getString("name"));
				b.setPass(rs.getString("pass"));
				b.setSubject(rs.getString("subject"));
				b.setContent(rs.getString("content"));
				b.setFiles(rs.getString("files"));
				b.setRe_ref(rs.getInt("re_ref"));
				b.setRe_lev(rs.getInt("re_lev"));
				b.setRe_seq(rs.getInt("re_seq"));
				b.setReadcount(rs.getInt("readcount"));
				b.setRegdate(rs.getDate("regdate"));
			}
		} catch (SQLException e) {
			System.out.println("getListCount error");
			e.printStackTrace();
		} finally {
			closeDB();
		}		
		return b;
	}
}
