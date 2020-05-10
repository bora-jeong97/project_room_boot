package bit.com.a.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QnADao {

	// 메인
	public List<QnADto> getQnAList(Param param);

	public int getQnACount(Param param);

	// 글쓰기
	public int getRef(QnADto dto);

	public int writeQnA(QnADto dto);

	// 디테일
	public QnADto getQnA(int id);

	// 수정
	public int updateQnA(QnADto dto);

	// 삭제
	public int deleteQnA(int id);

	// 답글
	public QnADto getReply(int id); // 3개의 값 ref step dept
	// public QnADto replyQnAInsert(QnADto dto); //type을 반환해주기 위함임 실행잘 안되서 잠시 봉인
	// Mapper method 'bit.com.a.qna.QnADao.replyQnAInsert' has an unsupported return
	// type
	// insert Mapper는 디비에 저장만 하되 리턴값이 없어서 그런걸수도 있음

	public int replyQnAInsert(QnADto dto);

	// 유저가 질문한 내용의 답글들만 보기
	public List<QnADto> getQnaUserList(QnADto qnADto);

	// 공인중개사가 질문한 내용의 답글들만 달기
	public List<QnADto> getQnaBrokerList(QnADto qnADto);

}
