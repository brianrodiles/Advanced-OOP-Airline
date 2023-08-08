import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * AutoTransactor class to handle all automatic transaction
 */
public class AutoTransactor {
    private Person myPerson;
    private Flight myFlight;
    private Ticket myTicket;
    private String firstName, lastName, userInput, classNumIn;
    private int flightTicketId, numOfTickets, age;
    private int ticketsCounter = 0;
    private boolean isValidCustomer, isValidChoice, isValidFlight, isValidClass, isValidBalance;
    private double totalSavings, finalBasePrice, seniorDisc;
    private FileWriter customerLog;
    private Map<String, Person> customerMap;
    private Map<String, Person> employeeMap;
    private Map<Integer, Flight> flightMap;
    private ArrayList<AutoTransaction> autoTransList;
    AirportSingleton airportManager = AirportSingleton.getInstance();

    /**
     * Constructor to initialize structures and logs for the class use.
     * [Taken from Ruben Carmona]
     * @param customerMapIn the map of customers
     * @param employeeMapIn the map of employees
     * @param flightMapIn the map of flights
     * @param customerLogIn the customer log
     */
    public AutoTransactor(Map<String, Person> customerMapIn, Map<String, Person> employeeMapIn, Map<Integer, Flight> flightMapIn, FileWriter customerLogIn){
        this.customerMap = customerMapIn;
        this.employeeMap = employeeMapIn;
        this.flightMap = flightMapIn;
        this.customerLog = customerLogIn;
        autoTransList = null;
    }

    /**
     * Setter for the transaction list.
     * [Taken from Ruben Carmona]
     * @param autoTransListIn the auto-transaction list
     */
    public void setTransList(ArrayList<AutoTransaction> autoTransListIn) {
        autoTransList = autoTransListIn;
    }

    /**
     * Initialize of automatic transactions.
     * [Taken from Ruben Carmona]
     */
    public void initTrans() { 
        System.out.println("\nInitializing automatic transactions.");
        for(AutoTransaction autoTrans : autoTransList) { 
            if (personExists(customerMap, autoTrans.getFirstName(), autoTrans.getLastName()) || personExists(employeeMap, autoTrans.getFirstName(), autoTrans.getLastName())) {
                if (!wantsToCancelTicket(autoTrans.getAction())) { 
                    generateSampleTicket();
                    if (flightExists(flightMap, customerLog, autoTrans.getFlightID())) {
                        if (autoTrans.getTicketType().equals("First Class")) {
                            classNumIn = "1";
                        }
                        else if (autoTrans.getTicketType().equals("Business Class")) {
                            classNumIn = "2";
                        }
                        else {
                            classNumIn = "3";
                        }
                        if (chooseClass(myFlight, customerLog, classNumIn)) {
                            if (confirmTicket(myFlight, customerLog, autoTrans.getTicketQty())) {
                                prepareTicket(myFlight, customerLog);
                                printTicket(myFlight, myTicket, customerLog);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("\nAutomatic transactions finalized.\n");
    }

    /**
     * Checks if the customer exists in file.
     * [Taken from Ruben Carmona]
     * @param personMap the map of persons
     * @param firstNameIn the first name of a customer
     * @param lastNameIn the last name of a customer
     * @return Binary decision tracker.
     */
    private boolean personExists(Map<String, Person> personMap, String firstNameIn, String lastNameIn) {
        isValidCustomer = false;
        while(!isValidCustomer) {
            firstName = firstNameIn;
            lastName = lastNameIn;
            if(personMap.containsKey((firstName + lastName).toLowerCase())) {
                isValidCustomer = true;
            }
            else {
                return false;
            }
        }
        myPerson = personMap.get((firstName + lastName).toLowerCase());
        return true;
    }

    /**
     * Method to determine if an user wants to buy or cancel a ticket.
     * [Taken from Ruben Carmona]
     * @param transType the type of transaction
     * @return Binary decision tracker.
     */
    private boolean wantsToCancelTicket(String transType) {
        boolean wantsToCancelTicket = false;
        isValidChoice = false;
        while(!isValidChoice) {
            userInput = transType;
            if(userInput.equals("Buy")){
                if(userInput.equals("Buy")) {
                    wantsToCancelTicket = false;
                }
                else {
                    wantsToCancelTicket = true;
                }
                isValidChoice = true;
            }
        }
        return wantsToCancelTicket;
    }

    /**
     * Generates empty Ticket object.
     * [Taken from Ruben Carmona]
     */
    private void generateSampleTicket() {
        myTicket = new Ticket();
    }

    /**
     * Checks if the requested flight exists.
     * [Taken from Ruben Carmona]
     * @param flightsMap the map of flights
     * @param customerLog the customer log
     * @param flightID the ID of the flight
     * @return Binary decision tracker.
     */
    private boolean flightExists(Map<Integer, Flight> flightsMap, FileWriter customerLog, int flightID) {
        try{
            isValidFlight = false;
            while(!isValidFlight) {
                flightTicketId = flightID;
                if(flightsMap.containsKey(flightTicketId)) {
                    myFlight = flightsMap.get(flightTicketId);
                    if(myFlight.getTotalSeats() == 0){
                        return false;
                    }
                    else if(myFlight.getIsCancelled()) {
                        return false;
                    }
                    else {
                        customerLog.write("\nCustomer with username " + myPerson.getUsername() + " selected flight with ID " + myFlight.getId() + ".");
                        isValidFlight = true;
                    }
                }
                else {
                    return false;
                }    
            }
            customerLog.flush();
        }
        catch(IOException e){
            System.out.println("\n\nAn error occured when writing to the command log.");
        }
        return true;
    }

    /**
     * Allows user to choose the class type for selected flight.
     * [Taken from Ruben Carmona]
     * @param myFlight the flight object
     * @param customerLog the customer log
     * @param classNumber the number of class
     * @return Binary decision tracker.
     */
    private boolean chooseClass(Flight myFlight, FileWriter customerLog, String classNumber) {
        try{
            isValidClass = false;
            while(!isValidClass) {
                userInput = classNumber;
                if(userInput.equals("1") || userInput.equals("2") || userInput.equals("3")) {
                    if(userInput.equals("1")) {
                        if(myFlight.getFirstSeats() == 0) {
                            return false;
                        }
                        else {
                            myTicket.setType("First Class");
                            isValidClass = true;
                        }
                    }
                    if(userInput.equals("2")) {
                        if(myFlight.getBusinSeats() == 0) {
                            return false;
                        }
                        else {
                            myTicket.setType("Business Class");
                            isValidClass = true;
                        }
                    }
                    if(userInput.equals("3")) {
                        if(myFlight.getMainSeats() == 0) {
                            return false;
                        }
                        else {
                            myTicket.setType("Main Class");
                            isValidClass = true;
                        }
                    }
                    customerLog.write("\nCustomer with username " + myPerson.getUsername() + " selected flight of type " + myTicket.getType() + ".");
                }
            }
            customerLog.flush();
        }
        catch(IOException e){
            System.out.println("\n\nAn error occured when writing to the command log.");
        }
        return true;
    }

    /**
     * Checks if customer has enough balance for transaction.
     * [Taken from Ruben Carmona]
     * @param myFlight the object of a flight
     * @param customerLog the customer log
     * @return Binary decision tracker.
     */
    private boolean confirmTicket(Flight myFlight, FileWriter customerLog, int numOfTicketsIn) {
        try{
            isValidBalance = true;
            isValidChoice = false;
            totalSavings = 0.0;
            finalBasePrice = 0.0;
            while(!isValidChoice) {
                    numOfTickets = numOfTicketsIn;
                    if(numOfTickets >= 1 && numOfTickets <= 8) {
                        customerLog.write("\nCustomer with username " + myPerson.getUsername() + " selected to buy " + numOfTickets + " tickets.");
                        myTicket.setTotalSecFee(5.60 * numOfTickets);
                        String dateOfBirth = myPerson.getDoB();
                        String[] dobParts = dateOfBirth.split("/");
                        int birthMonth = Integer.parseInt(dobParts[0]);
                        int birthDay = Integer.parseInt(dobParts[1]);
                        int birthYear = Integer.parseInt(dobParts[2]);
                        int currentYear = 2023;
                        int currentMonth = 4;
                        int currentDay = 25;
                        age = currentYear - birthYear;
                        if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
                            age--;
                        }
                        if(myTicket.getType().equals("First Class")) {
                            if(myFlight.getFirstSeats() < numOfTickets) {
                                return false;
                            }
                            else {
                                finalBasePrice = myFlight.getFirstPrice();
                                if(myPerson.getRole().equalsIgnoreCase("Employee")) {
                                    totalSavings += myFlight.getFirstPrice() * 0.5;
                                }
                                if(myPerson.getHasMembership()) {
                                    totalSavings += myFlight.getFirstPrice() * 0.05;
                                }
                                if(age >= 65) {
                                    seniorDisc = myFlight.getFirstPrice() * 0.05;
                                    totalSavings += seniorDisc;
                                }
                                finalBasePrice -= totalSavings;
                                myTicket.setTotalPrice((9.15 + myTicket.getTotalSecFee() + myFlight.getOrigFee() + myFlight.getDestFee() + ((finalBasePrice + myFlight.getSurcharge()) * numOfTickets)) * 1.0825);
                                if(myPerson.getBalance() < myTicket.getTotalPrice()) {
                                    isValidBalance = false;
                                }
                                else {
                                    isValidChoice = true;
                                }
                            }
                        }
                        else if(myTicket.getType().equals("Business Class")) {
                            if(myFlight.getBusinSeats() < numOfTickets) {
                                return false;
                            }
                            else {
                                finalBasePrice = myFlight.getBusinPrice();
                                if(myPerson.getRole().equalsIgnoreCase("Employee")) {
                                    totalSavings += myFlight.getBusinPrice() * 0.75;
                                }
                                if(myPerson.getHasMembership()) {
                                    totalSavings += myFlight.getBusinPrice() * 0.05;
                                }
                                if(age >= 65) {
                                    seniorDisc = myFlight.getBusinPrice() * 0.05;
                                    totalSavings += seniorDisc;
                                }
                                finalBasePrice -= totalSavings;
                                myTicket.setTotalPrice((9.15 + myTicket.getTotalSecFee() + myFlight.getOrigFee() + myFlight.getDestFee() + ((finalBasePrice + myFlight.getSurcharge()) * numOfTickets)) * 1.0825);
                                if(myPerson.getBalance() < myTicket.getTotalPrice()) {
                                    isValidBalance = false;
                                }
                                else {
                                    isValidChoice = true;
                                }
                            }
                        }
                        else if(myTicket.getType().equals("Main Class")) {
                            if(myFlight.getMainSeats() < numOfTickets) {
                                return false;
                            }
                            else {
                                finalBasePrice = myFlight.getMainPrice();
                                if(myPerson.getRole().equalsIgnoreCase("Employee")) {
                                    totalSavings += myFlight.getMainPrice() * 0.75;
                                }
                                if(myPerson.getHasMembership()) {
                                    totalSavings += myFlight.getMainPrice() * 0.05;
                                }
                                if(age >= 65) {
                                    seniorDisc = myFlight.getMainPrice() * 0.05;
                                    totalSavings += seniorDisc;
                                }
                                finalBasePrice -= totalSavings;
                                myTicket.setTotalPrice((9.15 + myTicket.getTotalSecFee() + myFlight.getOrigFee() + myFlight.getDestFee() + ((finalBasePrice + myFlight.getSurcharge()) * numOfTickets)) * 1.0825);
                                if(myPerson.getBalance() < myTicket.getTotalPrice()) {
                                    isValidBalance = false;
                                }
                                else {
                                    isValidChoice = true;
                                }
                            }
                        }
                        if(isValidBalance == false) {
                            customerLog.write("\nCustomer with username " + myPerson.getUsername() + " didn't have enough money for transaction.\n");
                            customerLog.flush();
                            return false;
                        }
                    }
                    else {
                        return false;
                    }
            }
        }
        catch(IOException e){
            System.out.println("\n\nAn error occured when writing to the command log.");
        }
        return true;
    }

    /**
     * Generates confirmation number and updates flight seats and customer balance.
     * [Taken from Ruben Carmona]
     * @param myFlight the object of a flight
     * @param customerLog the customer log
     */
    private void prepareTicket(Flight myFlight, FileWriter customerLog) {
        try{
            myTicket.setSeats(numOfTickets);
            if(myTicket.getType().equals("First Class")) {
                myFlight.calcFirstSeats(myTicket.getSeats());
                myFlight.setFirstSeatsSold(myFlight.getFirstSeatsSold() + numOfTickets);
                myFlight.setFirstClassRevenue(myFlight.getFirstClassRevenue() + finalBasePrice);
            }
            else if(myTicket.getType().equals("Business Class")) {
                myFlight.calcBusinSeats(myTicket.getSeats());
                myFlight.setBusinSeatsSold(myFlight.getBusinSeatsSold() + numOfTickets);
                myFlight.setBusinClassRevenue(myFlight.getBusinClassRevenue() + finalBasePrice);
            }
            else if(myTicket.getType().equals("Main Class")) {
                myFlight.calcMainSeats(myTicket.getSeats());
                myFlight.setMainSeatsSold(myFlight.getMainSeatsSold() + numOfTickets);
                myFlight.setMainClassRevenue(myFlight.getMainClassRevenue() + finalBasePrice);
            }
            myFlight.setTotalMinerFee(myFlight.getTotalMinerFee() + 9.15);
            myFlight.setTotalSecFee(myFlight.getTotalSecFee() + myTicket.getTotalSecFee());
            myFlight.setTotalTax(myFlight.getTotalTax() + (myTicket.getTotalPrice() * 0.0825));
            myPerson.setBalance(myPerson.getBalance() - myTicket.getTotalPrice());
            myPerson.setSavedMoney(myPerson.getSavedMoney() + totalSavings);
            myPerson.setTicketsPurchased(myPerson.getTicketsPurchased() + 1);
            myTicket.setConfirmationNumber(myFlight.getFlightNumber() + ticketsCounter);
            myTicket.setSavings(totalSavings);
            airportManager.getFromAirMap(myFlight.getOrigCode()).setFeesRev(airportManager.getFromAirMap(myFlight.getOrigCode()).getFeesRev() + myFlight.getOrigFee());
            airportManager.getFromAirMap(myFlight.getDestCode()).setFeesRev(airportManager.getFromAirMap(myFlight.getDestCode()).getFeesRev() + myFlight.getDestFee());
            ++ticketsCounter;
            customerLog.write("\nCustomer with username " + myPerson.getUsername() + " bought " + numOfTickets +
                              " ticket for flight with ID " + myFlight.getId() + ".\n");
            customerLog.flush();
        }
        catch(IOException e){
            System.out.println("\n\nAn error occured when writing to the command log.");
        }
    }

    /**
     * Prints ticket both on terminal and as a txt file.
     * [Taken from Ruben Carmona]
     * @param myFlight the flight object
     * @param myTicket the ticket object
     * @param ticketPrinter the ticket printer
     */
    private void printTicket(Flight myFlight, Ticket myTicket, FileWriter ticketPrinter) {
        String itineraryInfo = "";
        itineraryInfo = myFlight.getOrigName() + " (" + myFlight.getOrigCode() + ") --> " +
                        myFlight.getDestName() + " (" + myFlight.getDestCode() + ")\n" +
                        myFlight.getDepaDate() + " " + myFlight.getDepaTime() + " --> " +
                        myFlight.getArrDate() + " " + myFlight.getArrTime() + "\n\n" +
                        "Ticket(s) type: " + myTicket.getType() + "\n" +
                        "Seat(s) purchased: " + myTicket.getSeats() + "\n" + 
                        "MinerAirlines Fee: $9.15" + "\n" + 
                        "Security Fee: $" + String.format("%.2f", myTicket.getTotalSecFee()) + "\n" + 
                        (age >= 65 ? ("Senior Discount: $" + String.format("%.2f", seniorDisc) + "\n") : "") +
                        "Money Saved: $" + String.format("%.2f", totalSavings) + "\n" + 
                        "Total price: $" + String.format("%.2f", myTicket.getTotalPrice()) + "\n" +
                        "Transaction confirmation number: " + myTicket.getConfirmationNumber();
        myTicket.setItineraryInfo(itineraryInfo);
        myTicket.setAssociatedUsername(myPerson.getUsername());
        myTicket.setIsCancelled(false);
        // ticketPrinter = new FileWriter("Ticket(s) " + myTicket.getConfirmationNumber() + ".txt");
        // ticketPrinter.write("\nThank you for using MinerAir!\n\nHere is your ticket(s) information:\n");
        // ticketPrinter.write(myTicket.getItineraryInfo() + "\n");
        myFlight.addFlightTicket(myTicket);
        myPerson.addPersonTicket(myTicket);
    }
}
