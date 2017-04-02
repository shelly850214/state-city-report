/**********************************************************
 * Class Name     : cityInfo
 * Author         : Shao yu Cheng, Anthony Massacci
 * Date           : Mar 31 , 2017
 * Course/Section : CSC 264 - 001
 * Program Description: The class will store the city's information.
 *
 * Methods: + constructor(String name, int pop) 	: none
 *			+ getCityPop()                      	: int
 *			+ getCityName()                     	: String
 *			+ setCityPop(int cityPop)               : void
 *			+ setCityName(String cityName)			: void
 *		    + toString()							: String
 *
 **********************************************************/
public class CityInfo
{
    //local constants

    //local variables
    int cityPop;        //local city's population
    String cityName;    //local city's name

    /******************************************************************/

    /**********************************************************
     * Method Name    : cityInfo
     * Author         : Shao yu Cheng
     * Date           : Mar 31 , 2017
     * Course/Section : CSC 264 - 001
     * Program Description: The constructor of cityInfo which
     *    will get and store the city's name and population.
     *
     * BEGIN cityInfo
     *    the local population = the population gets from file
     *    the local name = the name gets from file
     * END cityInfo
     **********************************************************/
    public CityInfo(String name, int pop)
    {
        cityPop  = pop;    // the local population = the population gets from file
        cityName = name;   // the local name = the name gets from file
    } //END cityInfo

    /**********************************************************
     * Method Name    : getCityPop
     * Author         : Shao yu Cheng
     * Date           : Mar 31 , 2017
     * Course/Section : CSC 264 - 001
     * Program Description: Tto return only city's population.
     *
     * BEGIN getCityPop
     *    return city population
     * END getCityPop
     **********************************************************/
    public int getCityPop()
    {
        return cityPop;   //return city population
    } //END getCityPop

    /**********************************************************
     * Method Name    : setCityPop
     * Author         : Shao yu Cheng
     * Date           : Mar 31 , 2017
     * Course/Section : CSC 264 - 001
     * Program Description: To reset the city's population.
     *
     * BEGIN setCityPop
     *    local city population = new city population
     * END setCityPop
     **********************************************************/
    public void setCityPop(int cityPop)
    {
        this.cityPop = cityPop;   //local city population = new city population
    } //END setCityPop

    /**********************************************************
     * Method Name    : setCityName
     * Author         : Shao yu Cheng
     * Date           : Mar 31 , 2017
     * Course/Section : CSC 264 - 001
     * Program Description: To reset the city's name.
     *
     * BEGIN setCityName
     *    local city name = new city name
     * END setCityName
     **********************************************************/
    public void setCityName(String cityName)
    {
        this.cityName = cityName;   //local city name = new city name
    } //END setCityName

    /**********************************************************
     * Method Name    : toString
     * Author         : Shao yu Cheng
     * Date           : Mar 31 , 2017
     * Course/Section : CSC 264 - 001
     * Program Description: To print the city information.
     *
     * BEGIN toString
     *    return city name and city population
     * END toString
     **********************************************************/
    public String toString()
    {
        return cityName + "\t" + cityPop; //return city name and city population
    } //END toString

}
