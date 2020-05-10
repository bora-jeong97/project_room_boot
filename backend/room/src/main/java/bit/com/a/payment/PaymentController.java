package bit.com.a.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bit.com.a.broker.BrokerDto;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
	PaymentService service;

	@RequestMapping(value = "/payments", method = RequestMethod.GET)
	public int payments(BrokerDto brokerDto, PaymentDto paymentDto) {
		System.out.println("payments 컨트롤러 도착 ");

		int resulted = service.payments(brokerDto, paymentDto);

		System.out.println("insert된 PaymentDto = " + paymentDto);
		System.out.println("update된 BrokerDto = " + brokerDto);
		System.out.println("컨트롤러 최종 결과값은? resulted: " + resulted);

		return resulted;
	}

	@GetMapping(value = "/getPaymentAdminCount")
	public int getPaymentAdminCount() {

		return service.getPaymentAdminCount();
	}

	@RequestMapping(value = "/getPaymentAdmin", method = RequestMethod.GET)
	public List<PaymentDto> getPaymentAdmin(PaymentAdminParam paymentParam) {
		// System.out.println("getPaymentAdmin 컨트롤러 도착");

		// int sn = paymentParam.getPageNumber() - 1;

		// int start = sn * paymentParam.getRecordCountPerPage();

		// paymentParam.setStart(start);

		List<PaymentDto> dto = service.getPaymentAdmin(paymentParam);
		System.out.println("paymentList?" + dto);

		return dto;
	}

	@GetMapping("/getPaymentBrokerCount")
	public int getPaymentBrokerCount(int id) {
		return service.getPaymentBrokerCount(id);
	}

	// 회원의 결제 내역 리스트
	@GetMapping("/getPaymentBrokerList")
	public List<PaymentDto> getPaymentBrokerList(PaymentAdminParam paymentAdminParam) {
		return service.getPaymentBrokerList(paymentAdminParam);
	}

}
