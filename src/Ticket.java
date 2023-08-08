/**
 * Ticket class to encapsulate the attributes related to the ticket purchase for a flight.
 */
public class Ticket {
    private String type;
    private int seats;
    private double totalPrice;
    private String confirmationNumber;
    private String itineraryInfo;
    private String associatedUsername;
    private boolean isCancelled;
    private double totalSecFee;
    private double savings;
    
    /**
     * Empty constructor.
     * [Taken from Jose Luis Espinoza Gonzalez]
     */
    public Ticket() {
    }

    /**
     * Setter for type of ticket.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param typeIn
     */
    public void setType(String typeIn) {
        this.type = typeIn;
    }

    /**
     * Getter for type of ticket.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public String getType() {
        return this.type;
    }

    /**
     * Setter for seats purchased.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param seatsIn
     */
    public void setSeats(int seatsIn) {
        this.seats = seatsIn;
    }

    /**
     * Getter for seats purchased.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public int getSeats() {
        return this.seats;
    }

    /**
     * Setter for total ticket price.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param totalPriceIn
     */
    public void setTotalPrice(double totalPriceIn) {
        this.totalPrice = totalPriceIn;
    }

    /**
     * Getter for total ticket price.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Setter for ticket confirmation number.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param confirmationNumberIn
     */
    public void setConfirmationNumber(String confirmationNumberIn) {
        this.confirmationNumber = confirmationNumberIn;
    }

    /**
     * Getter for ticket confirmation number.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public String getConfirmationNumber() {
        return this.confirmationNumber;
    }

    /**
     * Setter for itinerary information.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param itinteraryInfoIn
     */
    public void setItineraryInfo(String itinteraryInfoIn) {
        this.itineraryInfo = itinteraryInfoIn;
    }

    /**
     * Getter for itinerary information.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public String getItineraryInfo() {
        return this.itineraryInfo;
    }

    /**
     * Setter for associated username to ticket.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param associatedUsernameIn
     */
    public void setAssociatedUsername(String associatedUsernameIn) {
        this.associatedUsername = associatedUsernameIn;
    }

    /**
     * Getter for associated username to ticket.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public String getAssociatedUsername() {
        return associatedUsername;
    }

    /**
     * Setter for boolean to know if a ticket was cancelled.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param isCancelledIn
     */
    public void setIsCancelled(boolean isCancelledIn) {
        if(isCancelledIn == true) {
            this.itineraryInfo = "\n***CANCELLED TICKET***\n\n" + this.itineraryInfo;
        }

        this.isCancelled = isCancelledIn;
    }

    /**
     * Getter for boolean to know if a ticket was cancelled.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public boolean getIsCancelled() {
        return this.isCancelled;
    }

    /**
     * Setter for total security fee.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param totalSecFeeIn
     */
    public void setTotalSecFee(double totalSecFeeIn) {
        this.totalSecFee = totalSecFeeIn;
    }

    /**
     * Getter for total security fee.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public double getTotalSecFee() {
        return this.totalSecFee;
    }

    /**
     * Setter for savings.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param savingsIn
     */
    public void setSavings(double savingsIn) {
        this.savings = savingsIn;
    }

    /**
     * Getter for savings.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public double getSavings() {
        return this.savings;
    }
    /**
     * Method to print the intinerary information.
     * [Taken from Jose Luis Espinoza Gonzalez]
     */
    public void printTicketInfo() {
        System.out.println(itineraryInfo);
    }
    
}
