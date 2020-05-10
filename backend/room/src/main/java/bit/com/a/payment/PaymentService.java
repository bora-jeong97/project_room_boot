package bit.com.a.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bit.com.a.broker.BrokerDto;

@Service
@Transactional
public class PaymentService {

	@Autowired
	PaymentDao dao;

	public int payments(BrokerDto brokerDto, PaymentDto paymentDto) {

		// select
		BrokerDto loginBroker = dao.getSelectedBroker(brokerDto); // id 값을 받아 조회한 해당 broker

		// Insert
		int insertResult = dao.insertPayment(paymentDto);

		System.out.println(insertResult); // 성공시 1

		int count = 0;

		if (paymentDto.getPayment() == 100000) {
			count = 10;
		} else if (paymentDto.getPayment() == 200000) {
			count = 22;
		} else {
			count = 35;
		}

		// 7이었을 경우 + 10 = 17
		loginBroker.setWriteCount((loginBroker.getWriteCount() + count));

		// update 브로커 write_count 업데이트
		int updateResult = dao.updatePaymentBroker(loginBroker); // 브로커 dto를 업데이트 해준다.

		if (insertResult > 0 && updateResult > 0) {
			return 1;
		} else if (insertResult > 0 && updateResult <= 0) { // insertResult만 실행
			return 2;
		} else if (insertResult <= 0 && updateResult > 0) { // updateResult만 실행
			return 3;
		}

		return -1; // 실패
	}

	// admin payment 리스트
	public List<PaymentDto> getPaymentAdmin(PaymentAdminParam paymentParam) {
		return dao.getPaymentAdmin(paymentParam);
	}

	public int getPaymentAdminCount() {

		return dao.getPaymentAdminCount();
	}

	public int getPaymentBrokerCount(int id) {
		return dao.getPaymentBrokerCount(id);
	}

	// 회원의 결제 내역 리스트
	public List<PaymentDto> getPaymentBrokerList(PaymentAdminParam paymentAdminParam) {
		return dao.getPaymentBrokerList(paymentAdminParam);
	}
}
