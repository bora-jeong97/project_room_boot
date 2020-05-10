package bit.com.a.review;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReviewDao {

    public int getCount(ReviewDto review);

    public int getRef(ReviewDto review);
    
    public int write(ReviewDto review);
    
    public int update(ReviewDto review);
    
    public Integer delete(ReviewDto review);
    
    public ReviewDto getReview(int id);


    int replyInsert(ReviewDto review) throws Exception;

    public ReviewDto getReply(int id);

    int replyUpdate(ReviewDto review) throws Exception;


    public List<ReviewDto> getReviewList(ReviewParam param);
    
    public int getReviewCount(ReviewParam param);

    public int getUserCount();

    // 리뷰 등급별 갯수
    public int getBestCount();

    public int getGoodCount();

    public int getFineCount();

    public int getBadCount();
    	    
}