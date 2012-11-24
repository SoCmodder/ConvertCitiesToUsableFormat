/**
 * Created with IntelliJ IDEA.
 * User: Development
 * Date: 11/24/12
 * Time: 12:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class City {
    private String stateName;
    private String cityName;
    private float lat;
    private float lon;

    public City(String state, String city, float lat, float lon){
        this.stateName = state;
        this.cityName = city;
        this.lat = lat;
        this.lon = lon;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
