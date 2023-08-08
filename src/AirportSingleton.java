import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class to manage the instance for the airport manager singleton.
 * [Taken from Brian G. Rodiles Delgado]
 */
public class AirportSingleton {
    private static AirportSingleton airportManager;
    private Set<String> origAirportSet;
    private Set<String> destAirportSet;
    private Set<String> everyAirportSet;
    private Map<String, Airport> airMap;

    /**
     * Constructor to define sets.
     * [Taken from Brian G. Rodiles Delgado]
     */
    private AirportSingleton() {
        origAirportSet = new HashSet<>();
        destAirportSet = new HashSet<>();
        everyAirportSet  = new HashSet<>();
    }

    /**
     * Instantiator for airport singleton manager.
     * [Taken from Brian G. Rodiles Delgado]
     * @return the singleton instance
     */
    public static AirportSingleton getInstance() {
        if(airportManager == null) {
            airportManager = new AirportSingleton();
        }
        return airportManager;
    }

    /**
     * Adds an origin airport to its set.
     * [Taken from Brian G. Rodiles Delgado]
     * @param airport the name of origin airport
     */
    public void addOrigAirport(String airport) {
        origAirportSet.add(airport);
    }

    /**
     * Removes and origin airport from its set.
     * [Taken from Brian G. Rodiles Delgado]
     * @param airport the name of origin airport
     */
    public void removeOrigAirport(String airport) {
        origAirportSet.remove(airport);
    }

    /**
     * Adds a destination airport from its set.
     * [Taken from Brian G. Rodiles Delgado]
     * @param airport the name of destination airport
     */
    public void addDestAirport(String airport) {
        destAirportSet.add(airport);
    }

    /**
     * Removes a destination airport from its set.
     * [Taken from Brian G. Rodiles Delgado]
     * @param airport the name of destination airport
     */
    public void removeDestAirport(String airport) {
        destAirportSet.remove(airport);
    }

    /**
     * Initializes the every airport set.
     * [Taken from Brian G. Rodiles Delgado]
     */
    public void initEveryAirport() {
        everyAirportSet.addAll(origAirportSet);
        everyAirportSet.addAll(destAirportSet);
    }

    /**
     * Initializes the airport map.
     * [Taken from Brian G. Rodiles Delgado]
     * @param airMapIn the map of airports
     */
    public void initAirMap(Map<String, Airport> airMapIn) {
        airMap = airMapIn;
    }

    /**
     * Getter from airport map.
     * [Taken from Brian G. Rodiles Delgado]
     * @param airCode the airport code
     * @return the airport object associated with the code
     */
    public Airport getFromAirMap(String airCode) {
        return airMap.get(airCode);
    }

    /**
     * Getter for a specific origin airport name.
     * [Taken from Brian G. Rodiles Delgado]
     * @param code the origin airport code
     * @return the name of the origin airport
     */
    public String getOrigAirport(String code) {
        for(String origName : origAirportSet) {
            if(origName.substring(origName.length() - 4, origName.length() - 1).equals(code)) {
                return origName;
            }
        }
        return "None";
    }

    /**
     * Getter for a specific destination airport name.
     * [Taken from Brian G. Rodiles Delgado]
     * @param code the destination airport code
     * @return the name of the destination airport
     */
    public String getDestAirport(String code) {
        for(String destName : destAirportSet) {
            if(destName.substring(destName.length() - 4, destName.length() - 1).equals(code)) {
                return destName;
            }
        }
        return "None";
    }

    /**
     * Getter for a specific airport name from all the available ones.
     * [Taken from Brian G. Rodiles Delgado]
     * @param code the code of the airport
     * @return the name of the airport
     */
    public String getFromEveryAirport(String code) {
        for(String everyName : everyAirportSet) {
            if(everyName.substring(everyName.length() - 4, everyName.length() - 1).equals(code)) {
                return everyName;
            }
        }
        return "None";
    }

    /**
     * Prints the origin airport names from the origin set.
     * [Taken from Brian G. Rodiles Delgado]
     */
    public void printOrigNames() {
        int i = 1;
        for(String origName : origAirportSet) {
            System.out.println(i + ") " + origName);
            i++;
        }
        System.out.println();
    }

    /**
     * Prints the destination airport names from the destination set.
     * [Taken from Brian G. Rodiles Delgado]
     * @param origCode the origin airport code
     */
    public void printDestNames(String origCode) {
        int i = 1;
        boolean removedDup = false;
        for(String destName : destAirportSet) {
            if(destName.substring(destName.length() - 4, destName.length() - 1).equals(origCode) && removedDup == false) {
                removedDup = true;
            }
            else {
                System.out.println(i + ") " + destName);
                i++;
            }
        }
        System.out.println();
    }

    /**
     * Prints all the airport names from the every set.
     * [Taken from Brian G. Rodiles Delgado]
     */
    public void printEveryNames() {
        int i = 1;
        for(String everyName : everyAirportSet) {
            System.out.println(i + ") " + everyName);
            i++;
        }
        System.out.println();
    }
}
