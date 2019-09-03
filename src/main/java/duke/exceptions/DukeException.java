package duke.exceptions;

public class DukeException extends Exception {

    /**
     * Exception specified for Duke programme.
     * @param msg Message describing the exception.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
