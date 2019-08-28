package execution;

import java.util.Scanner;

import models.Task;

public class Ui {

    private static String TAB = "\t____________________________________________________________";
    private static final Scanner sc = new Scanner(System.in);

    public void showLoadingError() {
        System.out.println("Data file cannot be loaded");
    }

    public void showWelcomeMessage() {
        print("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showDone(Task task) {
        print(" Nice! I've marked this task as done:\n\t   " + task);
    }

    public void showAddTask(Task task, int size) {
        print(" Got it. I've added this task:\n\t   " + task + "\n\tNow you have " +
                size + " tasks in the list.");
    }

    public void showDeleted(Task task, int size) {
        print(" Noted. I've removed this task: \n\t   " + task + "\n\t Now you have " +
                size + " tasks in the list.");
    }

    public void exit() {
        print("Bye. Hope to see you again soon!");
    }

    public void listTasks(TaskList taskList) {
        System.out.println(TAB);
        System.out.println("\t Here are the tasks in your list");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println("\t " + (i + 1) + ". " + taskList.get(i));
        }
        System.out.println(TAB + "\n");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    private void print(String txt) {
        System.out.println(TAB);
        System.out.println("\t" + txt);
        System.out.println(TAB + "\n");
    }
}
