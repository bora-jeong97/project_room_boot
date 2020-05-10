package bit.com.a.qna;

import java.io.Serializable;
import java.util.List;

public class QnADto implements Serializable {

	private int id; // seq

	private String userId; // 작성자 id = email 형식
	private String brokerId;

	private int ref; // 그룹번호 //엮여있는 그룹
	private int step; // 행(row) //번호 +1씩 늘어나는것. 1, 2, 3, 4, 5
	private int depth; // 깊이 //선택한 것의 들여쓰기

	private String title;
	private String content;
	private String wdate; // 작성일자

	private int del = 0;
	private int auth;

	private String type; // 문의유형

	private String updateDate; // 수정일
	private String adminId; // 관리자 id

	private List<QnADto> qnaList;

	private int userCnt;
	private int brokerCnt;

	public int getUserCnt() {
		return this.userCnt;
	}

	public void setUserCnt(int userCnt) {
		this.userCnt = userCnt;
	}

	public int getBrokerCnt() {
		return this.brokerCnt;
	}

	public void setBrokerCnt(int brokerCnt) {
		this.brokerCnt = brokerCnt;
	}

	private int start;

	public int getStart() {
		return this.start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public List<QnADto> getQnaList() {
		return this.qnaList;
	}

	public void setQnaList(List<QnADto> qnaList) {
		this.qnaList = qnaList;
	}

	// 생성자
	public QnADto() {
		// TODO Auto-generated constructor stub
	}

	public QnADto(int id, String userId, String brokerId, int ref, int step, int depth, String title, String content,
			String wdate, int del, int auth, String type, String updateDate, String adminId) {
		super();
		this.id = id;
		this.userId = userId;
		this.brokerId = brokerId;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.auth = auth;
		this.type = type;
		this.updateDate = updateDate;
		this.adminId = adminId;
	}

	// toString

	@Override
	public String toString() {
		return "QnADto [id=" + id + ", userId=" + userId + ", brokerId=" + brokerId + ", ref=" + ref + ", step=" + step
				+ ", depth=" + depth + ", title=" + title + ", content=" + content + ", wdate=" + wdate + ", del=" + del
				+ ", auth=" + auth + ", type=" + type + ", updateDate=" + updateDate + ", adminId=" + adminId + "]";
	}

	// get set

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

}
