/**
 * Class that uses the factory design pattern to create types of persons.
 */
public class PersonFactory {
    
    /**
     * Empty constructor.
     * [Taken from Brian G. Rodiles Delgado]
     */
    public PersonFactory() {
    }

    /**
     * Method to create a Customer or Employee person.
     * [Taken from Brian G. Rodiles Delgado]
     * @param role the person's role
     * @return person object
     */
    public Person createPerson(String role) {
        if(role.toLowerCase().equals("customer")) {
            return new Customer();
        }
        else {
            return new Employee();
        }
    }
}
