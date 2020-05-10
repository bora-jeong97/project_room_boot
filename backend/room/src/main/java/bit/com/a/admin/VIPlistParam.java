package bit.com.a.admin;

public class VIPlistParam {
	private int payment;
	private String id;
	
	public VIPlistParam() {
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "VIPlistParam [payment=" + payment + ", id=" + id + "]";
	}
}
