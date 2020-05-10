package bit.com.a.qna;

import java.io.Serializable;

public class Param implements Serializable {

	// search
	private String s_category; //search 메뉴
	private String s_keyword;  //search 단어

	// paging
	private int pageNumber = 0;	// 현재 페이지
	private int recordCountPerPage = 10;	// 표현할 페이지의 글수
	
	// DB
	private int start = 1;
	
	//생성자
	public Param() {
		// TODO Auto-generated constructor stub
	}

	public Param(String s_category, String s_keyword, int pageNumber, int recordCountPerPage, int start) {
		super();
		this.s_category = s_category;
		this.s_keyword = s_keyword;
		this.pageNumber = pageNumber;
		this.recordCountPerPage = recordCountPerPage;
		this.start = start;
	}
	
	//toString
	@Override
	public String toString() {
		return "Param [s_category=" + s_category + ", s_keyword=" + s_keyword + ", pageNumber=" + pageNumber
				+ ", recordCountPerPage=" + recordCountPerPage + ", start=" + start + "]";
	}

	//get set
	public String getS_category() {
		return s_category;
	}

	public void setS_category(String s_category) {
		this.s_category = s_category;
	}

	public String getS_keyword() {
		return s_keyword;
	}

	public void setS_keyword(String s_keyword) {
		this.s_keyword = s_keyword;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	

	

	
	
	
}







