package bit.com.a.admin;

import java.util.List;

import bit.com.a.broker.BrokerDto;
import bit.com.a.payment.PaymentDto;

public class AdminDto {
	private int id;
	private String adminId;
	private String adminPassword;
	private String name;
	private String registered_at;
	private String un_registered_at;
	private String auth;

	private int roomCount; // 차트용 방의 개수

	private int userCount; // 차트용 유저의 수

	private int brokerCount; // 차트용 브로커의 수

	private String todayRevenue; // 차트용 오늘의 수익

	private String allRevenue; // 차트용 누적 수익

	private List<BrokerDto> brokerList; // 브로커 리스트 받아오기

	private List<PaymentDto> paymentChart; // 차트용 broker_id별 수익

	private List<DateParam> dayOfRoomList; // 요일별 방의 갯수 받아오기

	private int memberCount;

	private List<PaymentDto> dayOfMonth;

	private List<PaymentDto> vipList;

	public List<PaymentDto> getDayOfMonth() {
		return this.dayOfMonth;
	}

	public void setDayOfMonth(List<PaymentDto> dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public List<PaymentDto> getVipList() {
		return this.vipList;
	}

	public void setVipList(List<PaymentDto> vipList) {
		this.vipList = vipList;
	}

	public int getMemberCount() {
		return this.memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	private DateParam dateParam;

	public List<DateParam> getDayOfRoomList() {
		return this.dayOfRoomList;
	}

	public void setDayOfRoomList(List<DateParam> dayOfRoomList) {
		this.dayOfRoomList = dayOfRoomList;
	}

	public int getRoomCount() {
		return this.roomCount;
	}

	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}

	public int getUserCount() {
		return this.userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public int getBrokerCount() {
		return this.brokerCount;
	}

	public void setBrokerCount(int brokerCount) {
		this.brokerCount = brokerCount;
	}

	public String getTodayRevenue() {
		return this.todayRevenue;
	}

	public void setTodayRevenue(String todayRevenue) {
		this.todayRevenue = todayRevenue;
	}

	public String getAllRevenue() {
		return this.allRevenue;
	}

	public void setAllRevenue(String allRevenue) {
		this.allRevenue = allRevenue;
	}

	public List<PaymentDto> getPaymentChart() {
		return this.paymentChart;
	}

	public void setPaymentChart(List<PaymentDto> paymentChart) {
		this.paymentChart = paymentChart;
	}

	public List<BrokerDto> getBrokerList() {
		return this.brokerList;
	}

	public void setBrokerList(List<BrokerDto> brokerList) {
		this.brokerList = brokerList;
	}

	public DateParam getDateParam() {
		return this.dateParam;
	}

	public void setDateParam(DateParam dateParam) {
		this.dateParam = dateParam;
	}

	public AdminDto() {
	}

	public AdminDto(int id, String adminId, String adminPassword, String name, String registered_at,
			String un_registered_at, String auth) {
		this.id = id;
		this.adminId = adminId;
		this.adminPassword = adminPassword;
		this.name = name;
		this.registered_at = registered_at;
		this.un_registered_at = un_registered_at;
		this.auth = auth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistered_at() {
		return registered_at;
	}

	public void setRegistered_at(String registered_at) {
		this.registered_at = registered_at;
	}

	public String getUn_registered_at() {
		return un_registered_at;
	}

	public void setUn_registered_at(String un_registered_at) {
		this.un_registered_at = un_registered_at;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String toString() {
		return "asdf [id=" + id + ", adminId=" + adminId + ", adminPassword=" + adminPassword + ", name=" + name
				+ ", registered_at=" + registered_at + ", un_registered_at=" + un_registered_at + ", auth=" + auth + "]";
	}
}