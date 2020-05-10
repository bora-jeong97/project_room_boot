package bit.com.a.admin;

import java.io.Serializable;

public class Param implements Serializable {

	// search
	private String s_category;
	private String s_keyword;

	// paging
	private int pageNumber = 1; // 현재 페이지
	private int recordCountPerPage = 5; // 표현할 페이지의 글수
	// DB
	private int start = 1;

	public Param() {
	}

	public Param(String s_category, String s_keyword, int pageNumber, int recordCountPerPage, int start) {
		super();
		this.s_category = s_category;
		this.s_keyword = s_keyword;
		this.pageNumber = pageNumber;
		this.recordCountPerPage = recordCountPerPage;
		this.start = start;
	}

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

	@Override
	public String toString() {
		return "BbsParam [s_category=" + s_category + ", s_keyword=" + s_keyword + ", pageNumber=" + pageNumber
				+ ", recordCountPerPage=" + recordCountPerPage + ", start=" + start + "]";
	}
}