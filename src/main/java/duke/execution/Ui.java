package duke.execution;

import duke.models.Task;

/**
 * User interface to handle printing of messages.
 */
public class Ui {

    private String response = "";

    /**
     * Shows loading error.
     */
    public void showLoadingError() {
        setResponse("Data file cannot be loaded");
    }

    /**
     * Shows welcome message.
     */
    public void showWelcomeMessage() {
        setResponse("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    /**
     * Shows error.
     * @param msg Error message.
     */
    public void showError(String msg) {
        System.out.println("ERROR: " + msg);
        setResponse(msg);
    }

    /**
     * Shows a done task.
     * @param task Task that has been marked as done.
     */
    public void showDone(Task task) {
        setResponse(" Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Shows a task that has been added.
     * @param task Task that bas been added.
     * @param size Number of tasks in the collections.
     */
    public void showAddTask(Task task, int size) {
        setResponse(" Got it. I've added this task:\n" + task + "\nNow you have "
                + size + " tasks in the list.");
    }

    /**
     * Shows a task that has been deleted.
     * @param task Task has been deleted.
     * @param size Number of tasks left in the collections.
     */
    public void showDeleted(Task task, int size) {
        setResponse(" Noted. I've removed this task:\n" + task + "\nNow you have "
                + size + " tasks in the list.");
    }

    /**
     * Shows exit message.
     */
    public void exit() {
        setResponse("Bye. Hope to see you again soon!");
    }

    /**
     * Lists down the tasks in the collections.
     * @param taskList Collections of tasks.
     */
    public void listTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            setResponse(" No tasks to be found.");
        } else {
            response = "Here are the tasks in your list\n";
            for (int i = 0; i < taskList.getSize(); i++) {
                response += (i + 1) + ". " + taskList.get(i) + "\n";
            }
        }
    }

    /**
     * Helper function to handle formatting of printing.
     * @param txt Text to be printed within the formatted borders.
     */
    private void setResponse(String txt) {
        this.response = txt;
    }

    public String getResponse() {
        return this.response;
    }
}
