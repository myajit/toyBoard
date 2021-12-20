package studyBoard.board.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import studyBoard.board.model.dao.BoardDao;
import studyBoard.board.model.dto.Board;
import studyBoard.common.db.JDBCTemplate;
import studyBoard.common.page.pagination;

public class BoardService {

	private JDBCTemplate template = JDBCTemplate.getInstance();
	private BoardDao boardDao = new BoardDao();
	
	public List<Board> selectBoardAll() {
	
		List<Board> boardList = null;
		
		Connection conn = template.getConnection();
		
		try {
			boardList = boardDao.selectBoardAll(conn);
		} finally {
			template.close(conn);
		}
		
		return boardList;
		
	}

	public Board serlectBoardByIdx(int boardIdx) {
		
		Board board = null;
		int hit = 0;
		
		Connection conn = template.getConnection();
		
		try {
			board = boardDao.selectBoardByidx(boardIdx, conn);
			hit = boardDao.selectByHit(boardIdx, conn);
			boardDao.insertHit(boardIdx,hit, conn);
		} finally {
			template.close(conn);
		}
		
		return board;
	}

	public void insertBoard(Board board) {
		
		Connection conn = template.getConnection();
		
		try {
			boardDao.insertBoard(board,conn);
//			for (FileDTO fileDTO : fileDTOs) {
//				boardDao.insertFile(fileDTO,conn);
//			}
			template.commit(conn);
		}catch (Exception e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
		
	}

	public void updateBoard(Board board) {
		
		Connection conn = template.getConnection();
		
		try {
			boardDao.updateBoard(board, conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			e.printStackTrace();
		}finally {
			template.close(conn);
		}
		
	}

	public void del(int boardIdx) {
		
		Connection conn = template.getConnection();
		
		try {
			boardDao.deleteBoard(boardIdx,conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			e.printStackTrace();
		}finally {
			template.close(conn);
		}
		
	}

	public List<Board> selectBoardPage(pagination pagination) {
		List<Board> boardList = null;
		
		Connection conn = template.getConnection();
		
		try {
			boardList = boardDao.selectBoardPage(pagination,conn);
		} finally {
			template.close(conn);
		}
		
		return boardList;
	}
	
}
