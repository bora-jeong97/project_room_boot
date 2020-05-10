package bit.com.a.broker;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bit.com.a.s3.S3Service;

@RestController
@RequestMapping("/api/broker") // 해당 컨트롤러의 기본 주소 매핑 따라서 /api/user/register 식으로 호출해야함
public class BrokerController {
	@Autowired
	BrokerService brokerService;
	@Autowired
	private S3Service s3Service;

	// 회원가입
	@PostMapping("/register")
	public String register(BrokerDto user) {
		System.out.println("컨트롤러");
		int number = 0;
		System.out.println(user.toString());

		number = brokerService.register(user);
		System.out.println(number);

		return number > 0 ? "OK" : "FAIL";
	}

	// 관리자 단에서 멤버관리를 위한 용도
	@GetMapping("/getMemberList")
	public List<BrokerDto> getMemberList() {
		return brokerService.getMemberList();
	}

	// 아이디 체크
	@PostMapping("/idCheck")
	public String idCheck(BrokerDto user) {
		int number = brokerService.idCheck(user);
		return number > 0 ? "FAIL" : "OK";
	}

	// 로그인
	@PostMapping("/login")
	public BrokerDto login(BrokerDto user) {
		return brokerService.login(user);
	}

	// 유저의 정보를 가져오기 위한용도
	// 마이페이지 정보 가져오기등
	@PostMapping("/getInformation")
	public BrokerDto getInformation(BrokerDto user) {
		return brokerService.getInformation(user);
	}

	// 프로필 업데이트
	@PostMapping("/updateProfile")
	public String updateProfile(BrokerDto user, MultipartFile file) throws IOException {
		String imgPath = s3Service.upload(user.getProfileImage(), file);
		user.setProfileImage(S3Service.CLOUD_FRONT_DOMAIN_NAME + imgPath);
		int number = brokerService.updateProfile(user);
		return number > 0 ? "OK" : "FAIL";
	}

	@PostMapping("/updateUser")
	public String updateUser(BrokerDto user) {
		System.out.println(user.toString());
		int number = brokerService.updateUser(user);
		return number > 0 ? "OK" : "FAIL";
	}

	@PostMapping("/updateWriteCount")
	public Integer updateWriteCount(BrokerDto user) {
		return brokerService.updateWriteCount(user);
	}

}
