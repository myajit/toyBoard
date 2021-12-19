package studyBoard.board.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import studyBoard.board.model.dao.BoardDao;
import studyBoard.board.model.dto.Board;
import studyBoard.common.db.JDBCTemplate;

public class BoardService {

	private JDBCTemplate template = JDBCTemplate.getInstance();
	private BoardDao boardDao = new BoardDao();
	
	public List<Board> selectBoardAll() {
	
		List<Board> boardList = new ArrayList<Board>();
		
		Connection conn = template.getConnection();
		
		try {
			boardDao.selectBoardAll(conn);
		} finally {
			template.close(conn);
		}
		
		return boardList;
		
	}
	
}
