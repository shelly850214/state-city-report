/**
 * Created by Shelly on 2017/3/10.
 */
public class cityInfo {
    //local constants
    //local variables
    int cityPop;
    String cityName;

    public cityInfo(String name, int pop){
        cityPop  = pop;
        cityName = name;
    }

    public int getCityPop() {
        return cityPop;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityPop(int cityPop){
        this.cityPop = cityPop;
    }

    public void setCityName(String cityName){
        this.cityName = cityName;
    }

    public String toString() {
        return cityName + "\t" + cityPop ;
    }
}
