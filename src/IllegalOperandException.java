/**
 * This class represents the IllegalOperandException.
 * @author Henry Chen
 * @version 1.0
 */
public class IllegalOperandException extends Exception {
    /**
     * Creates a new IllegalOperandException
     */
    public IllegalOperandException() {
        super();
    }

    /**
     * Creates a new IllegalOperandException
     * @param  msg Message passed to exception.
     */
    public IllegalOperandException(String msg) {
        super(msg);
    }
}