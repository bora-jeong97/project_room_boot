package bit.com.a.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bit.com.a.broker.BrokerDto;
import bit.com.a.payment.PaymentDto;
import bit.com.a.user.UserDto;

@Service
@Transactional
public class AdminService {
    @Autowired
    AdminDao adminDao;

    public AdminDto adminLogin(AdminDto admin) {
        // System.out.println("서비스에 들어온 아이디는 " + admin.getAdminId());
        return adminDao.adminLogin(admin);
    }

    public void brokerPermission(int id) {
        adminDao.brokerPermission(id);
    }

    public List<BrokerDto> getAllBrokerList(Param param) {
        return adminDao.getAllBrokerList(param);
    }

    public int getAllBrokerCount(Param param) {
        return adminDao.getAllBrokerCount(param);
    }

    public BrokerDto getBrokerDetail(int id) {
        return adminDao.getBrokerDetail(id);
    }

    public void brokerForcedDeletion(int id) {
        adminDao.brokerForcedDeletion(id);
    }

    public void brokerOutOfPermission(int id) {
        adminDao.brokerOutOfPermission(id);
    }

    public List<UserDto> getUserList(Param param) {
        return adminDao.getUserList(param);
    }

    public int getUserCount(Param param) {
        return adminDao.getUserCount(param);
    }

    public int notPermittedBrokerCount(Param param) {
        return adminDao.notPermittedBrokerCount(param);
    }

    public List<BrokerDto> notPermittedBrokerList(Param param) {
        return adminDao.notPermittedBrokerList(param);
    }

    public UserDto getUserDetail(int id) {
        return adminDao.getUserDetail(id);
    }

    public void userForcedDeletion(int id) {
        adminDao.userForcedDeletion(id);
    }

    public int getGoodbyeBrokerCount(Param param) {
        return adminDao.getGoodbyeBrokerCount(param);
    }

    public List<BrokerDto> getGoodByeBrokerList(Param param) {
        return adminDao.getGoodByeBrokerList(param);
    }

    public DateParam getRoomCount() {
        return adminDao.getRoomCount();
    }

    public RegiCountParam getRegisterCount() {
        return adminDao.getRegisterCount();
    }

    public List<PaymentDto> getPaymentList() {
        return adminDao.getPaymentList();
    }

    public List<AdminDto> getAll() {
        return adminDao.getAll();
    }

    public void userResurrection(int id) {
        adminDao.userResurrection(id);
    };

    public void brokerResurrection(int id) {
        adminDao.brokerResurrection(id);
    };

}