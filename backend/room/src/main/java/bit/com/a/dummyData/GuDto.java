package bit.com.a.dummyData;

public class GuDto {

    private int id;                 //AI , PK
    private String name;           
    private String count;
    private int siId;
    private double latitude;        //위도
    private double longitude;       //경도

    private SiDto siDto;    //참조용 Dto


    public GuDto() {

    }

    public GuDto(int id, String name, String count, int siId, double latitude, double longitude, SiDto siDto) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.siId = siId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.siDto = siDto;
    }

    public GuDto(int id, String name, String count, int siId, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.siId = siId;
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

	public int getSiId() {
		return siId;
	}

	public void setSiId(int siId) {
		this.siId = siId;
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

	public SiDto getSiDto() {
		return siDto;
	}

	public void setSiDto(SiDto siDto) {
		this.siDto = siDto;
	}

    @Override
    public String toString() {
        return "GuDto [count=" + count + ", id=" + id + ", latitude=" + latitude + ", longitude=" + longitude
                + ", name=" + name + ", siDto=" + siDto + ", siId=" + siId + "]";
    }

    
}