import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;

/**
 * CustomerTerminal class to handle all the activity a customer can make in the program.
 */
public class CustomerTerminal {
    private Person   myPerson;
    private Flight   myFlight;
    private Ticket   myTicket;
    private boolean  isIndividualCustomer, isValidCustomer, isAuthenticatedCustomer, isValidFlight, isValidIndex, isValidFlightChoice, isValidClass;
    private boolean  isValidChoice, isValidChoice2, isValidCancelSelect, isValidInnerChoice, isValidBalance, isValidTransaction;
    private String   userInput, firstName, lastName, username, password, numberOfTickets;
    private int      flightTicketId, numOfTickets, age;
    private int      ticketsCounter = 0;
    private double   totalSavings, finalBasePrice, seniorDisc;
    private Scanner scnr = new Scanner(System.in);
    AirportSingleton airportManager = AirportSingleton.getInstance();

    /**
     * Empty constructor.
     * [Taken from Brian G. Rodiles Delgado]
     */
    public CustomerTerminal() {
    }

    /**
     * Asks if user is an individual customer.
     * [Taken from Brian G. Rodiles Delgado]
     * @return boolean to know if user is individual customer
     */
    public boolean individualCustomer() {
        isIndividualCustomer = true;
        while(isIndividualCustomer) {
            try {
                System.out.print("\nAre you an individual customer? (Y/n): ");
                userInput = scnr.next();
                System.out.println();
                if(userInput.equalsIgnoreCase("Y")) {
                    System.out.println("Nice, let me prompt you to the log in system...");
                    return true;
                }
                else if(userInput.equalsIgnoreCase("n")) {
                    System.out.println("No problem. Returning to the main menu...");
                    isIndividualCustomer = false;
                }
                else {
                    throw new InvalidInput("That is not a valid choice.");

                }
            }
            catch(InvalidInput e) {
                System.out.println(" Please try again.");
            }
        }
        return false;
    }

    /**
     * Checks if the customer exists in file.
     * [Taken from Brian G. Rodiles Delgado]
     * @param personMap the map of persons
     * @return Customer object
     */
    public Person personExists(Map<String, Person> personMap) {
        isValidCustomer = false;
        while(!isValidCustomer) {
            System.out.print("\nPlease enter your first name: ");
            firstName = scnr.next();
            System.out.print("\nPlease enter your last name: ");
            lastName = scnr.next();
            if(personMap.containsKey((firstName + lastName).toLowerCase())) {
                System.out.println("\nFound username!");
                isValidCustomer = true;
            }
            else {
                System.out.println("Your first and last name do not match our records. Please try again.");
            }
        }
        myPerson = personMap.get((firstName + lastName).toLowerCase());
        return myPerson;
    }

    /**
     * Logs user if username and password are correct.
     * [Taken from Brian G. Rodiles Delgado]
     * @param myPerson the object of a person
     * @param loginLog the customer log
     */
    public void personLogin(Person myPerson, FileWriter loginLog) {
        try{
            isAuthenticatedCustomer = false;
            while(!isAuthenticatedCustomer) {
                System.out.print("\nPlease enter your username: ");
                username = scnr.next().toLowerCase();
                System.out.print("\nPlease enter your password: ");
                password = scnr.next();
                if(myPerson.getUsername().equals(username) && myPerson.getPassword().equals(password)) {
                    loginLog.write("\nCustomer with username " + myPerson.getUsername() + " logged in.");
                    System.out.println("\nPerfect, you are logged in.\n");
                    isAuthenticatedCustomer = true;
                }
                else {
                    System.out.println("\nThe username and password combination are incorrect. Please try again.");
                }
            }
            loginLog.flush();
        }
        catch(IOException e){
            System.out.println("\n\nAn error occured when writing to the command log.");
        }
    }

    /**
     * Method to determine if employee wants to go to the update flight or ticket purchasing menus.
     * [Taken from Brian G. Rodiles Delgado]
     * @return decision integer
     */
    public int wantsWhatToDo() {
        isValidChoice = false;
        while(!isValidChoice) {
            try {
                System.out.print("Select whether you want to:\n\n1) Go to the update menu.\n2) Go to ticketing.\n3) Inquire about an airport.\n" + 
                                "4) Generate an ETS (Electronic Ticket Summary) for a customer.\n5) Go to auto transaction menu.\n6) Exit the employee menu.\n\nEnter your selection here: ");
                userInput = scnr.next();
                if(userInput.equals("1") || userInput.equals("2") || userInput.equals("3") || userInput.equals("4") || userInput.equals("5") || userInput.equals("6")) {
                    if(userInput.equals("1")) {
                        return 1;
                    }
                    else if(userInput.equals("2")) {
                        return 2;
                    }
                    else if(userInput.equals("3")) {
                        return 3;
                    }
                    else if(userInput.equals("4")) {
                        return 4;
                    }
                    else if(userInput.equals("5")) {
                        return 5;
                    }
                    else if(userInput.equals("6")) {
                        return 6;
                    }
                    isValidChoice = true;
                }
                else {
                    throw new InvalidInput("\nNot a valid choice.");
                }
            }
            catch(InvalidInput e) {
                System.out.println(" Please try again.");
            }
        }
        return 0;
    }

    /**
     * Method to determine if an user wants to buy or cancel a ticket.
     * [Taken from Brian G. Rodiles Delgado]
     * @return Binary decision tracker.
     */
    public boolean wantsToCancelTicket() {
        boolean wantsToCancelTicket = false;
        isValidChoice = false;
        while(!isValidChoice) {
            try {
                System.out.print("Select whether you want to buy (1) or cancel (2) a ticket: ");
                userInput = scnr.next();
                if(userInput.equals("1") || userInput.equals("2")) {
                    if(userInput.equals("1")) {
                        wantsToCancelTicket = false;
                    }
                    else {
                        wantsToCancelTicket = true;
                    }
                    isValidChoice = true;
                }
                else {
                    throw new InvalidInput("\n\nNot a valid choice.");
                }
            }
            catch(InvalidInput e) {
                System.out.println(" Please try again.");
            }
        }
        return wantsToCancelTicket;
    }

    /**
     * Method to view or cancel tickets.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param customerLog the customer log
     * @return Binary decision tracker for canceling or not a ticket.
     */
    public boolean cancelMenu(FileWriter customerLog) {
        try{
            int purchasedTickets = myPerson.getTicketsPurchased();
            if(purchasedTickets == 0) { 
                System.out.println("\nYou have not purchased any tickets yet.\nMake your first purchase before canceling.\n\nReturning to the menu...\n");
                try {
                    Thread.sleep(2500);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
                return false;
            }

            int userTicketNumber = 0;
            isValidChoice = false;
            while(!isValidChoice) {
                try {
                    System.out.print("\nYou have purchased " + purchasedTickets + " tickets.\nSelect the ticket number to view/cancel (enter RETURN to go back to the menu or EXIT to log out): ");
                    userInput = scnr.next();
                    if(userInput.equalsIgnoreCase("RETURN")) {
                        System.out.println();
                        return false;
                    }
                    else if(userInput.equalsIgnoreCase("EXIT")) {
                        System.out.println();
                        return true;
                    }
                    Pattern patternProfile  = Pattern.compile("^[" + 1 + "-" + purchasedTickets + "]$");
                    Matcher profileMatcher = patternProfile.matcher(userInput);
                    if(profileMatcher.matches()) {
                        userTicketNumber = Integer.parseInt(userInput);
                        System.out.println("\nThis is the information for the ticket:");
                        myTicket = myPerson.getTicket(userTicketNumber - 1);
                        isValidCancelSelect = false;
                        while (!isValidCancelSelect) {
                            try {
                                myTicket.printTicketInfo();
                                if(!myTicket.getIsCancelled()){
                                    System.out.print("\nDo you want to cancel this ticket (Y/n): ");
                                    userInput = scnr.nextLine();
                                    System.out.println();
                                    if(userInput.equalsIgnoreCase("Y")) {
                                        System.out.println("Canceling ticket...");
                                        cancelTicket(myPerson, myFlight, myTicket, false);
                                        myTicket.setItineraryInfo("\n***CANCELLED TICKET***\n\n" +
                                        myFlight.getOrigName() + " (" + myFlight.getOrigCode() + ") --> " +
                                        myFlight.getDestName() + " (" + myFlight.getDestCode() + ")\n" +
                                        myFlight.getDepaDate() + " " + myFlight.getDepaTime() + " --> " +
                                        myFlight.getArrDate() + " " + myFlight.getArrTime() + "\n\n" +
                                        "Ticket(s) type: " + myTicket.getType() + "\n" +
                                        "Seat(s) purchased: " + myTicket.getSeats() + "\n" + 
                                        "MinerAirlines Fee: $9.15" + "\n" + 
                                        "Security Fee: $" + String.format("%.2f", myTicket.getTotalSecFee()) + "\n" + 
                                        "Money Saved: $" + String.format("%.2f", totalSavings) + "\n" + 
                                        (myPerson.getRole().equalsIgnoreCase("Customer") ? "Total price: $9.15 (Miner Airlines Fee not refunded)\n" : "Total price: $0 (refunded)\n") +
                                        "Transaction confirmation number: " + myTicket.getConfirmationNumber());
                                        customerLog.write("\nCustomer with username " + myPerson.getUsername() + " cancelled ticket for flight with id " + myFlight.getId() + " and confirmation number " + myTicket.getConfirmationNumber() + ".");
                                        isValidCancelSelect = true;
                                    }
                                    else if(userInput.equalsIgnoreCase("n")) {
                                        System.out.println("No problem. Returning to the ticket canceling selector...");
                                    }
                                    else {
                                        throw new InvalidInput("That is not a valid choice.");
                                    }
                                }
                                else {
                                    isValidCancelSelect = true;
                                }
                            }
                            catch(InvalidInput e) {
                                System.out.println(" Please try again.");
                            }
                        }
                    }
                    else {
                        throw new InvalidInput("\nEnter a correct ticket number, only integers, or RETURN correctly.");
                    }
                }
                catch(InvalidInput e) {
                    System.out.println(" Please try again.");
                }
            }
            customerLog.flush();
        } catch(IOException e){
            System.out.println("\n\nAn error occured while printing the ticket.");
        }
        return false;
    }

    /**
     * Helper method to cancel a ticket and return money and seats.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param cancelPerson the person object
     * @param cancelFlight the flight object
     * @param cancelTicket the ticket object
     * @param isCanceledByEmployee the status if a ticket is cancelled by an employee
     */
    public void cancelTicket(Person cancelPerson, Flight cancelFlight, Ticket cancelTicket, boolean isCanceledByEmployee) {
        cancelTicket.setIsCancelled(true);
        // Total amount is returned to an employee
        if(cancelPerson.getRole().equalsIgnoreCase("Employee") || isCanceledByEmployee) {
            cancelPerson.setBalance(cancelPerson.getBalance() + cancelTicket.getTotalPrice());
        }
        // The Miner Fee is not returned to a customer
        else {
            cancelPerson.setBalance(cancelPerson.getBalance() + cancelTicket.getTotalPrice() - 9.15);
        }
        cancelPerson.setTicketsPurchased(cancelPerson.getTicketsPurchased() - 1);
        if(cancelTicket.getType().equals("First Class")) {
            cancelFlight.setFirstSeats(cancelFlight.getFirstSeats() + cancelTicket.getSeats());
            cancelFlight.setFirstSeatsSold(cancelFlight.getFirstSeatsSold() - cancelTicket.getSeats());
        }
        else if(cancelTicket.getType().equals("Business Class")) {
            cancelFlight.setBusinSeats(cancelFlight.getBusinSeats() + cancelTicket.getSeats());
            cancelFlight.setBusinSeatsSold(cancelFlight.getBusinSeatsSold() - cancelTicket.getSeats());
        }
        else {
            cancelFlight.setMainSeats(cancelFlight.getMainSeats() + cancelTicket.getSeats());
            cancelFlight.setMainSeatsSold(cancelFlight.getMainSeatsSold() - cancelTicket.getSeats());
        }
        cancelFlight.setTotalSeats(cancelFlight.getTotalSeats() + cancelTicket.getSeats());
        cancelFlight.removePersonTicket(cancelTicket.getConfirmationNumber(), isCanceledByEmployee);
    }

    /**
     * Generates empty Ticket object.
     * [Taken from Brian G. Rodiles Delgado]
     * @return Ticket object
     */
    public Ticket generateSampleTicket() {
        return myTicket = new Ticket();
    }

    /**
     * Checks if the requested flight exists.
     * [Taken from Brian G. Rodiles Delgado]
     * @param flightsMap the map of flights
     * @param customerLog the customer log
     * @return Flight object
     */
    public Flight flightExists(Map<Integer, Flight> flightsMap, FileWriter customerLog) {
        try{
            isValidFlight = false;
            while(!isValidFlight) {
                try {
                    System.out.print("\nPlease select whether you want to select a flight by ID (1) or by airport menu (2): ");
                    userInput = scnr.next();
                    if(userInput.equals("1") || userInput.equals("2")) {
                        if(userInput.equals("1")) {
                            isValidIndex = false;
                            while (!isValidIndex) {
                                try {
                                    scnr.nextLine();
                                    System.out.print("\nChoose the ID of the flight you would like to buy tickets for (enter 0 to show all the flights or -1 to exit): ");
                                    flightTicketId = scnr.nextInt();
                                    if(flightTicketId == -1) {
                                        return null;
                                    }
                                    if(flightTicketId == 0) {
                                        System.out.println("\nThese are the available flights:");
                                        for(int i = 1; i <= flightsMap.size(); i++) {
                                            myFlight = flightsMap.get(i);
                                            System.out.print("\n" + myFlight.getId() + ": ");
                                            System.out.println(myFlight.getOrigName() + " (" + myFlight.getOrigCode() + ") --> " +
                                                            myFlight.getDestName() + " (" + myFlight.getDestCode() + ")");
                                            System.out.println(myFlight.getDepaDate() + " " + myFlight.getDepaTime() + " --> " +
                                                            myFlight.getArrDate() + " " + myFlight.getArrTime());
                                        }
                                    }
                                    else if(flightsMap.containsKey(flightTicketId)) {
                                        myFlight = flightsMap.get(flightTicketId);
                                        if(myFlight.getTotalSeats() == 0){
                                            System.out.println("\nAll tickets are sold out for this flight. Please select another one.");
                                        }
                                        else if(myFlight.getIsCancelled()) {
                                            System.out.println("\nThis flight was cancelled. Please select another flight.");
                                            try {
                                                Thread.sleep(2500);
                                            } catch(InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        else {
                                            System.out.println("\nPerfect, let me send you to ticketing.");
                                            isValidFlightChoice = false;
                                            while(!isValidFlightChoice) {
                                                try {
                                                    System.out.println("\nFlight Information");
                                                    System.out.println("-------------------");
                                                    System.out.print(myFlight.getId() + ": ");
                                                    System.out.println(myFlight.getOrigName() + " (" + myFlight.getOrigCode() + ") --> " +
                                                                    myFlight.getDestName() + " (" + myFlight.getDestCode() + ")");
                                                    System.out.println(myFlight.getDepaDate() + " " + myFlight.getDepaTime() + " --> " +
                                                                    myFlight.getArrDate() + " " + myFlight.getArrTime());
                                                    customerLog.write("\nCustomer with username " + myPerson.getUsername() + " SELECTED flight with ID " + myFlight.getId() + ".");
                                                    isValidFlight = true;
                                                    System.out.printf("\nYour current balance is $%.2f", myPerson.getBalance());
                                                    System.out.println();
                                                    System.out.println("\nAvailable class options:");
                                                    System.out.print("1. First Class: ");
                                                    if(myFlight.getFirstSeats() == 0) { 
                                                        System.out.println("Sold Out");
                                                    }
                                                    else {
                                                        System.out.print("$" + myFlight.getFirstPrice());
                                                        if(myFlight.getFlightType().equals("International")) {
                                                            System.out.printf(" + $" + myFlight.getSurcharge() + " (surcharge International Flight) = $%.2f", myFlight.getFirstPrice() + myFlight.getSurcharge());
                                                        }
                                                    }
                                                    System.out.print("\n2. Business Class: ");
                                                    if(myFlight.getBusinSeats() == 0) { 
                                                        System.out.println("Sold Out");
                                                    }
                                                    else {
                                                        System.out.print("$" + myFlight.getBusinPrice());
                                                        if(myFlight.getFlightType().equals("International")) {
                                                            System.out.printf(" + $" + myFlight.getSurcharge() + " (surcharge International Flight) = $%.2f", myFlight.getBusinPrice() + myFlight.getSurcharge());
                                                        }
                                                    }
                                                    System.out.print("\n3. Main Cabin Class: ");
                                                    if(myFlight.getMainSeats() == 0) { 
                                                        System.out.println("Sold Out");
                                                    }
                                                    else {
                                                        System.out.print("$" + myFlight.getMainPrice());
                                                        if(myFlight.getFlightType().equals("International")) {
                                                            System.out.printf(" + $" + myFlight.getSurcharge() + " (surcharge International Flight) = $%.2f", myFlight.getMainPrice() + myFlight.getSurcharge());
                                                        }
                                                    }
                                                    System.out.print("\n\nDo you want to buy tickets for this flight? (Y/n): ");
                                                    userInput = scnr.next();
                                                    if(userInput.equalsIgnoreCase("Y")) {
                                                        System.out.println("Perfect, let me send you to the class selection menu...");
                                                        isValidFlightChoice = true;
                                                        isValidIndex = true;
                                                    }
                                                    else if(userInput.equalsIgnoreCase("n")) {
                                                        System.out.println("\nNo problem. Returning to the ticket index menu...");
                                                        customerLog.write("\nCustomer with username " + myPerson.getUsername() + " UNSELECTED flight with ID " + myFlight.getId() + ".");
                                                        isValidFlightChoice = true;
                                                    }
                                                    else {
                                                        throw new InvalidInput("That is not a valid choice.");
                                                    }
                                                }
                                                catch(InvalidInput e) {
                                                    System.out.println(" Please try again.");
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        System.out.println("\nFlight not found. Please try again.");
                                    }
                                }
                                catch(InputMismatchException e){
                                    System.out.println("\nIntegers are only accepted. Please try again.");
                                }
                            }
                        }
                        else if(userInput.equals("2")) {
                            String origCode = "";
                            String destCode = "";
                            int flightIndex = 0;
                            String origAir = "";
                            String destAir = "";
                            ArrayList<Integer> matchIndexes = new ArrayList<Integer>();
                            isValidChoice = false;
                            while(!isValidChoice) {
                                System.out.println("\nHere are the available origin airports.\n");
                                airportManager.printOrigNames();
                                System.out.print("Please select the airport code from where you are departing (enter -1 to exit): ");
                                origCode = scnr.next();
                                if(origCode.equals("-1")) {
                                    return null;
                                }
                                Pattern patternProfile  = Pattern.compile("^[A-Z]{3}$");
                                Matcher profileMatcher = patternProfile.matcher(origCode);
                                if(profileMatcher.matches()) {
                                    origAir = airportManager.getOrigAirport(origCode);
                                    if (origAir.equals("None")) {
                                        System.out.println("\nThat airport code doesn't exist. Please try again.");
                                    }
                                    else {
                                        origAir = origAir.substring(0, origAir.length() - 6);
                                        System.out.println("You selected: " + origAir);
                                        isValidInnerChoice = false;
                                        while(!isValidInnerChoice) {
                                            System.out.println("\nHere are the available destination airports.\n");
                                            airportManager.printDestNames(origCode);
                                            System.out.print("Please select the airport code from where you are arriving: ");
                                            destCode = scnr.next();
                                            profileMatcher = patternProfile.matcher(destCode);
                                            if(profileMatcher.matches()) {
                                                destAir = airportManager.getDestAirport(destCode);
                                                if (destAir.equals("None")) {
                                                    System.out.println("\nThat airport code doesn't exist. Please try again.");
                                                }
                                                else {
                                                    destAir = destAir.substring(0, destAir.length() - 6);
                                                    System.out.println("You selected: " + destAir);
                                                    isValidChoice2 = false;
                                                    while(!isValidChoice2) {
                                                        System.out.print("\n\nPlease enter your desired travel date: ");
                                                        String travelDate = scnr.next();
                                                        patternProfile  = Pattern.compile("^([1-9]|1[0-2])/([1-9]|[12][0-9]|3[01])/([0-9]{4}|[0-9]{2})$");
                                                        profileMatcher = patternProfile.matcher(travelDate);
                                                        if (profileMatcher.matches()) {
                                                            if (travelDate.charAt(travelDate.length() - 3) == '/') {
                                                                travelDate = travelDate.substring(0, travelDate.length() - 2) + "20" + travelDate.substring(travelDate.length() - 2, travelDate.length());
                                                                System.out.println(travelDate); 
                                                            }
                                                            for(int i = 1; i < flightsMap.size(); i++) {
                                                                if(flightsMap.get(i).getOrigName().equalsIgnoreCase(origAir) && flightsMap.get(i).getDestName().equalsIgnoreCase(destAir) &&
                                                                flightsMap.get(i).getDepaDate().equalsIgnoreCase(travelDate)) {
                                                                    matchIndexes.add(i);
                                                                }
                                                            }
                                                            while(!isValidChoice) {
                                                                if(matchIndexes.size() != 0) {
                                                                    try {
                                                                        System.out.println("\nHere are the available flights:");
                                                                        int i;
                                                                        for(i = 1; i < matchIndexes.size(); i++) {
                                                                            myFlight = flightsMap.get(matchIndexes.get(i));
                                                                            if(!myFlight.getIsCancelled() || myFlight.getTotalSeats() != 0) {
                                                                                System.out.print("\n" + i + ") ");
                                                                                System.out.println(myFlight.getOrigName() + " (" + myFlight.getOrigCode() + ") --> " +
                                                                                                myFlight.getDestName() + " (" + myFlight.getDestCode() + ")");
                                                                                System.out.println(myFlight.getDepaDate() + " " + myFlight.getDepaTime() + " --> " +
                                                                                                myFlight.getArrDate() + " " + myFlight.getArrTime() + " " + myFlight.getId());
                                                                            }
                                                                            else {
                                                                                i--;
                                                                            }
                                                                        }
                                                                        System.out.print("\nPlease select the number of the flight you want to purchase tickets for (enter -1 to return to the airport menu): ");
                                                                        flightIndex = scnr.nextInt();
                                                                        if (flightIndex == -1) {
                                                                            isValidChoice2 = true;
                                                                            isValidInnerChoice = true;
                                                                            matchIndexes.clear();
                                                                            break;
                                                                        }
                                                                        if (flightIndex >= 1 && flightIndex <= i - 1) { 
                                                                            System.out.println("\nPerfect, let me send you to ticketing.");
                                                                            customerLog.write("\nCustomer with username " + myPerson.getUsername() + " selected flight with ID " + myFlight.getId() + ".");
                                                                            System.out.printf("\nYour current balance is $%.2f", myPerson.getBalance());
                                                                            System.out.println();
                                                                            myFlight = flightsMap.get(matchIndexes.get(flightIndex));
                                                                            isValidChoice = true;
                                                                            isValidChoice2 = true;
                                                                            isValidInnerChoice = true;
                                                                            isValidFlight = true;
                                                                        }
                                                                        else {
                                                                            throw new InvalidInput("\nNot a valid number.");
                                                                        }
                                                                    }
                                                                    catch(InvalidInput e) {
                                                                        System.out.println(" Please try again.");
                                                                    }
                                                                    catch(InputMismatchException e){
                                                                        System.out.println("\nIntegers are only accepted. Please try again.");
                                                                        scnr.nextLine();
                                                                    }
                                                                }
                                                                else {
                                                                    System.out.println("\nThere is no flight that lies in your desired origin, destination, or date choices. Please try again.");
                                                                    System.out.println("\nPrompting to the airport menu...");
                                                                    try {
                                                                        Thread.sleep(2500);
                                                                    } catch(InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                    isValidChoice2 = true;
                                                                    isValidInnerChoice = true;
                                                                    matchIndexes.clear();
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        else {
                                                            System.out.println("\nNot a valid departing date. Please try again.");
                                                        }
                                                    }
                                                }
                                            }
                                            else {
                                                System.out.println("\nNot a valid airport code. Please try again.");
                                            }
                                        }
                                    }
                                }
                                else {
                                    System.out.println("\nNot a valid airport code. Please try again.");
                                }

                            }
                        }
                        else {
                            throw new InvalidInput("\nNot a valid choice.");
                        }
                    }
                    else {
                        throw new InvalidInput("\nNot a valid choice.");
                    }
                }
                catch(InvalidInput e) {
                    System.out.println(" Please try again.");
                }
            }
            customerLog.flush();
        }
        catch(IOException e){
            System.out.println("\n\nAn error occured when writing to the command log.");
        }
        return myFlight;
    }

    /**
     * Allows user to choose the class type for selected flight.
     * [Taken from Brian G. Rodiles Delgado]
     * @param myFlight the flight object
     * @param customerLog the customer log
     */
    public void chooseClass(Flight myFlight, FileWriter customerLog) {
        try{
            isValidClass = false;
            while(!isValidClass) {
                try {
                    System.out.println("\nAvailable class options:");
                    System.out.print("\n1. First Class: ");
                    if(myFlight.getFirstSeats() == 0) { 
                        System.out.println("Sold Out");
                    }
                    else {
                        System.out.print("$" + myFlight.getFirstPrice());
                        if(myFlight.getFlightType().equals("International")) {
                            System.out.printf(" + $" + myFlight.getSurcharge() + " (surcharge International Flight) = $%.2f", myFlight.getFirstPrice() + myFlight.getSurcharge());
                        }
                        System.out.println("\nAvailable seats: " + myFlight.getFirstSeats());
                    }
                    System.out.print("\n2. Business Class: ");
                    if(myFlight.getBusinSeats() == 0) { 
                        System.out.println("Sold Out");
                    }
                    else {
                        System.out.print("$" + myFlight.getBusinPrice());
                        if(myFlight.getFlightType().equals("International")) {
                            System.out.printf(" + $" + myFlight.getSurcharge() + " (surcharge International Flight) = $%.2f", myFlight.getBusinPrice() + myFlight.getSurcharge());
                        }
                        System.out.println("\nAvailable seats: " + myFlight.getBusinSeats());
                    }
                    System.out.print("\n3. Main Cabin Class: ");
                    if(myFlight.getMainSeats() == 0) { 
                        System.out.println("Sold Out");
                    }
                    else {
                        System.out.print("$" + myFlight.getMainPrice());
                        if(myFlight.getFlightType().equals("International")) {
                            System.out.printf(" + $" + myFlight.getSurcharge() + " (surcharge International Flight) = $%.2f", myFlight.getMainPrice() + myFlight.getSurcharge());
                        }
                        System.out.println("\nAvailable seats: " + myFlight.getMainSeats());
                    }
                    System.out.print("\nPlease select a class number: ");
                    userInput = scnr.nextLine();
                    if(userInput.equals("1") || userInput.equals("2") || userInput.equals("3")) {
                        if(userInput.equals("1")) {
                            if(myFlight.getFirstSeats() == 0) {
                                System.out.println("\nThis class has no available seats. Select another one.");
                            }
                            else {
                                myTicket.setType("First Class");
                                isValidClass = true;
                            }
                        }
                        if(userInput.equals("2")) {
                            if(myFlight.getBusinSeats() == 0) {
                                System.out.println("\nThis class has no available seats. Select another one.");
                            }
                            else {
                                myTicket.setType("Business Class");
                                isValidClass = true;
                            }
                        }
                        if(userInput.equals("3")) {
                            if(myFlight.getMainSeats() == 0) {
                                System.out.println("\nThis class has no available seats. Select another one.");
                            }
                            else {
                                myTicket.setType("Main Class");
                                isValidClass = true;
                            }
                        }
                        System.out.println("\nPerfect, you chose " + myTicket.getType() + ".");
                        customerLog.write("\nCustomer with username " + myPerson.getUsername() + " selected flight of type " + myTicket.getType() + ".");
                    }
                    else {
                        throw new InvalidInput("\nNot a valid choice. Please try again.");
                    }
                }
                catch(InvalidInput e) {
                    System.out.println(" Please try again.");
                }
            }
            customerLog.flush();
        }
        catch(IOException e){
            System.out.println("\n\nAn error occured when writing to the command log.");
        }
    }

    /**
     * Checks if customer has enough balance for transaction.
     * [Taken from Brian G. Rodiles Delgado]
     * @param myFlight the flight object
     * @param customerLog the customer log
     * @return boolean to continue or not for the ticket generation
     */
    public boolean confirmTicket(Flight myFlight, FileWriter customerLog) {
        try{
            isValidBalance = true;
            isValidChoice = false;
            totalSavings = 0.0;
            finalBasePrice = 0.0;
            while(!isValidChoice) {
                try {
                    System.out.print("\nPlease enter the number of tickets you want to purchase (up to 8 per transaction): ");
                    numberOfTickets = scnr.nextLine();
                    Pattern patternProfile  = Pattern.compile("^\\d+$");
                    Matcher profileMatcher = patternProfile.matcher(numberOfTickets);
                    if(profileMatcher.matches()){
                        numOfTickets = Integer.parseInt(numberOfTickets);
                        if(numOfTickets >= 1 && numOfTickets <= 8) {
                            customerLog.write("\nCustomer with username " + myPerson.getUsername() + " selected to buy " + numberOfTickets + " tickets.");
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
                                    System.out.println("\nThe selected number of seats exceeds the available ones. Please select from 1 to " + myFlight.getFirstSeats() + " seats to purchase.");
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
                                    System.out.println("\nThe selected number of seats exceeds the available ones. Please select from 1 to " + myFlight.getBusinSeats() + " seats to purchase.");
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
                                    System.out.println("\nThe selected number of seats exceeds the available ones. Please select from 1 to " + myFlight.getMainSeats() + " seats to purchase.");
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
                                System.out.println("\nYou don't have enough money on your account for this transaction.");
                                customerLog.write("\nCustomer with username " + myPerson.getUsername() + " didn't have enough money for transaction.\n");
                                customerLog.flush();
                                return false;
                            }
                        }
                        else {
                            throw new InvalidInput("\nNot a valid number of tickets.");
                        }
                    }
                    else {
                        throw new InvalidInput("\nEnter only integers.");
                    }
                }
                catch(InvalidInput e) {
                    System.out.println(" Please try again.");
                }
            }
            isValidTransaction = false;
            while(!isValidTransaction) {
                try {
                    System.out.println("\nThe total price for the transaction would be $" + String.format("%.2f", myTicket.getTotalPrice()));
                    System.out.print("Do you confirm this transaction? (Y/n): ");
                    userInput = scnr.nextLine();
                    System.out.println();
                    if(userInput.equalsIgnoreCase("Y")) {
                        System.out.println("Perfect, let me generate your ticket confirmation...");
                        isValidTransaction = true;
                    }
                    else if(userInput.equalsIgnoreCase("n")) {
                        customerLog.write("\nCustomer with username " + myPerson.getUsername() + " cancelled the transaction.\n");
                        System.out.println("No problem. Returning to the main menu...");
                        return false;
                    }
                    else {
                        throw new InvalidInput("That is not a valid choice.");
                    }
                }
                catch(InvalidInput e) {
                    System.out.println(" Please try again.");
                }
            }
            System.out.println("\nGenerating ticket confirmation...");
            customerLog.flush();
        }
        catch(IOException e){
            System.out.println("\n\nAn error occured when writing to the command log.");
        }
        return true;
    }

    /**
     * Generates confirmation number and updates flight seats and customer balance.
     * [Taken from Brian G. Rodiles Delgado]
     * @param myFlight the flight object
     * @param customerLog the customer log
     */
    public void prepareTicket(Flight myFlight, FileWriter customerLog) {
        try{
            myTicket.setSeats(Integer.parseInt(numberOfTickets));
            if(myTicket.getType().equals("First Class")) {
                myFlight.calcFirstSeats(myTicket.getSeats());
                myFlight.setFirstSeatsSold(myFlight.getFirstSeatsSold() + Integer.parseInt(numberOfTickets));
                myFlight.setFirstClassRevenue(myFlight.getFirstClassRevenue() + finalBasePrice);
            }
            else if(myTicket.getType().equals("Business Class")) {
                myFlight.calcBusinSeats(myTicket.getSeats());
                myFlight.setBusinSeatsSold(myFlight.getBusinSeatsSold() + Integer.parseInt(numberOfTickets));
                myFlight.setBusinClassRevenue(myFlight.getBusinClassRevenue() + finalBasePrice);
            }
            else if(myTicket.getType().equals("Main Class")) {
                myFlight.calcMainSeats(myTicket.getSeats());
                myFlight.setMainSeatsSold(myFlight.getMainSeatsSold() + Integer.parseInt(numberOfTickets));
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
            System.out.println("\nYour confirmation number is: " + myTicket.getConfirmationNumber());
            customerLog.write("\nCustomer with username " + myPerson.getUsername() + " bought " + numberOfTickets +
                              " ticket for flight with ID " + myFlight.getId() + ".\n");
            System.out.println("\nPrinting your ticket(s)...");
            customerLog.flush();
        }
        catch(IOException e){
            System.out.println("\n\nAn error occured when writing to the command log.");
        }
    }

    /**
     * Prints ticket both on terminal and as a txt file.
     * [Taken from Brian G. Rodiles Delgado]
     * @param myFlight the flight object
     * @param myTicket the ticket object
     * @param ticketPrinter the ticket printer
     */
    public void printTicket(Flight myFlight, Ticket myTicket, FileWriter ticketPrinter) {
        String itineraryInfo = "";
        System.out.println("\nHere is your ticket(s) information:");

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
        myTicket.printTicketInfo();
        // ticketPrinter = new FileWriter("Ticket(s) " + myTicket.getConfirmationNumber() + ".txt");
        // ticketPrinter.write("\nThank you for using MinerAir!\n\nHere is your ticket(s) information:\n");
        // ticketPrinter.write(myTicket.getItineraryInfo() + "\n");
        myFlight.addFlightTicket(myTicket);
        myPerson.addPersonTicket(myTicket);
    }

    /**
     * Method to determine if an user wants to stay logged in or exit to the user selection menu.
     * @return Binary decision tracker to continue logged in or not.
     */
    public boolean continueLoggedIn() {
        isValidChoice = false;
        while(!isValidChoice){
            try {
                System.out.print("\nDo you wish to continue logged in? (Y/n): ");
                String userSelection = scnr.next();
                System.out.println();
                if(userSelection.equalsIgnoreCase("Y")) {
                    scnr.nextLine();
                    return false;
                }
                else if(userSelection.equalsIgnoreCase("n")) {
                    return true;
                }
                else {
                    throw new InvalidInput("That is not a valid choice.");
                }
            }
            catch(InvalidInput e) {
                System.out.println(" Please try again.");
            }
        }
        return false;
    }

}
