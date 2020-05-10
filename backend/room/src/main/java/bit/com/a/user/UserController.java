package bit.com.a.user;

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
@RequestMapping("/api/user") // 해당 컨트롤러의 기본 주소 매핑 따라서 /api/user/register 식으로 호출해야함
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private S3Service s3Service;

	// 회원가입
	@PostMapping("/register")

	public String register(UserDto user) {
		System.out.println("컨트롤러");
		int number = 0;
		System.out.println(user.toString());

		number = userService.register(user);
		System.out.println(number);

		return number > 0 ? "OK" : "FAIL";
	}

	// 관리자 단에서 멤버관리를 위한 용도
	@GetMapping("/getMemberList")
	public List<UserDto> getMemberList() {
		return userService.getMemberList();
	}

	// 로그인
	@PostMapping("/login")
	public UserDto login(UserDto user) {
		System.out.println(user.toString());
		return userService.login(user);
	}

	// 아이디 체크
	@PostMapping("/idCheck")
	public String idCheck(UserDto user) {
		int number = userService.idCheck(user);
		return number > 0 ? "FAIL" : "OK";
	}

	// 유저의 정보를 가져오기 위한용도
	// 마이페이지 정보 가져오기등
	@PostMapping("/getInformation")
	public UserDto getInformation(UserDto user) {
		System.out.println("넘어왔습니다");
		return userService.getInformation(user);
	}

	// 프로필 업데이트
	@PostMapping("/updateProfile")
	public String updateProfile(UserDto user, MultipartFile file) throws IOException {
		String imgPath = s3Service.upload(user.getProfileImage(), file);
		user.setProfileImage(S3Service.CLOUD_FRONT_DOMAIN_NAME + imgPath);
		int number = userService.updateProfile(user);
		return number > 0 ? "OK" : "FAIL";
	}

	@PostMapping("/updateUser")
	public String updateUser(UserDto user) {
		System.out.println(user.toString());
		int number = userService.updateUser(user);
		return number > 0 ? "OK" : "FAIL";
	}

}
