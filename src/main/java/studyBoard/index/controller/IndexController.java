package studyBoard.index.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import studyBoard.board.model.dto.Board;
import studyBoard.board.model.service.BoardService;
import studyBoard.common.page.Paging;
import studyBoard.common.page.pagination;

@Slf4j
@WebServlet(name="indexController", urlPatterns = "/index")
public class IndexController extends HttpServlet{
	
	private BoardService boardService = new BoardService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		List<Board> boardList = boardService.selectBoardAll();
		
//		for (Board board : boardList) {
//		board.setParseDate(board.dateparse(board.getWtDate()));
//		}
		
//		req.setAttribute("boardList", boardList);
		
		int page = 1;
		int range = 1;
		
		if(req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page")); 
			range = Integer.parseInt(req.getParameter("range")); 
		}

		int listCnt = boardService.selectBoardAll().size();
		int endPage = (int)Math.ceil(listCnt*0.1);
		
		pagination pagination = new pagination();
		pagination.pageInfo(page, range, listCnt);
		
		log.info("endpage={}",pagination.getEndPage());
		
		req.setAttribute("pagination", pagination);
		req.setAttribute("endPage", endPage);
		req.setAttribute("boardList", boardService.selectBoardPage(pagination));

		
		resp.setCharacterEncoding("utf-8");
		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
	}
	
}
