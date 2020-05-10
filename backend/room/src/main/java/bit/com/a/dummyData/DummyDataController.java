package bit.com.a.dummyData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dummyData") // 해당 컨트롤러의 기본 주소 매핑 따라서 /api/user/register 식으로 호출해야함
public class DummyDataController {

	@Autowired
	DummyDataService dummyDataService;

	// 데이터 넣기.
	@GetMapping("/createDummy")
	public String createDummy() {
        System.out.println("DummyDataController , createDummy 접근 확인");
        return dummyDataService.createDummy();
        
        /*
		int number = 0;
		System.out.println(user.toString());

		number = userService.register(user);
		System.out.println(number);

        return number > 0 ? "OK" : "FAIL";
        */
	}
}