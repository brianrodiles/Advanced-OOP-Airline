import java.util.ArrayList;
import java.util.Map;

/**
 * Flight class to encapsule all attributes and methods necessary 
 * to represent a flight from the consumable CSV file.
 */
public abstract class Flight{
    private int          id;
    private String       flightNumber;
    private Airport      origAirport;
    private Airport      destAirport;
    private String       depaDate;
    private String       depaTime;
    private int          duration;
    private int          distance;
    private int          timeDiff;
    private String       arrDate;
    private String       arrTime;
    private String       flightType;
    private double       surcharge;
    private boolean      isFoodServed;
    private double       routeCost;
    private int          minerPoints;
    private int          totalSeats;
    private int          firstSeats;
    private int          businSeats;
    private int          mainSeats;
    private double       firstPrice;
    private double       businPrice;
    private double       mainPrice;
    private boolean      isCancelled;
    private ArrayList<Ticket> flightTicketList;
    private int          firstSeatsSold;
    private int          businSeatsSold;
    private int          mainSeatsSold;
    private double       firstClassRevenue;
    private double       businClassRevenue;
    private double       mainClassRevenue;
    private double       totalMinerFee;
    private double       totalSecFee;
    private double       totalTax;

    /**
     * Empty constructor with empty values for Strings and zeros for integer variables.
     * [Taken from Ruben Carmona]
     */
    public Flight(){
        this.id = 0;
        this.flightNumber = "";
        this.depaDate = "";
        this.depaTime = "";
        this.duration = 0;
        this.distance = 0;
        this.timeDiff = 0;
        this.arrDate = "";
        this.arrTime = "";
        this.flightType = "";
        this.surcharge = 0.0;
        this.isFoodServed = false;
        this.routeCost = 0.0;
        this.minerPoints = 0;
        this.totalSeats = 0;
        this.firstSeats = 0;
        this.businSeats = 0;
        this.mainSeats = 0;
        this.firstPrice = 0.0;
        this.businPrice = 0.0;
        this.mainPrice = 0.0;
        this.flightTicketList = new ArrayList<Ticket>();
        this.firstSeatsSold = 0;
        this.businSeatsSold = 0;
        this.mainSeatsSold = 0;
        this.firstClassRevenue = 0.0;
        this.businClassRevenue = 0.0;
        this.mainClassRevenue = 0.0;
        this.totalMinerFee = 0.0;
        this.totalSecFee = 0.0;
        this.totalTax = 0.0;
    }

    /**
     * Constructor that enables the filling of all the attributes from the CSV file.
     * It also calculates the correct duration of the flight at the end.
     * [Taken from Ruben Carmona]
     * @param idIn the flight's ID
     * @param flightNumberIn the flight's number
     * @param origAirportIn the flight's origin airport name
     * @param destAirportIn the flight's destination airport name
     * @param depaDateIn the flight's departure date
     * @param depaTimeIn the flight's departure time
     * @param durationIn the flight's duration
     * @param distanceIn the flight's distance
     * @param timeDiffIn the flight's time zone difference
     * @param arrDateIn the flight's arrival date
     * @param arrTimeIn the flight's arrival time
     * @param flightTypeIn the flight's type
     * @param surchargeIn the flight's surcharge
     * @param isFoodServedIn the flight's food served status
     * @param routeCostIn the flight's route cost
     * @param minerPointsIn the flight's miner points
     * @param totalSeatsIn the flight's total seats
     * @param firstSeatsIn the flight's number of seats in first class
     * @param businSeatsIn the flight's number of seats in business class
     * @param mainSeatsIn the flight's number of seats in main cabin class
     * @param firstPriceIn the flight's first class price
     * @param businPriceIn the flight's business class price
     * @param mainPriceIn the flight's main cabin price
     */
    public Flight(int idIn, String flightNumberIn, Airport origAirportIn, Airport destAirportIn, String depaDateIn,
                  String depaTimeIn, int durationIn, int distanceIn, int timeDiffIn, String arrDateIn, String arrTimeIn, String flightTypeIn, double surchargeIn,
                  boolean isFoodServedIn, double routeCostIn, int minerPointsIn, int totalSeatsIn, int firstSeatsIn, int businSeatsIn, int mainSeatsIn, double firstPriceIn,
                  double businPriceIn, double mainPriceIn){
        this.id = idIn;
        this.flightNumber = flightNumberIn;
        this.origAirport = origAirportIn;
        this.destAirport = destAirportIn;
        this.depaDate = depaDateIn;
        this.depaTime = depaTimeIn;
        this.duration = durationIn;
        this.distance = distanceIn;
        this.timeDiff = timeDiffIn;
        this.arrDate = arrDateIn;
        this.arrTime = arrTimeIn;
        this.flightType = flightTypeIn;
        this.surcharge = surchargeIn;
        this.isFoodServed = isFoodServedIn;
        this.routeCost = routeCostIn;
        this.minerPoints = minerPointsIn;
        this.totalSeats = totalSeatsIn;
        this.firstSeats = firstSeatsIn;
        this.businSeats = businSeatsIn;
        this.mainSeats = mainSeatsIn;
        this.firstPrice = firstPriceIn;
        this.businPrice = businPriceIn;
        this.mainPrice = mainPriceIn;
        this.flightTicketList = new ArrayList<Ticket>();
        this.firstSeatsSold = 0;
        this.businSeatsSold = 0;
        this.mainSeatsSold = 0;
        this.firstClassRevenue = 0.0;
        this.businClassRevenue = 0.0;
        this.mainClassRevenue = 0.0;
    }
    
    /**
     * Setter for flight ID.
     * [Taken from Ruben Carmona]
     * @param idIn the flight's ID
     */
    public void setId(int idIn){
        this.id = idIn;
    }

    /**
     * Getter for flight ID.
     * [Taken from Ruben Carmona]
     * @return  the flight's ID
     */
    public int getId(){
        return this.id;
    }

    /**
     * Setter for flight number.
     * [Taken from Ruben Carmona]
     * @param flightNumberIn the flight's number
     */
    public void setFlightNumber(String flightNumberIn){
        this.flightNumber = flightNumberIn;
    }

    /** Getter for flight number
     * [Taken from Ruben Carmona]
     * @return the flight's number
     */
    public String getFlightNumber(){
        return this.flightNumber;
    }

    /**
     * Setter for origin airport.
     * [Taken from Ruben Carmona]
     * @param origAirportIn
     */
    public void setOrigAirport(Airport origAirportIn) {
        this.origAirport = origAirportIn;
        this.setOrigName(origAirport.getName());
        this.setOrigCode(origAirport.getCode());
        this.setOrigCity(origAirport.getCity());
        this.setOrigState(origAirport.getState());
        this.setOrigCountry(origAirport.getCountry());
        this.setOrigFee(origAirport.getFee());
        this.setOrigHasLounge(origAirport.getHasLounge());
    }

    /**
     * Getter for origin airport.
     * [Taken from Ruben Carmona]
     * @return
     */
    public Airport getOrigAirport() {
        return this.origAirport;
    }

    /**
     * Setter for origin name.
     * [Taken from Ruben Carmona]
     * @param origAirportIn
     */
    public void setOrigName(String origAirportIn){
        this.origAirport.setName(origAirportIn);
    }

    /**
     * Getter for origin name.
     * [Taken from Ruben Carmona]
     * @return Origin aiport.
     */
    public String getOrigName(){
        return this.origAirport.getName();
    }

    /**
     * Setter for origin code.
     * [Taken from Ruben Carmona]
     * @param origCodeIn
     */
    public void setOrigCode(String origCodeIn){
        this.origAirport.setCode(origCodeIn);
    }

    /**
     * Getter for origin code.
     * [Taken from Ruben Carmona]
     * @return Origin code.
     */
    public String getOrigCode(){
        return this.origAirport.getCode();
    }

    /**
     * Setter for origin city.
     * [Taken from Ruben Carmona]
     * @param origCityIn
     */
    public void setOrigCity(String origCityIn) {
        this.origAirport.setCity(origCityIn);
    }

    /**
     * Getter for origin city.
     * [Taken from Ruben Carmona]
     * @return
     */
    public String getOrigCity() {
        return this.origAirport.getCity();
    }

    /**
     * Setter for origin state.
     * [Taken from Ruben Carmona]
     * @param origStateIn
     */
    public void setOrigState(String origStateIn) {
        this.origAirport.setState(origStateIn);
    }

    /**
     * Getter for origin state.
     * [Taken from Ruben Carmona]
     * @return
     */
    public String getOrigState() {
        return this.origAirport.getState();
    }

    /**
     * Setter for origin country.
     * [Taken from Ruben Carmona]
     * @param origCountryIn
     */
    public void setOrigCountry(String origCountryIn) {
        this.origAirport.setCountry(origCountryIn);
    }

    /**
     * Getter for origin country.
     * [Taken from Ruben Carmona]
     * @return
     */
    public String getOrigCountry() {
        return this.origAirport.getCountry();
    }

    /**
     * Setter for origin fee.
     * [Taken from Ruben Carmona]
     * @param origFeeIn
     */
    public void setOrigFee(double origFeeIn) {
        this.origAirport.setFee(origFeeIn);
    }

    /**
     * Getter for origin fee.
     * [Taken from Ruben Carmona]
     * @return
     */
    public double getOrigFee() {
        return this.origAirport.getFee();
    }

    /**
     * Setter for origin lounge.
     * [Taken from Ruben Carmona]
     * @param origHasLoungeIn
     */
    public void setOrigHasLounge(boolean origHasLoungeIn) {
        this.origAirport.setHasLounge(origHasLoungeIn);
    }

    /**
     * Getter for origin lounge.
     * [Taken from Ruben Carmona]
     * @return
     */
    public boolean getOrigHasLounge() {
        return this.origAirport.getHasLounge();
    }

    /**
     * Setter for destination airport.
     * [Taken from Ruben Carmona]
     * @param destAirportIn
     */
    public void setDestAirport(Airport destAirportIn) {
        this.destAirport = destAirportIn;
        this.setDestName(destAirport.getName());
        this.setDestCode(destAirport.getCode());
        this.setDestCity(destAirport.getCity());
        this.setDestState(destAirport.getState());
        this.setDestCountry(destAirport.getCountry());
        this.setDestFee(destAirport.getFee());
        this.setDestHasLounge(destAirport.getHasLounge());
    }

    /**
     * Getter for destination airport.
     * [Taken from Ruben Carmona]
     * @return
     */
    public Airport getDestAirport() {
        return this.destAirport;
    }

    /**
     * Setter for destination name.
     * [Taken from Ruben Carmona]
     * @param destAirportIn
     */
    public void setDestName(String destAirportIn){
        this.destAirport.setName(destAirportIn);
    }

    /**
     * Getter for destination name.
     * [Taken from Ruben Carmona]
     * @return
     */
    public String getDestName(){
        return this.destAirport.getName();
    }

    /**
     * Setter for destination code.
     * [Taken from Ruben Carmona]
     * @param destCodeIn
     */
    public void setDestCode(String destCodeIn){
        this.destAirport.setCode(destCodeIn);
    }

    /**
     * Getter for destination code.
     * [Taken from Ruben Carmona]
     * @return
     */
    public String getDestCode(){
        return this.destAirport.getCode();
    }

    /**
     * Setter for destination city.
     * [Taken from Ruben Carmona]
     * @param destCityIn
     */
    public void setDestCity(String destCityIn) {
        this.destAirport.setCity(destCityIn);
    }

    /**
     * Getter for destination city.
     * [Taken from Ruben Carmona]
     * @return
     */
    public String getDestCity() {
        return this.destAirport.getCity();
    }

    /**
     * Setter for destination state.
     * [Taken from Ruben Carmona]
     * @param destStateIn
     */
    public void setDestState(String destStateIn) {
        this.destAirport.setState(destStateIn);
    }

    /**
     * Getter for destination state.
     * [Taken from Ruben Carmona]
     * @return
     */
    public String getDestState() {
        return this.destAirport.getState();
    }

    /**
     * Setter for destination country.
     * [Taken from Ruben Carmona]
     * @param destCountryIn
     */
    public void setDestCountry(String destCountryIn) {
        this.destAirport.setCountry(destCountryIn);
    }

    /**
     * Getter for destination country.
     * [Taken from Ruben Carmona]
     * @return
     */
    public String getDestCountry() {
        return this.destAirport.getCountry();
    }

    /**
     * Setter for destination fee.
     * [Taken from Ruben Carmona]
     * @param destFeeIn
     */
    public void setDestFee(double destFeeIn) {
        this.destAirport.setFee(destFeeIn);
    }

    /**
     * Getter for destination fee.
     * [Taken from Ruben Carmona]
     * @return
     */
    public double getDestFee() {
        return this.destAirport.getFee();
    }

    /**
     * Setter for destination has lounge.
     * [Taken from Ruben Carmona]
     * @param destHasLoungeIn
     */
    public void setDestHasLounge(boolean destHasLoungeIn) {
        this.destAirport.setHasLounge(destHasLoungeIn);
    }

    /**
     * Getter for destination has lounge.
     * [Taken from Ruben Carmona]
     * @return
     */
    public boolean getDestHasLounge() {
        return this.destAirport.getHasLounge();
    }

    /**
     * Setter for departure date with automatic updater for arrival date.
     * [Taken from Brian G. Rodiles Delgado]
     * @param depaDateIn
     */
    public void setDepaDate(String depaDateIn){
        this.depaDate = depaDateIn;
        if(this.arrDate != null){
            String[] timeBreaker1 = this.depaTime.split(":");
            String[] timeBreaker2 = this.arrTime.split(":");
            
            // The departure time ends in PM and the initial arrival time ends in AM
            if((timeBreaker1[1].substring(3).equals("PM")) && (timeBreaker2[1].substring(3).equals("AM"))){
                // Change the arrival date one day after the departure date
                this.arrDate = getNextArrDate(this.depaDate);
            }
            // There is no day change when arrival
            else{
                this.arrDate = depaDateIn;
            }
        }
    }

    /**
     * Getter for departure date.
     * [Taken from Ruben Carmona]
     * @return Departure date.
     */
    public String getDepaDate(){
        return this.depaDate;
    }

    /**
     * Setter for departure time with automatic updater for arrival time.
     * [Taken from Brian G. Rodiles Delgado]
     * @param depaTimeIn
     */
    public void setDepaTime(String depaTimeIn){
        if(this.arrTime != null){
            String newTime = depaTimeIn;
            this.depaTime = depaTimeIn;

            String[] timeBreakerNew = newTime.split(":");
            int hourNew = Integer.parseInt(timeBreakerNew[0]);
            int minsNew = Integer.parseInt(timeBreakerNew[1].substring(0, 2));

            // Calculate the new arrival time according to the flight's duration
            int durHours = getDuration() / 60;
            int durMins = getDuration() % 60;
            int tempNewHours = hourNew % 12 + durHours;
            int tempNewMins = minsNew + durMins;

            if (tempNewMins > 59){
                tempNewHours += 1;
                tempNewMins %= 60;
            }
            
            // Build the new arrival time
            String newHour = "";
            // Shift PM to AM or AM to PM
            if ((tempNewHours > 12 && tempNewHours < 24)){
                if (timeBreakerNew[1].substring(3).equals("PM")){
                    if (tempNewMins >=0 && tempNewMins < 10){
                        newHour = String.valueOf(tempNewHours % 12 == 0 ? 12 : tempNewHours % 12) + ":0" + String.valueOf(tempNewMins) + " AM";
                    }
                    else{
                        newHour = String.valueOf(tempNewHours % 12 == 0 ? 12 : tempNewHours % 12) + ":" + String.valueOf(tempNewMins) + " AM";
                    }
                    // Change the arrival date to the next one from the departure date
                    this.arrDate = getNextArrDate(this.depaDate);
                }
                else{
                    if (tempNewMins >=0 && tempNewMins < 10){
                        newHour = String.valueOf(tempNewHours % 12 == 0 ? 12 : tempNewHours % 12) + ":0" + String.valueOf(tempNewMins) + " PM";
                    }
                    else{
                        newHour = String.valueOf(tempNewHours % 12 == 0 ? 12 : tempNewHours % 12) + ":" + String.valueOf(tempNewMins) + " PM";
                    }
                    this.arrDate = this.depaDate;
                }
            }
            // Conserve AM or PM and return to normal hour format
            else if (tempNewHours >= 24){
                if (tempNewMins >=0 && tempNewMins < 10){
                    newHour = String.valueOf(tempNewHours % 24) + ":0" + String.valueOf(tempNewMins) + timeBreakerNew[1].substring(2);
                }
                else{
                    newHour = String.valueOf(tempNewHours % 24) + ":" + String.valueOf(tempNewMins) + timeBreakerNew[1].substring(2);
                }
                // Change the arrival date to the next one from the departure date
                this.arrDate = getNextArrDate(this.depaDate);
            }
            // Conserve AM or PM
            else{
                if (tempNewMins >=0 && tempNewMins < 10){
                    newHour = String.valueOf(tempNewHours) + ":0" + String.valueOf(tempNewMins) + timeBreakerNew[1].substring(2);
                }
                else{
                    newHour = String.valueOf(tempNewHours) + ":" + String.valueOf(tempNewMins) + timeBreakerNew[1].substring(2);
                }
                this.arrDate = this.depaDate;
            }
            this.arrTime = newHour;
        }
        else{
            this.depaTime = depaTimeIn;
        }
    }

    /**
     * Getter for departure time.
     * [Taken from Ruben Carmona]
     * @return Departure time.
     */
    public String getDepaTime(){
        return this.depaTime;
    }

    /**
     * Setter for arrival date.
     * [Taken from Ruben Carmona]
     * @param arrDateIn
     */
    public void setArrDate(String arrDateIn){
        this.arrDate = arrDateIn;
    }

    /**
     * Getter for arrival date.
     * [Taken from Ruben Carmona]
     * @return Arrival date.
     */
    public String getArrDate(){
        return this.arrDate;
    }

    /**
     * Setter for arrival time.
     * [Taken from Ruben Carmona]
     * @param arrTimeIn
     */
    public void setArrTime(String arrTimeIn){
        this.arrTime = arrTimeIn;
    }

    /**
     * Getter for arrival time.
     * [Taken from Ruben Carmona]
     * @return Arrival time.
     */
    public String getArrTime(){
        return this.arrTime;
    }

    /**
     * Setter for flight type.
     * [Taken from Ruben Carmona]
     * @param flightTypeIn
     */
    public void setFlightType(String flightTypeIn){
        this.flightType = flightTypeIn;
    }

    /**
     * Getter for flight type.
     * [Taken from Ruben Carmona]
     * @return Flight type.
     */
    public String getFlightType(){
        return this.flightType;
    }

    /**
     * Setter for surcharge.
     * [Taken from Ruben Carmona]
     * @param surchageIn
     */
    public void setSurcharge(double surchageIn){
        this.surcharge = surchageIn;
    }

    /**
     * Getter for surcharge
     * [Taken from Ruben Carmona]
     * @return Surcharge.
     */
    public double getSurcharge(){
        return this.surcharge;
    }

    /**
     * Setter for food serving.
     * [Taken from Ruben Carmona]
     * @param isFoodServedIn
     */
    public void setIsFoodServed(boolean isFoodServedIn){
        this.isFoodServed = isFoodServedIn;
    }

    /**
     * Getter for food serving.
     * [Taken from Ruben Carmona]
     * @return Food serving.
     */
    public boolean getIsFoodServed(){
        return this.isFoodServed;
    }

    /**
     * Setter for route cost.
     * [Taken from Ruben Carmona]
     * @param routeCostIn
     */
    public void setRouteCost(double routeCostIn){
        this.routeCost = routeCostIn;
    }

    /**
     * Getter for route cost.
     * [Taken from Ruben Carmona]
     * @return route cost.
     */
    public double getRouteCost(){
        return this.routeCost;
    }

    /**
     * Setter for miner points.
     * [Taken from Ruben Carmona]
     * @param minerPointsIn
     */
    public void setMinerPoints(int minerPointsIn){
        this.minerPoints = minerPointsIn;
    }

    /**
     * Getter for miner points.
     * [Taken from Ruben Carmona]
     * @return Miner points.
     */
    public int getMinerPoints(){
        return this.minerPoints;
    }

    /**
     * Setter for first class price.
     * [Taken from Ruben Carmona]
     * @param firstPriceIn
     */
    public void setFirstPrice(double firstPriceIn){
        this.firstPrice = firstPriceIn;
    }

    /**
     * Getter for first class price.
     * [Taken from Ruben Carmona]
     * @return First class price.
     */
    public double getFirstPrice(){
        return this.firstPrice;
    }

    /**
     * Setter for business class price.
     * [Taken from Ruben Carmona]
     * @param businPriceIn
     */
    public void setBusinPrice(double businPriceIn){
        this.businPrice = businPriceIn;
    }

    /**
     * Getter for business class price.
     * [Taken from Ruben Carmona]
     * @return Business class price.
     */
    public double getBusinPrice(){
        return this.businPrice;
    }

    /**
     * Setter for main class price.
     * [Taken from Ruben Carmona]
     * @param mainPrice
     */
    public void setMainPrice(double mainPrice){
        this.mainPrice = mainPrice;
    }

    /**
     * Getter for main cabin price.
     * [Taken from Ruben Carmona]
     * @return Main cabin price.
     */
    public double getMainPrice(){
        return this.mainPrice;
    }

    /**
     * Setter for duration.
     * [Taken from Ruben Carmona]
     * @param durationIn
     */
    public void setDuration(int durationIn){
        this.duration = durationIn;
    }
    
    /**
     * Getter for flight duration.
     * [Taken from Ruben Carmona]
     * @return Flight duration.
     */
    public int getDuration(){
        return this.duration;
    }

    /**
     * Setter for total flight distance.
     * [Taken from Ruben Carmona]
     * @param distanceIn
     */
    public void setDistance(int distanceIn){
        this.distance = distanceIn;
    }
    
    /**
     * Getter for flight distance.
     * [Taken from Ruben Carmona]
     * @return Flight distance.
     */
    public int getDistance(){
        return this.distance;
    }

    /**
     * Setter for time zone difference.
     * [Taken from Ruben Carmona]
     * @param timeDiffIn
     */
    public void setTimeDiff(int timeDiffIn){
        this.timeDiff = timeDiffIn;
    }

    /**
     * Getter for time difference.
     * [Taken from Ruben Carmona]
     * @return Time difference.
     */
    public int getTimeDiff(){
        return this.timeDiff;
    }

    /**
     * Setter for first seats.
     * [Taken from Ruben Carmona]
     * @param firstSeatsIn
     */
    public void setFirstSeats(int firstSeatsIn){
        this.firstSeats = firstSeatsIn;
    }

    /**
     * Getter for first class seats.
     * [Taken from Ruben Carmona]
     * @return First class seats.
     */
    public int getFirstSeats(){
        return this.firstSeats;
    }

    /**
     * Setter for first class seats.
     * [Taken from Ruben Carmona]
     * @param firstSeatsIn
     */
    public void calcFirstSeats(int firstSeatsIn) {
        this.firstSeats -= firstSeatsIn;
        this.totalSeats -= firstSeatsIn;
    }

    /**
     * Setter for business seats.
     * [Taken from Ruben Carmona]
     * @param businSeatsIn
     */
    public void setBusinSeats(int businSeatsIn){
        this.businSeats = businSeatsIn;
    }

    /**
     * Getter for business class seats.
     * [Taken from Ruben Carmona]
     * @return Business class seats.
     */
    public int getBusinSeats(){
        return this.businSeats;
    }

    /**
     * Setter for business class seats.
     * [Taken from Ruben Carmona]
     * @param businSeatsIn
     */
    public void calcBusinSeats(int businSeatsIn) {
        this.businSeats -= businSeatsIn;
        this.totalSeats -= businSeatsIn;
    }

    /**
     * Setter for main seats.
     * [Taken from Ruben Carmona]
     * @param mainSeatsIn
     */
    public void setMainSeats(int mainSeatsIn){
        this.mainSeats = mainSeatsIn;
    }

    /**
     * Getter for main cabin seats.
     * [Taken from Ruben Carmona]
     * @return Main cabin seats.
     */
    public int getMainSeats(){
        return this.mainSeats;
    }

    /**
     * Setter for main class seats.
     * [Taken from Ruben Carmona]
     * @param mainSeatsIn
     */
    public void calcMainSeats(int mainSeatsIn) {
        this.mainSeats -= mainSeatsIn;
        this.totalSeats -= mainSeatsIn;
    }

    /**
     * Setter for total seats.
     * [Taken from Ruben Carmona]
     * @param totalSeatsIn
     */
    public void setTotalSeats(int totalSeatsIn){
        this.totalSeats = totalSeatsIn;
    }
    
    /**
     * Getter for total seats.
     * [Taken from Ruben Carmona]
     * @return Total seats.
     */
    public int getTotalSeats(){
        return this.totalSeats;
    }

    /**
     * Private method to calculate the arrival date considering it is one day after the departure date.
     * [Taken from Brian G. Rodiles Delgado]
     * @param userDepaDate
     * @return Next arrival date from the departure date.
     */
    private String getNextArrDate(String userDepaDate){
        String[] dateBreaker = userDepaDate.split("/");
        String newArrDate = "";

        // Handle months that have 31 or 30 days
        if(((dateBreaker[0].equals("01") || dateBreaker[0].equals("03") || 
             dateBreaker[0].equals("05") || dateBreaker[0].equals("07") ||
             dateBreaker[0].equals("08") || dateBreaker[0].equals("10")) && 
             dateBreaker[1].equals("31")) || ((dateBreaker[0].equals("04") ||
             dateBreaker[0].equals("06") || dateBreaker[0].equals("09") ||
             dateBreaker[0].equals("11")) && dateBreaker[1].equals("30"))){
            newArrDate = String.valueOf(Integer.parseInt(dateBreaker[0]) + 1) + "/01/" + dateBreaker[2];
        }
        // Handle Februrary knowing if it is a leap year or not
        else if(dateBreaker[0].equals("02") &&
               ((dateBreaker[1].equals("28") && Integer.parseInt(dateBreaker[2]) % 4 != 0) ||
                (dateBreaker[1].equals("29") && Integer.parseInt(dateBreaker[2]) % 4 == 0))){
            newArrDate = "03/01/" + dateBreaker[2];
        }
        // Handle December to also change the year
        else if(dateBreaker[0].equals("12") && dateBreaker[1].equals("31")){
            newArrDate = "01/01/" + String.valueOf(Integer.parseInt(dateBreaker[2]) + 1);
        }
        // Handle no change of months nor years
        else{
            if(Integer.parseInt(dateBreaker[0]) < 10){
                newArrDate += "0";
            }
            newArrDate += dateBreaker[0] + "/";
            if(Integer.parseInt(dateBreaker[1]) + 1 < 10){
                newArrDate += "0";
            }
            newArrDate += String.valueOf(Integer.parseInt(dateBreaker[1]) + 1) + "/" + dateBreaker[2];
        }
        return newArrDate;
    }

    /**
     * Getter for flight ticket list.
     * [Taken from Ruben Carmona]
     * @return Flight ticket list.
     */
    public ArrayList<Ticket> getFlightTicketList() {
        return this.flightTicketList;
    }

    /**
     * Public method to add a ticket to flight ticket list.
     * [Taken from Ruben Carmona]
     * @param myTicket
     */
    public void addFlightTicket(Ticket myTicket) {
        this.flightTicketList.add(myTicket);
    }

    /**
     * Getter for flight ticket list size.
     * [Taken from Ruben Carmona]
     * @return Flight ticket list size.
     */
    public int getFlightTicketSize() {
        return this.flightTicketList.size();
    }

    /**
     * Public method to remove a person's ticket from the flight ticket list.
     * [Taken from Brian G. Rodiles Delgado]
     * @param confNumber
     * @param isCanceledByEmployee
     */
    public void removePersonTicket(String confNumber, boolean isCanceledByEmployee) {
        // System.out.println("Before removal: " + this.flightTicketList);
        for(int i = 0; i < this.flightTicketList.size(); ++i) {
            if(this.flightTicketList.get(i).getConfirmationNumber().equals(confNumber)) {
                if(!isCanceledByEmployee){
                    // System.out.println(this.flightTicketList.get(i).getConfirmationNumber());
                    this.flightTicketList.remove(i);
                }
            }
        }
        // System.out.println("After removal: " + this.flightTicketList);
    }

    /**
     * Setter for variable to track if a flight is cancelled.
     * [Taken from Ruben Carmona]
     * @param isCancelledIn
     */
    public void setIsCancelled(boolean isCancelledIn) {
        this.isCancelled = isCancelledIn;
    }

    /**
     * Getter for variable to track if a flight is cancelled.
     * [Taken from Ruben Carmona]
     * @return Flight cancelled boolean tracker.
     */
    public boolean getIsCancelled() {
        return this.isCancelled;
    }

    /**
     * Setter for first class seats sold.
     * [Taken from Ruben Carmona]
     * @param firstSeatsSoldIn
     */
    public void setFirstSeatsSold(int firstSeatsSoldIn) {
        this.firstSeatsSold = firstSeatsSoldIn;
    }

    /**
     * Getter for first class seats sold.
     * [Taken from Ruben Carmona]
     * @return First class seats sold.
     */
    public int getFirstSeatsSold() {
        return firstSeatsSold;
    }

    /**
     * Setter for business class seats sold.
     * [Taken from Ruben Carmona]
     * @param businSeatsSoldIn
     */
    public void setBusinSeatsSold(int businSeatsSoldIn) {
        this.businSeatsSold = businSeatsSoldIn;
    }

    /**
     * Getter for business class seats sold.
     * [Taken from Ruben Carmona]
     * @return Business class seats sold.
     */
    public int getBusinSeatsSold() {
        return businSeatsSold;
    }

    /**
     * Setter for main class seats sold.
     * [Taken from Ruben Carmona]
     * @param mainSeatsSoldIn
     */
    public void setMainSeatsSold(int mainSeatsSoldIn) {
        this.mainSeatsSold = mainSeatsSoldIn;
    }

    /**
     * Getter for main class seats sold.
     * [Taken from Ruben Carmona]
     * @return Main class seats sold.
     */
    public int getMainSeatsSold() {
        return mainSeatsSold;
    }   

    /**
     * Setter for first class tickets revenue.
     * [Taken from Ruben Carmona]
     * @param firstClassRevenueIn
     */
    public void setFirstClassRevenue(double firstClassRevenueIn) {
        this.firstClassRevenue = firstClassRevenueIn;
    }

    /**
     * Getter for first class tickets revenue.
     * [Taken from Ruben Carmona]
     * @return First class tickets revenue.
     */
    public double getFirstClassRevenue() {
        return firstClassRevenue;
    }

    /**
     * Setter for business class tickets revenue.
     * [Taken from Ruben Carmona]
     * @param businClassRevenueIn
     */
    public void setBusinClassRevenue(double businClassRevenueIn) {
        this.businClassRevenue = businClassRevenueIn;
    }

    /**
     * Getter for business class tickets revenue.
     * [Taken from Ruben Carmona]
     * @return Business class tickets revenue.
     */
    public double getBusinClassRevenue() {
        return businClassRevenue;
    }

    /**
     * Setter for main class tickets revenue.
     * [Taken from Ruben Carmona]
     * @param mainClassRevenueIn
     */
    public void setMainClassRevenue(double mainClassRevenueIn) {
        this.mainClassRevenue = mainClassRevenueIn;
    }

    /**
     * Getter for main class tickets revenue.
     * [Taken from Ruben Carmona]
     * @return Main class tickets revenue.
     */
    public double getMainClassRevenue() {
        return mainClassRevenue;
    }

    /**
     * Setter for total miner fee.
     * [Taken from Ruben Carmona]
     * @param totalMinerFeeIn
     */
    public void setTotalMinerFee(double totalMinerFeeIn) {
        this.totalMinerFee = totalMinerFeeIn;
    }

    /**
     * Getter for total miner fee.
     * [Taken from Ruben Carmona]
     * @return
     */
    public double getTotalMinerFee() {
        return this.totalMinerFee;
    }

    /**
     * Setter for total security fee.
     * [Taken from Ruben Carmona]
     * @param totalSecFeeIn
     */
    public void setTotalSecFee(double totalSecFeeIn) {
        this.totalSecFee = totalSecFeeIn;
    }

    /**
     * Getter for total security fee.
     * [Taken from Ruben Carmona]
     * @return
     */
    public double getTotalSecFee() {
        return this.totalSecFee;
    }

    /**
     * Setter for total tax.
     * [Taken from Ruben Carmona]
     * @param totalTaxIn
     */
    public void setTotalTax(double totalTaxIn) {
        this.totalTax = totalTaxIn;
    }

    /**
     * Getter for total tax.
     * [Taken from Ruben Carmona]
     * @return
     */
    public double getTotalTax() {
        return this.totalTax;
    }

    /**
     * Method to print the customers that purchased tickets for the flight.
     * [Taken from Brian G. Rodiles Delgado]
     * @param customerMap
     * @param employeeMap
     */
    public void printFlightCustomers(Map<String, Person> customerMap, Map<String, Person> employeeMap) {
        if(flightTicketList.size() == 0) {
            System.out.println("\n\nThere are no customers for this flight yet.\nReturning to the flight update menu...\n");
            try {
                Thread.sleep(2500);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        else{
            for(Ticket loopTicket : flightTicketList) {
                Person testPerson;
                if(customerMap.containsKey(loopTicket.getAssociatedUsername())) {
                    testPerson = customerMap.get(loopTicket.getAssociatedUsername());
                }
                else {
                    testPerson = employeeMap.get(loopTicket.getAssociatedUsername());
                }
                System.out.println("\nCustomer: " + testPerson.getFirstName() + " " + testPerson.getLastName());
                System.out.println(loopTicket.getType() + ": " + loopTicket.getSeats() + " seats");
                System.out.printf("Total Price: $%.2f", loopTicket.getTotalPrice());
                System.out.println();
            }
        }
    }

    /**
     * Method to print the financial information for a flight.
     * [Taken from Brian G. Rodiles Delgado]
     */
    public void printFinancialInfo() {
        System.out.println("\nFinancial Information");
        System.out.println("\nFirst Class Seats Remaining: " + (this.firstSeats - this.firstSeatsSold));
        System.out.println("Business Class Seats Remaining: " + (this.businSeats - this.businSeatsSold));
        System.out.println("Main Cabin Seats Remaining: " + (this.mainSeats - this.mainSeatsSold));
        System.out.println("Total Seats Remaining: " + (this.totalSeats - this.firstSeatsSold - this.businSeatsSold - this.mainSeatsSold));
        System.out.println("\nFirst Class Seats Sold: " + this.firstSeatsSold);
        System.out.println("Business Class Seats Sold: " + this.businSeatsSold);
        System.out.println("Main Cabin Seats Sold: " + this.mainSeatsSold);
        System.out.println("Total Seats Sold: " + (this.firstSeatsSold + this.businSeatsSold + this.mainSeatsSold));
        System.out.printf("\nTotal Miner Airlines Fee collected: %.2f", this.totalMinerFee);
        System.out.printf("\nTotal Security Fee collected: $%.2f", this.totalSecFee);
        System.out.printf("\nTotal Tax collected: $%.2f", this.totalTax);
        System.out.printf("\nFirst Class Revenue: $%.2f", this.firstClassRevenue);
        System.out.printf("\nBusiness Class Revenue: $%.2f",this.businClassRevenue);
        System.out.printf("\nMain Cabin Revenue: $%.2f", this.mainClassRevenue);
        System.out.printf("\nProfit Expected for Flight: $%.2f", ((this.firstSeats + this.firstSeatsSold) * this.firstPrice) + ((this.businSeats + this.businSeatsSold) * this.businPrice) + ((this.mainSeats + this.mainSeatsSold) * this.mainPrice));
        System.out.printf("\nCurrent Profit: $%.2f", (this.firstClassRevenue + this.businClassRevenue + this.mainClassRevenue) - (((this.firstSeats + this.firstSeatsSold) * this.firstPrice) + ((this.businSeats + this.businSeatsSold) * this.businPrice) + ((this.mainSeats + this.mainSeatsSold) * this.mainPrice)));
        System.out.println();
    }

    /**
     * Method to print all the information of a CSV-consumed flight with the exception of the ID and flight number.
     * [Taken from Ruben Carmona]
     */
    public void printFlight(){
        System.out.println("\nOrigin Airport: " + getOrigName());
        System.out.println("Origin Code: " + getOrigCode());
        System.out.println("Destination Airport: " + getDestName());
        System.out.println("Destination Code: " + getDestCode());
        System.out.println("Flight Type: " + getFlightType());
        System.out.println("Departure Date: " + getDepaDate());
        System.out.println("Departure Time: " + getDepaTime());
        System.out.println("Arrival Date: " + getArrDate());
        System.out.println("Arrival Time: " + getArrTime());
        System.out.println("Duration: " + getDuration() + " minutes");
        System.out.println("Distance: " + getDistance() + " miles");
        System.out.println("Time Zone Difference: " + getTimeDiff());
        System.out.println("First Class Price: $" + getFirstPrice());
        System.out.println("Business Class Price: $" + getBusinPrice());
        System.out.println("Main Cabin Price: $" + getMainPrice());
        System.out.println("Surcharge: $" + getSurcharge());
        System.out.println("Route Cost: $" + getRouteCost());
        System.out.println("First Class Seats: " + (getFirstSeats() + getFirstSeatsSold()));
        System.out.println("Business Class Seats: " + (getBusinSeats() + getBusinSeatsSold()));
        System.out.println("Main Cabin Seats: " + (getMainSeats() + getMainSeatsSold()));
        System.out.println("Total Seats: " + (getTotalSeats() + getFirstSeatsSold() + getBusinSeatsSold() + getMainSeatsSold()));
    }
}
