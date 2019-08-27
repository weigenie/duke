import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static String TAB = "\t____________________________________________________________";

    static Scanner sc = new Scanner(System.in);
    static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        print("Hello! I'm Duke\n\tWhat can I do for you?");
        boolean quit = false;
        while (!quit) {
            String input = sc.next();
            String rest;
            String[] split;
            try {
            switch (input) {
                case "bye":
                    print("Bye. Hope to see you again soon!");
                    quit = true;
                    break;
                case "list":
                    System.out.println(TAB);
                    System.out.println("\t Here are the tasks in your list");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("\t " + (i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println(TAB + "\n");
                    break;
                case "done":
                    rest = sc.nextLine();
                    if (rest.isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The description of a done cannot be empty.");
                    }
                    int doneNum = Integer.parseInt(rest) - 1;
                    tasks.get(doneNum).markAsDone();
                    print(" Nice! I've marked this task as done:\n\t   " + tasks.get(doneNum));
                    break;
                case "todo":
                    rest = sc.nextLine();
                    if (rest.isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(rest));
                    print(" Got it. I've added this task:\n\t   " + tasks.get(tasks.size() - 1) + "\n\tNow you have " +
                            tasks.size() + " tasks in the list.");
                    break;
                case "deadline":
                    rest = sc.nextLine();
                    if (rest.isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else if (!rest.contains("/by")) {
                        throw new DukeException(" ☹ OOPS!!! Deadline input should include '/by'.");
                    }
                    split = rest.split("/by ");
                    tasks.add(new Deadline(split[0], split[1]));
                    print(" Got it. I've added this task:\n\t   " + tasks.get(tasks.size() - 1) + "\n\tNow you have " +
                            tasks.size() + " tasks in the list.");
                    break;
                case "event":
                    rest = sc.nextLine();
                    if (rest.isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The description of an event cannot be empty.");
                    } else if (!rest.contains("/at")) {
                        throw new DukeException(" ☹ OOPS!!! Deadline input should include '/at'.");
                    }
                    split = rest.split("/at ");
                    tasks.add(new Event(split[0], split[1]));
                    print(" Got it. I've added this task:\n\t   " + tasks.get(tasks.size() - 1) + "\n\tNow you have " +
                            tasks.size() + " tasks in the list.");
                    break;
                case "delete":
                    rest = sc.nextLine();
                    if (rest.isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The description of an delete cannot be empty.");
                    }
                    int deleteNum = Integer.parseInt(sc.next()) - 1;
                    Task toDelete = tasks.get(deleteNum);
                    print(" Noted. I've removed this task: \n\t   " + toDelete + "\n\t Now you have " +
                            (tasks.size() - 1) + " tasks in the list.");
                    tasks.remove(deleteNum);
                    break;
                default:
                    sc.nextLine();
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                print (e.getMessage());
            }
        }
    }

    static void print(String txt) {
        System.out.println(TAB);
        System.out.println("\t" + txt);
        System.out.println(TAB + "\n");
    }
}
