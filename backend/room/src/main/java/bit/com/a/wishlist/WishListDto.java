package bit.com.a.wishlist;

import java.io.Serializable;
import java.util.List;

import bit.com.a.room.RoomDto;
import bit.com.a.room.RoomOptionDto;
import bit.com.a.room.RoomPictureDto;
import bit.com.a.user.UserDto;

public class WishListDto implements Serializable {

    private int id;

    private int USERId;

    private int ROOMId;

    private UserDto userDto;

    private RoomDto roomDto;

    private RoomOptionDto roomOptionDto;

	private List<RoomPictureDto> roomPictureDtoList;


    public WishListDto() {
    }    

    @Override
    public String toString() {
        return "WishListDto [ROOMId=" + ROOMId + ", USERId=" + USERId + ", id=" + id + "]";
    }

    public WishListDto(int id, int uSERId, int rOOMId) {
        this.id = id;
        USERId = uSERId;
        ROOMId = rOOMId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUSERId() {
        return USERId;
    }

    public void setUSERId(int uSERId) {
        USERId = uSERId;
    }

    public int getROOMId() {
        return ROOMId;
    }

    public void setROOMId(int rOOMId) {
        ROOMId = rOOMId;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public RoomDto getRoomDto() {
        return roomDto;
    }

    public void setRoomDto(RoomDto roomDto) {
        this.roomDto = roomDto;
    }

    public RoomOptionDto getRoomOptionDto() {
        return roomOptionDto;
    }

    public void setRoomOptionDto(RoomOptionDto roomOptionDto) {
        this.roomOptionDto = roomOptionDto;
    }

    public List<RoomPictureDto> getRoomPictureDtoList() {
        return roomPictureDtoList;
    }

    public void setRoomPictureDtoList(List<RoomPictureDto> roomPictureDtoList) {
        this.roomPictureDtoList = roomPictureDtoList;
    }    

}