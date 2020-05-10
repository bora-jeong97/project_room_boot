package bit.com.a.room;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import bit.com.a.dummyData.DongDto;
import bit.com.a.dummyData.GuDto;
import bit.com.a.dummyData.SiDto;

@Mapper
@Repository
public interface RoomDao {

	public int addroom(RoomDto dto);

	public int addRoomOption(RoomOptionDto optionDto);

	public int addRoomPicture(RoomPictureDto roomPictureDto);

	public RoomDto getRoomMap(RoomDto dto);

	// 전체 리스트 출력
	public List<RoomDto> getRoomMapList(@Param("swLat") Double swLat, @Param("swLng") Double swLng, @Param("neLat") Double neLat, @Param("neLng") Double neLng,
    @Param("RoomOption1") String RoomOption1, @Param("RoomOption2") String RoomOption2, @Param("RoomOption3") String RoomOption3,
    @Param("PayOption1") String PayOption1, @Param("PayOption2") String PayOption2, @Param("PayOption3") String PayOption3,
	@Param("deposit") int deposit, @Param("lease") int lease, @Param("monthRent") int monthRent, @Param("roomSpace") int roomSpace, @Param("currentPage") int currentPage);
	
	//리스트 갯수 출력
	public int getRoomMapListCount(@Param("swLat") Double swLat, @Param("swLng") Double swLng, @Param("neLat") Double neLat, @Param("neLng") Double neLng,
    @Param("RoomOption1") String RoomOption1, @Param("RoomOption2") String RoomOption2, @Param("RoomOption3") String RoomOption3,
    @Param("PayOption1") String PayOption1, @Param("PayOption2") String PayOption2, @Param("PayOption3") String PayOption3,
    @Param("deposit") int deposit, @Param("lease") int lease, @Param("monthRent") int monthRent, @Param("roomSpace") int roomSpace);

	// room detail 출력
	public RoomDto getRoomDetail(RoomDto dto);

	public List<RoomDto> getRoomBrokerList(RoomDto dto);

	public int deletePictures(RoomPictureDto roomPictureDto);

	public int updateRoom(RoomDto dto);

	public int updateRoomOption(RoomOptionDto optionDto);

	public int deleteRoom(RoomDto dto);
	// 방 등록
	// public int addRoom(RoomDto dto);
	// public int addRoomOption(RoomOptionDto optionDto);

	// 방 등록 결과값
	/* public List<RoomDto> getRoomList(); */

	public List<DongDto> getDongByName(String dongTemp);	//이름으로 동 찾기. 중복이 있을 가능성을 감안하여 리스트로.

	public DongDto getDong(int value);

	public GuDto getGu(int value);

	public SiDto getSi(int value);

	public int updateDongCount(int value);

	public int updateGuCount(int value);

	public int updateSiCount(int value);

}
