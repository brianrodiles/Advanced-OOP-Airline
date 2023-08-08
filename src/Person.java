import java.util.ArrayList;

/**
 * Person abstract class to encapsulate the attributes for child classes.
 */
public abstract class Person {
    private int    id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String role;
    private double balance;
    private double savedMoney;
    private int ticketsPurchased;
    private boolean hasMembership;
    private String username;
    private String password;
    private ArrayList<Ticket> personTicketList = new ArrayList<Ticket>();
    
    /**
     * Empty constructor.
     * [Taken from Jose Luis Espinoza Gonzalez]
     */
    public Person() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.dateOfBirth = "";
        this.role = "";
        this.balance = 0.0;
        this.savedMoney = 0.0;
        this.ticketsPurchased = 0;
        this.hasMembership = false;
        this.username = "";
        this.password = "";
    }

    /**
     * Setter for id.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param idIn
     */
    public void setId(int idIn) {
        this.id = idIn;
    }

    /**
     * Getter for id.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * Setter for first name.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param firstNameIn
     */
    public void setFirstName(String firstNameIn) {
        this.firstName = firstNameIn;
    }

    /**
     * Getter for first name.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Setter for last name.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param lastNameIn
     */
    public void setLastName(String lastNameIn) {
        this.lastName = lastNameIn;
    }

    /**
     * Getter for last name.
     * [Taken from Jose Luis Espinoza Gonzalez]
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Setter for date of birth.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param dateOfBirthIn
     */
    public void setDoB(String dateOfBirthIn) {
        this.dateOfBirth = dateOfBirthIn;
    }

    /**
     * Getter for date of birth.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public String getDoB() {
        return this.dateOfBirth;
    }

    /**
     * Getter for role.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param roleIn
     */
    public void setRole(String roleIn) {
        this.role = roleIn;
    }

    /**
     * Setter for role.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public String getRole() {
        return this.role;
    }
     
    /**
     * Setter for money balance.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param balanceIn
     */
    public void setBalance(double balanceIn) {
        this.balance = balanceIn;
    }

    /**
     * Getter for money balance.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Setter for saved money.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param savedMoneyIn
     */
    public void setSavedMoney(double savedMoneyIn) {
        this.savedMoney = savedMoneyIn;
    }

    /**
     * Getter for saved money.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public double getSavedMoney() {
        return this.savedMoney;
    }

    /**
     * Setter for tickets purchased.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param ticketsPurchasedIn
     */
    public void setTicketsPurchased(int ticketsPurchasedIn) {
        this.ticketsPurchased = ticketsPurchasedIn;
    }

    /**
     * Getter for tickets purchased.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public int getTicketsPurchased() {
        return this.ticketsPurchased;
    }

    /**
     * Setter for membership status.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param hasMembershipIn
     */
    public void setHasMembership(boolean hasMembershipIn) {
        this.hasMembership = hasMembershipIn;
    }

    /**
     * Getter for membership status.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public boolean getHasMembership() {
        return this.hasMembership;
    }
    
    /**
     * Setter for username.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param usernameIn
     */
    public void setUsername(String usernameIn) {
        this.username = usernameIn;
    }

    /**
     * Getter for username.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Setter for password.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param passwordIn
     */
    public void setPassword(String passwordIn) {
        this.password = passwordIn;
    }

    /**
     * Getter for password.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Method to add a ticket to the list of a person.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param myTicket
     */
    public void addPersonTicket(Ticket myTicket) {
        personTicketList.add(myTicket);
    }

    /**
     * Getter for size of person's ticket list.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @return Size of person's ticket list.
     */
    public int getPersonTicketListSize() {
        return this.personTicketList.size();
    }

    /**
     * Getter for person's ticket list.
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param index
     * @return Person's ticket list.
     */
    public Ticket getTicket(int index) {
        return personTicketList.get(index);
    }
    
}
