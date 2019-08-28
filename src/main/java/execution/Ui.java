package execution;

import models.Task;

import java.util.Scanner;

/**
 * User interface to handle printing of messages.
 */
public class Ui {

    /** Standard line for formatting */
    private static String TAB = "\t____________________________________________________________";

    /** Scans for input */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Shows loading error.
     */
    public void showLoadingError() {
        System.out.println("Data file cannot be loaded");
    }

    /**
     * Shows welcome message.
     */
    public void showWelcomeMessage() {
        print("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    /**
     * Shows error.
     * @param msg Error message.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Shows a done task.
     * @param task Task that has been marked as done.
     */
    public void showDone(Task task) {
        print(" Nice! I've marked this task as done:\n\t   " + task);
    }

    /**
     * Shows a task that has been added.
     * @param task Task that bas been added.
     * @param size Number of tasks in the collections.
     */
    public void showAddTask(Task task, int size) {
        print(" Got it. I've added this task:\n\t   " + task + "\n\tNow you have " +
                size + " tasks in the list.");
    }

    /**
     * Shows a task that has been deleted.
     * @param task Task has been deleted.
     * @param size Number of tasks left in the collections.
     */
    public void showDeleted(Task task, int size) {
        print(" Noted. I've removed this task: \n\t   " + task + "\n\t Now you have " +
                size + " tasks in the list.");
    }

    /**
     * Shows exit message.
     */
    public void exit() {
        print("Bye. Hope to see you again soon!");
    }

    /**
     * Lists down the tasks in the collections.
     * @param taskList Collections of tasks.
     */
    public void listTasks(TaskList taskList) {
        System.out.println(TAB);
        System.out.println("\t Here are the tasks in your list");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println("\t " + (i + 1) + ". " + taskList.get(i));
        }
        System.out.println(TAB + "\n");
    }

    /**
     * Scans for input.
     * @return Scanned input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Helper function to handle formatting of printing.
     * @param txt Text to be printed within the formatted borders.
     */
    private void print(String txt) {
        System.out.println(TAB);
        System.out.println("\t" + txt);
        System.out.println(TAB + "\n");
    }
}
