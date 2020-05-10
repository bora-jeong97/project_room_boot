package bit.com.a.review;

import java.io.Serializable;

public class ReviewParam implements Serializable {

    // paging
    private int pageNumber = 0;     // 현재 페이지
    private int recordCountPerPage = 10;    // 표현할 페이지의 글수

    // DB
    private int start = 1;    

    public ReviewParam() {
    }

    public ReviewParam(int pageNumber, int recordCountPerPage, int start) {     // , int end
        this.pageNumber = pageNumber;
        this.recordCountPerPage = recordCountPerPage;
        this.start = start;        
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
        return "ReviewParam [pageNumber=" + pageNumber + ", recordCountPerPage=" + recordCountPerPage
                + ", start=" + start + "]";
    }

    
}