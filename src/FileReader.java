import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileReader class to handle all data consumption from CSV files.
 */
public class FileReader {
    private String fileName;
    private Scanner scnr;
    private Person person;
    private Flight flight;
    private AutoTransaction autoTransaction;
    private PersonFactory personfactory = new PersonFactory();
    private FlightFactory flightFactory = new FlightFactory();
    private Map<Integer, Flight> flightMap = new HashMap<>();
    private Map<String, Integer> flightHeaderMap = new HashMap<>();
    private Map<String, Person> customerMap = new HashMap<>();
    private Map<String, Person> employeeMap = new HashMap<>();
    private Map<String, Integer> personHeaderMap = new HashMap<>();
    private Map<String, Airport> airportMap = new HashMap<>();
    private Map<String, Integer> autoPurchaseHeaderMap = new HashMap<>();
    private ArrayList<AutoTransaction> autoTransList = null;

    /**
     * Empty constructor.
     * [Taken from Jose Luis Espinoza Gonzalez]
     */
    public FileReader() {
    }

    /**
     * Getter for customer map.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return the map of customers
     */
    public Map<String, Person> getCustomerMap() {
        return customerMap;
    }

    /**
     * Getter for employee map.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return the map of employees
     */
    public Map<String, Person> getEmployeeMap() {
        return employeeMap;
    }

    /**
     * Consumes customer CSV file depending on previously registered headers.
     * [Taken from Ruben Carmona]
     * @param fileNameIn the name of the input file
     */
    public void createMapPerson(String fileNameIn) {
        try{
            this.fileName = fileNameIn;
            scnr = new Scanner(new File(fileName));
            String[] headers = scnr.nextLine().split(",");
            for(int i = 0; i < headers.length; i++){
                personHeaderMap.put(headers[i], i);
            }
            scnr.close();
            scnr = new Scanner(new File(fileName));
            scnr.nextLine();
            int id = 0;
            String firstName = "";
            String lastName = "";
            String dateOfBirth = "";
            String role = "";
            double balance = 0.0;
            int ticketsPurchased = 0;
            boolean hasMembership = false;
            String username = "";
            String password = "";
            while(scnr.hasNextLine()){
                String[] personInfo = scnr.nextLine().split(",");
                id = Integer.parseInt(personInfo[personHeaderMap.get("ID")]);
                firstName = personInfo[personHeaderMap.get("First Name")];
                lastName = personInfo[personHeaderMap.get("Last Name")];
                dateOfBirth = personInfo[personHeaderMap.get("DOB")];
                role = personInfo[personHeaderMap.get("Role")];
                balance = Double.parseDouble(personInfo[personHeaderMap.get("Money Available")]);
                ticketsPurchased = Integer.parseInt(personInfo[personHeaderMap.get("Flights Purchased")]);
                hasMembership = Boolean.parseBoolean(personInfo[personHeaderMap.get("MinerAirlines Membership")]);
                username = personInfo[personHeaderMap.get("Username")];
                password = personInfo[personHeaderMap.get("Password")];

                person = personfactory.createPerson(role);

                person.setId(id);
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setDoB(dateOfBirth);
                person.setRole(role);
                person.setBalance(balance);
                person.setTicketsPurchased(ticketsPurchased);
                person.setHasMembership(hasMembership);
                person.setUsername(username);
                person.setPassword(password);
                
                if(person.getRole().equals("Customer")) {
                    customerMap.put(person.getUsername(), person);
                }
                else {
                    employeeMap.put(person.getUsername(), person);
                }
                
            }
            scnr.close();
        }catch(FileNotFoundException e) {
            System.out.println("The specified file was not found.");
            System.out.println("Please check if the file exists as written.");
        }
    }

    /**
     * Consumes flights CSV file depending on previously registered headers.
     * [Taken from Ruben Carmona]
     * @param fileName the name of the input file
     * @return the map of flights
     */
    public Map<Integer, Flight> createMapFlights(String fileName) {
        try{
            scnr = new Scanner(new File(fileName));
            String[] headers = scnr.nextLine().split(",");
            for(int i = 0; i < headers.length; i++){
                flightHeaderMap.put(headers[i], i);
            }
            scnr.close();
            scnr = new Scanner(new File(fileName));
            scnr.nextLine();
            int id = 0;
            String flightNumber = "";
            String originName = "";
            String originCode = "";
            String originCity = "";
            String originState = "";
            String originCountry = "";
            double originFee = 0.0;
            boolean originHasLounge = false;
            String destName = "";
            String destCode = "";
            String destCity = "";
            String destState = "";
            String destCountry = "";
            double destFee = 0.0;
            boolean destHasLounge = false;
            String depaDate = "";
            String depaTime = "";
            int duration = 0;
            int distance = 0;
            int timeDiff = 0;
            String arrDate = "";
            String arrTime = "";
            String flightType = "";
            double surcharge = 0.0;
            boolean isFoodServed = false;
            double routeCost = 0.0;
            int minerPoints = 0;
            int totalSeats = 0;
            int firstSeats = 0;
            int businSeats = 0;
            int mainSeats = 0;
            double firstPrice = 0.0;
            double businPrice = 0.0;
            double mainPrice = 0.0;
            AirportSingleton airportManager = AirportSingleton.getInstance();

            while(scnr.hasNextLine()){
                String[] flightInfo = scnr.nextLine().split(",");
                id = Integer.parseInt(flightInfo[flightHeaderMap.get("ID")]);
                flightNumber = flightInfo[flightHeaderMap.get("Flight Number")];
                originName = flightInfo[flightHeaderMap.get("Origin Airport")];
                originCode = flightInfo[flightHeaderMap.get("Origin Code")];
                originCity = flightInfo[flightHeaderMap.get("Origin Airport City")];
                originState = flightInfo[flightHeaderMap.get("Origin Airport State")];
                originCountry = flightInfo[flightHeaderMap.get("Origin Airport Country")];
                originFee = Double.parseDouble(flightInfo[flightHeaderMap.get("Origin Airport Fee")]);
                originHasLounge = Boolean.parseBoolean(flightInfo[flightHeaderMap.get("Origin Airport Lounge")]);
                destName = flightInfo[flightHeaderMap.get("Destination Airport")];
                destCode = flightInfo[flightHeaderMap.get("Destination Code")];
                destCity = flightInfo[flightHeaderMap.get("Destination Airport City")];
                destState = flightInfo[flightHeaderMap.get("Destination Airport State")];
                destCountry = flightInfo[flightHeaderMap.get("Destination Airport Country")];
                destFee = Double.parseDouble(flightInfo[flightHeaderMap.get("Destination Airport Fee")]);
                destHasLounge = Boolean.parseBoolean(flightInfo[flightHeaderMap.get("Destination Airport Lounge")]);
                depaDate = flightInfo[flightHeaderMap.get("Departing Date")];
                depaTime = flightInfo[flightHeaderMap.get("Departing Time")];
                duration = Integer.parseInt(flightInfo[flightHeaderMap.get("Duration")]);
                distance = Integer.parseInt(flightInfo[flightHeaderMap.get("Distance")]);
                timeDiff = Integer.parseInt(flightInfo[flightHeaderMap.get("Time Zone Difference")]);
                arrDate = flightInfo[flightHeaderMap.get("Arrival Date")];
                arrTime = flightInfo[flightHeaderMap.get("Arrival Time")];
                flightType = flightInfo[flightHeaderMap.get("Type")];
                surcharge = Double.parseDouble(flightInfo[flightHeaderMap.get("Surcharge")]);
                isFoodServed = Boolean.parseBoolean(flightInfo[flightHeaderMap.get("Food Served")]);
                routeCost = Double.parseDouble(flightInfo[flightHeaderMap.get("Route Cost")]);
                minerPoints = Integer.parseInt(flightInfo[flightHeaderMap.get("Miner Points")]);
                totalSeats = Integer.parseInt(flightInfo[flightHeaderMap.get("Total Seats")]);
                firstSeats = Integer.parseInt(flightInfo[flightHeaderMap.get("First Class Seats")]);
                businSeats = Integer.parseInt(flightInfo[flightHeaderMap.get("Business Class Seats")]);
                mainSeats = Integer.parseInt(flightInfo[flightHeaderMap.get("Main Cabin Seats")]);
                firstPrice = Double.parseDouble(flightInfo[flightHeaderMap.get("First Class Price")]);
                businPrice = Double.parseDouble(flightInfo[flightHeaderMap.get("Business Class Price")]);
                mainPrice = Double.parseDouble(flightInfo[flightHeaderMap.get("Main Cabin Price")]);

                flight = flightFactory.createFlight(flightType);

                flight.setId(id);
                flight.setFlightNumber(flightNumber);
                Airport origAirport = new Airport(originName, originCode, originCity, originState, originCountry, originFee, originHasLounge);
                flight.setOrigAirport(origAirport);
                airportManager.addOrigAirport(originName + " (" + originCode + ")");
                Airport destAirport = new Airport(destName, destCode, destCity, destState, destCountry, destFee, destHasLounge);
                flight.setDestAirport(destAirport);
                airportManager.addDestAirport(destName + " (" + destCode + ")");
                airportMap.put(originCode, origAirport);
                airportMap.put(destCode, destAirport);
                flight.setDepaTime(depaTime);
                flight.setArrTime(arrTime);
                flight.setDepaDate(depaDate);
                flight.setArrDate(arrDate);
                flight.setDuration(duration);
                flight.setDistance(distance);
                flight.setTimeDiff(timeDiff);
                flight.setFlightType(flightType);
                flight.setSurcharge(surcharge);
                flight.setIsFoodServed(isFoodServed);
                flight.setRouteCost(routeCost);
                flight.setMinerPoints(minerPoints);
                flight.setTotalSeats(totalSeats);
                flight.setFirstSeats(firstSeats);
                flight.setBusinSeats(businSeats);
                flight.setMainSeats(mainSeats);
                flight.setFirstPrice(firstPrice);
                flight.setBusinPrice(businPrice);
                flight.setMainPrice(mainPrice);
                flight.setIsCancelled(false);

                flightMap.put(flight.getId(), flight);               
            }
            airportManager.initEveryAirport();
            airportManager.initAirMap(airportMap);
            scnr.close();
        }catch(FileNotFoundException e) {
            System.out.println("The specified file was not found.");
            System.out.println("Please check if the file exists as written.");
        }
        return flightMap;
    }

    /**
     * Handles initial line of auto purchaser CSV file to obtain all headers.
     * [Taken from Brian G. Rodiles Delgado]
     * @param fileName the name of the input file
     */
    public void autoPurchaserHeaderMapping(String fileName) {
        try{
            scnr = new Scanner(new File(fileName));
            String[] headers = scnr.nextLine().split(",");
            for(int i = 0; i < headers.length; i++){
                autoPurchaseHeaderMap.put(headers[i], i);
            }
        }catch(FileNotFoundException e){
            System.out.println("The specified file was not found.");
            System.out.println("Please check if the file exists as written.");
        }
        scnr.close();
    }

    /**
     * Consumes customer CSV file depending on previously registered headers.
     * [Taken from Brian G. Rodiles Delgado]
     * @param fileNameAuto the name of the input file
     * @return the list of automatic transactions
     */
    public ArrayList<AutoTransaction> autoTransMapping(String fileNameAuto) {
        autoTransList = new ArrayList<AutoTransaction>();
        try{
            scnr = new Scanner(new File(fileNameAuto));
            scnr.nextLine();
            String firstName = "";
            String lastName = "";
            String action = "";
            int flightID = 0;
            String origCode = "";
            String destCode = "";
            String ticketType = "";
            int ticketQty = 0;
            while(scnr.hasNextLine()){
                autoTransaction = new AutoTransaction();
                String[] autoInfo = scnr.nextLine().split(",");
                firstName = autoInfo[autoPurchaseHeaderMap.get("First Name")];
                lastName = autoInfo[autoPurchaseHeaderMap.get("Last Name")];
                action = autoInfo[autoPurchaseHeaderMap.get("Action")];
                flightID = Integer.parseInt(autoInfo[autoPurchaseHeaderMap.get("Flight ID")]);
                origCode = autoInfo[autoPurchaseHeaderMap.get("Origin Airport")];
                destCode = autoInfo[autoPurchaseHeaderMap.get("Destination Airport")];
                ticketType = autoInfo[autoPurchaseHeaderMap.get("Ticket Type")];
                ticketQty = Integer.parseInt(autoInfo[autoPurchaseHeaderMap.get("Ticket Quantity")]);

                autoTransaction.setFirstName(firstName);
                autoTransaction.setLastName(lastName);
                autoTransaction.setAction(action);
                autoTransaction.setFlightID(flightID);
                autoTransaction.setOrigCode(origCode);
                autoTransaction.setDestCode(destCode);
                autoTransaction.setTicketType(ticketType);
                autoTransaction.setTicketQty(ticketQty);

                autoTransList.add(autoTransaction);
            }
            scnr.close();
        }catch(FileNotFoundException e) {
            System.out.println("The specified file was not found.");
            System.out.println("Please check if the file exists as written.");
        }
        return autoTransList;
    }
}
