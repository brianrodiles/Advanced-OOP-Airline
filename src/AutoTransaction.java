/**
 * AutoTransaction class to store all the attributes of an automatic action.
 */
public class AutoTransaction {
    private String firstName;
    private String lastName;
    private String action;
    private int    flightID;
    private String origCode;
    private String destCode;
    private String ticketType;
    private int    ticketQty;

    /**
     * Constructor to initialize attributes.
     * [Taken from Brian G. Rodiles Delgado]
     */
    public AutoTransaction() {
        firstName = "";
        lastName = "";
        action = "";
        flightID = 0;
        origCode = "";
        destCode = "";
        ticketType = "";
        ticketQty = 0;
    }
    
    /**
     * Setter for first name.
     * [Taken from Brian G. Rodiles Delgado]
     * @param firstNameIn the customer's first name
     */
    public void setFirstName(String firstNameIn) {
        this.firstName = firstNameIn;
    }

    /**
     * Getter for first name.
     * [Taken from Brian G. Rodiles Delgado]
     * @return the customer's first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Setter for last name.
     * [Taken from Brian G. Rodiles Delgado]
     * @param lastNameIn the customer's last name
     */
    public void setLastName(String lastNameIn) {
        this.lastName = lastNameIn;
    }

    /**
     * Getter for last name.
     * [Taken from Brian G. Rodiles Delgado]
     * @return the customer's last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Setter for action.
     * [Taken from Brian G. Rodiles Delgado]
     * @param actionIn the customer's action
     */
    public void setAction(String actionIn) {
        this.action = actionIn;
    }

    /**
     * Getter for action.
     * [Taken from Brian G. Rodiles Delgado]
     * @return the customer's action
     */
    public String getAction() {
        return action;
    }
    
    /**
     * Setter for flight ID.
     * [Taken from Brian G. Rodiles Delgado]
     * @param flightIDIn the customer's flight ID
     */
    public void setFlightID(int flightIDIn) {
        this.flightID = flightIDIn;
    }

    /**
     * Getter for flight ID.
     * [Taken from Brian G. Rodiles Delgado]
     * @return the customer's flight ID
     */
    public int getFlightID() {
        return flightID;
    }
    
    /**
     * Setter for origin code.
     * [Taken from Brian G. Rodiles Delgado]
     * @param origCodeIn the customer's origin code
     */
    public void setOrigCode(String origCodeIn) {
        this.origCode = origCodeIn;
    }

    /**
     * Getter for origin code.
     * [Taken from Brian G. Rodiles Delgado]
     * @return the customer's origin code
     */
    public String getOrigCode() {
        return origCode;
    }
    
    /**
     * Setter for destination code.
     * [Taken from Brian G. Rodiles Delgado]
     * @param destCodeIn the customer's destination code
     */
    public void setDestCode(String destCodeIn) {
        this.destCode = destCodeIn;
    }

    /**
     * Getter for destination code.
     * [Taken from Brian G. Rodiles Delgado]
     * @return the customer's destination code
     */
    public String getDestCode() {
        return destCode;
    }
    
    /**
     * Setter for ticket type.
     * [Taken from Brian G. Rodiles Delgado]
     * @param ticketTypeIn the customer's ticket type
     */
    public void setTicketType(String ticketTypeIn) {
        this.ticketType = ticketTypeIn;
    }

    /**
     * Getter for ticket type.
     * [Taken from Brian G. Rodiles Delgado]
     * @return the customer's ticket type
     */
    public String getTicketType() {
        return ticketType;
    }
    
    /**
     * Setter for ticket quantity.
     * [Taken from Brian G. Rodiles Delgado]
     * @param ticketQtyIn the customer's ticket quantity
     */
    public void setTicketQty(int ticketQtyIn) {
        this.ticketQty = ticketQtyIn;
    }

    /**
     * Getter for ticket quantity.
     * [Taken from Brian G. Rodiles Delgado]
     * @return the customer's ticket quantity
     */ 
    public int getTicketQty() {
        return ticketQty;
    }  
}
