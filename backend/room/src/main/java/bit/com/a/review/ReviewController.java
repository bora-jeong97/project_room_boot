package bit.com.a.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    // 리뷰 쓰기
    @GetMapping("/write")
    public String write(ReviewDto review) {
        System.out.println("리뷰 컨트롤러");
        int number = 0;
        System.out.println("쓰기 " + review.toString());

        number = reviewService.write(review);
        System.out.println(number);

        return number > 0 ? "YES" : "NO";
    }

    // 리뷰 수정
    @GetMapping("/update")
    public String update(ReviewDto review) {
        System.out.println("수정 " + review.toString());
        int number = reviewService.update(review);
        return number > 0 ? "YES" : "NO";
    }

    // 리뷰 삭제
    @GetMapping("/delete")
    public String delete(ReviewDto review) {
        System.out.println("삭제 " + review.toString());
        int number = reviewService.delete(review);
        return number > 0 ? "NO" : "YES";
    }

    // 리뷰 디테일
    @GetMapping("/getReview")
    public ReviewDto getReview(int id) {
        System.out.println("디테일 id: " + id);
        return reviewService.getReview(id);
    }


    // 중개사가 답글 쓰기
    @GetMapping("/replyInsert")
    public String replyInsert(ReviewDto review, int id) throws Exception {
        System.out.println("중개사 답글 " + review.toString());
        int number = reviewService.replyInsert(review, id);
        return number > 0 ? "YES" : "NO";
    }

    // 그룹번호에 따른 행(row) 번호 늘려주기
    @GetMapping("/replyUpdate")
    public int replyUpdate(ReviewDto review, int id) throws Exception {
        System.out.println("행번호 늘려준다, 받아라 " + review.toString());
        return reviewService.replyUpdate(review, id);
    }


    // 리뷰 리스트
    @GetMapping("/getReviewList")
    public List<ReviewDto> getReviewList(ReviewParam param) {
        System.out.println("ReviewController getReviewList()");
        System.out.println(param.toString());
        System.out.println("현재 페이지 수: " + param.getPageNumber());
        
        int sn = param.getPageNumber() - 1;     // 0, 1, 2
        System.out.println("sn: " + sn);
        int start = sn * param.getRecordCountPerPage();     // 1 11
        System.out.println("start: " + start);         // -10 이라서 sts에서 오류 뜸
        
        param.setStart(start);

        return reviewService.getReviewList(param);
    }

    // getReviewList와 함께 쓰일 총 리뷰 수
    @GetMapping("/getReviewCount")
    public int getReviewCount(ReviewParam param) {
        System.out.println("총 리뷰 수 " + param.toString());

        return reviewService.getReviewCount(param);
    }

    // 사용자 리뷰 수
    @GetMapping("/getUserCount")
    public int getUserCount() {
        return reviewService.getUserCount();
    }

    // 리뷰 등급별 갯수
    @GetMapping("/getBestCount")
    public int getBestCount() {
        return reviewService.getBestCount();
    }

    @GetMapping("/getGoodCount")
    public int getGoodCount() {
        return reviewService.getGoodCount();
    }

    @GetMapping("/getFineCount")
    public int getFineCount() {
        return reviewService.getFineCount();
    }

    @GetMapping("/getBadCount")
    public int getBadCount() {
        return reviewService.getBadCount();
    }
    
}