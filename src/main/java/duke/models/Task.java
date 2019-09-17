package duke.models;

/**
 * Task with a description and boolean of whether is has been done.
 */
public abstract class Task {
    /** Description of task. */
    protected String description;
    /** Boolean of whether the task has been marked as done. */
    protected boolean isDone;

    /**
     * Task.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets a tick or cross depending whether the task has been done.
     * @return Tick or cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the data of the task to be saved.
     * @return Data of the task to be saved.
     */
    public abstract String getData();

    /**
     * Checks whether task title contains specified text.
     * @param txt Specified text to look for.
     * @return Boolean of whether task title contains text.
     */
    public boolean contains(String txt) {
        return description.toLowerCase().contains(txt.toLowerCase());
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}