package studyBoard.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studyBoard.board.model.dto.Board;
import studyBoard.board.model.service.BoardService;

@WebServlet(name="boardController" , urlPatterns = "/board/*")
public class BoardController extends HttpServlet{

	BoardService boardService = new BoardService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] urlArr = req.getRequestURI().split("/");
		
		switch (urlArr[urlArr.length-1]) {
		case "write":
			write(req,resp);
			break;
		case "detail":
			detail(req,resp);
			break;
		case "write-input":
			writeInput(req,resp);
			break;
		case "update":
			update(req,resp);
			break;
		default:
			break;
		}
		
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	private void writeInput(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("utf-8");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		
		boardService.insertBoard(board);
		resp.sendRedirect("/index");
	}

	private void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int boardIdx = Integer.parseInt(req.getParameter("boardIdx"));
		Board board = boardService.serlectBoardByIdx(boardIdx);
		req.setAttribute("board", board);
		req.getRequestDispatcher("/WEB-INF/views/board/detail.jsp").forward(req, resp);
		
	}

	private void write(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(req, resp);
		
	}
	
}
