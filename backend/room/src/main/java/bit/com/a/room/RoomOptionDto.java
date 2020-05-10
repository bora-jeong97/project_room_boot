package bit.com.a.room;

import java.io.Serializable;

public class RoomOptionDto implements Serializable {

	private int id;

	private String tv;

	private String airconditioner;

	private String refrigerator; // 상세주소 기본주소는 DONG_ID

	private String aircleaner;

	private String bed;

	private String microwave;

	private String washer;

	private int roomId;

	// 기본 생성자
	public RoomOptionDto() {

	}

	@Override
	public String toString() {
		return "RoomOptionDto [tv=" + tv + ", airconditioner=" + airconditioner + ", refrigerator=" + refrigerator
				+ ", aircleaner=" + aircleaner + ", bed=" + bed + ", microwave=" + microwave + ", washer=" + washer
				+ ", roomId=" + roomId + "]";
	}

	public RoomOptionDto(String tv, String airconditioner, String refrigerator, String aircleaner, String bed,
			String microwave, String washer, int roomId) {
		super();
		this.tv = tv;
		this.airconditioner = airconditioner;
		this.refrigerator = refrigerator;
		this.aircleaner = aircleaner;
		this.bed = bed;
		this.microwave = microwave;
		this.washer = washer;
		this.roomId = roomId;
	}

	public String getTv() {
		return tv;
	}

	public void setTv(String tv) {
		this.tv = tv;
	}

	public String getAirconditioner() {
		return airconditioner;
	}

	public void setAirconditioner(String airconditioner) {
		this.airconditioner = airconditioner;
	}

	public String getRefrigerator() {
		return refrigerator;
	}

	public void setRefrigerator(String refrigerator) {
		this.refrigerator = refrigerator;
	}

	public String getAircleaner() {
		return aircleaner;
	}

	public void setAircleaner(String aircleaner) {
		this.aircleaner = aircleaner;
	}

	public String getBed() {
		return bed;
	}

	public void setBed(String bed) {
		this.bed = bed;
	}

	public String getMicrowave() {
		return microwave;
	}

	public void setMicrowave(String microwave) {
		this.microwave = microwave;
	}

	public String getWasher() {
		return washer;
	}

	public void setWasher(String washer) {
		this.washer = washer;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

}
