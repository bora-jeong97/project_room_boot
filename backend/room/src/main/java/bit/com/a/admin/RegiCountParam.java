package bit.com.a.admin;

import java.io.Serializable;

public class RegiCountParam implements Serializable {
	private int todayRegisterCount;
	private int yesterdayRegisterCount;
	
	public RegiCountParam() {
	}

	public RegiCountParam(int todayRegisterCount, int yesterdayRegisterCount) {
		super();
		this.todayRegisterCount = todayRegisterCount;
		this.yesterdayRegisterCount = yesterdayRegisterCount;
	}

	public int getTodayRegisterCount() {
		return todayRegisterCount;
	}

	public void setTodayRegisterCount(int todayRegisterCount) {
		this.todayRegisterCount = todayRegisterCount;
	}

	public int getYesterdayRegisterCount() {
		return yesterdayRegisterCount;
	}

	public void setYesterdayRegisterCount(int yesterdayRegisterCount) {
		this.yesterdayRegisterCount = yesterdayRegisterCount;
	}

	@Override
	public String toString() {
		return "RegiCountParam [todayRegisterCount=" + todayRegisterCount + ", yesterdayRegisterCount=" + yesterdayRegisterCount + "]";
	}
}
