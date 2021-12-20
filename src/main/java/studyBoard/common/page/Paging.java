package studyBoard.common.page;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Paging {
	 	
	private int pageSize = 10;
	private int rangeSize = 10;
	private int curpage = 1;
	private int curRange = 1;
	private int listCnt;
	private int pageCnt;
	private int rangeCnt;
	private int startPage = 1;
	private int endPage = 1;
	private int startIndex = 0;
	private int prevPage;
	private int nextPage;

	public Paging(int listCnt, int curPage) {
		
		setCurpage(curPage);
		
		setListCnt(listCnt);
		
		setPageCnt(listCnt);
		
		setRangeCnt(pageCnt);
		
		rangeSetting(curPage);
		
		setStartIndex(curPage);
		
	}
	
	public void setPageCnt(int listCnt) {
		this.pageCnt = (int) Math.ceil(listCnt*1.0/pageSize);
	}
	
	public void setRangeCnt(int pageCnt) {
        this.rangeCnt = (int) Math.ceil(pageCnt*1.0/rangeSize);
    }
	
    public void rangeSetting(int curPage){
        
        setCurRange(curPage);        
        this.startPage = (curRange - 1) * rangeSize + 1;
        this.endPage = startPage + rangeSize - 1;
        
        if(endPage > pageCnt){
            this.endPage = pageCnt;
        }
        
        this.prevPage = curPage - 1;
        this.nextPage = curPage + 1;
    }
    
    public void setCurRange(int curPage) {
        this.curRange = (int)((curPage-1)/rangeSize) + 1;
    }
    
    public void setStartIndex(int curPage) {
        this.startIndex = (curPage-1) * pageSize;
    }
	
	
}
