package duke.execution;

import duke.models.Task;

/**
 * User interface to handle printing of messages.
 */
public class Ui {

    private String response = "";

    /**
     * Shows loading error.
     * @param e Exception.
     */
    public void showLoadingError(Exception e) {
        e.printStackTrace();
        System.out.println("Data file cannot be loaded");
    }

    /**
     * Shows welcome message.
     */
    public void showWelcomeMessage() {
        setResponse("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    /**
     * Shows error.
     * @param e Exception.
     */
    public void showError(Exception e) {
        e.printStackTrace();
        setResponse(e.getMessage());
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

    public void showHelp() {
        setResponse("Commands available:",
                "'list', 'todo', 'event', 'deadline', 'done', 'delete', 'find'",
                "",
                "Type 'help <command>' to find out more about the command (eg. 'help list')");
    }

    public void showHelp(String command) {
        switch(command.trim()) {
        case "list":
            showListHelp();
            break;
        case "todo":
            showTodoHelp();
            break;
        case "event":
            showEventHelp();
            break;
        case "deadline":
            showDeadlineHelp();
            break;
        case "done":
            showDoneHelp();
            break;
        case "delete":
            showDeleteHelp();
            break;
        case "find":
            showFindHelp();
            break;
        default:
            showHelpError(command);
        }
    }

    private void showListHelp() {
        setResponse("Type 'list' to retrieve the list of tasks on hand currently.");
    }

    private void showTodoHelp() {
        setResponse("Type 'todo <todo name>' to add a new Todo.",
                "eg. 'todo Buy Groceries for my Mother'");
    }

    private void showEventHelp() {
        setResponse("Type 'event <event name> /at <date (DD/MM/YYYY HHmm)>' to add a new Event.",
                "eg. 'event Facebook Hackathon /at 10/10/2019 0900'");
    }

    private void showDeadlineHelp() {
        setResponse("Type 'deadline <event name> /by <date (DD/MM/YYYY HHmm)>' to add a new Deadline.",
                "eg. 'deadline Assignment /by 21/09/2019 2359'");
    }

    private void showDoneHelp() {
        setResponse("Type 'done <task S/N>' to mark a task as done, as shown in list.",
                "eg. 'done 2'");
    }

    private void showDeleteHelp() {
        setResponse("Type 'delete <task S/N>' to delete a task, as shown in list.",
                "eg. 'delete 2'");
    }

    private void showFindHelp() {
        setResponse("Type 'find <text>' to find tasks containing the text.",
                "eg. 'find buy'");
    }

    private void showHelpError(String command) {
        setResponse("I am sorry, '" + command + "' cannot be understood as a command");
    }

    /**
     * Helper function to handle formatting of printing.
     * @param txt Text to be printed within the formatted borders.
     */
    private void setResponse(String... txt) {
        this.response = "";
        for (String s: txt) {
            this.response += s;
            this.response += "\n";
        }
    }

    public String getResponse() {
        return this.response;
    }
}
