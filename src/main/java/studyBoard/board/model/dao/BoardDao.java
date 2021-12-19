package studyBoard.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import studyBoard.board.model.dto.Board;
import studyBoard.common.db.JDBCTemplate;

public class BoardDao {
	JDBCTemplate template = JDBCTemplate.getInstance();
	
	public List<Board> selectBoardAll(Connection conn) {
		String sql = "select * from \"board\" order by table_idx desc";
		PreparedStatement pstm = null;
		ResultSet rset = null;
		Board board = null;
		List<Board> boardList = new ArrayList<>();
		
		try {
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				board = new Board();
				board.setTableIdx(rset.getInt("table_idx"));
				board.setTitle(rset.getString("title"));
				board.setContent(rset.getString("content"));
				board.setUserId(rset.getString("user_id"));
				board.setWtDate(rset.getDate("wt_date"));
				board.setHit(rset.getInt("hit"));
				board.setLove(rset.getInt("love"));
				boardList.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			template.close(rset, pstm);
		}
		
		return boardList;
	}

	public Board selectBoardByidx(int boardIdx, Connection conn) {
		String sql = "select * from \"board\" where table_idx = ?";
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		Board board = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, boardIdx);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				board = new Board();
				board.setTableIdx(rset.getInt("table_idx"));
				board.setTitle(rset.getString("title"));
				board.setContent(rset.getString("content"));
				board.setUserId(rset.getString("user_id"));
				board.setWtDate(rset.getDate("wt_date"));
				board.setHit(rset.getInt("hit"));
				board.setLove(rset.getInt("love"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			template.close(rset, pstm);
		}
		
		return board;
	}

	public void insertBoard(Board board, Connection conn) {
		
		String user = "jung";
		
		String sql = "insert into \"board\" "
				+ "(table_idx,title,content,user_id) "
				+ "values(table_seq.nextval,?,?,?)";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, board.getTitle());
			pstm.setString(2, board.getContent());
			pstm.setString(3, user);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			template.close(pstm);
		}
		
	}

	public void updateBoard(Board board, Connection conn) {
		
		PreparedStatement pstm = null;
		String query = "update \"board\" set title = ?, content = ? where table_idx = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, board.getTitle());
			pstm.setString(2, board.getContent());
			pstm.setInt(3, board.getTableIdx());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			template.close(pstm);
		}
	}

	public void deleteBoard(int boardIdx, Connection conn) {
		
		PreparedStatement pstm = null;
		String query = "delete from \"board\" where table_idx = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, boardIdx);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			template.close(pstm);
		}
		
	}

	public void insertHit(int boardIdx,int hit, Connection conn) {
		
		String user = "jung";
		
		String sql = "update \"board\" set hit=? where table_idx = ?";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, hit+1);
			pstm.setInt(2, boardIdx);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			template.close(pstm);
		}
		
	}

	public int selectByHit(int boardIdx,Connection conn) {
		String sql = "select hit from \"board\" where table_idx = ?";
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		int hit = 0;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, boardIdx);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				hit = rset.getInt("hit");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			template.close(rset, pstm);
		}
		
		return hit;
	}
	
}
