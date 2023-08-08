/**
 * Employee class that inherits Person abstract class.
 */
public class Employee extends Person{
    /**
     * Empty constructor.
     * [Taken from Jose Luis Espinoza Gonzalez]
     */
    public Employee() {
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
     * [Taken from Jose Luis Espinoza Gonzalez]
     * @param idIn the employee's ID
     * @param firstNameIn the employee's first name
     * @param lastNameIn the employee's last name
     * @param balanceIn the employee's balance
     * @param dateOfBirthIn the employee's birthday date
     * @param roleIn the employee's role
     * @param ticketsPurchasedIn the employee's number of tickets purchased
     * @param hasMembershipIn the employee's membership status
     * @param usernameIn the employee's username
     * @param passwordIn the employee's password
     */
    public Employee(int idIn, String firstNameIn, String lastNameIn, String dateOfBirthIn, String roleIn, double balanceIn, int ticketsPurchasedIn,
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
