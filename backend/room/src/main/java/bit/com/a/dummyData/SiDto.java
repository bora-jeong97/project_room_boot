package bit.com.a.dummyData;

public class SiDto {

    private int id;                 //AI , PK
    private String name;           
    private String count;
    private double latitude;        //위도
    private double longitude;       //경도


    public SiDto() {
    }

    public SiDto(int id, String name, String count, double latitude, double longitude) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "SiDto [count=" + count + ", id=" + id + ", latitude=" + latitude + ", longitude=" + longitude
                + ", name=" + name + "]";
    }
    
}