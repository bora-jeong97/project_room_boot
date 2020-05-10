package bit.com.a.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewService {

    @Autowired
    ReviewDao reviewDao;

    public int write(ReviewDto review) {
        System.out.println("review service");
        System.out.println(review.toString());
        review.setReviewCount(reviewDao.getCount(review));
        review.setRef(reviewDao.getRef(review));
        return reviewDao.write(review);
    }

    public int update(ReviewDto review) {
        System.out.println("review update");
        return reviewDao.update(review);
    }

    public Integer delete(ReviewDto review) {
        System.out.println("review delete");
        return reviewDao.delete(review);
    }

    public ReviewDto getReview(int id) {
        System.out.println("review detail");
        return reviewDao.getReview(id);
    }
                                            
    public int replyInsert(ReviewDto review, int id) throws Exception{
        System.out.println("reply insert");
        ReviewDto rDto = reviewDao.getReply(id);
        
        review.setRef(rDto.getRef());
        review.setStep(rDto.getStep());
        review.setDepth(rDto.getDepth());
        return reviewDao.replyInsert(review);
    }

    // 수정 요망
    public int replyUpdate(ReviewDto review, int id) throws Exception {
        System.out.println("reply update");
        return reviewDao.replyUpdate(review);
    }


    public List<ReviewDto> getReviewList(ReviewParam param) {
        System.out.println("Review List");
        return reviewDao.getReviewList(param);
    }

    // 리스트와 함께 사용 될 리뷰 수
    public int getReviewCount(ReviewParam param) {
        System.out.println("Review Count");
        return reviewDao.getReviewCount(param);
    }

    // 사용자 총 리뷰 수
    public int getUserCount(){
        System.out.println("User Review Count");
        return reviewDao.getUserCount();
    }

    // 리뷰 등급별 갯수
    public int getBestCount(){
        System.out.println("Best Review Count");
        return reviewDao.getBestCount();
    }

    public int getGoodCount(){
        System.out.println("Best Review Count");
        return reviewDao.getGoodCount();
    }

    public int getFineCount(){
        System.out.println("Best Review Count");
        return reviewDao.getFineCount();
    }

    public int getBadCount(){
        System.out.println("Best Review Count");
        return reviewDao.getBadCount();
    }

    

}