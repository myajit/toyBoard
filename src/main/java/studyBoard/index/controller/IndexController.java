package studyBoard.index.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studyBoard.board.model.dto.Board;
import studyBoard.board.model.service.BoardService;

@WebServlet(name="indexController", urlPatterns = "/index")
public class IndexController extends HttpServlet{
	
	private BoardService boardService = new BoardService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Board> boardList = boardService.selectBoardAll();
		
		req.setAttribute("boardList", boardList);
		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
	}
	
}