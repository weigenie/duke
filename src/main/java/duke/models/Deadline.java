package duke.models;

/**
 * Task with a deadline.
 */
public class Deadline extends Task {

    /** Deadline of task. */
    private String date;

    /**
     * Deadline Task.
     * @param description Description of task.
     * @param date Date of task.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date + ")";
    }

    @Override
    public String getData() {
        int isDone = super.isDone
                ? 1
                : 0;
        return "D|" + isDone + "|" + super.description + "|" + this.date;
    }
}
