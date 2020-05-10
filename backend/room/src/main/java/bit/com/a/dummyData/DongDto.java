package bit.com.a.dummyData;

public class DongDto {
    
    private int id;                 //AI , PK
    private String name;           
    private String count;
    private int guId;
    private double latitude;        //위도
    private double longitude;       //경도

    private GuDto guDto;    //참조용 Dto


    public DongDto() {

    }

    public DongDto(int id, String name, String count, int guId, double latitude, double longitude, GuDto guDto) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.guId = guId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.guDto = guDto;
    }

    public DongDto(int id, String name, String count, int guId, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.guId = guId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getGuId() {
        return guId;
    }

    public void setGuId(int guId) {
        this.guId = guId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public GuDto getGuDto() {
        return guDto;
    }

    public void setGuDto(GuDto guDto) {
        this.guDto = guDto;
    }

	@Override
	public String toString() {
		return "DongDto [count=" + count + ", guDto=" + guDto + ", guId=" + guId + ", id=" + id + ", latitude="
				+ latitude + ", longitude=" + longitude + ", name=" + name + "]";
	}

    
}