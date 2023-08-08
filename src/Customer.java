/**
 * Customer class that inherits Person abstract class.
 */
public class Customer extends Person{
    
    /**
     * Empty constructor.
     * [Taken from Ruben Carmona]
     */
    public Customer() {
        setId(0);
        setFirstName("");
        setLastName("");
        setDoB("");
        setRole("");
        setBalance(0.0);
        setTicketsPurchased(0);
        setHasMembership(false);
        setUsername("");
        setPassword("");
    }

    /**
     * Constructor that enables the filling of all Customer's attributes from the CSV file.
     * [Taken from Ruben Carmona]
     * @param idIn the customer's ID
     * @param firstNameIn the customer's first name
     * @param lastNameIn the customer's last name
     * @param balanceIn the customer's balance
     * @param dateOfBirthIn the customer's birthday
     * @param roleIn the customer's role
     * @param ticketsPurchasedIn the customer's number of purchased tickets
     * @param hasMembershipIn the customer's membership status
     * @param usernameIn the customer's username
     * @param passwordIn the customer's password
     */
    public Customer(int idIn, String firstNameIn, String lastNameIn, String dateOfBirthIn, String roleIn, double balanceIn, int ticketsPurchasedIn,
                    boolean hasMembershipIn, String usernameIn, String passwordIn) {
        setId(idIn);
        setFirstName(firstNameIn);
        setLastName(lastNameIn);
        setDoB(dateOfBirthIn);
        setRole(roleIn);
        setBalance(balanceIn);
        setTicketsPurchased(ticketsPurchasedIn);
        setHasMembership(hasMembershipIn);
        setUsername(usernameIn);
        setPassword(passwordIn);
    }
}
