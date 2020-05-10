package bit.com.a.user;

import java.util.List;

import bit.com.a.wishlist.WishListDto;

public class UserDto {

	private int id;

	private String userId;

	private String userPassword;

	private String name;

	private String email;

	private int age;

	private String phoneNumber;

	private String address;

	private String auth;

	private String registeredAt;

	private String registeredBy;

	private String updatedAt;

	private String updatedBy;

	private String profileImage;

	private String unRegisteredAt;

	private String unRegisteredBy;

	private List<WishListDto> wishList;

	public List<WishListDto> getWishList() {
		return this.wishList;
	}

	public void setWishList(List<WishListDto> wishList) {
		this.wishList = wishList;
	}

	// 회원수 저장용
	private int count;

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public UserDto() {
	}

	public UserDto(int id, String userId, String userPassword, String name, String email, int age, String phoneNumber,
			String address, String auth, String registeredAt, String registeredBy, String updatedAt, String updatedBy,
			String profileImage, String unRegisteredAt, String unRegisteredBy) {
		this.id = id;
		this.userId = userId;
		this.userPassword = userPassword;
		this.name = name;
		this.email = email;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.auth = auth;
		this.registeredAt = registeredAt;
		this.registeredBy = registeredBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
		this.profileImage = profileImage;
		this.unRegisteredAt = unRegisteredAt;
		this.unRegisteredBy = unRegisteredBy;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAuth() {
		return this.auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getRegisteredAt() {
		return this.registeredAt;
	}

	public void setRegisteredAt(String registeredAt) {
		this.registeredAt = registeredAt;
	}

	public String getRegisteredBy() {
		return this.registeredBy;
	}

	public void setRegisteredBy(String registeredBy) {
		this.registeredBy = registeredBy;
	}

	public String getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getProfileImage() {
		return this.profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getUnRegisteredAt() {
		return this.unRegisteredAt;
	}

	public void setUnRegisteredAt(String unRegisteredAt) {
		this.unRegisteredAt = unRegisteredAt;
	}

	public String getUnRegisteredBy() {
		return this.unRegisteredBy;
	}

	public void setUnRegisteredBy(String unRegisteredBy) {
		this.unRegisteredBy = unRegisteredBy;
	}

	public UserDto id(int id) {
		this.id = id;
		return this;
	}

	public UserDto userId(String userId) {
		this.userId = userId;
		return this;
	}

	public UserDto userPassword(String userPassword) {
		this.userPassword = userPassword;
		return this;
	}

	public UserDto name(String name) {
		this.name = name;
		return this;
	}

	public UserDto email(String email) {
		this.email = email;
		return this;
	}

	public UserDto age(int age) {
		this.age = age;
		return this;
	}

	public UserDto phoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public UserDto address(String address) {
		this.address = address;
		return this;
	}

	public UserDto auth(String auth) {
		this.auth = auth;
		return this;
	}

	public UserDto registeredAt(String registeredAt) {
		this.registeredAt = registeredAt;
		return this;
	}

	public UserDto registeredBy(String registeredBy) {
		this.registeredBy = registeredBy;
		return this;
	}

	public UserDto updatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

	public UserDto updatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
		return this;
	}

	public UserDto profileImage(String profileImage) {
		this.profileImage = profileImage;
		return this;
	}

	public UserDto unRegisteredAt(String unRegisteredAt) {
		this.unRegisteredAt = unRegisteredAt;
		return this;
	}

	public UserDto unRegisteredBy(String unRegisteredBy) {
		this.unRegisteredBy = unRegisteredBy;
		return this;
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", userId='" + getUserId() + "'" + ", userPassword='" + getUserPassword()
				+ "'" + ", name='" + getName() + "'" + ", email='" + getEmail() + "'" + ", age='" + getAge() + "'"
				+ ", phoneNumber='" + getPhoneNumber() + "'" + ", address='" + getAddress() + "'" + ", auth='" + getAuth() + "'"
				+ ", registeredAt='" + getRegisteredAt() + "'" + ", registeredBy='" + getRegisteredBy() + "'" + ", updatedAt='"
				+ getUpdatedAt() + "'" + ", updatedBy='" + getUpdatedBy() + "'" + ", profileImage='" + getProfileImage() + "'"
				+ ", unRegisteredAt='" + getUnRegisteredAt() + "'" + ", unRegisteredBy='" + getUnRegisteredBy() + "'" + "}";
	}

}
