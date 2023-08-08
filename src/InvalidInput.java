/**
 * Class for custom exception on input mismatch.
 */
public class InvalidInput extends Exception {
    
    /**
     * Method to print the exception of input mismatch.
     * [Taken from Brian G. Rodiles Delgado]
     * @param messageIn the message to print
     */
    public InvalidInput(String messageIn) {
        System.out.print(messageIn);
    }
}
