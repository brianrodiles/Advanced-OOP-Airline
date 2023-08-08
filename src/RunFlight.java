import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * Terminal class to consume the CSV file of flights and prompt the user a menu to interact with the methods on the Flight class
 * to update a specific flight's information given the ID number.
 * At the end a commands log is generated for the user to see all the updates done on the flights' information.
 */
public class RunFlight {

    /**
     * Main method to run all the CSV-consumption, menu interface, and log generation.
     * [Taken from Brian G. Rodiles Delgado]
     * @param args the array of arguments passed to the program
     */
    public static void main(String[] args) {
        FileWriter ticketPrinter = null; // FileWriter to generate the actual ticket files
        FileWriter customerLog = null;   // FileWriter to create the users log
        FileWriter commandLog = null;    // FileWriter to create the commands log
        Scanner userScnr = null;         // Scanner for user to interact with menu
        Person myPerson = null;
        Flight myFlight = null;
        Ticket myTicket = null;
        String listSelection;
        boolean userLogout = false, isValidChoice;
        
        try{
            // Initialize menu Scanner
            userScnr = new Scanner(System.in);
            
            // Initialize FileWriter commands logger
            commandLog = new FileWriter("CommandLog.txt");

            // Initialize FileWriter customer logger
            customerLog = new FileWriter("CustomerLog.txt");

            // Read the flights CSV file
            FileReader file = new FileReader();
            Map<Integer, Flight> flightsMap = file.createMapFlights("FlightSchedulePA6.csv");
            
            // Read the customers CSV file
            file.createMapPerson("CustomerListPA6.csv");
            Map<String, Person> customerMap = file.getCustomerMap();
            Map<String, Person> employeeMap = file.getEmployeeMap();

            // Read the Auto Purchaser files
            file.autoPurchaserHeaderMapping("AutoPurchaser10K.csv");
            ArrayList<AutoTransaction> autoTransList10 = file.autoTransMapping("AutoPurchaser10K.csv");
            ArrayList<AutoTransaction> autoTransList100 = file.autoTransMapping("AutoPurchaser100K.csv");
            ArrayList<AutoTransaction> autoTransList400 = file.autoTransMapping("AutoPurchaser400K.csv");
            ArrayList<AutoTransaction> autoTransList800 = file.autoTransMapping("AutoPurchaser800K.csv");
            ArrayList<AutoTransaction> autoTransList1M = file.autoTransMapping("AutoPurchaser1M.csv");
            
            // Enable Employee terminal
            EmployeeTerminal employeeTerminal = new EmployeeTerminal(customerMap, employeeMap);
            CustomerTerminal customerTerminal = new CustomerTerminal();
            AutoTransactor   autoTransactor   = new AutoTransactor(customerMap, employeeMap, flightsMap, customerLog);
            
            int profileInput = -1;
            while(profileInput != 0){
                try {
                    System.out.println("\nWelcome to MinerAir!");
                    System.out.println("\nHere are the available profiles:");
                    System.out.println("1) Customer");
                    System.out.println("2) Employee");
                    System.out.print("Please enter what you are (enter \"EXIT\" to stop using the program): ");
                    
                    // Enable EXIT input recognition
                    String profileSelection = "";
                    Pattern patternProfile  = Pattern.compile("^\\d+$");
                    profileSelection = userScnr.next();
                    if(profileSelection.equalsIgnoreCase("EXIT")) {
                        System.out.println("\nThank you for using MinerAir!\n");
                        break;
                    }
                    Matcher profileMatcher = patternProfile.matcher(profileSelection);
                    if(profileMatcher.matches()) {
                        // Customer selected
                        if(profileSelection.equals("1")) {
                            if(customerTerminal.individualCustomer()) {
                                myPerson = customerTerminal.personExists(customerMap);
                                customerTerminal.personLogin(myPerson, customerLog);
                                while(!userLogout){

                                    // Prompt to canceling menu
                                    if(customerTerminal.wantsToCancelTicket()){
                                        userLogout = customerTerminal.cancelMenu(customerLog);
                                    }

                                    // Prompt to buying menu
                                    else{
                                        myTicket = customerTerminal.generateSampleTicket();
                                        myFlight = customerTerminal.flightExists(flightsMap, customerLog);
                                        if(myFlight != null) {
                                            customerTerminal.chooseClass(myFlight, customerLog);
                                            if(customerTerminal.confirmTicket(myFlight, customerLog)) {
                                                customerTerminal.prepareTicket(myFlight, customerLog);
                                                customerTerminal.printTicket(myFlight, myTicket, ticketPrinter);
                                            }
                                        }
                                        userLogout = customerTerminal.continueLoggedIn();
                                    }
                                }
                                userLogout = false;
                            }
                        }
                        // Employee selected
                        else if (profileSelection.equals("2")) {
                            myPerson = customerTerminal.personExists(employeeMap);
                            customerTerminal.personLogin(myPerson, commandLog);
                            while(!userLogout){

                                // Prompt to update menu
                                int userSelect = customerTerminal.wantsWhatToDo();
                                if (userSelect == 1) {
                                    userLogout = employeeTerminal.updateSelector(flightsMap, commandLog, ticketPrinter);
                                }
                                else if (userSelect == 2) {
                                    // Ask to buy or cancel a ticket
                                    if(customerTerminal.wantsToCancelTicket()){
                                        userLogout = customerTerminal.cancelMenu(customerLog);
                                    }

                                    // Prompt to buying menu
                                    else{
                                        myTicket = customerTerminal.generateSampleTicket();
                                        myFlight = customerTerminal.flightExists(flightsMap, customerLog);
                                        customerTerminal.chooseClass(myFlight, customerLog);
                                        if(customerTerminal.confirmTicket(myFlight, customerLog)) {
                                            customerTerminal.prepareTicket(myFlight, customerLog);
                                            customerTerminal.printTicket(myFlight, myTicket, ticketPrinter);
                                        }
                                        userLogout = customerTerminal.continueLoggedIn();
                                    }
                                }
                                else if (userSelect == 3) {
                                    employeeTerminal.inquireAir();
                                }
                                else if (userSelect == 4) {
                                    employeeTerminal.generateETS(ticketPrinter);
                                }
                                else if (userSelect == 5) {
                                    isValidChoice = false;
                                    while(!isValidChoice) {
                                        try {
                                            System.out.println("\nHere are the available transaction lists to test:");
                                            System.out.println("\n1) Auto Transaction 10K");
                                            System.out.println("2) Auto Transaction 100K");
                                            System.out.println("3) Auto Transaction 400K");
                                            System.out.println("4) Auto Transaction 800K");
                                            System.out.println("5) Auto Transaction 1M");
                                            System.out.print("\nPlease enter the list number you want to test: ");
                                            listSelection = userScnr.next();
                                            if (listSelection.equals("1") || listSelection.equals("2") || listSelection.equals("3") || listSelection.equals("4") || listSelection.equals("5")) { 
                                                if(listSelection.equals("1")) {
                                                    autoTransactor.setTransList(autoTransList10);
                                                }
                                                else if(listSelection.equals("2")) {
                                                    autoTransactor.setTransList(autoTransList100);
                                                }
                                                else if(listSelection.equals("3")) {
                                                    autoTransactor.setTransList(autoTransList400);
                                                }
                                                else if(listSelection.equals("4")) {
                                                    autoTransactor.setTransList(autoTransList800);
                                                }
                                                else if(listSelection.equals("5")) {
                                                    autoTransactor.setTransList(autoTransList1M);
                                                }
                                                autoTransactor.initTrans();
                                                isValidChoice = true;
                                            }
                                            else {
                                                throw new InvalidInput("\nNot a valid input.");
                                            }
                                        }
                                        catch(InvalidInput e) {
                                            System.out.println(" Please try again.");
                                        }
                                    }
                                }
                                else {
                                    userLogout = true;
                                }
                            }
                            userLogout = false;
                        }
                        else{
                            throw new InvalidInput("\nSelect only 1 or 2.");
                        }
                    }
                    else{
                        throw new InvalidInput("\nIntegers are only accepted as input.");
                    }
                }
                catch(InvalidInput e) {
                    System.out.println(" Please try again.");
                }
            }

            // Initialize CSV writer
            CSVWriter csvWriter = new CSVWriter();

            // Generate output to CSV files
            csvWriter.generateFlightSchedule(flightsMap, "FlightSchedulePA5.csv");
            csvWriter.generateCustomerList(customerMap, employeeMap, "CustomerListPA5.csv");
        }
        catch(NoSuchElementException e){
            System.out.println("\n\nThe Scanner received no input. If you didn't exited the program with Ctrl + C, check if the CSV file has all the data.");
        }
        catch(IOException e){
            System.out.println("\n\nAn error occured when writing to the command log.");
        }
        finally{
            try{
                // Close Scanner and File resources
                userScnr.close();
                customerLog.close();
                commandLog.close();
            }
            catch(NullPointerException e){
                System.out.println("\nThe Scanner was not initialized.");
            }
            catch(IOException e){
                System.out.println("\n\nAn error occured while closing the command log.");
            }
        }
            
    }
}
