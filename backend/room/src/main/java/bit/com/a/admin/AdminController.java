package bit.com.a.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bit.com.a.broker.BrokerDto;
import bit.com.a.payment.PaymentDto;
import bit.com.a.user.UserDto;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    // 로그인
    @PostMapping("/adminLogin")
    public AdminDto adminLogin(AdminDto admin) {
        // System.out.println("체크할 아이디는 " + admin.getAdminId());
        // System.out.println("체크할 비번은 " + admin.getAdminPassword());
        System.out.println(admin.toString());
        return adminService.adminLogin(admin);
    }

    // 브로커 회원가입 승인여부
    @GetMapping("/brokerPermission")
    public void brokerPermission(int id) {
        // System.out.println("넘어온 브로커 id는 " + id);

        adminService.brokerPermission(id);
    }

    // 브로커리스트
    @GetMapping("/getAllBrokerList")
    public List<BrokerDto> getAllBrokerList(Param param) {
        // System.out.println("getBrokerList 메소드 호출");
        // System.out.println(param.toString());
        // System.out.println("현재 페이지 수는 " + param.getPageNumber());

        // int sn = param.getPageNumber() - 1; // 0, 1, 2...
        // // System.out.println("sn : " + sn);
        // int start = sn * param.getRecordCountPerPage();

        // param.setStart(start);

        return adminService.getAllBrokerList(param);
    }

    // 브로커 갯수구하기
    @GetMapping("/getAllBrokerCount")
    public int getBrokerCount(Param param) {
        // System.out.println("getBrokerCount 메소드 호출");

        System.out.println("asdfasdf " + param.toString());

        return adminService.getAllBrokerCount(param);
    }

    // 브로커 디테일
    @GetMapping("/getBrokerDetail")
    public BrokerDto getBrokerDetail(int id) {
        // System.out.println("넘어온 id는 " + id);
        return adminService.getBrokerDetail(id);
    }

    @GetMapping("/brokerForcedDeletion")
    public void brokerForcedDeletion(int id) {
        System.out.println("넘어온 아이디는 " + id);

        adminService.brokerForcedDeletion(id);
    }

    @GetMapping("/brokerOutOfPermission")
    public void brokerOutOfPermission(int id) {
        adminService.brokerOutOfPermission(id);
    }

    @GetMapping("/getUserList")
    public List<UserDto> getUserList(Param param) {
        System.out.println("param투스트링 " + param.toString());

        // int sn = param.getPageNumber() - 1; // 0, 1, 2...
        // // System.out.println("sn : " + sn);
        // int start = sn * param.getRecordCountPerPage();

        // param.setStart(start);

        return adminService.getUserList(param);
    }

    @GetMapping("/getUserCount")
    public int getUserCount(Param param) {
        System.out.println("param");
        System.out.println(param.getS_category());
        System.out.println(param.toString());
        return adminService.getUserCount(param);
    }

    @GetMapping("/notPermittedBrokerCount")
    public int notPermittedBrokerCount(Param param) {
        return adminService.notPermittedBrokerCount(param);
    }

    @GetMapping("/notPermittedBrokerList")
    public List<BrokerDto> notPermittedBrokerList(Param param) {
        // System.out.println(param.toString());
        int sn = param.getPageNumber() - 1; // 0, 1, 2...
        // System.out.println("sn : " + sn);
        int start = sn * param.getRecordCountPerPage();

        param.setStart(start);

        return adminService.notPermittedBrokerList(param);
    }

    @GetMapping("/getUserDetail")
    public UserDto getUserDetail(int id) {
        return adminService.getUserDetail(id);
    }

    @GetMapping("/userForcedDeletion")
    public void userForcedDeletion(int id) {
        System.out.println(id);
        adminService.userForcedDeletion(id);
    }

    @GetMapping("/getGoodbyeBrokerCount")
    public int getGoodbyeBrokerCount(Param param) {
        return adminService.getGoodbyeBrokerCount(param);
    }

    @GetMapping("/getGoodByeBrokerList")
    public List<BrokerDto> getGoodByeBrokerList(Param param) {

        int sn = param.getPageNumber() - 1; // 0, 1, 2...
        // System.out.println("sn : " + sn);
        int start = sn * param.getRecordCountPerPage();

        param.setStart(start);

        return adminService.getGoodByeBrokerList(param);
    }

    @GetMapping("/userResurrection")
    public void userResurrection(int id) {
        System.out.println("id " + id);
        adminService.userResurrection(id);
    }

    @GetMapping("/brokerResurrection")
    public void brokerResurrection(int id) {
        System.out.println("id " + id);
        adminService.brokerResurrection(id);
    }

    @GetMapping("/getRoomCount")
    public DateParam getRoomCount() {
        // System.out.println("getRoomCount 메소드 호출");

        DateParam param = adminService.getRoomCount();

        // System.out.println("파람투스트링 : " + param.toString());

        return param;
    }

    @GetMapping("/getRegisterCount")
    public RegiCountParam getRegisterCount() {
        RegiCountParam param = adminService.getRegisterCount();

        // System.out.println("param.toString(): " + param.toString());

        return param;
    }

    // 페이먼트테이블리스트
    @GetMapping("/getPaymentList")
    public List<PaymentDto> getPaymentList() {
        System.out.println("getPaymentList 호출");

        List<PaymentDto> list = adminService.getPaymentList();

        System.out.println("페이먼트 리스트 : " + list.toString());

        return list;
    }

    @GetMapping("/getAll")
    public List<AdminDto> getAll() {

        return adminService.getAll();
    }
}
