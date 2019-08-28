package models;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getData() {
        int isDone = super.isDone
                ? 1
                : 0;
        return "T|" + isDone + "|" + super.description;
    }
}
