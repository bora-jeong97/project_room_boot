package bit.com.a.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/qna")
public class QnAController {

	@Autowired
	QnAService service;

	// 메인 리스트
	@RequestMapping(method = RequestMethod.GET, value = "/getQnAList")
	public List<QnADto> getQnAList(Param param) { // end주의 없어도됨
		System.out.println("getQnAList 컨트롤러 도착");
		System.out.println(param.toString());

		// int sn = param.getPageNumber() - 1; // 0 1 2
		// int start = sn * param.getRecordCountPerPage(); // 1 11

		// // int end = ( sn + 1 ) * param.getRecordCountPerPage(); // 10 20

		// param.setStart(start); // paramDto에 start를 넣어준다.

		// param.setEnd(end);

		List<QnADto> list = service.getQnAList(param);

		return list;
	}

	// 총 글수
	@RequestMapping(method = RequestMethod.GET, value = "/getQnACount")
	public int getQnACount(Param param) {
		System.out.println("getQnACount 컨트롤러 도착");

		int len = service.getQnACount(param);
		System.out.println("총수 (len): " + len);

		return len;
	}

	// 글쓰기
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "/writeQnA")
	public boolean writeQnA(QnADto dto) {
		System.out.println("writeQnA 컨트롤러 도착");

		boolean b = service.writeQnA(dto); // true and false
		System.out.println("글쓰기 결과값 : " + b);
		System.out.println(dto);

		return b;
	}

	// 답글
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "/replyQnAInsert")
	public int replyQnAInsert(QnADto dto, int id) {
		System.out.println("replyQnAInsert 컨트롤러 도착");

		int result = service.replyQnAInsert(dto, id); // 1이면 성공

		System.out.println("답글 결과값 : " + result);
		System.out.println("답글 생성 적용 후 dto : " + dto);

		return result;

	}

	// 디데일
	@RequestMapping(method = RequestMethod.GET, value = "/getQnA")
	public QnADto getQnA(int id) {
		System.out.println("getQnA 컨트롤러 도착");

		QnADto dto = service.getQnA(id);
		System.out.println(dto);
		return dto;
	}

	// 수정
	@RequestMapping(value = "/updateQnA", method = RequestMethod.GET)
	public int updateQnA(QnADto dto) throws Exception {
		System.out.println("updateQnA 컨트롤러 도착");
		int resulted = service.updateQnA(dto);
		System.out.println("변경된 날짜 dto =" + dto.toString());
		System.out.println("결과값은 ? " + resulted);

		return resulted;
	}

	@RequestMapping(value = "/deleteQnA", method = RequestMethod.GET)
	public int deleteQnA(int id) throws Exception {
		System.out.println("deleteQnA 컨트롤러 도착");
		int resulted = service.deleteQnA(id);

		return resulted;
	}

	// 유저가 질문한 내용의 답글들만 보기
	@GetMapping("/getQnaUserList")
	public List<QnADto> getQnaUserList(QnADto qnADto) {
		return service.getQnaUserList(qnADto);
	}

	// 공인중개사가 질문한 내용의 답글들만 달기
	@GetMapping("/getQnaBrokerList")
	public List<QnADto> getQnaBrokerList(QnADto qnADto) {
		return service.getQnaBrokerList(qnADto);
	}

}
