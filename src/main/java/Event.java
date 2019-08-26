public class Event extends Task {

    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getData() {
        int isDone = super.isDone
                ? 1
                : 0;
        return "E|" + isDone + "|" + super.description + "|" + this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.date + ")";
    }
}
