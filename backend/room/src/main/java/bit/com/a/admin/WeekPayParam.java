package bit.com.a.admin;

public class WeekPayParam {
	private String week;	// 무슨 요일?
	private int payment;	// 매출
	
	public WeekPayParam() {
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "WeekPayParam [week=" + week + ", payment=" + payment + "]";
	}
}
