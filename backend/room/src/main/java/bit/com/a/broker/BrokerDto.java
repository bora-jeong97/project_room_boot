package bit.com.a.broker;

import java.util.List;

import bit.com.a.payment.PaymentDto;
import bit.com.a.review.ReviewDto;
import bit.com.a.room.RoomDto;

public class BrokerDto {

	private int id;

	private String brokerId;

	private String brokerPassword;

	private String name;

	private String email;

	private int age;

	private String phoneNumber;

	private String address;

	private String auth;

	private String certification;

	private String originalCertification;

	private String registeredAt;

	private String registeredBy;

	private String updatedAt;

	private String updatedBy;

	private String profileImage;

	private String originalProfileImage;

	private String unRegisteredAt;

	private String unRegisteredBy;

	private int writeCount;

	private List<PaymentDto> paymentList;

	private List<RoomDto> roomList;

	private List<ReviewDto> reviewList;

	private int start;

	public int getStart() {
		return this.start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public List<RoomDto> getRoomList() {
		return this.roomList;
	}

	public void setRoomList(List<RoomDto> roomList) {
		this.roomList = roomList;
	}

	public List<ReviewDto> getReviewList() {
		return this.reviewList;
	}

	public void setReviewList(List<ReviewDto> reviewList) {
		this.reviewList = reviewList;
	}

	public List<PaymentDto> getPaymentList() {
		return this.paymentList;
	}

	public void setPaymentList(List<PaymentDto> paymentList) {
		this.paymentList = paymentList;
	}

	// 회원수 저장용
	private int count;

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BrokerDto() {
	}

	public BrokerDto(int id, String brokerId, String brokerPassword, String name, String email, int age,
			String phoneNumber, String address, String auth, String certification, String originalCertification,
			String registeredAt, String registeredBy, String updatedAt, String updatedBy, String profileImage,
			String originalProfileImage, String unRegisteredAt, String unRegisteredBy, int writeCount) {
		this.id = id;
		this.brokerId = brokerId;
		this.brokerPassword = brokerPassword;
		this.name = name;
		this.email = email;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.auth = auth;
		this.certification = certification;
		this.originalCertification = originalCertification;
		this.registeredAt = registeredAt;
		this.registeredBy = registeredBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
		this.profileImage = profileImage;
		this.originalProfileImage = originalProfileImage;
		this.unRegisteredAt = unRegisteredAt;
		this.unRegisteredBy = unRegisteredBy;
		this.writeCount = writeCount;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrokerId() {
		return this.brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getBrokerPassword() {
		return this.brokerPassword;
	}

	public void setBrokerPassword(String brokerPassword) {
		this.brokerPassword = brokerPassword;
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

	public String getCertification() {
		return this.certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getOriginalCertification() {
		return this.originalCertification;
	}

	public void setOriginalCertification(String originalCertification) {
		this.originalCertification = originalCertification;
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

	public String getOriginalProfileImage() {
		return this.originalProfileImage;
	}

	public void setOriginalProfileImage(String originalProfileImage) {
		this.originalProfileImage = originalProfileImage;
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

	public int getWriteCount() {
		return this.writeCount;
	}

	public void setWriteCount(int writeCount) {
		this.writeCount = writeCount;
	}

	public BrokerDto id(int id) {
		this.id = id;
		return this;
	}

	public BrokerDto brokerId(String brokerId) {
		this.brokerId = brokerId;
		return this;
	}

	public BrokerDto brokerPassword(String brokerPassword) {
		this.brokerPassword = brokerPassword;
		return this;
	}

	public BrokerDto name(String name) {
		this.name = name;
		return this;
	}

	public BrokerDto email(String email) {
		this.email = email;
		return this;
	}

	public BrokerDto age(int age) {
		this.age = age;
		return this;
	}

	public BrokerDto phoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public BrokerDto address(String address) {
		this.address = address;
		return this;
	}

	public BrokerDto auth(String auth) {
		this.auth = auth;
		return this;
	}

	public BrokerDto certification(String certification) {
		this.certification = certification;
		return this;
	}

	public BrokerDto originalCertification(String originalCertification) {
		this.originalCertification = originalCertification;
		return this;
	}

	public BrokerDto registeredAt(String registeredAt) {
		this.registeredAt = registeredAt;
		return this;
	}

	public BrokerDto registeredBy(String registeredBy) {
		this.registeredBy = registeredBy;
		return this;
	}

	public BrokerDto updatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

	public BrokerDto updatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
		return this;
	}

	public BrokerDto profileImage(String profileImage) {
		this.profileImage = profileImage;
		return this;
	}

	public BrokerDto originalProfileImage(String originalProfileImage) {
		this.originalProfileImage = originalProfileImage;
		return this;
	}

	public BrokerDto unRegisteredAt(String unRegisteredAt) {
		this.unRegisteredAt = unRegisteredAt;
		return this;
	}

	public BrokerDto unRegisteredBy(String unRegisteredBy) {
		this.unRegisteredBy = unRegisteredBy;
		return this;
	}

	public BrokerDto writeCount(int writeCount) {
		this.writeCount = writeCount;
		return this;
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", brokerId='" + getBrokerId() + "'" + ", brokerPassword='"
				+ getBrokerPassword() + "'" + ", name='" + getName() + "'" + ", email='" + getEmail() + "'" + ", age='"
				+ getAge() + "'" + ", phoneNumber='" + getPhoneNumber() + "'" + ", address='" + getAddress() + "'" + ", auth='"
				+ getAuth() + "'" + ", certification='" + getCertification() + "'" + ", originalCertification='"
				+ getOriginalCertification() + "'" + ", registeredAt='" + getRegisteredAt() + "'" + ", registeredBy='"
				+ getRegisteredBy() + "'" + ", updatedAt='" + getUpdatedAt() + "'" + ", updatedBy='" + getUpdatedBy() + "'"
				+ ", profileImage='" + getProfileImage() + "'" + ", originalProfileImage='" + getOriginalProfileImage() + "'"
				+ ", unRegisteredAt='" + getUnRegisteredAt() + "'" + ", unRegisteredBy='" + getUnRegisteredBy() + "'"
				+ ", writeCount='" + getWriteCount() + "'" + "}";
	}

}
