/**
 * Created by Shelly on 2017/3/10.
 */
import java.util.Arrays;
import java.util.Comparator;

public class stateInfo {

    //local constants
    final static int CITIES_MAX = 30;
    final static int BY_NAME = 1;
    final static int BY_POP =2;

    //local variables
    String stateName;
    int statePop = 0;
    private int numCities;
    private int stateCount = 0;
    private cityInfo city[] = new cityInfo [30];

    public stateInfo(String stateName) {
        this.stateName = stateName;
        numCities = 0;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
        stateCount++;
    }

    public String getStateName() {
        return stateName;
    }

    public cityInfo getCity(int choice) {
        return city[choice-1];
    }

    public int getNumCities() {
        return numCities;
    }

    public int getStatePop() {
        for (int i = 0; i < numCities; i++){
            statePop = city [i].getCityPop() + statePop;
        }
        return statePop;
    }

    public void addCities(String name,int pop) {
        city[numCities] = new cityInfo(name, pop);
        numCities ++;
    }

    public void removeCities(int index) {
        city [index] = null;
        for (int i = index; i < numCities; i++){
            city [i] = city [i+1];
        }
        numCities--;
    }

    public boolean modifyCity(String name, int pop, int index) {
        try{
            city[index].setCityPop(pop);
            city[index].setCityName(name);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void sortCities(int choice) {
        System.out.print("Before sorting:");
        toString();
        if (choice == BY_NAME){
            Arrays.sort(city, Comparator.comparing(city -> city.cityName));
            System.out.print("After sorting:");
            toString();
        }
        else if (choice == BY_POP) {
            Arrays.sort(city, Comparator.comparing(city -> city.cityPop));
            System.out.print("After sorting:");
            toString();
        }
        else {
                System.out.print("Invalid choice.");
        }
    }

    public String toString() {
        String str = "";
        for(int i = 0; i < numCities; i++) {
            str += "\t\t" + (i+1) + ". " + city[i].toString() + "\r\n";
        }
        return str;
    }
}