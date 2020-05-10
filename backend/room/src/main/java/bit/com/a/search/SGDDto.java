package bit.com.a.search;

public class SGDDto {
    
    private int id;                 //AI , PK , Dong의 ID임
    private String sName;           
    private String gName;           
    private String dName;           
    private int count;           //Dong의 Count
    private double latitude;        //DONG의 위도
    private double longitude;       //DONG의 경도

    public SGDDto() {

    }

    @Override
    public String toString() {
        return "SGDDto [count=" + count + ", dName=" + dName + ", gName=" + gName + ", id=" + id + ", latitude="
                + latitude + ", longitude=" + longitude + ", sName=" + sName + "]";
    }

    public SGDDto(int id, String sName, String gName, String dName, int count, double latitude, double longitude) {
        this.id = id;
        this.sName = sName;
        this.gName = gName;
        this.dName = dName;
        this.count = count;
        this.latitude = latitude;
        this.longitude = longitude;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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
    

    
}