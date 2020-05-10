package bit.com.a.payment;

import java.io.Serializable;

import bit.com.a.broker.BrokerDto;

public class PaymentDto implements Serializable {

	private int id;
	private int payment;
	private String paymentType; // 카카오 현금 이니시스 등
	private int brokerId; // id값 aaa같은 broker테이블의 id와 동일하다
	private String paymentAt; // now()
	private String paymentSum;

	// join을 통해 해당 broker의 정보를 받아오기
	private BrokerDto brokerDto;

	public BrokerDto getBrokerDto() {
		return this.brokerDto;
	}

	public void setBrokerDto(BrokerDto brokerDto) {
		this.brokerDto = brokerDto;
	}

	public String getpaymentSum() {
		return this.paymentSum;
	}

	public void setpaymentSum(String paymentSum) {
		this.paymentSum = paymentSum;
	}

	// private int pay10 = 100000;
	// private int pay20 = 200000;
	// private int pay30 = 300000;
	//
	// private int count10 = 10;
	// private int count20 = 20;
	// private int count30 = 30;
	//

	// 기본생성자
	public PaymentDto() {
	}
	// 전체 생성자

	public PaymentDto(int id, int payment, String paymentType, int brokerId, String paymentAt) {
		super();
		this.id = id;
		this.payment = payment;
		this.paymentType = paymentType;
		this.brokerId = brokerId;
		this.paymentAt = paymentAt;
	}

	// toString
	@Override
	public String toString() {
		return "PaymentDto [id=" + id + ", payment=" + payment + ", paymentType=" + paymentType + ", brokerId=" + brokerId
				+ ", paymentAt=" + paymentAt + "]";
	}

	// get set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public int getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(int brokerId) {
		this.brokerId = brokerId;
	}

	public String getPaymentAt() {
		return paymentAt;
	}

	public void setPaymentAt(String paymentAt) {
		this.paymentAt = paymentAt;
	}

}
