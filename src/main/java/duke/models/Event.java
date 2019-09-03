package duke.models;

/**
 * Event with a specified date.
 */
public class Event extends Task {

    /** Specified date for the event. */
    private String date;

    /**
     * Event Task.
     * @param description Description of task.
     * @param date Date of task.
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.date + ")";
    }

    @Override
    public String getData() {
        int isDone = super.isDone
                ? 1
                : 0;
        return "E|" + isDone + "|" + super.description + "|" + this.date;
    }
}
