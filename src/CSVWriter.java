import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class to write output CSV files before ending the program.
 */
public class CSVWriter {
    private Map<String, Integer> flightHeaderMap = new HashMap<>();
    private Map<Integer, String> flightLineMap = new HashMap<>();
    private Map<String, Integer> personHeaderMap = new HashMap<>();
    private Map<Integer, String> personLineMap = new HashMap<>(); 
    private FileWriter customerCSVWriter;
    private FileWriter flightsCSVWriter;
    private Scanner scnrFlights;
    private Scanner scnrCustomers;
    private Scanner scnr;
    private String flightsHeader;
    private String flightsLine;
    private String customersHeader;
    private String customersLine;
    private String customerUsername;
    private String[] personInfo;
    private Person loopPerson;
    private int    loopCount;
    
    /**
     * Empty constructor.
     * [Taken from Brian G. Rodiles Delgado]
     */
    public CSVWriter() {
        try{
            flightsCSVWriter = new FileWriter("UpdatedFlightSchedule.csv");
            customerCSVWriter = new FileWriter("UpdatedCustomerList.csv");
        } catch(IOException e) {
            System.out.println("\n\nAn error occured when writing to the CSV files.");
        }
    }

    /**
     * Method to generate the output CSV for the flight schedule.
     * [Taken from Brian G. Rodiles Delgado]
     * @param flightMap the map of flights
     * @param fileName the name of input file
     */
    public void generateFlightSchedule(Map<Integer, Flight> flightMap, String fileName) {
        try{
            scnr = new Scanner(new File(fileName));
            String[] headers = scnr.nextLine().split(",");
            for(int i = 0; i < headers.length; i++){
                flightHeaderMap.put(headers[i], i);
            }
            scnr.close();
            scnrFlights = new Scanner(new File("FlightSchedulePA6.csv"));
            scnrFlights.nextLine();
            flightsHeader = "ID,Flight Number,Origin Airport,Origin Code,Origin Airport City,Origin Airport State,Origin Airport Country,";
            flightsHeader += "Origin Airport Fee,Origin Airport Lounge,Destination Airport,Destination Code,Destination Airport City,Destination Airport State,Destination Airport Country,";
            flightsHeader += "Destination Airport Fee,Destination Airport Lounge,Departing Date,Departing Time,Duration,Distance,Time Zone Difference,Arrival Date,Arrival Time,Type,Surcharge,";
            flightsHeader += "Food Served,Route Cost,Miner Points,Total Seats,First Class Seats,Business Class Seats,Main Cabin Seats,First Class Price,Business Class Price,Main Cabin Price,";
            flightsHeader += "First Class Seats Sold,Business Class Seats Sold,Main Cabin Seats Sold,Total First Class Revenue,Total Business Class Revenue,Total Main Cabin Revenue, Total Miner Fee Collected, Total Security Fee Collected, Total Tax Collected\n";
            flightsCSVWriter.write(flightsHeader);
            loopCount = 1;
            while(scnrFlights.hasNextLine()) {
                scnrFlights.nextLine();
                flightsLine = flightMap.get(loopCount).getId() + "," + flightMap.get(loopCount).getFlightNumber() + "," + flightMap.get(loopCount).getOrigName() + "," +
                               flightMap.get(loopCount).getOrigCode() + "," + flightMap.get(loopCount).getOrigCity() + "," + flightMap.get(loopCount).getOrigState() + "," +
                               flightMap.get(loopCount).getOrigCountry() + "," + flightMap.get(loopCount).getOrigFee() + "," + flightMap.get(loopCount).getOrigHasLounge() + "," +
                               flightMap.get(loopCount).getDestName() + "," + flightMap.get(loopCount).getDestCode() + "," + flightMap.get(loopCount).getDestCity() + "," + flightMap.get(loopCount).getDestState() + "," +
                               flightMap.get(loopCount).getDestCountry() + "," + flightMap.get(loopCount).getDestFee() + "," + flightMap.get(loopCount).getDestHasLounge() + "," + flightMap.get(loopCount).getDepaDate() + "," +
                               flightMap.get(loopCount).getDepaTime() + "," + flightMap.get(loopCount).getDuration() + "," + flightMap.get(loopCount).getDistance() + "," + flightMap.get(loopCount).getTimeDiff() + "," +
                               flightMap.get(loopCount).getArrDate() + "," + flightMap.get(loopCount).getArrTime() + "," + flightMap.get(loopCount).getFlightType() + "," + flightMap.get(loopCount).getSurcharge() + "," +
                               flightMap.get(loopCount).getIsFoodServed() + "," + flightMap.get(loopCount).getRouteCost() + "," + flightMap.get(loopCount).getMinerPoints() + "," + flightMap.get(loopCount).getTotalSeats() + "," +
                               flightMap.get(loopCount).getFirstSeats() + "," + flightMap.get(loopCount).getBusinSeats() + "," + flightMap.get(loopCount).getMainSeats() + "," + flightMap.get(loopCount).getFirstPrice() + "," +
                               flightMap.get(loopCount).getBusinPrice() + "," + flightMap.get(loopCount).getMainPrice();
                flightsLine += "," + flightMap.get(loopCount).getFirstSeatsSold() + "," + flightMap.get(loopCount).getBusinSeatsSold() + "," + flightMap.get(loopCount).getMainSeatsSold();
                flightsLine += "," + String.format("%.2f", flightMap.get(loopCount).getFirstClassRevenue()) + "," + String.format("%.2f", flightMap.get(loopCount).getBusinClassRevenue()) + "," + String.format("%.2f", flightMap.get(loopCount).getMainClassRevenue());
                flightsLine += "," + String.format("%.2f", flightMap.get(loopCount).getTotalMinerFee()) + "," + String.format("%.2f", flightMap.get(loopCount).getTotalSecFee()) + "," + String.format("%.2f", flightMap.get(loopCount).getTotalTax()) + "\n";
                flightLineMap.put(flightMap.get(loopCount).getId(), flightsLine);
                loopCount++;
            }
            for (int i = 1; i < flightLineMap.size() + 1; i++) { 
                flightsCSVWriter.write(flightLineMap.get(i));
            }
            flightsCSVWriter.flush();
            flightsCSVWriter.close();
        } catch (IOException e) {
            System.out.println("\n\nAn error occured when writing to the CSV files.");
        }
    }

    /**
     * Method to generate the output CSV for the customer list.
     * [Taken from Brian G. Rodiles Delgado]
     * @param customerMap the map of customers
     * @param employeeMap the map of employees
     * @param fileName the name of the input file
     */
    public void generateCustomerList(Map<String, Person> customerMap, Map<String, Person> employeeMap, String fileName) {
        try{
            scnr = new Scanner(new File(fileName));
            String[] headers = scnr.nextLine().split(",");
            for(int i = 0; i < headers.length; i++){
                personHeaderMap.put(headers[i], i);
            }
            scnr.close();
            scnrCustomers = new Scanner(new File(fileName));
            customersHeader = "ID,First Name, Last Name,DOB,Role,Money Available,Flights Purchased,MinerAir Membership,Username,Password\n";
            scnrCustomers.nextLine();
            customerCSVWriter.write(customersHeader);
            while(scnrCustomers.hasNextLine()) {
                customersLine = scnrCustomers.nextLine();
                personInfo = customersLine.split(",");
                customersLine = personInfo[personHeaderMap.get("ID")] + "," + personInfo[personHeaderMap.get("First Name")] + ",";
                customerUsername = personInfo[personHeaderMap.get("Username")];
                if(customerMap.containsKey(customerUsername)) {
                    loopPerson = customerMap.get(customerUsername);
                }
                else {
                    loopPerson = employeeMap.get(customerUsername);
                }
                customersLine += personInfo[personHeaderMap.get("Last Name")] + "," + personInfo[personHeaderMap.get("DOB")] + "," + personInfo[personHeaderMap.get("Role")] + "," + String.format("%.2f", loopPerson.getBalance()) + ",";
                customersLine += loopPerson.getTicketsPurchased() + "," + personInfo[personHeaderMap.get("MinerAirlines Membership")] + "," + personInfo[personHeaderMap.get("Username")] + "," + personInfo[personHeaderMap.get("Password")] + "\n";
                personLineMap.put(Integer.parseInt(personInfo[personHeaderMap.get("ID")]), customersLine);
            }
            for (int i = 1; i < personLineMap.size() + 1; i++) { 
                customerCSVWriter.write(personLineMap.get(i));
            }
            customerCSVWriter.flush();
            customerCSVWriter.close();
        } catch (IOException e) {
            System.out.println("\n\nAn error occured when writing to the CSV files.");
            e.printStackTrace();
        }
    }
}
