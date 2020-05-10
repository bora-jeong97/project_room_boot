package bit.com.a.payment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import bit.com.a.broker.BrokerDto;

@Mapper
@Repository
public interface PaymentDao {

	// broker writeCount +10
	public int updatePaymentBroker(BrokerDto dto);

	// select login한 Broker정보
	public BrokerDto getSelectedBroker(BrokerDto dto);

	// 결제 리스트
	public int insertPayment(PaymentDto dto);

	// admin payment 리스트
	public List<PaymentDto> getPaymentAdmin(PaymentAdminParam paymentParam);

	// 결제내역의 총 갯수
	public int getPaymentAdminCount();

	// 회원의 결제 내역 갯수
	public int getPaymentBrokerCount(int id);

	// 회원의 결제 내역 리스트
	public List<PaymentDto> getPaymentBrokerList(PaymentAdminParam paymentAdminParam);

}
