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
		String sql = "selcet * from board";
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
	
}
