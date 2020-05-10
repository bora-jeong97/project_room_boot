package bit.com.a.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

	public int register(UserDto user);

	public List<UserDto> getMemberList();

	public Integer idCheck(UserDto user);

	public UserDto login(UserDto user);

	public UserDto getInformation(UserDto user);

	public Integer updateProfile(UserDto user);

	public Integer updateUser(UserDto user);

}
