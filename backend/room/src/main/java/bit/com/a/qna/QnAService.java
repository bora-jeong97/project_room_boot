package bit.com.a.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QnAService {

	@Autowired
	QnADao dao;

	// 메인 글 리스트
	public List<QnADto> getQnAList(Param param) {
		return dao.getQnAList(param);
	}

	// 글의 총수
	public int getQnACount(Param param) {
		return dao.getQnACount(param);
	}

	// 글쓰기

	public boolean writeQnA(QnADto dto) {
		dto.setRef(dao.getRef(dto));
		int len = dao.writeQnA(dto);
		return len > 0 ? true : false;
	}

	// 답글
	public int replyQnAInsert(QnADto dto, int id) {

		// System.out.println("first dto : " + dto.toString());
		QnADto getDto = dao.getReply(id);

		dto.setRef(getDto.getRef());
		dto.setStep(getDto.getStep());
		dto.setDepth(getDto.getDepth());
		dto.setType(getDto.getType());
		// System.out.println("second dto : " + dto.toString());

		int result = dao.replyQnAInsert(dto);

		// QnADto resultDto = dao.replyQnAInsert(dto);

		return result; // type값을 반환하기 위해 변경함
	}

	// 디테일
	public QnADto getQnA(int id) {
		QnADto dto = dao.getQnA(id);
		return dto;
	}

	// 수정
	public int updateQnA(QnADto dto) {

		int result = dao.updateQnA(dto); // 성공하면 1실패시 0

		return result;
	}

	// 삭제
	public int deleteQnA(int id) {

		int result = dao.deleteQnA(id);

		return result;
	}

	// 유저가 질문한 내용의 답글들만 보기
	public List<QnADto> getQnaUserList(QnADto qnADto) {
		return dao.getQnaUserList(qnADto);
	}

	// 공인중개사가 질문한 내용의 답글들만 달기
	public List<QnADto> getQnaBrokerList(QnADto qnADto) {
		return dao.getQnaBrokerList(qnADto);
	}

}
