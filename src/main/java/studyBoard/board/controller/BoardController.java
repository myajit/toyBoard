package studyBoard.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="boardController" , urlPatterns = "/board/*")
public class BoardController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] urlArr = req.getRequestURI().split("/");
		
		switch (urlArr[urlArr.length-1]) {
		case "write":
			write(req,resp);
			break;
		default:
			break;
		}
		
	}

	private void write(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(req, resp);
		
	}
	
}
