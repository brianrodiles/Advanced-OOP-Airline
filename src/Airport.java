/**
 * Airport class to encapsulate attributes related to airports.
 */
public class Airport {
    private String name;
    private String code;
    private String city;
    private String state;
    private String country;
    private double fee;
    private boolean hasLounge;
    private double feesRev;

    /**
     * Empty constructor.
     * [Taken from Ruben Carmona]
     */
    public Airport() {
        this.name = "";
        this.code = "";
        this.city = "";
        this.state = "";
        this.country = "";
        this.fee = 0.0;
        this.hasLounge = false;
        this.feesRev = 0.0;
    }

    /**
     * Constructor with parameters.
     * [Taken from Ruben Carmona]
     * @param nameIn the airport name
     * @param codeIn the airport code
     * @param cityIn the airport city
     * @param stateIn the airport state
     * @param countryIn the airport country
     * @param feeIn the airport fee
     * @param hasLoungeIn the airport lounge
     */
    public Airport(String nameIn, String codeIn, String cityIn, String stateIn, String countryIn, double feeIn, boolean hasLoungeIn) {
        this.name = nameIn;
        this.code = codeIn;
        this.city = cityIn;
        this.state = stateIn;
        this.country = countryIn;
        this.fee = feeIn;
        this.hasLounge = hasLoungeIn;
    }

    /**
     * Setter for airport name.
     * [Taken from Ruben Carmona]
     * @param nameIn the airport name
     */
    public void setName(String nameIn) {
        this.name = nameIn;
    }

    /**
     * Getter for airport name.
     * [Taken from Ruben Carmona]
     * @return the airport name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for airport code.
     * [Taken from Ruben Carmona]
     * @param codeIn the airport code
     */
    public void setCode(String codeIn) {
        this.code = codeIn;
    }

    /**
     * Getter for airport code.
     * [Taken from Ruben Carmona]
     * @return the airport code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter for airport city.
     * [Taken from Ruben Carmona]
     * @param cityIn the airport city
     */
    public void setCity(String cityIn) {
        this.city = cityIn;
    }

    /**
     * Getter for airport city.
     * [Taken from Ruben Carmona]
     * @return the airport city
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter for airport state.
     * [Taken from Ruben Carmona]
     * @param stateIn the airport state
     */
    public void setState(String stateIn) {
        this.state = stateIn;
    }

    /**
     * Getter for airport state.
     * [Taken from Ruben Carmona]
     * @return the airport state
     */
    public String getState() {
        return state;
    }
    
    /**
     * Setter for airport country.
     * [Taken from Ruben Carmona]
     * @param countryIn the airport country
     */
    public void setCountry(String countryIn) {
        this.country = countryIn;
    }

    /**
     * Getter for airport country.
     * [Taken from Ruben Carmona]
     * @return the airport country
     */
    public String getCountry() {
        return country;
    }

    
    /**
     * Setter for airport fee.
     * [Taken from Ruben Carmona]
     * @param feeIn the airport fee
     */
    public void setFee(double feeIn) {
        this.fee = feeIn;
    }

    /**
     * Getter for airport fee.
     * [Taken from Ruben Carmona]
     * @return the airport fee
     */
    public double getFee() {
        return fee;
    }
    
    /**
     * Setter for airport has lounge.
     * [Taken from Ruben Carmona]
     * @param hasLoungeIn the airport lounge
     */
    public void setHasLounge(boolean hasLoungeIn) {
        this.hasLounge = hasLoungeIn;
    }

    /**
     * Getter for airport has lounge.
     * [Taken from Ruben Carmona]
     * @return the airport lounge
     */
    public boolean getHasLounge() {
        return hasLounge;
    }

     /**
     * Setter for revenues from fees.
     * [Taken from Ruben Carmona]
     * @param feesRevIn the airport fees revenue
     */
    public void setFeesRev(double feesRevIn) {
        this.feesRev = feesRevIn;
    }

    /**
     * Getter for revenues from fees.
     * [Taken from Ruben Carmona]
     * @return the airport fees revenue
     */
    public double getFeesRev() {
        return feesRev;
    }

}
