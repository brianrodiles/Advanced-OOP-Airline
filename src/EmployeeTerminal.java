import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * EmployeeTerminal class to handle all employee's activity.
 */
public class EmployeeTerminal {
    private Scanner userScnr = new Scanner(System.in);
    private Map<String, Person> customerMap;
    private Map<String, Person> employeeMap;
    private CustomerTerminal customerTerminal = new CustomerTerminal();
    private ArrayList<Ticket> flightEmployeeTicketList;
    private Airport checkAirport;
    private Person myPerson;
    private Ticket myTicket;
    private String everyCode, everyAir, selectUsername, userInput;
    private boolean isValidPerson, isValidOption;
    AirportSingleton airportManager = AirportSingleton.getInstance();
    
    /**
     * Empty constructor.
     * [Taken from Brian G. Rodiles Delgado]
     */
    public EmployeeTerminal(){
    }

    /**
     * Constructor to initialize maps.
     * [Taken from Brian G. Rodiles Delgado]
     * @param customerMapIn the map of customers
     * @param employeeMapIn the map of employees
     */
    public EmployeeTerminal(Map<String, Person> customerMapIn, Map<String, Person> employeeMapIn){
        this.customerMap = customerMapIn;
        this.employeeMap = employeeMapIn;
    }

    /**
     * Method to generate Electronic Ticket Summaries (ETSs).
     * [Taken from Brian G. Rodiles Delgado]
     * @param ticketPrinter the ticket printer
     * @return Binary decision tracker for canceling or not a ticket.
     */
    public boolean generateETS(FileWriter ticketPrinter) {
        try{
            isValidPerson = false;
            while(!isValidPerson) {
                try {
                    System.out.print("\nPlease enter an username to look for their tickets (enter -1 to go back to the employee menu): ");
                    selectUsername = userScnr.nextLine();
                    if(selectUsername.equalsIgnoreCase("-1")) {
                        System.out.println();
                        return true;
                    }
                    if (!(customerMap.containsKey(selectUsername) || employeeMap.containsKey(selectUsername))) {
                        throw new InvalidInput("\nThere is no person with that listed username.");
                    }
                    else {
                        if (customerMap.containsKey(selectUsername)) {
                            myPerson = customerMap.get(selectUsername);
                        }
                        else {
                            myPerson = employeeMap.get(selectUsername);
                        }
                        isValidPerson = true;
                    }
                }
                catch(InvalidInput e) {
                    System.out.println(" Please try again.");
                }
            }
            int purchasedTickets = myPerson.getTicketsPurchased();
            if(purchasedTickets == 0) { 
                System.out.println("\nThis user has not purchased any tickets yet.\n\nReturning to the employee menu...\n");
                try {
                    Thread.sleep(2500);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
                return false;
            }
            isValidOption = false;
            while(!isValidOption) {
                try {
                    System.out.print("\nThis user has purchased " + purchasedTickets + " tickets.\n");
                    System.out.print("\nDo you want to generate the Electronic Ticket Summary (ETS)? (Y/n): ");
                    userInput = userScnr.nextLine();
                    System.out.println();
                    if(userInput.equalsIgnoreCase("Y")) {
                        ticketPrinter = new FileWriter(myPerson.getUsername() + " ETS" + ".txt");
                        ticketPrinter.write("\nThank you for using Miner Airlines, " + myPerson.getFirstName() + " " + myPerson.getLastName() + "!\n\n");
                        for(int i = 0; i < purchasedTickets; i++) {
                            myTicket = myPerson.getTicket(i);
                            ticketPrinter.write("---------------------------------------------------------------------------------------\n" + myTicket.getItineraryInfo() + "\n\n");
                        }
                        ticketPrinter.flush();
                        isValidOption = true;
                    }
                    else if(userInput.equalsIgnoreCase("n")) {
                        System.out.println("No problem. Returning to the ticket selector menu...");
                        isValidOption = true;
                    }
                    else {
                        throw new InvalidInput("That is not a valid choice. Please try again.\n");
                    }
                }
                catch(InvalidInput e) {
                    System.out.println(" Please try again.");
                }
            }
        } catch(IOException e){
            System.out.println("\n\nAn error occured while printing the ticket.");
        }
        return false;
    }

    /**
     * Allows employee to inquire info about an airport.
     * [Taken from Ruben Carmona]
     */
    public void inquireAir() {
        boolean isValidChoice = false;
        while(!isValidChoice) {
            try {
                System.out.println("\nHere are all the available airports.\n");
                airportManager.printEveryNames();
                System.out.print("Please select the airport code to view information from it (enter -1 to exit the inquire menu): ");
                everyCode = userScnr.next();
                if (everyCode.equals("-1")) {
                    break;
                }
                Pattern patternProfile  = Pattern.compile("^[A-Z]{3}$");
                Matcher profileMatcher = patternProfile.matcher(everyCode);
                if(profileMatcher.matches()) {
                    everyAir = airportManager.getFromEveryAirport(everyCode);
                    if (everyAir.equals("None")) {
                        System.out.println("\nThat airport code doesn't exist. Please try again.");
                    }
                    else {
                        checkAirport = airportManager.getFromAirMap(everyCode);
                        System.out.println("\nCode: " + checkAirport.getCode() + "\nAirport Name: " + checkAirport.getName() + "\nCity: " + checkAirport.getCity() +
                                        "\nState: " + checkAirport.getState() + "\nCountry: " + checkAirport.getCountry() + "\nFees: $" + String.format("%.2f", checkAirport.getFee()) +
                                        "\nLounge Status: " + (checkAirport.getHasLounge() ? "Available" : "Unavailable") + "\nFees revenue: $" + String.format("%.2f", checkAirport.getFeesRev()));
                    }
                }
                else {
                    throw new InvalidInput("\nNot a valid airport code.");
                }
            }
            catch(InvalidInput e) {
                System.out.println(" Please try again.");
            }
        }
    }
    
    /**
     * Allows employee to update information on all flights.
     * [Taken from Brian G. Rodiles Delgado]
     * @param flightsMap the map of flights
     * @param commandLog the command log
     * @param ticketPrinter the ticket printer
     * @return boolean value if an employee wants to log out
     */
    public boolean updateSelector(Map<Integer, Flight> flightsMap, FileWriter commandLog, FileWriter ticketPrinter) {
        try{
            Pattern patternProfile  = Pattern.compile("^\\d+$");
            int keepUpdating = -1;
            int flightsCounter = flightsMap.size();
            boolean setExc;

            while(keepUpdating != 0){
                try{
                    System.out.printf("\nPlease enter a number from 1 to %d to select flight (enter \"EXIT\" to log out): ", flightsCounter);
                    String userInputString = userScnr.next();
                    if(userInputString.equalsIgnoreCase("EXIT")) {
                        System.out.println("\n\nReturning to the main menu...");
                        break;
                    }
                    Matcher profileMatcher = patternProfile.matcher(userInputString);
                    if(profileMatcher.matches()) {
                        int userInput = Integer.parseInt(userInputString);
                        
                        // Prompt warning for no negative integers
                        if (userInput <= 0){
                            throw new InvalidInput("\nEnter a positive integer and non-zero.");
                        }
                        
                        // Select an available flight from HashMap
                        else if (userInput > 0 && userInput < flightsCounter + 1){

                            if(flightsMap.get(Integer.valueOf(userInput)).getIsCancelled()) {
                                System.out.println("\n\nTHIS FLIGHT WAS CANCELLED.\nReturning to the flight update menu...\n");
                                try {
                                    Thread.sleep(2500);
                                } catch(InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else{
                                flightsMap.get(Integer.valueOf(userInput)).printFlight();

                                int userSelect = 0;
                                while (userSelect != 11){
                                    try {
                                        System.out.println("\nWould you like to update any information about flight " + userInput + "?\n");
                                        System.out.println("1) Update origin airport and code.");
                                        System.out.println("2) Update destination airport and code.");
                                        System.out.println("3) Update departure date.");
                                        System.out.println("4) Update departure time.");
                                        System.out.println("5) Update first class price.");
                                        System.out.println("6) Update business class price.");
                                        System.out.println("7) Update main cabine price.");
                                        System.out.println("8) Cancel flight.");
                                        System.out.println("9) List every customer in flight.");
                                        System.out.println("10) Display financial information.");
                                        System.out.println("11) Do nothing.");
                                        System.out.print("\nSelect what you want to do: ");
                                        userSelect = userScnr.nextInt();
                                        if (userSelect < 1 || userSelect > 11){
                                            throw new InvalidInput("\nNot a valid input.");
                                        }
                                        else{
                                            switch(userSelect){
                                                // Update origin airport and code
                                                case 1:
                                                    userScnr.nextLine();
                                                    System.out.print("\nEnter new origin airport name: ");
                                                    String newOrigName = userScnr.nextLine();
                                                    flightsMap.get(Integer.valueOf(userInput)).setOrigName(newOrigName);
                                                    System.out.print("Enter new origin code: ");
                                                    String newOriginCode = userScnr.next();
                                                    flightsMap.get(Integer.valueOf(userInput)).setOrigCode(newOriginCode);
                                                    System.out.println("\nThis is the updated flight information: ");
                                                    flightsMap.get(Integer.valueOf(userInput)).printFlight();
                                                    commandLog.write("\nFlight ID " + userInput + " updated origin airport to " + newOrigName + " and origin code to " + newOriginCode + ".\n");
                                                    break;
                                                // Update destination airport and code    
                                                case 2:
                                                    userScnr.nextLine();
                                                    System.out.print("Enter new destination airport: ");
                                                    String newDestName = userScnr.nextLine();
                                                    flightsMap.get(Integer.valueOf(userInput)).setDestName(newDestName);
                                                    System.out.print("Enter new destination code: ");
                                                    String newDestCode = userScnr.next();
                                                    flightsMap.get(Integer.valueOf(userInput)).setDestCode(newDestCode);
                                                    System.out.println("\nThis is the updated information for flight " + userInput + ": ");
                                                    flightsMap.get(Integer.valueOf(userInput)).printFlight();
                                                    commandLog.write("\nFlight ID " + userInput + " updated destination airport to " + newDestName + " and destination code to " + newDestCode + ".\n");
                                                    break;
                                                // Update departure date and programatically the arrival date
                                                case 3:
                                                    userScnr.nextLine();
                                                    Pattern patternDate = Pattern.compile("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/[0-9]{4}$");
                                                    String newDepaDate = "";
                                                    while(true){
                                                        try {
                                                            System.out.print("\nEnter new departure date in mm/dd/yyyy format (Initial zeros are required): ");
                                                            newDepaDate = userScnr.next();
                                                            Matcher matcher = patternDate.matcher(newDepaDate);
                                                            boolean dateIsValid = matcher.matches();
                                                            if(dateIsValid){
                                                                flightsMap.get(Integer.valueOf(userInput)).setDepaDate(newDepaDate);
                                                                System.out.println("New departure date is updated!");
                                                                break;
                                                            }
                                                            else{
                                                                throw new InvalidInput("New departure date is not valid.");
                                                            }
                                                        }
                                                        catch(InvalidInput e) {
                                                            System.out.println(" Please try again.");
                                                        }
                                                    }
                                                    System.out.println("\nThis is the updated information for flight " + userInput + ": ");
                                                    flightsMap.get(Integer.valueOf(userInput)).printFlight();
                                                    commandLog.write("\nFlight ID " + userInput + " updated departure date to " + newDepaDate + " and automatically set arrival date to " + flightsMap.get(Integer.valueOf(userInput)).getArrDate() + ".\n");
                                                    break;
                                                // Update departure time and programatically the departure time
                                                case 4:
                                                    userScnr.nextLine();
                                                    Pattern patternTime = Pattern.compile("^([1-9]|1[0-2]):(0[0-9]|[1-5][0-9]) (AM|PM)$");
                                                    String newDepaTime = "";
                                                    while(true){
                                                        try {
                                                            System.out.print("\nEnter new departure time in hh:mm AM/PM format (no initial zeros are allowed): ");
                                                            newDepaTime = userScnr.nextLine();
                                                            Matcher matcher = patternTime.matcher(newDepaTime);
                                                            boolean timeIsValid = matcher.matches();
                                                            if(timeIsValid){
                                                                flightsMap.get(Integer.valueOf(userInput)).setDepaTime(newDepaTime);
                                                                System.out.println("New departure time is updated!");
                                                                break;
                                                            }
                                                            else{
                                                                throw new InvalidInput("New departure time is not valid.");
                                                            }
                                                        }
                                                        catch(InvalidInput e) {
                                                            System.out.println(" Please try again.");
                                                        }
                                                    }
                                                    System.out.println("\nThis is the updated information for flight " + userInput + ": ");
                                                    flightsMap.get(Integer.valueOf(userInput)).printFlight();
                                                    commandLog.write("\nFlight ID " + userInput + " updated departure time to " + newDepaTime + " and automatically set arrival time to " + flightsMap.get(Integer.valueOf(userInput)).getArrTime() + ".\n");
                                                    break;
                                                // Update the first class price
                                                case 5:
                                                    setExc = true;
                                                    do{
                                                        try{
                                                            userScnr.nextLine();
                                                            System.out.print("Enter new first class price: ");
                                                            int newFirstPrice = userScnr.nextInt();
                                                            flightsMap.get(Integer.valueOf(userInput)).setFirstPrice(newFirstPrice);
                                                            System.out.println("\nThis is the updated information for flight " + userInput + ": ");
                                                            flightsMap.get(Integer.valueOf(userInput)).printFlight();
                                                            commandLog.write("\nFlight ID " + userInput + " updated first class price to $" + newFirstPrice + ".\n");
                                                            setExc = false;
                                                        }
                                                        catch(InputMismatchException e){
                                                            System.out.println("\n\nEnter only an integer.\n\n");
                                                            userScnr.next();
                                                        }
                                                    }while(setExc);
                                                    break;
                                                // Update the business class price
                                                case 6:
                                                    setExc = true;
                                                    do{
                                                        try{
                                                            userScnr.nextLine();    
                                                            System.out.print("Enter new business class price: ");
                                                            int newBusinPrice = userScnr.nextInt();
                                                            flightsMap.get(Integer.valueOf(userInput)).setBusinPrice(newBusinPrice);
                                                            System.out.println("\nThis is the updated information for flight " + userInput + ": ");
                                                            flightsMap.get(Integer.valueOf(userInput)).printFlight();
                                                            commandLog.write("\nFlight ID " + userInput + " updated business class price to $" + newBusinPrice + ".\n");
                                                            setExc = false;
                                                        }
                                                        catch(InputMismatchException e){
                                                            System.out.println("\n\nEnter only an integer.\n\n");
                                                            userScnr.next();
                                                        }
                                                    }while(setExc);
                                                    break;
                                                // Update the main cabin price
                                                case 7:
                                                    setExc = true;
                                                    do{
                                                        try{
                                                            userScnr.nextLine();
                                                            System.out.print("Enter new main cabine price: ");
                                                            int newMainPrice = userScnr.nextInt();
                                                            flightsMap.get(Integer.valueOf(userInput)).setMainPrice(newMainPrice);
                                                            System.out.println("\nThis is the updated information for flight " + userInput + ": ");
                                                            flightsMap.get(Integer.valueOf(userInput)).printFlight();
                                                            commandLog.write("\nFlight ID " + userInput + " updated cabine class price to $" + newMainPrice + ".\n");
                                                            setExc = false;
                                                        }
                                                        catch(InputMismatchException e){
                                                            System.out.println("\n\nEnter only an integer.\n\n");
                                                            userScnr.next();
                                                        }
                                                    }while(setExc);
                                                    break;
                                                // Cancel flight
                                                case 8:
                                                    try{
                                                        System.out.print("\nDo you want to cancel this flight (Y/n): ");
                                                        String userSelection = userScnr.next();
                                                        System.out.println();
                                                        if(userSelection.equalsIgnoreCase("Y")) {
                                                            System.out.println("Canceling flight and tickets...");
                                                            System.out.println();
                                                            flightEmployeeTicketList = (flightsMap.get(Integer.valueOf(userInput))).getFlightTicketList();
                                                            for(int i = 0; i < flightEmployeeTicketList.size(); ++i) {
                                                                Ticket flightTicket = flightEmployeeTicketList.get(i);
                                                                Person testPerson;
                                                                if(customerMap.containsKey(flightTicket.getAssociatedUsername())) {
                                                                    testPerson = customerMap.get(flightTicket.getAssociatedUsername());
                                                                }
                                                                else {
                                                                    testPerson = employeeMap.get(flightTicket.getAssociatedUsername());
                                                                }
                                                                System.out.println("Cancelled ticket with username " + testPerson.getUsername() + " and flight ID " + flightTicket.getConfirmationNumber() + ".");
                                                                customerTerminal.cancelTicket(testPerson, flightsMap.get(Integer.valueOf(userInput)), flightTicket, true);
                                                                flightTicket.setItineraryInfo("\n***CANCELLED TICKET***\n\n" +
                                                                flightsMap.get(Integer.valueOf(userInput)).getOrigAirport() + " (" + flightsMap.get(Integer.valueOf(userInput)).getOrigCode() + ") --> " +
                                                                flightsMap.get(Integer.valueOf(userInput)).getDestAirport() + " (" + flightsMap.get(Integer.valueOf(userInput)).getDestCode() + ")\n" +
                                                                flightsMap.get(Integer.valueOf(userInput)).getDepaDate() + " " + flightsMap.get(Integer.valueOf(userInput)).getDepaTime() + " --> " +
                                                                flightsMap.get(Integer.valueOf(userInput)).getArrDate() + " " + flightsMap.get(Integer.valueOf(userInput)).getArrTime() + "\n\n" +
                                                                "Ticket(s) type: " + flightTicket.getType() + "\n" +
                                                                "Seat(s) purchased: " + flightTicket.getSeats() + "\n" + 
                                                                "MinerAirlines Fee: $9.15" + "\n" + 
                                                                "Security Fee: $" + String.format("%.2f", flightTicket.getTotalSecFee()) + "\n" +
                                                                "Money Saved: $" + String.format("%.2f", flightTicket.getSavings()) + "\n" +
                                                                "Total price: $0 (refunded)\n" +
                                                                "Transaction confirmation number: " + flightTicket.getConfirmationNumber());
                                                                commandLog.write("\nCustomer with username " + testPerson.getUsername() + " cancelled ticket for flight with id " + flightsMap.get(Integer.valueOf(userInput)).getId() + " and confirmation number " + flightTicket.getConfirmationNumber() + ".");
                                                                // Removed to prevent size being changed
                                                                // flightEmployeeTicketList.remove(i);
                                                            }
                                                            flightsMap.get(Integer.valueOf(userInput)).setIsCancelled(true);
                                                            System.out.println("\n\nReturning to flight update menu...");
                                                            return false;
                                                        }
                                                        else if(userSelection.equalsIgnoreCase("n")) {
                                                            System.out.println("No problem. Returning to the options menu...");
                                                        }
                                                        else {
                                                            throw new InvalidInput("That is not a valid choice.");
                                                        }
                                                    
                                                    }
                                                    catch(InvalidInput e) {
                                                        System.out.println(" Please try again.");
                                                    } 
                                                    catch(IOException e){
                                                        System.out.println("\n\nAn error occured while printing the ticket.");
                                                    }
                                                    break;
                                                // Display all customers in flight
                                                case 9:
                                                    flightsMap.get(Integer.valueOf(userInput)).printFlightCustomers(customerMap, employeeMap);
                                                    commandLog.write("\nEmployee inquired about flight customers.");
                                                    break;
                                                // Display financial information
                                                case 10:
                                                    flightsMap.get(Integer.valueOf(userInput)).printFinancialInfo();
                                                    commandLog.write("\nEmployee inquired about flight's financial information.");
                                                    break;
                                                // Do nothing
                                                default:
                                                    break;
                                            }
                                        }
                                    }
                                    catch(InvalidInput e) {
                                        System.out.println(" Please try again.");
                                    }
                                }
                            }
                        }
                        else{
                            throw new InvalidInput("\n\nFlight number not available.");
                        }
                    }
                    else{
                        throw new InvalidInput("\nEnter only integers or write EXIT correctly.");
                    }
                }
                catch(InvalidInput e) {
                    System.out.println(" Please try again.");
                }
                catch(InputMismatchException e){
                    System.out.println("\n\nEnter only an integer.\n");
                    userScnr.next();
                }
            }
            commandLog.write("\n");
            commandLog.flush();
        }
        catch(NoSuchElementException e){
            System.out.println("\n\nThe Scanner received no input. If you didn't exited the program with Ctrl + C, check if the CSV file has all the data.");
        }
        catch(IOException e){
            System.out.println("\n\nAn error occured when writing to the command log.");
        }
        System.out.println();
        return true;
    }
}
