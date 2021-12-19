package studyBoard.board.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Board {
	
	private int tableIdx;
	private String title;
	private String content;
	private String userId;
	private Date wtDate;
	private int hit;
	private int love;
	
}
