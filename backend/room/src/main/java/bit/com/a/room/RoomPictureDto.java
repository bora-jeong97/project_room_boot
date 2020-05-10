package bit.com.a.room;

public class RoomPictureDto {

  private int id;
  private String fileName;

  private String realFileName;

  private int roomId;

  private int downloadCount;

  public RoomPictureDto() {

  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", fileName='" + getFileName() + "'" + ", realFileName='" + getRealFileName()
        + "'" + ", roomId='" + getRoomId() + "'" + ", downloadCount='" + getDownloadCount() + "'" + "}";
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFileName() {
    return this.fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getRealFileName() {
    return this.realFileName;
  }

  public void setRealFileName(String realFileName) {
    this.realFileName = realFileName;
  }

  public int getRoomId() {
    return this.roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }

  public int getDownloadCount() {
    return this.downloadCount;
  }

  public void setDownloadCount(int downloadCount) {
    this.downloadCount = downloadCount;
  }

  public RoomPictureDto(int id, String fileName, String realFileName, int roomId, int downloadCount) {
    this.id = id;
    this.fileName = fileName;
    this.realFileName = realFileName;
    this.roomId = roomId;
    this.downloadCount = downloadCount;
  }

}