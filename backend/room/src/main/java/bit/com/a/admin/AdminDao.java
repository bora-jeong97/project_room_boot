package bit.com.a.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import bit.com.a.broker.BrokerDto;
import bit.com.a.payment.PaymentDto;
import bit.com.a.user.UserDto;

@Mapper
@Repository
public interface AdminDao {
    public AdminDto adminLogin(AdminDto admin);

    public void brokerPermission(int id);

    public List<BrokerDto> getAllBrokerList(Param param);

    public int getAllBrokerCount(Param param);

    public BrokerDto getBrokerDetail(int id);

    public void brokerForcedDeletion(int id);

    public void brokerOutOfPermission(int id);

    public List<UserDto> getUserList(Param param);

    public int getUserCount(Param param);

    public int notPermittedBrokerCount(Param param);

    public List<BrokerDto> notPermittedBrokerList(Param param);

    public UserDto getUserDetail(int id);

    public void userForcedDeletion(int id);

    public int getGoodbyeBrokerCount(Param param);

    public List<BrokerDto> getGoodByeBrokerList(Param param);

    public DateParam getRoomCount();

    public RegiCountParam getRegisterCount();

    public List<PaymentDto> getPaymentList();

    public List<AdminDto> getAll();

    public void brokerResurrection(int id);

    public void userResurrection(int id);
}