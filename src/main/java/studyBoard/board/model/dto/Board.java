package studyBoard.board.model.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;

import lombok.Data;

@Data
public class Board {
	
	private int tableIdx;
	private String title;
	private String content;
	private String userId;
	private Date wtDate;
	private String parseDate;
	private int hit;
	private int love;
	
	public String dateparse(Date wtDate) {
			
		String parseDate = "";
		
		try {
			Date date = wtDate; 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			parseDate = sdf.format(date);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return parseDate;
	}

}
