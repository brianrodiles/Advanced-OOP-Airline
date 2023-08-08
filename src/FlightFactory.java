/**
 * Class that uses the factory design patter to create types of flights.
 */
public class FlightFactory {
    
    /**
     * Empty construtor.
     * [Taken from Brian G. Rodiles Delgado]
     */
    public FlightFactory() {
    }

    /**
     * Method to create a Domestic or International flight.
     * [Taken from Brian G. Rodiles Delgado]
     * @param flightType the fight type
     * @return flight object
     */
    public Flight createFlight(String flightType) {
        if(flightType.toLowerCase().equals("domestic")) {
            return new Domestic();
        }
        else {
            return new International();
        }
    }
}
